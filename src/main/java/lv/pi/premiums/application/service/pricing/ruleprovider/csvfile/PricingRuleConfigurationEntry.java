package lv.pi.premiums.application.service.pricing.ruleprovider.csvfile;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lv.pi.premiums.application.domain.RiskType;

import java.math.BigDecimal;

@JsonPropertyOrder({"riskType", "minRange","maxRange", "coefficient"})
@Getter
@ToString
class PricingRuleConfigurationEntry {

    private final RiskType riskType;
    private final BigDecimal minRange;
    private final BigDecimal maxRange;
    private final BigDecimal coefficient;

    @JsonCreator
    public PricingRuleConfigurationEntry(@JsonProperty("riskType") RiskType riskType,
                                         @JsonProperty("minRange") BigDecimal minRange,
                                         @JsonProperty("maxRange") BigDecimal maxRange,
                                         @JsonProperty("coefficient") BigDecimal coefficient) {
        this.riskType = riskType;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.coefficient = coefficient;

    }
}
