package lv.pi.premiums.application.service.validation.policyobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;

import static org.junit.jupiter.api.Assertions.*;

class PolicyObjectSubObjectValidationTest {

    private final PolicyObjectSubObjectValidation policyObjectSubObjectValidation
            = new PolicyObjectSubObjectValidation();

    @Test
    void doesNotThrowErrorWhenSubObjectsEmpty() {
        //given
        PolicyObject policyObject = new PolicyObject("TV", Collections.emptySet());
        //when
        //then
        assertDoesNotThrow(() -> policyObjectSubObjectValidation.validate(policyObject));
    }

    @Test
    void doesNotThrowErrorWhenSubObjectsIsNotEmpty() {
        //given
        PolicySubObject policySubObject = new PolicySubObject("Name", BigDecimal.ONE, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("TV", Set.of(policySubObject));
        //when
        //then
        assertDoesNotThrow(() -> policyObjectSubObjectValidation.validate(policyObject));
    }
}