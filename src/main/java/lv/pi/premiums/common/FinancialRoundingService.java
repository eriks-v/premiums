package lv.pi.premiums.common;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class FinancialRoundingService implements FinancialRounding {

    private static final int MONETARY_SCALE = 2;
    @Value("${lv.pi.financial.rounding-rule}")
    private RoundingMode roundingMode;

    public BigDecimal round(@NonNull BigDecimal amountToRound) {
        return amountToRound.setScale(MONETARY_SCALE, roundingMode);
    }
}
