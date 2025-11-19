package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class UserNotFoundException extends BusinessException{

    public UserNotFoundException(long userId) {
        super("USER_NOT_FOUND", "User not found with user id: " + userId);
    }

    public UserNotFoundException(String username) {
        super("USER_NOT_FOUND", "User not found with username: " + username);
    }
}
