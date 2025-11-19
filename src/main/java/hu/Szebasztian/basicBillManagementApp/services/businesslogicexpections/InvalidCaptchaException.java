package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class InvalidCaptchaException extends BusinessException{

    public InvalidCaptchaException() {
    super("INVALID_CAPTCHA", "Captcha is invalid");
    }

}
