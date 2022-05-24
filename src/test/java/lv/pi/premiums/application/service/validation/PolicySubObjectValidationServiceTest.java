package lv.pi.premiums.application.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PolicySubObjectValidationServiceTest {

    @Mock
    ValidationRule<PolicySubObject> validationRule;

    @Test
    void throwsExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<PolicySubObject>> validationRules = new ArrayList<>();
        //when
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> new PolicySubObjectValidationService(validationRules)
        );

        assertEquals("Validation rule list cant be empty.", illegalArgumentException.getMessage());

    }

    @Test
    void doesNotThrowExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<PolicySubObject>> validationRules = List.of(validationRule);
        //when
        //then
        assertDoesNotThrow(() -> new PolicySubObjectValidationService(validationRules));

    }

    @Test
    void usesValidateFromRule() {
        //given
        int NUMBER_OF_TIMES_CALLED = 1;
        List<ValidationRule<PolicySubObject>> validationRules = List.of(validationRule);
        PolicySubObjectValidationService policySubObjectValidationService =
                new PolicySubObjectValidationService(validationRules);
        PolicySubObject policySubObject = new PolicySubObject("Name", BigDecimal.ZERO, RiskType.FIRE);
        //when
        policySubObjectValidationService.validate(policySubObject);
        //then
        verify(validationRule, times(NUMBER_OF_TIMES_CALLED)).validate(policySubObject);

    }

}