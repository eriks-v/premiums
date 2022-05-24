package lv.pi.premiums.application.service.validation.policysubobject;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order()
class PolicySubObjectNameValidation implements ValidationRule<PolicySubObject> {

    private static final int MINIMUM_POLICY_SUB_OBJECT_NAME_LENGTH = 1;

    @Override
    public void validate(PolicySubObject policySubObject) {
        String policySubObjectName = policySubObject.getName();

        if (policySubObjectName.isBlank()) {
            throw new PolicyValidationException("Policy Sub-object name should be entered.");
        }
        if (policySubObjectName.length() <= MINIMUM_POLICY_SUB_OBJECT_NAME_LENGTH) {
            throw new PolicyValidationException("Policy Sub-object name should be at least " +
                    MINIMUM_POLICY_SUB_OBJECT_NAME_LENGTH + " character long.");
        }
    }
}
