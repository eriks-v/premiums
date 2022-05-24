package lv.pi.premiums.application.service.pricing.rule.fire;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.rule.FirePricingDecision;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.rule.SumInsuredIsGreaterPricingDecision;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(0)
@Slf4j
@Profile("DEFAULT")
class FireOver100PricingRule implements PremiumPricingRule, FirePricingDecision, SumInsuredIsGreaterPricingDecision {

    private static final BigDecimal PREMIUM_COEFFICIENT = new BigDecimal("0.024");
    private static final BigDecimal UPPER_SUM_INSURED_THRESHOLD = BigDecimal.valueOf(100);

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {

        log.debug("FireOver100PricingRule: " + premiumAttribute);

        return isFire(premiumAttribute) && sumInsuredIsGreater(premiumAttribute, UPPER_SUM_INSURED_THRESHOLD);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }
}
