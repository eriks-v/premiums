package lv.pi.premiums.application.service.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PremiumAttributeServiceTest {

  @Mock
  Policy policy;

  @Mock
  PolicyObject policyObject;

  @Test
  void sumsSumInsured() {

    //given
    PremiumAttributeCollector premiumAttributeService = new PremiumAttributeService();
    Set<PolicySubObject> policySubObjects = Set.of(
        new PolicySubObject("pso1", new BigDecimal("1"), RiskType.FIRE),
        new PolicySubObject("pso2", new BigDecimal("1"), RiskType.THEFT),
        new PolicySubObject("pso3", new BigDecimal("1"), RiskType.FIRE),
        new PolicySubObject("pso4", new BigDecimal("1"), RiskType.THEFT),
        new PolicySubObject("pso5", new BigDecimal("1"), RiskType.FIRE)
    );
    Set<PremiumAttribute> expectedResult = Set.of(
        new PremiumAttribute(RiskType.THEFT, BigDecimal.valueOf(2)),
        new PremiumAttribute(RiskType.FIRE, BigDecimal.valueOf(3))
    );

    //when

    when(policy.getPolicyObjects()).thenReturn(Set.of(policyObject));
    when(policyObject.getPolicySubObjects()).thenReturn(policySubObjects);

    Set<PremiumAttribute> premiumAttributes =
        premiumAttributeService.collectPremiumAttributes(policy);

    //then
    assertEquals(expectedResult, premiumAttributes);

  }

  @Test
  void throwsExceptionWhenAllPolicySubObjectsEmpty() {

    //given
    PremiumAttributeCollector premiumAttributeService = new PremiumAttributeService();
    Set<PolicySubObject> policySubObjects = Collections.emptySet();
    String expectedErrorMessage = "Cant create policy attributes.";

    //when
    when(policy.getPolicyObjects()).thenReturn(Set.of(policyObject));
    when(policyObject.getPolicySubObjects()).thenReturn(policySubObjects);

    //then
    PremiumCalculationException premiumCalculationException = assertThrows(
        PremiumCalculationException.class,
        () -> premiumAttributeService.collectPremiumAttributes(policy)
    );

    assertEquals(expectedErrorMessage, premiumCalculationException.getMessage());

  }
}