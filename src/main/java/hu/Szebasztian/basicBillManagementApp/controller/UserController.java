package hu.Szebasztian.basicBillManagementApp.controller;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.UserRes;
import hu.Szebasztian.basicBillManagementApp.services.UserService;
import hu.Szebasztian.basicBillManagementApp.services.mappers.UserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users/me/details")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsMapper userDetailsMapper;

    @GetMapping("/")
    public UserRes getUserByUsername(Authentication authentication) {
        return userDetailsMapper.entityToDto(userService.listUserByUsername(authentication.getName()));
    }


}
