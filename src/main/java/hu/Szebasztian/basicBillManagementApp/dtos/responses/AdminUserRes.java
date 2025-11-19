package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.time.LocalDate;
import java.util.List;

public record AdminUserRes(
        long id,
        String name,
        String username,
        String password,
        LocalDate enterDate,
        List<String> roles
) {
}
