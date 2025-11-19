package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String code;

    protected BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

}
