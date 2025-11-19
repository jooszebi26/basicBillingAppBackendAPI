package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class RoleNotFoundException extends BusinessException{

    public RoleNotFoundException(String role) {
        super("ROLE_NOT_FOUND", "Role not found with name: " + role);
    }

}
