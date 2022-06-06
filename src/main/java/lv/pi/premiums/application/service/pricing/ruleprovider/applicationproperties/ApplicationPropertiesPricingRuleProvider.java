package lv.pi.premiums.application.service.pricing.ruleprovider.applicationproperties;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.ruleprovider.PricingRuleProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "premium")
@ConstructorBinding
@Slf4j
class ApplicationPropertiesPricingRuleProvider implements PricingRuleProvider {

  private final List<UnifiedPricingRule> premiumPricingRules;

  public ApplicationPropertiesPricingRuleProvider(List<UnifiedPricingRule> premiumPricingRules) {
    this.premiumPricingRules = premiumPricingRules;
  }

  @Override
  public List<PremiumPricingRule> getPricingRules() {
    return Collections.unmodifiableList(premiumPricingRules);
  }
}
