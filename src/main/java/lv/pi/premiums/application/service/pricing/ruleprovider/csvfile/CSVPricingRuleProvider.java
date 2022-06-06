package lv.pi.premiums.application.service.pricing.ruleprovider.csvfile;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.domain.PricingRange;
import lv.pi.premiums.application.service.pricing.rule.PremiumPricingRule;
import lv.pi.premiums.application.service.pricing.ruleprovider.PricingRuleProvider;
import lv.pi.premiums.application.service.pricing.ruleprovider.exception.PricingRuleConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("CSV_RULES")
@Slf4j
class CSVPricingRuleProvider implements PricingRuleProvider {

    private final String contents;
    private final CsvMapper csvMapper;

    public CSVPricingRuleProvider(@Value("${lv.pi.rule.configuration-file-location}") String fileLocation) {

        try {
            File ruleConfigFile = ResourceUtils.getFile(fileLocation);
            this.contents = Files.readString(ruleConfigFile.toPath());
        } catch (IOException e) {
            log.error("Rule configuration entry construction error: " + e.getMessage());
            throw new PricingRuleConfigurationException(e);
        }
        this.csvMapper = new CsvMapper();

        log.debug("contents: " + contents);

    }

    @Override
    public List<PremiumPricingRule> getPricingRules() {

        CsvSchema rulesSchema = csvMapper.schemaFor(PricingRuleConfigurationEntry.class);
        List<PricingRuleConfigurationEntry> pricingRuleConfigurationEntries;

        try (MappingIterator<PricingRuleConfigurationEntry> it = csvMapper.readerFor(PricingRuleConfigurationEntry.class)
                .with(rulesSchema.withHeader())
                .readValues(this.contents)
        ) {

            pricingRuleConfigurationEntries = it.readAll();
            log.debug("Red rules: " + pricingRuleConfigurationEntries);

        } catch (IOException e) {
            log.error("Rule configuration entry construction error: " + e.getMessage());
            throw new PricingRuleConfigurationException(e);
        }

        return mapToRules(pricingRuleConfigurationEntries);
    }
    private List<PremiumPricingRule> mapToRules(List<PricingRuleConfigurationEntry> pricingRuleConfigurationEntries) {

        return pricingRuleConfigurationEntries.stream()
                .map(entry -> {
                    log.debug("entry: " + entry);
                    PricingRange pricingRange = new PricingRange(entry.getMinRange(), entry.getMaxRange());
                    return new UnifiedPricingRule(
                                    entry.getCoefficient(),
                                    pricingRange,
                                    entry.getRiskType()
                            );
                 }).collect(Collectors.toUnmodifiableList());

    }
}






















