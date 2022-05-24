package lv.pi.premiums.application.service.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicyObject;
import lv.pi.premiums.application.domain.PolicyStatus;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.validation.exception.PolicyValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PolicyValidationIntegrationTests {

    @Autowired
    PolicyValidationService policyValidationService;

    @Test
    void throwsExceptionIfPolicyNumberNotCorrectLength() {
        //given
        Set<PolicyObject> policyObjects
                = Set.of(new PolicyObject("po-1", Set.of(new PolicySubObject("pso-1", new BigDecimal("500"), RiskType.FIRE)
                , new PolicySubObject("pso-2", new BigDecimal("102.51"), RiskType.THEFT))));
        Policy policy = new Policy("LV20-02-100000-55", PolicyStatus.REGISTERED, policyObjects);
        //when
        //then
        assertThrows(PolicyValidationException.class, () -> policyValidationService.validate(policy));
    }

    @Test
    void throwsExceptionIfOneOfSubObjectsNull() {
        //given
        Set<PolicySubObject> policySubObjects = new HashSet<>();
        policySubObjects.add(new PolicySubObject("pso-1", new BigDecimal("500"), RiskType.FIRE));
        policySubObjects.add(new PolicySubObject("pso-2", new BigDecimal("102.51"), RiskType.THEFT));
        policySubObjects.add(null);

        Set<PolicyObject> policyObjects
                = Set.of(new PolicyObject("po-1", policySubObjects));
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, policyObjects);
        //when
        //then
        assertThrows(PolicyValidationException.class, () -> policyValidationService.validate(policy));
    }

    @Test
    void throwsExceptionWhenSubObjectNameBlank() {
        //given
        Set<PolicyObject> policyObjects
                = Set.of(new PolicyObject("po-1", Set.of(new PolicySubObject("pso-1", new BigDecimal("500"), RiskType.FIRE)
                , new PolicySubObject("", new BigDecimal("102.51"), RiskType.THEFT))));
        Policy policy = new Policy("LV20-02-100000-55", PolicyStatus.REGISTERED, policyObjects);
        //when
        //then
        assertThrows(PolicyValidationException.class, () -> policyValidationService.validate(policy));
    }

    @Test
    void worksWithCorrectPolicy() {
        //given
        Set<PolicyObject> policyObjects
                = Set.of(new PolicyObject("po-1", Set.of(new PolicySubObject("pso-1", new BigDecimal("500"), RiskType.FIRE)
                                                             , new PolicySubObject("pso-2", new BigDecimal("102.51"), RiskType.THEFT))));
        Policy policy = new Policy("LV20-02-100000-5", PolicyStatus.REGISTERED, policyObjects);
        //when
        //then
        assertDoesNotThrow(() -> policyValidationService.validate(policy));
    }

}
