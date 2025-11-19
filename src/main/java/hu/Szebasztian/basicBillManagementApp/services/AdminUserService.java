package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.data.entities.Role;
import hu.Szebasztian.basicBillManagementApp.data.entities.User;
import hu.Szebasztian.basicBillManagementApp.data.repositories.RoleRepo;
import hu.Szebasztian.basicBillManagementApp.data.repositories.UserRepo;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.RoleNotFoundException;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public List<User> listAllUsers() {
        return userRepo.findAll();
    }

    public User listUserById(Long userId) {

        return userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId)
        );
    }

    public User listUserByUsername(String username) {

        return userRepo.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(username)
        );
    }

    public User updateRoles(String username, List<String> roles) {
        User u = listUserByUsername(username);

        Set<Role> newRoles = roles.stream()
                .map(roleName -> roleRepo.findRoleByName(roleName)
                        .orElseThrow(() -> new RoleNotFoundException(roleName)))
                .collect(Collectors.toSet());

        u.setRoles(newRoles);
        return userRepo.save(u);

    }

    public void deleteUserById(Long userId) {
        User user = listUserById(userId);
        userRepo.delete(user);
    }

    public void deleteUserByUsername(String username) {
        User user = listUserByUsername(username);
        System.out.println(user);
        userRepo.delete(user);
    }


}
