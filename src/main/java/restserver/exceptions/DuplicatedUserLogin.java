package restserver.exceptions;

@SuppressWarnings("serial")
public class DuplicatedUserLogin extends Exception {

    private String userLogin;

    public DuplicatedUserLogin(String userLogin) {
        super("The login = " + userLogin + " already exists");
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

}