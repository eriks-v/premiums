package lv.pi.premiums.application.service.pricing.configurablerule;

import lombok.NonNull;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExternalRuleMapperService implements ExternalRuleMapper {
    @Override
    public List<PremiumPricingRule> mapFromExternalRules(@NonNull List<PricingRuleConfigurationEntry> pricingRuleConfigurationEntries) {
        return pricingRuleConfigurationEntries.stream().map(configEntry -> {
            List<PremiumAttributePredicate> predicates = new LinkedList<>();
            predicates.add(new IsSameRiskTypeDecision(configEntry.getRiskType()));

            if (configEntry.getSumInsuredLowerThreshold() != null) {
                predicates.add(new IsSumInsuredIsGreaterPricingDecision(configEntry.getSumInsuredLowerThreshold()));
            }
            return new ConfigurablePremiumPricingRule(predicates, configEntry.getCoefficient());

        }).collect(Collectors.toUnmodifiableList());
    }
}
