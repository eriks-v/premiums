package lv.pi.premiums.application.service.pricing.rule;

import lombok.NonNull;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.PricingRange;

public interface SumInsuredIsInRangePricingDecision {
    default boolean sumInsuredIsInRange(@NonNull PremiumAttribute premiumAttribute, @NonNull PricingRange pricingRange) {
      return pricingRange.isInRange(premiumAttribute.getSumInsured());
    }

}
