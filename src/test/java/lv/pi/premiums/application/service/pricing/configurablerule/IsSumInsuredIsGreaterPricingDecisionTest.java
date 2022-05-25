package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IsSumInsuredIsGreaterPricingDecisionTest {

    @Test
    void returnsFalseIfBelowThreshold() {

        //given
        BigDecimal thresholdValue = new BigDecimal("14.99");
        BigDecimal testableValue = new BigDecimal("14.00");
        IsSumInsuredIsGreaterPricingDecision rule = new IsSumInsuredIsGreaterPricingDecision(thresholdValue);
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, testableValue);

        //when
        //then
        assertFalse(rule.test(premiumAttribute));

    }

    @Test
    void returnsTrueIfAboveThreshold() {

        //given
        BigDecimal thresholdValue = new BigDecimal("14.99");
        BigDecimal testableValue = new BigDecimal("14.9901");
        PremiumAttributePredicate rule = new IsSumInsuredIsGreaterPricingDecision(thresholdValue);
        PremiumAttribute premiumAttribute = new PremiumAttribute(RiskType.FIRE, testableValue);

        //when
        //then
        assertTrue(rule.test(premiumAttribute));

    }

}