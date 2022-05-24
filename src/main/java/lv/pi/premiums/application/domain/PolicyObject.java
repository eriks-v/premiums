package lv.pi.premiums.application.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
public class PolicyObject {

    @NonNull
    private final String name;
    @NonNull
    private final Set<PolicySubObject> policySubObjects;

}
