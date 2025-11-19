package hu.Szebasztian.basicBillManagementApp.dtos.responses;

import java.util.Set;

public record LoginRes(
        String token,
        String username,
        Set<String> roles
) {
}
