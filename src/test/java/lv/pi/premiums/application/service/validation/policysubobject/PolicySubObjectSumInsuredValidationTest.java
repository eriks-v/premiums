package lv.pi.premiums.application.service.validation.policysubobject;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PolicySubObjectSumInsuredValidationTest {

    private final PolicySubObjectSumInsuredValidation policySubObjectSumInsuredValidation
            = new PolicySubObjectSumInsuredValidation();

    @ParameterizedTest
    @ValueSource(strings = {"0","-1"})
    void throwsExceptionWhenSumInsuredIsZeroOrLess(String amount) {
        //given
        PolicySubObject policySubObject = new PolicySubObject("Name", new BigDecimal(amount), RiskType.FIRE);
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policySubObjectSumInsuredValidation.validate(policySubObject)
        );

        assertEquals("Policy Sub-object sum insured should be greater than 0.01.",
                policyValidationException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1.111","0.1101"})
    void throwsExceptionWhenSumInsuredHasMoreThanTwoDecimals(String stringAmount) {
        //given
        BigDecimal sumInsured = new BigDecimal(stringAmount);
        PolicySubObject policySubObject = new PolicySubObject("Name", sumInsured, RiskType.FIRE);
        //when
        //then
        PolicyValidationException policyValidationException = assertThrows(
                PolicyValidationException.class,
                () -> policySubObjectSumInsuredValidation.validate(policySubObject)
        );

        assertEquals("Policy Sub-object sum insured  (" + sumInsured
                        + ") precision cant be greater than 2.",
                policyValidationException.getMessage());
    }

    @Test
    void doesNotThrowExceptionWhenSumInsuredHasMoreThanTwoDecimalsWithTrailingZeroes() {
        //given
        BigDecimal sumInsured = new BigDecimal("1.110");
        PolicySubObject policySubObject = new PolicySubObject("Name", sumInsured, RiskType.FIRE);
        //when
        //then
        assertDoesNotThrow(() -> policySubObjectSumInsuredValidation.validate(policySubObject));

    }

}