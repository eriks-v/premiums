package lv.pi.premiums.application.service.validation.policyobject;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PolicyObjectNullValidationTest {

    private final PolicyObjectNullValidation policyObjectNullValidation
            = new PolicyObjectNullValidation();

    @Test
    void throwsErrorWhenSubObjectsNull() {
        //given
        PolicyObject policyObject = null;
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policyObjectNullValidation.validate(policyObject)
        );

        assertEquals("Policy object can not be null.", policyValidationException.getMessage());

    }

    @Test
    void shouldNotThrowErrorWhenSubObjectsNotNull() {
        //given
        PolicyObject policyObject = new PolicyObject("TV", Collections.emptySet());
        //when
        //then
        assertDoesNotThrow(() -> policyObjectNullValidation.validate(policyObject));
    }

}