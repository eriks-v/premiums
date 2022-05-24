package lv.pi.premiums.application.domain;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PolicySubObject {

    @NonNull
    private final String name;
    @NonNull
    private final BigDecimal sumInsured;
    @NonNull
    private final RiskType riskType;
}