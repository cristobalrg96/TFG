package restserver.exceptions;

@SuppressWarnings("serial")
public class DuplicatedUserEmail extends Exception {

    private String userEmail;

    public DuplicatedUserEmail(String userEmail) {
        super("The email = " + userEmail + " already exists");
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
    
}
