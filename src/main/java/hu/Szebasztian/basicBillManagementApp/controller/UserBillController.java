package hu.Szebasztian.basicBillManagementApp.controller;

import hu.Szebasztian.basicBillManagementApp.dtos.responses.UserBillRes;
import hu.Szebasztian.basicBillManagementApp.services.UserBillService;
import hu.Szebasztian.basicBillManagementApp.services.mappers.UserBillMapper;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("api/users/me")
@RequiredArgsConstructor
public class UserBillController {

    private final UserBillService billService;
    private final UserBillMapper userBillMapper;

    @GetMapping("/bills")
    public List<UserBillRes> getAllBills(
            Authentication authentication
    ) {

        return userBillMapper.entitiesToDtos(billService.listMyBills(authentication.getName()));
    }

    @GetMapping("/{billId}")
    public UserBillRes getBill(@PathVariable @NotNull @Positive Long billId,
                               Authentication authentication) {
        return userBillMapper.entityToDto(billService.listMyBillById(billId, authentication.getName()));
    }

}
