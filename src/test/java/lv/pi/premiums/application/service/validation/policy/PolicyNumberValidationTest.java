package lv.pi.premiums.application.service.validation.policy;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyStatus;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PolicyNumberValidationTest {

    private static final int EXACT_POLICY_NUMBER_LENGTH = 16;
    private static final PolicyNumberValidation policyNumberValidation = new PolicyNumberValidation();

    @ParameterizedTest
    @ValueSource(strings = {""," ","    ",""})
    void throwsExceptionWhenPolicyNumberBlank(String name) {
        //given
        Policy emptyNamePolicy = new Policy(name, PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(PolicyValidationException.class,
                () -> policyNumberValidation.validate(emptyNamePolicy));

        assertEquals("Policy number should be entered.", policyValidationException.getMessage());
    }

    @Test
    void throwsExceptionWhenPolicyNumberTooShort() {
        //given
        Policy policyWithShortName = new Policy("123", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(PolicyValidationException.class,
                () -> policyNumberValidation.validate(policyWithShortName));

        assertEquals("Policy number should be " + EXACT_POLICY_NUMBER_LENGTH + " characters long.", policyValidationException.getMessage());
    }

    @Test
    void throwsExceptionWhenPolicyNumberTooLong() {
        //given
        Policy policyWithLongName = new Policy("012356789ABCDEFGH", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(PolicyValidationException.class,
                () -> policyNumberValidation.validate(policyWithLongName));

        assertEquals("Policy number should be " + EXACT_POLICY_NUMBER_LENGTH + " characters long.", policyValidationException.getMessage());
    }

    @Test
    void throwsExceptionWhenPolicyNumberDoesNotMatchPattern() {
        //given
        Policy policyWithNotMatchingPatternName = new Policy("012356789ABCDEFG", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(PolicyValidationException.class,
                () -> policyNumberValidation.validate(policyWithNotMatchingPatternName));

        assertEquals("Policy number does not match policy number pattern.", policyValidationException.getMessage());
    }

    @Test
    void doesNotThrowExceptionWhenCorrectlyFilled() {
        //given
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, Collections.emptySet());
        //when
        //then
        assertDoesNotThrow(() -> policyNumberValidation.validate(policy));
    }



}