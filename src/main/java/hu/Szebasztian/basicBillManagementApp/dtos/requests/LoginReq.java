package hu.Szebasztian.basicBillManagementApp.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginReq(
        @NotNull @NotEmpty String username,
        @NotNull @NotEmpty String password,
        String captchaToken
) {
}
