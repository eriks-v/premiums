package lv.pi.premiums.application.service.validation.policyobject;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.stereotype.Component;

@Component
class PolicyObjectNameValidation implements ValidationRule<PolicyObject> {

    private static final int MINIMUM_SUB_OBJECT_NAME_LENGTH = 1;

    @Override
    public void validate(PolicyObject validationEntity) {
        String policySubObjectName = validationEntity.getName();

        if (policySubObjectName.isBlank()) {
            throw new PolicyValidationException("Policy object name should be entered.");
        }
        if (policySubObjectName.length() <= MINIMUM_SUB_OBJECT_NAME_LENGTH) {
            throw new PolicyValidationException(
                    "Policy object name should be at least one character long.");
        }
    }
}
