package restserver.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@BatchSize(size=50)
public class User {
	
	private Long userId;
	private String userLogin;
	private String userEmail;
	private String userPassword;
	private String userPicture;
	private Date userCreationDate;
	private Boolean userPrivacy;
	
	public User(String userLogin, String userEmail, String userPassword, String userPicture, Date userCreationDate, Boolean userPrivacy) {
		this.userLogin = userLogin;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPicture = userPicture;
		this.userCreationDate = userCreationDate;
		this.userPrivacy = userPrivacy;
	}

	public User(String userLogin, String userEmail, String userPassword, String userPicture) {
		this.userLogin = userLogin;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPicture = userPicture;
	}
	
	public User() {
	}

	@SequenceGenerator( // It only takes effect for
	name = "UserIdGenerator", // databases providing identifier
	sequenceName = "UserIdSeq")
	// generators.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserIdGenerator")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@JsonIgnore //No se quiere enviar la contraseña
    @JsonProperty(value = "user_password") //Para no omitir la contraseña en el registro
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public Date getUserCreationDate() {
		return userCreationDate;
	}

	public void setUserCreationDate(Date userCreationDate) {
		this.userCreationDate = userCreationDate;
	}

	public Boolean getUserPrivacy() {
		return userPrivacy;
	}

	public void setUserPrivacy(Boolean userPrivacy) {
		this.userPrivacy = userPrivacy;
	}
	
}
