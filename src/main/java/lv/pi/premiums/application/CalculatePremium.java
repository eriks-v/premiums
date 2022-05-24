package lv.pi.premiums.application;

import lombok.NonNull;
import lv.pi.premiums.application.domain.Policy;
import lv.pi.premiums.application.domain.Premium;

public interface CalculatePremium {

    Premium calculate(@NonNull Policy policy);
}
