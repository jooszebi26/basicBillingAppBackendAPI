package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.data.entities.Role;
import hu.Szebasztian.basicBillManagementApp.data.entities.User;
import hu.Szebasztian.basicBillManagementApp.data.repositories.RoleRepo;
import hu.Szebasztian.basicBillManagementApp.data.repositories.UserRepo;
import hu.Szebasztian.basicBillManagementApp.dtos.requests.LoginReq;
import hu.Szebasztian.basicBillManagementApp.dtos.requests.RegisterReq;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AuthRes;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.LoginRes;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.CaptchaFailedException;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.CaptchaRequiredException;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.RoleNotFoundException;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.UsernameAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CaptchaService captchaService;
    private final LoginAttemptService loginAttemptService;

    public AuthRes register(RegisterReq request) {

        Role role = roleRepo.findRoleByName(request.role()).orElseThrow(
                () -> new RoleNotFoundException(request.role())
        );
        if (userRepo.existsByUsername(request.username())) {
            throw new UsernameAlreadyTakenException(request.username());
        }


        User user = User.builder()
                .name(request.name())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(Set.of(role))
                .build();

        userRepo.save(user);

        return new AuthRes("Registered Successfully!");
    }

    public LoginRes login(LoginReq request) {

        String key = request.username();


        if (loginAttemptService.isCaptchaRequired(key)) {
            if (request.captchaToken() == null || request.captchaToken().isBlank()) {
                throw new CaptchaRequiredException();
            }

            boolean captchaOk = captchaService.verify(request.captchaToken());
            if (!captchaOk) {
                throw new CaptchaFailedException();
            }
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException ex) {

            loginAttemptService.loginFailed(key);
            throw ex;
        }

        User user = userRepo.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException(request.username()));

        user.setLastLoginDate(LocalDate.now());
        userRepo.save(user);
        String token = jwtService.generateToken(user);

        Set<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        loginAttemptService.loginSucceeded(key);
        return new LoginRes(token, user.getUsername(), roles);
    }


}
