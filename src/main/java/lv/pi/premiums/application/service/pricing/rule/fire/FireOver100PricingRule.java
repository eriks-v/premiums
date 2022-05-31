package lv.pi.premiums.application.service.pricing.rule.fire;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.PricingRange;
import lv.pi.premiums.application.service.pricing.rule.FirePricingDecision;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.rule.SumInsuredIsInRangePricingDecision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
class FireOver100PricingRule implements PremiumPricingRule, FirePricingDecision, SumInsuredIsInRangePricingDecision {

    private final BigDecimal PREMIUM_COEFFICIENT;
    private final PricingRange PRICING_RANGE;

    public FireOver100PricingRule(@Value("${lv.pi.financial.pricing.rule.fire-over-100.coefficient}") BigDecimal premiumCoefficient,
                                  @Value("#{new lv.pi.premiums.application.domain.PricingRange(${lv.pi.financial.pricing.rule.fire-over-100.start}, ${lv.pi.financial.pricing.rule.fire-over-100.end})}") PricingRange pricingRange) {
        this.PREMIUM_COEFFICIENT = premiumCoefficient;
        this.PRICING_RANGE = pricingRange;
        log.debug("Rule created with range: " + this.PRICING_RANGE + " and coefficient: " + this.PREMIUM_COEFFICIENT);
    }

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {

        log.debug("FireOver100PricingRule: " + premiumAttribute);

        return isFire(premiumAttribute) && sumInsuredIsInRange(premiumAttribute, PRICING_RANGE);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }
}
