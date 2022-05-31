package lv.pi.premiums.application.service.pricing.rule.theft;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.PricingRange;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.rule.SumInsuredIsInRangePricingDecision;
import lv.pi.premiums.application.service.pricing.rule.TheftPricingDecision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
class TheftDefaultPricingRule implements PremiumPricingRule, TheftPricingDecision, SumInsuredIsInRangePricingDecision {

    private final BigDecimal PREMIUM_COEFFICIENT;
    private final PricingRange PRICING_RANGE;

    public TheftDefaultPricingRule(@Value("${lv.pi.financial.pricing.rule.theft-default.coefficient}") BigDecimal premiumCoefficient,
                                   @Value("#{new lv.pi.premiums.application.domain.PricingRange(${lv.pi.financial.pricing.rule.theft-default.start}, ${lv.pi.financial.pricing.rule.theft-default.end})}") PricingRange pricingRange) {
        this.PREMIUM_COEFFICIENT = premiumCoefficient;
        this.PRICING_RANGE = pricingRange;
        log.debug("Rule created with range: " + this.PRICING_RANGE + " and coefficient: " + this.PREMIUM_COEFFICIENT);
    }

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {

        log.debug("TheftDefaultPricingRule: " + premiumAttribute);

        return isTheft(premiumAttribute) && sumInsuredIsInRange(premiumAttribute, PRICING_RANGE);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }
}
