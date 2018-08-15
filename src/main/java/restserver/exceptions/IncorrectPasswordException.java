package restserver.exceptions;

@SuppressWarnings("serial")
public class IncorrectPasswordException extends Exception {

    private String userLogin;

    public IncorrectPasswordException(String userLogin) {
        super("Incorrect password for userLogin = " + userLogin);
        this.userLogin = userLogin;
    }

    public String getLoginName() {
        return userLogin;
    }

}