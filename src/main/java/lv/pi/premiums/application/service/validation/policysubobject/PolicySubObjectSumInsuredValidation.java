package lv.pi.premiums.application.service.validation.policysubobject;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order()
class PolicySubObjectSumInsuredValidation implements ValidationRule<PolicySubObject> {

    private static final int TWO_DECIMALS_AFTER_POINT = 2;

    @Override
    public void validate(PolicySubObject policySubObject) {
        BigDecimal sumInsured = policySubObject.getSumInsured();

        if (sumInsured.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PolicyValidationException(
                    "Policy Sub-object sum insured should be greater than 0.01.");
        }
        if (sumInsured.stripTrailingZeros().scale() > TWO_DECIMALS_AFTER_POINT) {
            throw new PolicyValidationException("Policy Sub-object sum insured  (" + sumInsured
                    + ") precision cant be greater than " + TWO_DECIMALS_AFTER_POINT + ".");
        }

    }
}
