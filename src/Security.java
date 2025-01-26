public class Security implements InterfaceSecurity{
    public boolean checkAccount(int accountNumber){
         boolean value=false;
         int count=FileHandling.countAllAccountReport();
         String[][] allAccountReportArray=new String[2][count];
        FileHandling.getInfoInAllAccReportToArray(count,allAccountReportArray);
         for(int j=0;j<count;++j){
                 if(accountNumber == Integer.valueOf(allAccountReportArray[0][j])){
                     value=true;
                 }
         }
         return value;
    }
    public boolean checkAccount(int accountNumber,String nullValue){
        boolean value=false;
        int count=FileHandling.countAllAdminAccountReport();
        String[][] allAdminAccReportArray=new String[2][count];
        FileHandling.getInfoInAllAccReportToArray(count,allAdminAccReportArray,"null");
        for(int j=0;j<count;++j){
            if(accountNumber == Integer.valueOf(allAdminAccReportArray[0][j])){
                value=true;
            }
        }
        return value;
    }
    public boolean  checkPassword(int accountNumber,int userPassword){
        Boolean passwordValue=false;
        int pin=FileHandling.getPin(accountNumber);
        if(pin==userPassword){
            passwordValue=true;
            return passwordValue;
        }
        else{
            return passwordValue;
        }
    }
    public boolean  checkPassword(int accountNumber,int adminID,String nullValue){
        Boolean passwordValue=false;
        int pin=FileHandling.getPin(accountNumber,"null");
        if(pin==adminID){
            passwordValue=true;
        }
        return passwordValue;
    }
    public boolean  checkPhoneNumber(){
        Boolean passwordValue=false;
        String pin=FileHandling.getPhoneNumber();
        if(pin.compareTo(User.userPhoneNumber)==0){
            passwordValue=true;
            return passwordValue;
        }
        else{
            return passwordValue;
        }
    }
    public boolean  checkPhoneNumber(String nullValue){
        Boolean passwordValue=false;
        String pin=FileHandling.getPhoneNumber("null");
        if(pin.compareTo(Admin.adminPhoneNumber)==0){
            passwordValue=true;
            return passwordValue;
        }
        else{
            return passwordValue;
        }
    }
}
