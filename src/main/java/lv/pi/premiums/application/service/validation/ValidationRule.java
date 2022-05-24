package lv.pi.premiums.application.service.validation;

public interface ValidationRule<T> {

    void validate(T validationEntity);
}
