package lv.pi.premiums.application.service.pricing.configurablerule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.ToString;
import lv.pi.premiums.application.domain.RiskType;

import java.math.BigDecimal;

@JsonPropertyOrder({"riskType", "sumInsuredLowerThreshold", "coefficient"})
@Getter
@ToString
public class PricingRuleConfigurationEntry {

    private final RiskType riskType;
    private final BigDecimal sumInsuredLowerThreshold;
    private final BigDecimal coefficient;

    @JsonCreator
    public PricingRuleConfigurationEntry(@JsonProperty("riskType") RiskType riskType,
                                         @JsonProperty("sumInsuredLowerThreshold") BigDecimal sumInsuredLowerThreshold,
                                         @JsonProperty("coefficient") BigDecimal coefficient) {
        this.riskType = riskType;
        this.sumInsuredLowerThreshold = sumInsuredLowerThreshold;
        this.coefficient = coefficient;
    }
}
