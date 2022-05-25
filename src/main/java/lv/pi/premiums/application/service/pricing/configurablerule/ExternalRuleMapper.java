package lv.pi.premiums.application.service.pricing.configurablerule;

import lombok.NonNull;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;

import java.util.List;

public interface ExternalRuleMapper {
    List<PremiumPricingRule> mapFromExternalRules(@NonNull List<PricingRuleConfigurationEntry> pricingRuleConfigurationEntries);
}
