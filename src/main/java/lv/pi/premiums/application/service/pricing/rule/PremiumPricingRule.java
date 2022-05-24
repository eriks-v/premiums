package lv.pi.premiums.application.service.pricing.rule;

import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;

import java.math.BigDecimal;

public interface PremiumPricingRule {

    boolean isRuleApplicable(PremiumAttribute premiumAttribute);

    BigDecimal getPremiumCoefficient();

    default BigDecimal applyRule(PremiumAttribute premiumAttribute) {
        if (isRuleApplicable(premiumAttribute)) {
            return premiumAttribute.getSumInsured().multiply(getPremiumCoefficient());
        } else {
            throw new PremiumCalculationException("Rule cannot be applied.");
        }
    }

}