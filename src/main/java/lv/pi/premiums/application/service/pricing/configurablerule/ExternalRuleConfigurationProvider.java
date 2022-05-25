package lv.pi.premiums.application.service.pricing.configurablerule;

import java.util.List;

public interface ExternalRuleConfigurationProvider {
    List<PricingRuleConfigurationEntry> getRuleConfigurationEntries();
}
