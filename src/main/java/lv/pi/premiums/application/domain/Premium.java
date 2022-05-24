package lv.pi.premiums.application.domain;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;
@ToString
@EqualsAndHashCode
public class Premium {

    public static final BigDecimal MINIMUM_PREMIUM_AMOUNT = new BigDecimal("0.01");
    private final BigDecimal amount;

    public Premium(@NonNull BigDecimal amount) {

        if (MINIMUM_PREMIUM_AMOUNT.compareTo(amount) > 0) {
            throw new IllegalArgumentException(
                    "Premium amount: " + amount + " is lower than " + MINIMUM_PREMIUM_AMOUNT);
        }
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
