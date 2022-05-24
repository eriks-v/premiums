package lv.pi.premiums.application.service.validation.policyobject;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PolicyObjectNameValidationTest {

    private final PolicyObjectNameValidation policyObjectNameValidation = new PolicyObjectNameValidation();

    @ParameterizedTest
    @ValueSource(strings = {""," ","    ",""})
    void throwsExceptionWhenNameIsBlank(String name) {
        //given
        PolicyObject policyObject = new PolicyObject(name, Collections.emptySet());
        String expectedMessage = "Policy object name should be entered.";
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policyObjectNameValidation.validate(policyObject)
        );

        assertEquals(expectedMessage, policyValidationException.getMessage());
    }

    @Test
    void throwsErrorWhenNameIsTooShort() {
        //given
        PolicyObject policyObject = new PolicyObject("A", Collections.emptySet());
        String expectedMessage = "Policy object name should be at least one character long.";
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policyObjectNameValidation.validate(policyObject)
        );

        assertEquals(expectedMessage
                , policyValidationException.getMessage());
    }

    @Test
    void doNotThrowErrorWhenNameIsCorrect() {
        //given
        PolicyObject policyObject = new PolicyObject("TV", Collections.emptySet());
        //when
        //then
        assertDoesNotThrow(() -> policyObjectNameValidation.validate(policyObject));
    }

}