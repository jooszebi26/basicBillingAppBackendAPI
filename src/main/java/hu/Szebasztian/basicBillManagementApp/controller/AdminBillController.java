package hu.Szebasztian.basicBillManagementApp.controller;

import hu.Szebasztian.basicBillManagementApp.dtos.requests.CreateBillReq;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AdminBillRes;
import hu.Szebasztian.basicBillManagementApp.services.AdminBillService;
import hu.Szebasztian.basicBillManagementApp.services.mappers.AdminBillMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class AdminBillController {

    private final AdminBillService adminBillService;
    private final AdminBillMapper adminBillMapper;

    @GetMapping("/")
    public List<AdminBillRes> getAllBills() {
        return adminBillMapper.entitiesToDtos(adminBillService.listAllBills());
    }

    @GetMapping("/{username}/bills/")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public List<AdminBillRes> getBill(@PathVariable @NotNull @NotEmpty String username) {
        return adminBillMapper.entitiesToDtos(adminBillService.listAllBillsByUsername(username));
    }

    @GetMapping("/bills/{billId}")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public AdminBillRes getBillById(@PathVariable @NotNull @Positive long billId) {
        return adminBillMapper.entityToDto(adminBillService.listBillById(billId));
    }

    @GetMapping("/{username}/bills/{billId}")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public AdminBillRes getBillByIdAndUsername(
            @PathVariable @NotNull @NotEmpty String username,
            @PathVariable @NotNull @Positive long billId)
    {
        return adminBillMapper.entityToDto(adminBillService.listBillByIdAndUsername(billId, username));
    }

    @PostMapping("/bills/create")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public AdminBillRes createBill(@RequestBody @Valid CreateBillReq req) {
        return adminBillMapper.entityToDto(adminBillService.createBill(
                req.username(), req.exhibitionDate(), req.dueDate(),
                req.comment(), req.name(), req.amount()
        ));
    }

    @DeleteMapping("/bills/delete/{billId}")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public void deleteBillById(@PathVariable @NotNull @Positive Long billId) {
        adminBillService.deleteBillById(billId);
    }

    @DeleteMapping("{username}/bills/delete/{billId}")
    @PreAuthorize("hasAnyAuthority('ACCOUNTANT','ADMIN')")
    public void deleteBillByIdAndUser(
            @PathVariable @NotNull @Positive Long billId,
            @PathVariable @NotNull @NotEmpty String username) {
        adminBillService.deleteBillByIdAndUsername(billId, username);
    }

}
