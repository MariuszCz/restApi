package pl.edu.uam.restapi.storage.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User")
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;


    public User() {
    }

    public void setId(String id) {
        this.id = id;
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


    public User(String id, String firstName, String lastName, String email, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    @ApiModelProperty(value = "User id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "User first name", required = true)
    public String getFirstName() {
        return firstName;
    }

    @ApiModelProperty(value = "User last name", required = true)
    public String getLastName() {
        return lastName;
    }

    @ApiModelProperty(value = "User email", required = true)
    public String getEmail() {
        return email;
    }

    @ApiModelProperty(value = "User login", required = true)
    public String getLogin() {
        return login;
    }

    @ApiModelProperty(value = "User password", required = true)
    public String getPassword() {
        return password;
    }

}
