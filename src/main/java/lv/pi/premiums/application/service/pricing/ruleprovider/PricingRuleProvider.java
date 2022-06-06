package lv.pi.premiums.application.service.pricing.ruleprovider;

import java.util.List;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;

public interface PricingRuleProvider {

  List<PremiumPricingRule> getPricingRules();

}
