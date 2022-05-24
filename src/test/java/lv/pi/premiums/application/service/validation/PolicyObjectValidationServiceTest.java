package lv.pi.premiums.application.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PolicyObjectValidationServiceTest {

    @Mock
    PolicySubObjectValidationService policySubObjectValidationService;

    @Mock
    ValidationRule<PolicyObject> validationRule;

    @Test
    void throwsExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<PolicyObject>> validationRules = new ArrayList<>();
        //when
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> new PolicyObjectValidationService(validationRules, policySubObjectValidationService)
        );

        assertEquals("Validation rule list cant be empty.", illegalArgumentException.getMessage());

    }

    @Test
    void doesNotThrowExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<PolicyObject>> validationRules = List.of(validationRule);
        //when
        //then
        assertDoesNotThrow(() -> new PolicyObjectValidationService(validationRules, policySubObjectValidationService));

    }

    @Test
    void usesValidateFromRule() {
        //given
        int NUMBER_OF_TIMES_CALLED = 1;
        List<ValidationRule<PolicyObject>> validationRules = List.of(validationRule);
        PolicyObjectValidationService policyObjectValidationService =
                new PolicyObjectValidationService(validationRules, policySubObjectValidationService);
        PolicySubObject policySubObject = new PolicySubObject("Name", BigDecimal.ZERO, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("Name", Set.of(policySubObject));

        //when
        policyObjectValidationService.validate(policyObject);
        //then
        verify(validationRule, times(NUMBER_OF_TIMES_CALLED)).validate(policyObject);
        verify(policySubObjectValidationService).validate(policySubObject);

    }


}