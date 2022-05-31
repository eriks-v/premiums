package lv.pi.premiums.application.service.pricing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PolicyPricingServiceTest {

  @Mock
  private PremiumAttributeCollector premiumAttributeService;

  @Mock
  Policy policy;

  @Test
  void throwsExceptionWhenNoPricingRuleFound() {

    //given
    List<PremiumPricingRule> premiumPricingRules = List.of();
    PolicyPricing policyPricingService = new PolicyPricingService(premiumAttributeService, premiumPricingRules);
    Set<PremiumAttribute> premiumAttributes = Set.of(
        new PremiumAttribute(RiskType.FIRE, BigDecimal.valueOf(1))
    );
    String expectedMessage = "No matching rule found for pricing attribute: PremiumAttribute(riskType=FIRE, sumInsured=1)";
    //when
    when(premiumAttributeService.collectPremiumAttributes(policy)).thenReturn(premiumAttributes);
    //then
    PremiumCalculationException premiumCalculationException = assertThrows(
        PremiumCalculationException.class,
        () -> policyPricingService.calculatePrice(policy)
    );

    assertEquals(expectedMessage, premiumCalculationException.getMessage());

  }

  @Test
  void throwsExceptionWhenPricingRuleNotApplicableFound() {

    //given
    List<PremiumPricingRule> premiumPricingRules = List.of(
        new PremiumPricingRule() {
          @Override
          public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
            return false;
          }

          @Override
          public BigDecimal getPremiumCoefficient() {
            return BigDecimal.ONE;
          }
        }
    );

    PolicyPricing policyPricingService = new PolicyPricingService(premiumAttributeService, premiumPricingRules);
    Set<PremiumAttribute> premiumAttributes = Set.of(
        new PremiumAttribute(RiskType.FIRE, BigDecimal.valueOf(1))
    );
    String expectedMessage = "No matching rule found for pricing attribute: PremiumAttribute(riskType=FIRE, sumInsured=1)";
    //when
    when(premiumAttributeService.collectPremiumAttributes(policy)).thenReturn(premiumAttributes);
    //then
    PremiumCalculationException premiumCalculationException = assertThrows(
        PremiumCalculationException.class,
        () -> policyPricingService.calculatePrice(policy)
    );

    assertEquals(expectedMessage, premiumCalculationException.getMessage());

  }

  @Test
  void returnsSumWhenPricingRuleFound() {

    //given
    List<PremiumPricingRule> premiumPricingRules = List.of(
        new PremiumPricingRule() {
          @Override
          public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
            return true;
          }

          @Override
          public BigDecimal getPremiumCoefficient() {
            return BigDecimal.valueOf(2);
          }
        }
    );

    PolicyPricing policyPricingService = new PolicyPricingService(premiumAttributeService, premiumPricingRules);
    Set<PremiumAttribute> premiumAttributes = Set.of(
        new PremiumAttribute(RiskType.FIRE, BigDecimal.valueOf(5))
    );
    BigDecimal expectedResult = BigDecimal.TEN;
    //when
    when(premiumAttributeService.collectPremiumAttributes(policy)).thenReturn(premiumAttributes);
    BigDecimal result = policyPricingService.calculatePrice(policy);
    //then

    assertEquals(expectedResult, result);

  }

  @Test
  void returnsSumForFirstPricingRuleFound() {

    //given
    List<PremiumPricingRule> premiumPricingRules = List.of(
        new PremiumPricingRule() {
          @Override
          public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
            return false;
          }

          @Override
          public BigDecimal getPremiumCoefficient() {
            return BigDecimal.valueOf(3);
          }
        },
        new PremiumPricingRule() {
          @Override
          public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
            return true;
          }

          @Override
          public BigDecimal getPremiumCoefficient() {
            return BigDecimal.valueOf(2);
          }
        }
    );

    PolicyPricing policyPricingService = new PolicyPricingService(premiumAttributeService, premiumPricingRules);
    Set<PremiumAttribute> premiumAttributes = Set.of(
        new PremiumAttribute(RiskType.FIRE, BigDecimal.valueOf(5))
    );
    BigDecimal expectedResult = BigDecimal.TEN;
    //when
    when(premiumAttributeService.collectPremiumAttributes(policy)).thenReturn(premiumAttributes);
    BigDecimal result = policyPricingService.calculatePrice(policy);
    //then

    assertEquals(expectedResult, result);

  }

}