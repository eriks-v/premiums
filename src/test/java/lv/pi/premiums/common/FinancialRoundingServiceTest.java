package lv.pi.premiums.common;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class FinancialRoundingServiceTest {

  private static final RoundingMode appUsedRoundingMode = RoundingMode.HALF_UP;

  @Test
  void roundsUp() {
    //given
    FinancialRounding financialRounding = new FinancialRoundingService();
    BigDecimal roundableAmount = new BigDecimal("0.005");
    BigDecimal expectedResult = new BigDecimal("0.01");

    //when
    ReflectionTestUtils.setField(financialRounding, "roundingMode", appUsedRoundingMode);
    BigDecimal result = financialRounding.round(roundableAmount);

    //then
    assertEquals(expectedResult.toString(),result.toString() );

  }

  @Test
  void roundsDown() {
    //given
    FinancialRounding financialRounding = new FinancialRoundingService();
    BigDecimal roundableAmount = new BigDecimal("0.004");
    BigDecimal expectedResult = new BigDecimal("0");

    //when
    ReflectionTestUtils.setField(financialRounding, "roundingMode", appUsedRoundingMode);
    BigDecimal result = financialRounding.round(roundableAmount).stripTrailingZeros();

    //then
    assertEquals(expectedResult.toString(),result.toString() );

  }

}