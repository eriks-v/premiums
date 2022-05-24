package lv.pi.premiums.application.service.validation;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class PolicyValidationService extends Validate<Policy> {

    private final Validate<PolicyObject> policyObjectValidationService;

    public PolicyValidationService(List<ValidationRule<Policy>> validationRules,
                                   Validate<PolicyObject> policyObjectValidationService) {
        super(validationRules);
        this.policyObjectValidationService = policyObjectValidationService;
        log.debug("(Policy) Validation rules loaded: " + validationRules.size());
    }

    @Override
    public void validate(Policy policy) {
        super.validateEntity(policy);
        policy.getPolicyObjects().forEach(policyObjectValidationService::validate);
    }

}
