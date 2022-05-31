package lv.pi.premiums.application.domain;

import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class PricingRange {
    BigDecimal start;
    BigDecimal end;

    public PricingRange(BigDecimal start, BigDecimal end) {

        if (start != null && end != null && start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Range start value : " + start + " is greater than end value: " + end);
        }

        this.start = start;
        this.end = end;
    }

    public boolean isInRange(@NonNull BigDecimal value) {

        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);

    }
}
