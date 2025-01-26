import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Account implements InterfaceAccount{
    protected static int userPassword;
    protected static int accountNumber;
    protected static Double accountBalance;
    protected static String accountName;
    protected static String userPhoneNumber;
    public boolean cashWithdraw(int withdrawAmount){
        boolean valueBoolean=false;

        FileHandling.fileWriteDetail( User.accountNumber,User.accountName,User.userPassword,User.accountBalance);

        File myObj2=new File(String.valueOf(accountNumber)+"_Details"+".txt");
        if(myObj2.exists()){
            try {
                FileWriter writerObj2=new FileWriter(myObj2,true);
                writerObj2.append("\n"+"Withdraw amount : "+withdrawAmount);
                writerObj2.close();
                valueBoolean=true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Couldn't write in detail file about withdraw");
        }

        return valueBoolean;
    }
    public boolean  cashDeposit(Double dipositeAmount){
        boolean returnValue=false;
        double DepositAmount=dipositeAmount;
        dipositeAmount+=accountBalance;
        User.accountBalance=dipositeAmount;//new account Balance
        System.out.println("Your new balance is :"+dipositeAmount);
        FileHandling.fileWriteDetail( accountNumber,accountName,User.userPassword,dipositeAmount);

        File myObj2=new File(String.valueOf(accountNumber)+"_Details"+".txt");
        if(myObj2.exists()){
            try {
                FileWriter writerObj2=new FileWriter(myObj2,true);
                writerObj2.append("\n"+"Deposit amount : "+DepositAmount);
                writerObj2.close();
                returnValue=true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Couldn't write in detail file");
        }
        return returnValue;
    }
    public int accountNumberGenerate(){
        int currentAccountCount=FileHandling.countAllAccountReport();
         int newAccNum;
         newAccNum = currentAccountCount + 1;

        return newAccNum;
    }
    public int accountNumberGenerate(String nullValue){
        int currentAccountCount=FileHandling.countAllAdminAccountReport();
        int newAccNum;
        newAccNum = currentAccountCount + 1;

        return newAccNum;
    }
}
