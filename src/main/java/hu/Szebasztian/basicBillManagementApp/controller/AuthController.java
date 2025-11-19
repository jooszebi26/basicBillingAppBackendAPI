package hu.Szebasztian.basicBillManagementApp.controller;

import hu.Szebasztian.basicBillManagementApp.dtos.requests.LoginReq;
import hu.Szebasztian.basicBillManagementApp.dtos.requests.RegisterReq;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AuthRes;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.LoginRes;
import hu.Szebasztian.basicBillManagementApp.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    @PostMapping("/login")
    public LoginRes login(@Valid @RequestBody LoginReq request){
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthRes register(@Valid @RequestBody RegisterReq request){
        return authService.register(request);
    }

}
