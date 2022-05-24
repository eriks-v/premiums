package lv.pi.premiums.application.service.pricing.rule.theft;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.rule.TheftPricingDecision;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
@Order(1)
@Profile("DEFAULT")
class TheftDefaultPricingRule implements PremiumPricingRule, TheftPricingDecision {

    private static final BigDecimal PREMIUM_COEFFICIENT = new BigDecimal("0.11");

    @Override
    public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {

        log.debug("TheftDefaultPricingRule: " + premiumAttribute);

        return isTheft(premiumAttribute);
    }

    @Override
    public BigDecimal getPremiumCoefficient() {
        return PREMIUM_COEFFICIENT;
    }
}
