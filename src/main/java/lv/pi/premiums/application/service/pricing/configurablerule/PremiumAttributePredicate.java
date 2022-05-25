package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.PremiumAttribute;

import java.util.function.Predicate;

public interface PremiumAttributePredicate extends Predicate<PremiumAttribute> {
}
