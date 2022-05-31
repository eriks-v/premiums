package lv.pi.premiums.application.service.pricing.rule.fire;

import lv.pi.premiums.application.domain.PricingRange;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.domain.PremiumAttribute;

import static org.junit.jupiter.api.Assertions.*;

class FireOver100PricingRuleTest {

    private final FireOver100PricingRule fireOver100PricingRule = new FireOver100PricingRule(new BigDecimal("0.024"),
                                                                                             new PricingRange(new BigDecimal("100.01"), null));

    @Test
    void isNotApplicableForNotFireRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, BigDecimal.TEN);
        //when
        boolean result = fireOver100PricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertFalse(result);
    }

    @Test
    void isNotApplicableForNotFireRiskTypeWithSumInsuredBelowLowerBoundary() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, new BigDecimal("100"));
        //when
        boolean result = fireOver100PricingRule.isRuleApplicable(premiumAttribute);
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
                            () -> fireOver100PricingRule.applyRule(premiumAttribute));

        assertEquals("Rule cannot be applied.", premiumCalculationException.getMessage());
    }

    @Test
    void isApplicableForNotFireRiskTypeWithSumInsuredAboveLowerBoundary() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, new BigDecimal("100.01"));
        //when
        boolean result = fireOver100PricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertTrue(result);
    }

    @Test
    void appliesExpectedCoefficient() {
        //given
        final BigDecimal EXPECTED_RESULT = new BigDecimal("2.40024");
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, new BigDecimal("100.01"));
        //when
        BigDecimal result = fireOver100PricingRule.applyRule(premiumAttribute);
        //then
        assertEquals(EXPECTED_RESULT,result);
    }

}