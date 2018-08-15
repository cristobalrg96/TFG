package restserver.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {

    private String userLogin;

    public UserNotFoundException(String userLogin) {
        super("The user with login/email " + userLogin + " was not found");
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

}