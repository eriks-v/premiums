package lv.pi.premiums.application.service.pricing.rule.theft;

import lv.pi.premiums.application.domain.PricingRange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.domain.PremiumAttribute;

import static org.junit.jupiter.api.Assertions.*;

class TheftDefaultPricingRuleTest {

    private final TheftDefaultPricingRule theftDefaultPricingRule = new TheftDefaultPricingRule(new BigDecimal("0.11"),
                                                                                                new PricingRange(null, new BigDecimal("14.99")));

    @Test
    void isNotApplicableForNotTheftRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, BigDecimal.TEN);
        //when
        boolean result = theftDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertFalse(result);
    }

    @Test
    void throwsExceptionWhenTriesToApplyNonApplicableRule() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, new BigDecimal("100"));
        //when
        //then
        PremiumCalculationException premiumCalculationException =
                assertThrows(PremiumCalculationException.class,
                             () -> theftDefaultPricingRule.applyRule(premiumAttribute));

        assertEquals("Rule cannot be applied.", premiumCalculationException.getMessage());
    }

    @Test
    void isApplicableForTheftRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, BigDecimal.ONE);
        //when
        boolean result = theftDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertTrue(result);

    }

    @Test
    void appliesExpectedCoefficient() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, BigDecimal.ONE);
        final BigDecimal DEFAULT_THEFT_COEFFICIENT = new BigDecimal("0.11");
        //when
        boolean result = theftDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertEquals(DEFAULT_THEFT_COEFFICIENT, theftDefaultPricingRule.applyRule(premiumAttribute));

    }

}