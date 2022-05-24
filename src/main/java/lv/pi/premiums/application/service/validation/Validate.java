package lv.pi.premiums.application.service.validation;

import lombok.NonNull;

import java.util.List;


public abstract class Validate<T> {

    private final List<ValidationRule<T>> rules;

    protected Validate(@NonNull List<ValidationRule<T>> rules) {
        if (rules.isEmpty()) {
            throw new IllegalArgumentException("Validation rule list cant be empty.");
        }
        this.rules = rules;
    }

    protected void validateEntity(T validationEntity) {
        rules.forEach(rule -> rule.validate(validationEntity));
    }

    public abstract void validate(T validationEntity);

}
