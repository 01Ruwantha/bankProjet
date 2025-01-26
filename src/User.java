import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class User extends Account implements InterfaceUserAdmin{
    public void  signUp(){
        FileHandling.AccountFileCreate(accountNumber,"Mr: "+accountName,userPassword,accountBalance,userPhoneNumber);
    }
    public boolean signIn(){
        boolean returnValue=false;
        File myObj = new File(String.valueOf(accountNumber)+".txt");
        if (myObj.exists()) {
            try {
                Scanner myReader = new Scanner(myObj);
                User.accountName = myReader.nextLine();
                User.userPassword = myReader.nextInt();
                User.accountBalance = myReader.nextDouble();
                User.userPhoneNumber = String.valueOf(myReader.nextInt());
                returnValue=true;
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("!!! ERROR-File dose not exist !!!");
                e.printStackTrace();
            }
        } else {
            System.out.println("The account file does not exist.");
        }
        return returnValue;
    }
    public boolean changePassword(){
        boolean returnValue=false;
        User.userPhoneNumber=FileHandling.getPhoneNumber();
        boolean value =FileHandling.changePassword(accountNumber);
        if(value){
            returnValue=true;
        }
        return returnValue;

    }
    public boolean forgotPassword(){
        boolean returnValue=false;
        boolean value =FileHandling.changePassword(accountNumber);
        if(value){
            returnValue=true;
        }
        return returnValue;
    }
    public boolean  deleteAccount(){
        boolean returnValue=false;
        File myObj = new File(String.valueOf(accountNumber)+".txt");
        if (myObj.delete()) {
            System.out.println("Successfully deleted your account");
            returnValue=true;
        } else {
            System.out.println("!!! Error !!! - cannot deleted your account ");
        }
        return returnValue;
    }
    public String[] history(){
        int count = FileHandling.recodeCount();

        File myObj=new File(String.valueOf(User.accountNumber)+"_Details"+".txt");

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
