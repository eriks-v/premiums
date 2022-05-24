package lv.pi.premiums.application.service.pricing;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
class PolicyPricingService implements PolicyPricing {

    private final PremiumAttributeCollector premiumAttributeService;
    private final List<PremiumPricingRule> premiumPricingRules;

    private PremiumPricingRule resolveRule(PremiumAttribute premiumAttribute) {
        PolicyPricingService.log.debug("Find rule for: " + premiumAttribute);

        PremiumPricingRule applicableRule = premiumPricingRules.stream()
                .filter(rule -> rule.isRuleApplicable(premiumAttribute))
                .findFirst()
                .orElseThrow(() -> {
                    PolicyPricingService.log.error("Could not find rule for premium attributes: " + premiumAttribute);
                    return new PremiumCalculationException("No rule found.");
                });

        PolicyPricingService.log.debug("Selected rule: " + applicableRule.getClass().getSimpleName());

        return applicableRule;

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
}
