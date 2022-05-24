package lv.pi.premiums.application.service.pricing;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PolicySubObject;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.exception.PremiumCalculationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

@Service
@Slf4j
class PremiumAttributeService implements PremiumAttributeCollector {

    private Set<PremiumAttribute> mapPremiumSumGroupings(
            Map<RiskType, BigDecimal> sumInsuredGroupedByRiskTypes) {
        return sumInsuredGroupedByRiskTypes.entrySet()
                .stream()
                .map(grouping -> new PremiumAttribute(grouping.getKey(), grouping.getValue()))
                .collect(toUnmodifiableSet());
    }

    @Override
    public Set<PremiumAttribute> collectPremiumAttributes(@NonNull Policy policy) {

        Map<RiskType, BigDecimal> sumInsuredGroupedByRiskTypes =
                policy.getPolicyObjects().stream()
                        .flatMap(policyObject -> policyObject.getPolicySubObjects().stream())
                        .collect(groupingBy(PolicySubObject::getRiskType,
                                reducing(BigDecimal.ZERO, PolicySubObject::getSumInsured, BigDecimal::add)));

        if (sumInsuredGroupedByRiskTypes.isEmpty()) {
            log.error("Cant create policy attributes for policy: " + policy);
            throw new PremiumCalculationException("Cant create policy attributes.");
        }

        return mapPremiumSumGroupings(sumInsuredGroupedByRiskTypes);

    }
}
