package lv.pi.premiums.application.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode
public class PremiumAttribute {

    public static final BigDecimal MINIMUM_SUM_INSURED_AMOUNT = new BigDecimal("0.01");
    @NonNull
    private final RiskType riskType;
    @NonNull
    private final BigDecimal sumInsured;

    public PremiumAttribute(@NonNull RiskType riskType, @NonNull BigDecimal sumInsured) {

        if(MINIMUM_SUM_INSURED_AMOUNT.compareTo(sumInsured) > 0) {
            throw new IllegalArgumentException(
                    "Premium amount: " + sumInsured + " is lower than " + MINIMUM_SUM_INSURED_AMOUNT);
        }

        this.riskType = riskType;
        this.sumInsured = sumInsured;
    }
}
