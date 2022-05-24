package lv.pi.premiums.application.service.validation.policysubobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;

import static org.junit.jupiter.api.Assertions.*;

class PolicySubObjectNullValidationTest {

    private final PolicySubObjectNullValidation policySubObjectNullValidation = new PolicySubObjectNullValidation();

    @Test
    void throwsExceptionWhenPolicySubObjectIsNull() {
        //given
        PolicySubObject policySubObject = null;
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policySubObjectNullValidation.validate(policySubObject)
        );

        assertEquals("Policy Sub-object can not be null.", policyValidationException.getMessage());
    }

    @Test
    void doesNotThrowExceptionWhenPolicySubObjectIsNull() {
        //given
        PolicySubObject policySubObject = new PolicySubObject("TV", BigDecimal.ONE, RiskType.FIRE);
        //when
        //then
        assertDoesNotThrow(() -> policySubObjectNullValidation.validate(policySubObject));
    }

}