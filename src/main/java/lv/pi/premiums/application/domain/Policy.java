package lv.pi.premiums.application.domain;

import lombok.NonNull;
import lombok.Value;

import java.util.Set;

@Value
public class Policy {

    @NonNull
    private final String policyNumber;
    @NonNull
    private final PolicyStatus policyStatus;
    @NonNull
    private final Set<PolicyObject> policyObjects;

}
