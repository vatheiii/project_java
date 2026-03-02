public abstract class UserBase implements Iuser {
    private String ID;
    private String userName;
    private String password;
    public UserBase(String ID, String userName, String password) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
    }
    public String getID() {
        return ID;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public abstract String getRole();
    public abstract boolean can(String action);
    
}
