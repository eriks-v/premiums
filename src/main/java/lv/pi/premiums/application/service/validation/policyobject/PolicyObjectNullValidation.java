package lv.pi.premiums.application.service.validation.policyobject;

import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class PolicyObjectNullValidation implements ValidationRule<PolicyObject> {

    @Override
    public void validate(PolicyObject policyObject) {
        if (policyObject == null) {
            throw new PolicyValidationException("Policy object can not be null.");
        }

    }
}
