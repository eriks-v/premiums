package lv.pi.premiums.application;

import java.math.BigDecimal;
import java.util.Set;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicyStatus;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.Premium;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PremiumCalculatorIntegrationTest {

  @Autowired
  CalculatePremium premiumCalculator;

  @Test
  void shouldReturnAmountWhenFireAndTheftAmountsOverDefaultThreshold() {
    //given
    BigDecimal expectedAmount = new BigDecimal("17.13");
    Policy policy =
        new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, Set.of(
            new PolicyObject("po-1",
                Set.of(new PolicySubObject("pso-1", new BigDecimal("500"), RiskType.FIRE),
                    new PolicySubObject("pso-2", new BigDecimal("102.51"), RiskType.THEFT)
                )
            )));
    //when
    Premium result = premiumCalculator.calculate(policy);
    //then
    assertEquals(expectedAmount, result.getAmount());
  }

  @Test
  void shouldReturnAmountWhenFireAndTheftAmountsBelowDefaultThreshold() {
    //given
    BigDecimal expectedAmount = new BigDecimal("2.28");
    Policy policy =
        new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, Set.of(
            new PolicyObject("po-1",
                Set.of(new PolicySubObject("pso-1", new BigDecimal("100"), RiskType.FIRE),
                    new PolicySubObject("pso-2", new BigDecimal("8"), RiskType.THEFT)
                )
            )));
    //when
    Premium result = premiumCalculator.calculate(policy);
    //then
    assertEquals(expectedAmount, result.getAmount());
  }

  @Test
  void shouldThrowExceptionWhenIncomingPolicyNameInvalid() {
    //given
    String expectedMessage = "Policy number should be 16 characters long.";
    BigDecimal expectedAmount = new BigDecimal("2.28");
    Policy policy =
        new Policy("LV", PolicyStatus.REGISTERED, Set.of(
            new PolicyObject("po-1",
                Set.of(new PolicySubObject("pso-1", new BigDecimal("100"), RiskType.FIRE),
                    new PolicySubObject("pso-2", new BigDecimal("8"), RiskType.THEFT)
                )
            )));
    //when
    //then
    RuntimeException exception = assertThrows(
        PolicyValidationException.class,
        () -> premiumCalculator.calculate(policy)
    );

    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIncomingPolicySubObjectAmountInvalid() {
    //given
    String expectedMessage = "Policy Sub-object sum insured should be greater than 0.01.";
    BigDecimal expectedAmount = new BigDecimal("2.28");
    Policy policy =
        new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, Set.of(
            new PolicyObject("po-1",
                Set.of(new PolicySubObject("pso-1", new BigDecimal("100"), RiskType.FIRE),
                    new PolicySubObject("pso-2", new BigDecimal("0"), RiskType.THEFT)
                )
            )));
    //when
    //then
    RuntimeException exception = assertThrows(
        PolicyValidationException.class,
        () -> premiumCalculator.calculate(policy)
    );

    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIncomingPolicySubObjectAmountScaleIncorrect() {
    //given
    String expectedMessage = "Policy Sub-object sum insured  (100.001) precision cant be greater than 2.";
    BigDecimal expectedAmount = new BigDecimal("2.28");
    Policy policy =
        new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, Set.of(
            new PolicyObject("po-1",
                Set.of(new PolicySubObject("pso-1", new BigDecimal("100.001"), RiskType.FIRE),
                    new PolicySubObject("pso-2", new BigDecimal("10"), RiskType.THEFT)
                )
            )));
    //when
    //then
    RuntimeException exception = assertThrows(
        PolicyValidationException.class,
        () -> premiumCalculator.calculate(policy)
    );

    assertEquals(expectedMessage, exception.getMessage());
  }

}