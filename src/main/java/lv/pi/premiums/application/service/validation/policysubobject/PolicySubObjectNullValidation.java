package lv.pi.premiums.application.service.validation.policysubobject;

import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class PolicySubObjectNullValidation implements ValidationRule<PolicySubObject> {

    @Override
    public void validate(PolicySubObject policySubObject) {
        if (policySubObject == null) {
            throw new PolicyValidationException("Policy Sub-object can not be null.");
        }

    }
}
