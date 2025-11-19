package hu.Szebasztian.basicBillManagementApp.services.mappers;
import hu.Szebasztian.basicBillManagementApp.data.entities.Role;
import hu.Szebasztian.basicBillManagementApp.data.entities.User;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AdminUserRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AdminUserMapper {
    @Mapping(target = "roles", source = "roles")
    AdminUserRes entityToDto(User user);
    List<AdminUserRes> entitiesToDtos(List<User> user);


    default List<String> mapRoles(Set<Role> roles) {
        if (roles == null) return List.of();
        return roles.stream()
                .map(Role::getName)
                .toList();
    }


}
