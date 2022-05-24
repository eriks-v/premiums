package lv.pi.premiums.application.service.pricing;

import lombok.NonNull;
import lv.pi.premiums.application.domain.Policy;

import java.math.BigDecimal;

public interface PolicyPricing {

    BigDecimal calculatePrice(@NonNull Policy policy);
}
