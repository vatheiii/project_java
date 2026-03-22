package user;

@FunctionalInterface
public interface UserFilter {
    boolean match (Iuser user);
}