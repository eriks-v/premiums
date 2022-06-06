package lv.pi.premiums.application.service.pricing;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.ruleprovider.PricingRuleProvider;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
class PolicyPricingService implements PolicyPricing {

    private final PremiumAttributeCollector premiumAttributeService;
    private final List<PremiumPricingRule> premiumPricingRules;

    private static final int MAX_APPLICABLE_RULE_COUNT = 1;
    private static final int FIRST_VALUE = 0;

    public PolicyPricingService(PremiumAttributeCollector premiumAttributeService,
        PricingRuleProvider pricingRuleProvider
    ) {
        this.premiumAttributeService = premiumAttributeService;
        this.premiumPricingRules = pricingRuleProvider.getPricingRules();
        log.debug("premiumPricingRules: " + premiumPricingRules);
    }

    @Override
    public BigDecimal calculatePrice(@NonNull Policy policy) {

        Set<PremiumAttribute> premiumAttributes = premiumAttributeService.collectPremiumAttributes(
                policy);

        return premiumAttributes
                .stream()
                .map(premiumAttribute -> resolveRule(premiumAttribute).applyRule(premiumAttribute))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private PremiumPricingRule resolveRule(PremiumAttribute premiumAttribute) {

        PolicyPricingService.log.debug("Find rule for: " + premiumAttribute);

        List<PremiumPricingRule> applicableRules = premiumPricingRules.stream()
                .filter(rule -> rule.isRuleApplicable(premiumAttribute))
                .collect(Collectors.toUnmodifiableList());

        if(applicableRules.isEmpty()) {
            throw new PremiumCalculationException("No matching rule found for pricing attribute: " + premiumAttribute);
        } else if(applicableRules.size() > MAX_APPLICABLE_RULE_COUNT) {
            throw new PremiumCalculationException("Multiple matching rules found for pricing attribute: " + premiumAttribute);
        }

        PolicyPricingService.log.debug("Selected rule: " + applicableRules.getClass().getSimpleName());

        return applicableRules.get(FIRST_VALUE);

    }

}
