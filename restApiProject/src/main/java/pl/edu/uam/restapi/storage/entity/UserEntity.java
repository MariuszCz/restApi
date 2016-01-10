package pl.edu.uam.restapi.storage.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "users.findAll", query = "SELECT u FROM UserEntity u")
})
public class UserEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Pattern(regexp = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", message = "{wrong.email}")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "[a-z]*") @Size(min = 4 , max = 20)
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;


    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public UserEntity() {
    }

    public UserEntity(String firstName, String lastName, String email, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("login", login)
                .add("password", password)
                .toString();
    }
}
