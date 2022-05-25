package lv.pi.premiums.application.service.pricing.configurablerule.configuration;

import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.service.pricing.PolicyPricing;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.configurablerule.ExternalRuleConfigurationProvider;
import lv.pi.premiums.application.service.pricing.configurablerule.ExternalRuleMapper;
import lv.pi.premiums.application.service.pricing.configurablerule.PricingRuleConfigurationEntry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = PolicyPricing.class)
@Profile("CONFIGURABLE_RULES")
@Slf4j
public class RuleConfiguration {

    private final ExternalRuleConfigurationProvider externalRuleConfigurationProvider;
    private final ExternalRuleMapper externalRuleMapperService;

    public RuleConfiguration(ExternalRuleConfigurationProvider externalRuleConfigurationProvider
            , ExternalRuleMapper externalRuleMapperService) {
        this.externalRuleConfigurationProvider = externalRuleConfigurationProvider;
        this.externalRuleMapperService = externalRuleMapperService;
        log.debug("RuleConfiguration loaded: " + (externalRuleConfigurationProvider != null));
    }

    @Bean
    public List<PremiumPricingRule> getConfigurableRules() {

        List<PricingRuleConfigurationEntry> ruleConfiguration = externalRuleConfigurationProvider.getRuleConfigurationEntries();

        log.debug("RuleConfiguration size: " + ruleConfiguration.size());

        return externalRuleMapperService.mapFromExternalRules(ruleConfiguration);
    }

}
