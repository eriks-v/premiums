package lv.pi.premiums.application.service.pricing.rule.fire;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.rule.FirePricingDecision;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@Order(1)
class FireDefaultPricingRule implements PremiumPricingRule, FirePricingDecision {

    private static final BigDecimal PREMIUM_COEFFICIENT = new BigDecimal("0.014");

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
        log.debug("FireDefaultPricingRule: " + premiumAttribute);
        return isFire(premiumAttribute);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }

}
