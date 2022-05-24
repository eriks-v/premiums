package lv.pi.premiums.application.service.validation;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicySubObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
class PolicyObjectValidationService extends Validate<PolicyObject> {

    private final Validate<PolicySubObject> policySubObjectValidationService;

    public PolicyObjectValidationService(List<ValidationRule<PolicyObject>> validationRules,
                                         Validate<PolicySubObject> policySubObjectValidationService) {
        super(validationRules);
        this.policySubObjectValidationService = policySubObjectValidationService;
        log.debug("(PolicyObject) Validation rules loaded: " + validationRules.size());
    }

    @Override
    public void validate(PolicyObject validationEntity) {
        super.validateEntity(validationEntity);
        validationEntity.getPolicySubObjects().forEach(policySubObjectValidationService::validate);

    }

}
