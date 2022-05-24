package lv.pi.premiums.application.service.validation.policyobject;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.service.validation.ValidationRule;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class PolicyObjectSubObjectValidation implements ValidationRule<PolicyObject> {

    @Override
    public void validate(PolicyObject policyObject) {
        if (policyObject.getPolicySubObjects().isEmpty()) {
            log.warn("Policy Sub-objects empty for policy object: " + policyObject.getName());
        }
    }
}
