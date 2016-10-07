package backend;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * The class contains user's email, password and type(admin/client) information.
 */

public class UserID implements Serializable {

    private static final long serialVersionUID = 12L;

    private String email;
    private String password;
    private String type;

    /**
     * Constructor.
     *
     * @param email user's email.
     * @param password user's password.
     * @param type user's type of account (admin/client).
     */
    public UserID(String email, String password, String type ) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    //Getters and Setters.

    /**
     * Return user's email.
     * @return user's email in string.
     */

    public String getEmail() {
        return email;
    }

    /**
     * Return user's password.
     * @return user's password in string.
     */

    public String getPassword() {
        return password;
    }

    /**
     * Change user's password.
     *
     * @param password user's new password in string.
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Change user's email.
     *
     * @param email user's new email in string.
     */

    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Return user's type of account
     *
     * @return user's type of account. (1 if admin, 2 if client)
     */
    public String getType() {
        return type;
    }

}
