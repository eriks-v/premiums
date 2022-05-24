package lv.pi.premiums.application.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PremiumAttributeTest {

    @ParameterizedTest
    @ValueSource(strings = {"0","-1"})
    void throwsExceptionWhenAmountIsZeroOrLess(String amount) {
        //given
        BigDecimal sumInsured = new BigDecimal(amount);
        String expectedMessage = "Premium amount: " + amount +" is lower than 0.01";
        //when
        //then
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> new PremiumAttribute(RiskType.FIRE, sumInsured)
        );

        assertEquals(expectedMessage, illegalArgumentException.getMessage());
    }

}