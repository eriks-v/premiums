package lv.pi.premiums.application.service.validation.policy;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyStatus;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;

import static org.junit.jupiter.api.Assertions.*;

class PolicyNullValidationTest {

    private final PolicyNullValidation policyNullValidation = new PolicyNullValidation();

    @Test
    void throwsExceptionWhenNullPassed() {
        //given
        Policy policy = null;
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(PolicyValidationException.class,
                () -> policyNullValidation.validate(policy));

        assertEquals("Policy can not be null.", policyValidationException.getMessage());

    }

    @Test
    void DoesNotThrowExceptionWhenNullPassed() {
        //given
        Policy policy = new Policy("Test_number_1", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        assertDoesNotThrow(() -> policyNullValidation.validate(policy));

    }

}