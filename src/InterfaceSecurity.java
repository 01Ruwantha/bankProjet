public interface InterfaceSecurity {
    boolean checkAccount(int accountNumber);
    boolean checkAccount(int accountNumber,String nullValue);
    boolean  checkPassword(int accountNumber,int userPassword);
    boolean  checkPassword(int accountNumber,int adminID,String nullValue);
    boolean  checkPhoneNumber();
    boolean  checkPhoneNumber(String nullValue);

}
