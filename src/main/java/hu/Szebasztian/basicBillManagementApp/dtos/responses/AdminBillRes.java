package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.time.LocalDate;

public record AdminBillRes(
        int id,
        String username,
        LocalDate exhibitionDate,
        LocalDate dueDate,
        String name,
        String comment,
        int amount
) {
}
