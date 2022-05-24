package lv.pi.premiums.application.service.pricing.rule.fire;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.domain.PremiumAttribute;

import static org.junit.jupiter.api.Assertions.*;

class FireDefaultPricingRuleTest {

    private final FireDefaultPricingRule fireDefaultPricingRule = new FireDefaultPricingRule();

    @Test
    void isNotApplicableForNotFireRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, BigDecimal.TEN);
        //when
        boolean result = fireDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertFalse(result);
    }

    @Test
    void throwsExceptionWhenTriesToApplyNonApplicableRule() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.THEFT, new BigDecimal("100"));
        //when
        //then
        PremiumCalculationException premiumCalculationException =
                assertThrows(PremiumCalculationException.class,
                             () -> fireDefaultPricingRule.applyRule(premiumAttribute));

        assertEquals("Rule cannot be applied.", premiumCalculationException.getMessage());
    }

    @Test
    void isApplicableForFireRiskType() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, BigDecimal.ONE);
        //when
        boolean result = fireDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertTrue(result);

    }

    @Test
    void appliesExpectedCoefficient() {
        //given
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, BigDecimal.ONE);
        final BigDecimal DEFAULT_FIRE_COEFFICIENT = new BigDecimal("0.014");
        //when
        boolean result = fireDefaultPricingRule.isRuleApplicable(premiumAttribute);
        //then
        assertTrue(result);
        assertEquals(DEFAULT_FIRE_COEFFICIENT, fireDefaultPricingRule.applyRule(premiumAttribute));

    }

}