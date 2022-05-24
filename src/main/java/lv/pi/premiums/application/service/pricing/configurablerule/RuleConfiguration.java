package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.PolicyPricing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = PolicyPricing.class)
public class RuleConfiguration {

    @Bean
    public List<PremiumPricingRule> getConfigurableRules() {


        return List.of(new ConfigurablePremiumPricingRule(List.of(new IsSameRiskTypeDecision(RiskType.FIRE), new IsSumInsuredIsGreaterPricingDecision(BigDecimal.valueOf(100))), new BigDecimal("0.024")),
                       new ConfigurablePremiumPricingRule(List.of(new IsSameRiskTypeDecision(RiskType.FIRE)), new BigDecimal("0.014")),
                       new ConfigurablePremiumPricingRule(List.of(new IsSameRiskTypeDecision(RiskType.THEFT), new IsSumInsuredIsGreaterPricingDecision(new BigDecimal("14.99"))), new BigDecimal("0.05")),
                       new ConfigurablePremiumPricingRule(List.of(new IsSameRiskTypeDecision(RiskType.THEFT)), new BigDecimal("0.11"))
                );
    }

}
