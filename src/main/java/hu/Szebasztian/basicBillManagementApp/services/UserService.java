package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.data.entities.User;
import hu.Szebasztian.basicBillManagementApp.data.repositories.UserRepo;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User listUserByUsername(String username) {

        return userRepo.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

}
