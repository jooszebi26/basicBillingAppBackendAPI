package hu.Szebasztian.basicBillManagementApp.dtos.requests;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateBillReq(
        @NotNull @NotEmpty String username,
        @NotNull @PastOrPresent LocalDate exhibitionDate,
        @NotNull @FutureOrPresent LocalDate dueDate,
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String comment,
        @NotNull @PositiveOrZero int amount
) {
}
