package hu.Szebasztian.basicBillManagementApp.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterReq(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String username,
        @NotNull @NotEmpty String password,
        @NotNull @NotEmpty String role
) {
}
