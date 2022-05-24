package lv.pi.premiums.application.service.validation.policysubobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PolicySubObjectNameValidationTest {

    private final PolicySubObjectNameValidation policySubObjectNameValidation = new PolicySubObjectNameValidation();
    private static final int MINIMUM_POLICY_SUB_OBJECT_NAME_LENGTH = 1;

    @ParameterizedTest
    @ValueSource(strings = {""," ","    ",""})
    void throwsExceptionWhenNameIsBlank(String name) {

        //given
        PolicySubObject policySubObject = new PolicySubObject(name, BigDecimal.ONE, RiskType.FIRE);
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policySubObjectNameValidation.validate(policySubObject)
        );

        assertEquals("Policy Sub-object name should be entered.", policyValidationException.getMessage());

    }

    @Test
    void throwsExceptionWhenNameIsTooShort() {

        //given
        PolicySubObject policySubObject = new PolicySubObject("1", BigDecimal.ONE, RiskType.FIRE);
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policySubObjectNameValidation.validate(policySubObject)
        );

        assertEquals("Policy Sub-object name should be at least " +
                MINIMUM_POLICY_SUB_OBJECT_NAME_LENGTH + " character long."
                , policyValidationException.getMessage());
    }

    @Test
    void doesNotThrowExceptionWhenNameIsCorrect() {

        //given
        PolicySubObject policySubObject = new PolicySubObject("TV", BigDecimal.ONE, RiskType.FIRE);
        //when
        //then
        assertDoesNotThrow(() -> policySubObjectNameValidation.validate(policySubObject));

    }

}