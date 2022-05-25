package lv.pi.premiums.application.service.pricing.configurablerule;

import lv.pi.premiums.application.domain.RiskType;
import lv.pi.premiums.application.service.pricing.PremiumPricingRule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ExternalRuleMapperServiceTest {


    @Test
    void emptyExternalRuleSetReturnsEmpty() {
        //given
        List<PricingRuleConfigurationEntry> externalRules = Collections.emptyList();
        ExternalRuleMapper externalRuleMapper = new ExternalRuleMapperService();
        //when
        List<PremiumPricingRule> result = externalRuleMapper.mapFromExternalRules(externalRules);
        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void mappedRuleCountMatches() {
        //given
        ExternalRuleMapper externalRuleMapper = new ExternalRuleMapperService();
        List<PricingRuleConfigurationEntry> externalRules = List.of(
                new PricingRuleConfigurationEntry(RiskType.FIRE, BigDecimal.ZERO,BigDecimal.TEN),
                new PricingRuleConfigurationEntry(RiskType.THEFT, BigDecimal.ZERO,BigDecimal.ZERO),
                new PricingRuleConfigurationEntry(RiskType.FIRE, BigDecimal.ZERO,BigDecimal.ONE)
        );
        //when
        List<PremiumPricingRule> result = externalRuleMapper.mapFromExternalRules(externalRules);
        //then
        assertEquals(externalRules.size(), result.size());
    }

    @Test
    void mappedRuleOrderMatches() {
        //given
        ExternalRuleMapper externalRuleMapper = new ExternalRuleMapperService();
        List<PricingRuleConfigurationEntry> externalRules = List.of(
                new PricingRuleConfigurationEntry(RiskType.FIRE, BigDecimal.ZERO,BigDecimal.TEN),
                new PricingRuleConfigurationEntry(RiskType.THEFT, BigDecimal.ZERO,BigDecimal.ZERO),
                new PricingRuleConfigurationEntry(RiskType.FIRE, BigDecimal.ZERO,BigDecimal.ONE)
        );
        BigDecimal[] expectedResult = {BigDecimal.TEN, BigDecimal.ZERO, BigDecimal.ONE};
        //when
        List<PremiumPricingRule> result = externalRuleMapper.mapFromExternalRules(externalRules);
        BigDecimal[] resultArray = new BigDecimal[result.size()];
        externalRules.stream()
                .map(PricingRuleConfigurationEntry::getCoefficient)
                .collect(Collectors.toUnmodifiableList())
                .toArray(resultArray);
        //then
        assertArrayEquals(expectedResult,resultArray);
    }

}