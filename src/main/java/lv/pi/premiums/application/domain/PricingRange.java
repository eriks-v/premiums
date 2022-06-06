package lv.pi.premiums.application.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
public class PricingRange {
    private final BigDecimal start;
    private final BigDecimal end;

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
