package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class CaptchaFailedException extends BusinessException {
    public CaptchaFailedException() {

      super("CAPTCHA FAILED", "CAPTCHA FAILED");
    }
}
