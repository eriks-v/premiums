package lv.pi.premiums.application.service.pricing.rule.theft;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.rule.SumInsuredIsGreaterPricingDecision;
import lv.pi.premiums.application.service.pricing.rule.TheftPricingDecision;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(0)
@Slf4j
class TheftOverOrEqual15PricingRule implements PremiumPricingRule, TheftPricingDecision, SumInsuredIsGreaterPricingDecision {

    private static final BigDecimal UPPER_SUM_INSURED_THRESHOLD = new BigDecimal("15").subtract(new BigDecimal("0.01"));
    private static final BigDecimal PREMIUM_COEFFICIENT = new BigDecimal("0.05");

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {

        log.debug("TheftOverOrEqual15PricingRule: " + premiumAttribute);

        return isTheft(premiumAttribute) && sumInsuredIsGreater(premiumAttribute, UPPER_SUM_INSURED_THRESHOLD);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }
}
