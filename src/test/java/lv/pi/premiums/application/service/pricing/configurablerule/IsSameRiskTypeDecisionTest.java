package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IsSameRiskTypeDecisionTest {

    @Test
    void returnsTrueWhenRiskTypesMatch() {
        //given
        RiskType referenceValue = RiskType.FIRE;
        RiskType premiumAttributeValue = RiskType.FIRE;
        PremiumAttributePredicate rule = new IsSameRiskTypeDecision(referenceValue);
        PremiumAttribute premiumAttribute = new PremiumAttribute(premiumAttributeValue, BigDecimal.ONE);

        //when
        //then
        assertTrue(rule.test(premiumAttribute));
    }

    @Test
    void returnsFalseWhenRiskTypesDoNotMatch() {
        //given
        RiskType referenceValue = RiskType.FIRE;
        RiskType premiumAttributeValue = RiskType.THEFT;
        PremiumAttributePredicate rule = new IsSameRiskTypeDecision(referenceValue);
        PremiumAttribute premiumAttribute = new PremiumAttribute(premiumAttributeValue, BigDecimal.ONE);

        //when
        //then
        assertFalse(rule.test(premiumAttribute));
    }

}