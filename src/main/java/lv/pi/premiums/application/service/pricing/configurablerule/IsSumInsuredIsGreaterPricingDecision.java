package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.PremiumAttribute;

import java.math.BigDecimal;

public class IsSumInsuredIsGreaterPricingDecision implements PremiumAttributePredicate {

    private final BigDecimal lowerThreshold;

    public IsSumInsuredIsGreaterPricingDecision(BigDecimal lowerThreshold) {
        this.lowerThreshold = lowerThreshold;
    }

    @Override
    public boolean test(PremiumAttribute premiumAttribute) {
        return premiumAttribute.getSumInsured().compareTo(lowerThreshold) > 0;
    }
}
