package lv.pi.premiums.application.service.pricing;

import lombok.NonNull;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.PremiumAttribute;

import java.util.Set;

interface PremiumAttributeCollector {
    Set<PremiumAttribute> collectPremiumAttributes(@NonNull Policy policy);
}
