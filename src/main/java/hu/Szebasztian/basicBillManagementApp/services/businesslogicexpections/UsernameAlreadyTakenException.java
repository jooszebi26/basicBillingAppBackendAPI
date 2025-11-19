package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class UsernameAlreadyTakenException extends BusinessException{

    public UsernameAlreadyTakenException(String username) {
        super("USERNAME_TAKEN", "Username already taken: " + username);
    }

}
