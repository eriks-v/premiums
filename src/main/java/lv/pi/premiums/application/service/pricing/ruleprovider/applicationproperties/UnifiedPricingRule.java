package lv.pi.premiums.application.service.pricing.ruleprovider.applicationproperties;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.PricingRange;
import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
class UnifiedPricingRule implements PremiumPricingRule {

  @NonNull
  private BigDecimal premiumCoefficient;
  @NonNull
  private PricingRange pricingRange;
  @NonNull
  private RiskType riskType;

  @Override
  public boolean isRuleApplicable(PremiumAttribute premiumAttribute) {
    return premiumAttribute.getRiskType() == riskType && pricingRange.isInRange(premiumAttribute.getSumInsured());
  }

  @Override
  public BigDecimal getPremiumCoefficient() {
    return premiumCoefficient;
  }
}
