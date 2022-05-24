package lv.pi.premiums.common;

import lombok.NonNull;

import java.math.BigDecimal;

public interface FinancialRounding {

    BigDecimal round(@NonNull BigDecimal amountToRound);
}
