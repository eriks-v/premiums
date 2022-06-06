package lv.pi.premiums.application.service.pricing.ruleprovider.exception;

public class PricingRuleConfigurationException extends RuntimeException {
    public PricingRuleConfigurationException(Throwable cause) {
        super(cause);
    }

    public PricingRuleConfigurationException(String message) {
        super(message);
    }
}
