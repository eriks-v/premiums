package lv.pi.premiums.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.Premium;
import lv.pi.premiums.application.service.pricing.PolicyPricing;
import lv.pi.premiums.application.service.validation.Validate;
import lv.pi.premiums.common.FinancialRounding;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class PremiumCalculator implements CalculatePremium {

    private final Validate<Policy> policyValidationService;
    private final PolicyPricing policyPricingService;
    private final FinancialRounding financialRoundingService;

    @Override
    public Premium calculate(@NonNull Policy policy) {

        policyValidationService.validate(policy);

        BigDecimal totalUnroundedPrice = policyPricingService.calculatePrice(policy);

        return new Premium(financialRoundingService.round(totalUnroundedPrice));
    }
}
