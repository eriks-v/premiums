package lv.pi.premiums.application.service.pricing.rule.theft;

import lv.pi.premiums.application.domain.PricingRange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.domain.PremiumAttribute;

import static org.junit.jupiter.api.Assertions.*;

class TheftOverOrEqual15PricingRuleTest {

    private final TheftOverOrEqual15PricingRule theftOverOrEqual15PricingRule
            = new TheftOverOrEqual15PricingRule(new BigDecimal("0.05"),
                                                new PricingRange(new BigDecimal("15"), null));

    @Test
    void isNotApplicableForNotTheftRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, BigDecimal.TEN);
        //when
        boolean result = theftOverOrEqual15PricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertFalse(result);
    }

    @Test
    void isNotApplicableForNotFireRiskTypeWithSumInsuredBelowLowerBoundary() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, new BigDecimal("14.99"));
        //when
        boolean result = theftOverOrEqual15PricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertFalse(result);
    }

    @Test
    void throwsExceptionWhenTriesToApplyNonApplicableRule() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, new BigDecimal("14.99"));
        //when
        //then
        PremiumCalculationException premiumCalculationException =
                assertThrows(PremiumCalculationException.class,
                             () -> theftOverOrEqual15PricingRule.applyRule(premiumAttribute));

        assertEquals("Rule cannot be applied.", premiumCalculationException.getMessage());
    }

    @Test
    void isApplicableForTheftRiskTypeWithSumInsuredOnLowerBoundary() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, new BigDecimal("15"));
        //when
        boolean result = theftOverOrEqual15PricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertTrue(result);
    }

    @Test
    void appliesExpectedCoefficient() {
        //given
        final BigDecimal EXPECTED_RESULT = new BigDecimal("5");
        final int EQUALS = 0;
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, new BigDecimal("100"));
        //when
        BigDecimal result = theftOverOrEqual15PricingRule.applyRule(premiumAttribute);
        //then
        assertEquals(EQUALS,result.compareTo(EXPECTED_RESULT));
    }

}