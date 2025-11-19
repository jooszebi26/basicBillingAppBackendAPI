package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.dtos.responses.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {

    @Value("${recaptcha.secret}")
    private String secret;

    @Value("${recaptcha.verifyUrl}")
    private String verifyUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean verify(String captchaToken) {
        if (captchaToken == null || captchaToken.isBlank()) {
            return false;
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", secret);
        params.add("response", captchaToken);

        RecaptchaResponse recaptchaResponse = restTemplate.postForObject(
                verifyUrl,
                params,
                RecaptchaResponse.class
        );

        if (recaptchaResponse == null) {
            return false;
        }


        return recaptchaResponse.success();
    }
}
