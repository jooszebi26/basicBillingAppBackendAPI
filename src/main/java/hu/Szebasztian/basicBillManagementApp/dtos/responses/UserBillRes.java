package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.time.LocalDate;

public record UserBillRes(
    long id,
    LocalDate exhibitionDate,
    LocalDate dueDate,
    String name,
    String comment,
    int amount
) {
}
