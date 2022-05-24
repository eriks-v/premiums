package lv.pi.premiums.application.service.pricing.configurablerule;


import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;

import java.util.function.Predicate;

public class IsSameRiskTypeDecision implements Predicate<PremiumAttribute> {

    private final RiskType checkRiskType;

    public IsSameRiskTypeDecision(RiskType checkRiskType) {
        this.checkRiskType = checkRiskType;
    }

    @Override
    public boolean test(PremiumAttribute premiumAttribute) {
        return premiumAttribute.getRiskType() == checkRiskType;
    }
}
