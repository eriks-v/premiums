package lv.pi.premiums.application.service.validation.policy;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.stereotype.Component;

@Component
class PolicyObjectSetValidation implements ValidationRule<Policy> {

    @Override
    public void validate(Policy policy) {
        if (policy.getPolicyObjects().isEmpty()) {
            throw new PolicyValidationException("Policy objects cannot be empty.");
        }
    }
}
