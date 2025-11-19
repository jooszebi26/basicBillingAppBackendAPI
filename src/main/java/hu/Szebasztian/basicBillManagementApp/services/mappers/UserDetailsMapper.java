package hu.Szebasztian.basicBillManagementApp.services.mappers;

import hu.Szebasztian.basicBillManagementApp.data.entities.Role;
import hu.Szebasztian.basicBillManagementApp.data.entities.User;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.UserRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    @Mapping(target = "role", source = "roles")
    UserRes entityToDto(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        if (roles == null) return Set.of();
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
