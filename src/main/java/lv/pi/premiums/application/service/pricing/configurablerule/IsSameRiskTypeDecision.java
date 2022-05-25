package lv.pi.premiums.application.service.pricing.configurablerule;


import lv.pi.premiums.application.domain.PremiumAttribute;
import lv.pi.premiums.application.domain.RiskType;

public class IsSameRiskTypeDecision implements PremiumAttributePredicate {

    private final RiskType checkRiskType;

    public IsSameRiskTypeDecision(RiskType checkRiskType) {
        this.checkRiskType = checkRiskType;
    }

    @Override
    public boolean test(PremiumAttribute premiumAttribute) {
        return premiumAttribute.getRiskType() == checkRiskType;
    }
}
