package user;

public class UserBase implements Iuser {
    private String id;
    private String username;
    private String password;
    private boolean active;
public UserBase(String id, String username, String password) {
        setId(id);
        setUsername(username);
        setPassword(password);
        this.active = true;
    }
@Override
    public String getID() {
        return id;
    }

@Override
    public String getUserName() {
        return username;
    }

@Override
    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }
public void setId(String id) {
        if (isBlank(id))
            this.id = "UNKNOWN";
        else
            this.id = id.trim();
    }
public void setUsername(String username) {
        if (isBlank(username))
            this.username = "user_" + id;
        else
            this.username = username.trim();
    }
public void setPassword(String password) {
        String pw = (password == null) ? "" : password;

        if (pw.length() < 4)
            this.password = "0000";
        else
            this.password = pw;
    }
public void setActive(boolean active) {
        this.active = active;
    }
public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }
@Override
    public boolean can(String action) {
        return false;
    }
@Override
    public String getRole() {
        return "User";
    }
private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
@Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", active=" + active +
                '}';
    }
}
