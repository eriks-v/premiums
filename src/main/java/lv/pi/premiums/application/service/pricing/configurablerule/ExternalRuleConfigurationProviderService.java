package lv.pi.premiums.application.service.pricing.configurablerule;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import lv.pi.premiums.application.service.pricing.configurablerule.exception.PricingRuleConfigurationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@Slf4j
@Profile("CONFIGURABLE_RULES")
class ExternalRuleConfigurationProviderService implements ExternalRuleConfigurationProvider {

    private final String contents;

    private final CsvMapper csvMapper;

    public ExternalRuleConfigurationProviderService(@Value("${lv.pi.rule.configuration-file-location}") String fileLocation) {

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
    public List<PricingRuleConfigurationEntry> getRuleConfigurationEntries() {

        CsvSchema rulesSchema = csvMapper.schemaFor(PricingRuleConfigurationEntry.class);

        try (MappingIterator<PricingRuleConfigurationEntry> it = csvMapper.readerFor(PricingRuleConfigurationEntry.class)
                .with(rulesSchema.withHeader())
                .readValues(this.contents)
        ) {

            List<PricingRuleConfigurationEntry> pricingRuleConfigurationEntries = it.readAll();
            log.debug("Red rules: " + pricingRuleConfigurationEntries);

            return pricingRuleConfigurationEntries;
        } catch (IOException e) {
            log.error("Rule configuration entry construction error: " + e.getMessage());
            throw new PricingRuleConfigurationException(e);
        }
    }
}
