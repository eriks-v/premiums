package lv.pi.premiums.application.service.validation.exception;

public class PolicyValidationException extends RuntimeException {

    public PolicyValidationException(String text) {
        super(text);
    }
}
