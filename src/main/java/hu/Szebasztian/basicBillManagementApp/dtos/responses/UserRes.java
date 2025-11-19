package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.time.LocalDate;
import java.util.Set;

public record UserRes(
        String name,
        String username,
        LocalDate lastLoginDate,
        Set<String> role
) {
}
