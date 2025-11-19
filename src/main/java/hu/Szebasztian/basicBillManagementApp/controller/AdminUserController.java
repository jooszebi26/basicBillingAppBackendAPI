package hu.Szebasztian.basicBillManagementApp.controller;

import hu.Szebasztian.basicBillManagementApp.dtos.requests.UpdateRoleReq;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AdminUserRes;
import hu.Szebasztian.basicBillManagementApp.services.AdminUserService;
import hu.Szebasztian.basicBillManagementApp.services.mappers.AdminUserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final AdminUserMapper adminUserMapper;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AdminUserRes> getAllUsers() {
        return adminUserMapper.entitiesToDtos(adminUserService.listAllUsers());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AdminUserRes getUserById(@PathVariable @NotNull @Positive Long userId) {
        return adminUserMapper.entityToDto(adminUserService.listUserById(userId));
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AdminUserRes getUserByUsername(@PathVariable @NotNull @NotEmpty String username) {
        return adminUserMapper.entityToDto(adminUserService.listUserByUsername(username));
    }

    @PatchMapping("/{username}/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AdminUserRes updateUserRoles(@PathVariable @NotNull @NotEmpty String username, @RequestBody @Valid UpdateRoleReq updateRoleReq) {
        return adminUserMapper.entityToDto(adminUserService.updateRoles(username, updateRoleReq.roles()));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUserById(@PathVariable @NotNull @Positive Long userId) {
        adminUserService.deleteUserById(userId);
    }

    @DeleteMapping("/by/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUserByUsername(@PathVariable @NotNull @NotEmpty String username) {
        adminUserService.deleteUserByUsername(username);
    }

}
