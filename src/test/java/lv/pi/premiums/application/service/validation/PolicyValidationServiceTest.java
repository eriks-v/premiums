package lv.pi.premiums.application.service.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicyStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PolicyValidationServiceTest {

    @Mock
    PolicyObjectValidationService policyObjectValidationService;

    @Mock
    ValidationRule<Policy> validationRule;

    @Test
    void throwsExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<Policy>> validationRules = new ArrayList<>();
        //when
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> new PolicyValidationService(validationRules, policyObjectValidationService)
        );

        assertEquals("Validation rule list cant be empty.", illegalArgumentException.getMessage());

    }

    @Test
    void doesNotThrowExceptionWhenRuleListEmpty() {
        //given
        List<ValidationRule<Policy>> validationRules = List.of(validationRule);
        //when
        //then
        assertDoesNotThrow(() -> new PolicyValidationService(validationRules, policyObjectValidationService));

    }

    @Test
    void usesValidateFromRule() {
        //given
        int NUMBER_OF_TIMES_CALLED = 1;
        List<ValidationRule<Policy>> validationRules = List.of(validationRule);
        PolicyValidationService policyValidationService =
                new PolicyValidationService(validationRules, policyObjectValidationService);
        PolicyObject policyObject = new PolicyObject("Number", Collections.emptySet());
        Policy policy = new Policy("Number", PolicyStatus.REGISTERED, Set.of(policyObject));

        //when
        policyValidationService.validate(policy);
        //then
        verify(validationRule, times(NUMBER_OF_TIMES_CALLED)).validate(policy);
        verify(policyObjectValidationService).validate(policyObject);

    }


}