public interface InterfaceUserAdmin extends InterfaceAccount{
    void  signUp();
    boolean signIn();
    boolean changePassword();
    boolean forgotPassword();
    boolean  deleteAccount();
    String[] history();
}
