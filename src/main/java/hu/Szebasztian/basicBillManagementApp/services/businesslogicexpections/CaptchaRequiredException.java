package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class CaptchaRequiredException extends BusinessException {
  public CaptchaRequiredException() {
    super("CAPTCHA REQUIRED", "CAPTCHA REQUIRED");
  }
}
