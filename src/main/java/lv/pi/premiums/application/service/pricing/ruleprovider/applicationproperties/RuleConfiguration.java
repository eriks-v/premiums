package lv.pi.premiums.application.service.pricing.ruleprovider.applicationproperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationPropertiesPricingRuleProvider.class)
class RuleConfiguration {
  //This is here to make @ConstructorBinding work for ApplicationPropertiesPricingRuleProvider
}
