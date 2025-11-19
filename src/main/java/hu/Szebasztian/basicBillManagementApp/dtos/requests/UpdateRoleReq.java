package hu.Szebasztian.basicBillManagementApp.dtos.requests;

import java.util.List;

public record UpdateRoleReq(
        List<String> roles
) {
}
