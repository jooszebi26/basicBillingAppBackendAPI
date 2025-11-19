package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.util.List;

public record RecaptchaResponse(
        boolean success,
        String challenge_ts,
        String hostname,
        List<String> errorCodes
) {
}
