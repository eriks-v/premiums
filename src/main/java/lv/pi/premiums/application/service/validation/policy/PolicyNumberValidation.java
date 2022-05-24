package lv.pi.premiums.application.service.validation.policy;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.stereotype.Component;

@Component
class PolicyNumberValidation implements ValidationRule<Policy> {

    private static final int EXACT_POLICY_NUMBER_LENGTH = 16;

    @Override
    public void validate(Policy policy) {
        String policyNumber = policy.getPolicyNumber();
        if (policyNumber.isBlank()) {
            throw new PolicyValidationException("Policy number should be entered.");
        }
        if (policyNumber.length() != EXACT_POLICY_NUMBER_LENGTH) {
            throw new PolicyValidationException(
                    "Policy number should be " + EXACT_POLICY_NUMBER_LENGTH + " characters long.");
        }
        if (!policyNumber.matches("[A-Z]{2}\\d{2}-\\d{2}-\\d{6}-\\d")) {
            throw new PolicyValidationException("Policy number does not match policy number pattern.");
        }
    }
}
