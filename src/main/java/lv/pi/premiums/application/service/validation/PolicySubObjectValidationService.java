package lv.pi.premiums.application.service.validation;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PolicySubObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class PolicySubObjectValidationService extends Validate<PolicySubObject> {

    public PolicySubObjectValidationService(List<ValidationRule<PolicySubObject>> validationRules) {
        super(validationRules);
        log.debug("(PolicySubObject) Validation rules loaded: " + validationRules.size());
    }

    @Override
    public void validate(PolicySubObject policySubObject) {
        super.validateEntity(policySubObject);
    }
}
