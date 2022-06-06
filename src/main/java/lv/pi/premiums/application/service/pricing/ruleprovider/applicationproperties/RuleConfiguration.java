package lv.pi.premiums.application.service.pricing.ruleprovider.applicationproperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(ApplicationPropertiesPricingRuleProvider.class)
@Profile("APPLICATION_PROPERTIES_RULES")
class RuleConfiguration {
  //This is here to make @ConstructorBinding work for ApplicationPropertiesPricingRuleProvider
}
