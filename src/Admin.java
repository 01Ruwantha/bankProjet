import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Admin extends User implements InterfaceUserAdmin,InterfaceAdminOnly{
    protected static int adminID;
    protected static String adminPhoneNumber;
    protected static int accountNumberAdmin;
    public void  signUp(){
        FileHandling.AccountFileCreate(Admin.accountNumberAdmin,"Mr: "+accountName,adminID,adminPhoneNumber);
    }
    public boolean signIn(){
                    boolean returnValue=false;
                    File myObj = new File("Admin_" + Admin.accountNumberAdmin + ".txt");
                    if (myObj.exists()) {
                        try {
                            Scanner myReader = new Scanner(myObj);
                            accountName = myReader.nextLine();
                            adminID = myReader.nextInt();
                            adminPhoneNumber = String.valueOf(myReader.nextInt());
                            returnValue=true;
                            myReader.close();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("The account file does not exist.");
                    }

        return returnValue;
    }
    public String[][]  allAccountReport(){
        int count=FileHandling.countAllAccountReport();
        String[][] allAccountReportArray=new String[2][count];
        FileHandling.getInfoInAllAccReportToArray(count,allAccountReportArray);
        return allAccountReportArray;
    }
    public  boolean  updateAccount(){
        boolean returnValue = FileHandling.updateAccount(User.accountNumber);
        return returnValue;

    }
    public boolean changePassword(){
        boolean returnValue=false;
        Admin.adminPhoneNumber=FileHandling.getPhoneNumber("null");
        System.out.println(adminPhoneNumber);
        boolean value =FileHandling.changePassword(Admin.accountNumberAdmin,"null");
        if(value){
            returnValue=true;
        }
        return returnValue;
    }
    public  boolean forgotPassword(){
        boolean returnValue=false;
        boolean value =FileHandling.changePassword(Admin.accountNumberAdmin,"null");
        if(value){
            returnValue=true;
        }
        return returnValue;
    }
    public boolean  deleteAccount(){
        boolean returnValue=false;
        File myObjAdmin = new File("Admin_" + Admin.accountNumberAdmin + ".txt");
        if (myObjAdmin.delete()) {
            System.out.println("Successfully deleted your account");
            returnValue=true;
        } else {
            System.out.println("!!! Error !!! - cannot deleted your account ");
        }
        return returnValue;
    }
    public String[] history(){

        int count=FileHandling.recodeCount( "nullValue");

        File myObj=new File("AdminDetails_"+String.valueOf(Admin.accountNumberAdmin)+".txt");

        String[] list = new String[count];

        int i=0;
        if (myObj.exists()) {
            try {
                Scanner myReader = new Scanner(myObj);
                String lineName = myReader.nextLine();
                while (myReader.hasNextLine()){
                    list[i]=myReader.nextLine();
                    ++i;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
