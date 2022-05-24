package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

public class ConfigurablePremiumPricingRule implements PremiumPricingRule {
    private final List<Predicate<PremiumAttribute>> predicates;
    private final BigDecimal coefficient;

    public ConfigurablePremiumPricingRule(List<Predicate<PremiumAttribute>> predicates, BigDecimal coefficient) {
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
