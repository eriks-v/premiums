package lv.pi.premiums.application.service.pricing.rule;

import lombok.NonNull;
import lv.pi.premiums.application.domain.PremiumAttribute;

import java.math.BigDecimal;

public interface SumInsuredIsGreaterPricingDecision {
    default boolean sumInsuredIsGreater(@NonNull PremiumAttribute premiumAttribute, @NonNull BigDecimal amount) {
      return premiumAttribute.getSumInsured().compareTo(amount) > 0;
    }

}
