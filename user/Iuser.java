package user;
public interface Iuser {
    String getID();
    String getUserName();
    String getPassword();
    String getRole();
    boolean can(String action);
    
}
