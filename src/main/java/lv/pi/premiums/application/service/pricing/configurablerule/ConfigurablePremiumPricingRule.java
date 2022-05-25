package lv.pi.premiums.application.service.pricing.configurablerule;

import lombok.NonNull;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.configurablerule.exception.PricingRuleConfigurationException;

import java.math.BigDecimal;
import java.util.List;

public class ConfigurablePremiumPricingRule implements PremiumPricingRule {
    private final List<PremiumAttributePredicate> predicates;
    private final BigDecimal coefficient;

    public ConfigurablePremiumPricingRule(@NonNull List<PremiumAttributePredicate> predicates, @NonNull BigDecimal coefficient) {
        if (predicates.isEmpty()) {
            throw new PricingRuleConfigurationException("Pricing rule predicates can not be empty");
        }
        this.predicates = predicates;
        this.coefficient = coefficient;
    }

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
        return predicates.stream().allMatch(predicate -> predicate.test(premiumAttribute));
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return coefficient;
    }
}
