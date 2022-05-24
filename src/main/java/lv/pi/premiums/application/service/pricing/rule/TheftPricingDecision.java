package lv.pi.premiums.application.service.pricing.rule;

import lombok.NonNull;
import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;

public interface TheftPricingDecision {
    default boolean isTheft(@NonNull PremiumAttribute premiumAttribute) {
        return premiumAttribute.getRiskType() == RiskType.THEFT;
    }
}
