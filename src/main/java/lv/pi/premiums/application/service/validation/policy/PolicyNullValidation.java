package lv.pi.premiums.application.service.validation.policy;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class PolicyNullValidation implements ValidationRule<Policy> {

    @Override
    public void validate(Policy policy) {
        if (policy == null) {
            throw new PolicyValidationException("Policy can not be null.");
        }
    }
}
