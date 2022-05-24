package lv.pi.premiums.application.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PremiumTest {

  @Test
  void throwsExceptionWhenAmountLessThanMinimum() {
    //given
    String expectedMessage = "Premium amount: 0 is lower than 0.01";
    BigDecimal zeroAmount = BigDecimal.ZERO;
    //when
    //then
    IllegalArgumentException illegalArgumentException = assertThrows(
        IllegalArgumentException.class,
        () -> new Premium(zeroAmount)
    );

    assertEquals(expectedMessage, illegalArgumentException.getMessage());
  }

}