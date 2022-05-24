package lv.pi.premiums.application.service.validation.policy;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicyStatus;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;

import static org.junit.jupiter.api.Assertions.*;

class PolicyObjectSetValidationTest {

    private final PolicyObjectSetValidation policyObjectSetValidation = new PolicyObjectSetValidation();

    @Test
    void throwsExceptionWhenSetIsEmpty() {
        //given
        Policy policy = new Policy("1", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policyObjectSetValidation.validate(policy)
        );

        assertEquals("Policy objects cannot be empty.", policyValidationException.getMessage());
    }

    @Test
    void doesNotThrowExceptionWhenSetIsNotEmpty() {
        //given
        PolicyObject policyObject = new PolicyObject("1", Collections.emptySet());
        Policy policy = new Policy("1", PolicyStatus.REGISTERED, Set.of(policyObject));
        //when
        //then
        assertDoesNotThrow(() -> policyObjectSetValidation.validate(policy));
    }

}