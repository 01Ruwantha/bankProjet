import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
public class FileHandling {
    private InterfaceAccount interAccountObj=new Account();
    private InterfaceSecurity interSecurityObj=new Security();
    protected static void fileWriteDetail(int accountNumber, String accountName, int userPassword, Double accountBalance) {
       File myObj=new File(String.valueOf(accountNumber+".txt"));
       if (myObj.exists()) {
           try{
               FileWriter writerObj=new FileWriter(myObj);
               writerObj.write(accountName + "\n" );
               writerObj.write(userPassword + "\n" );
               writerObj.write(accountBalance+ "\n" );
               writerObj.write(User.userPhoneNumber);
               writerObj.close();
               System.out.println("Saved Information");
           }
           catch(IOException e){
               System.out.println("!!! Error !!!-cannot update accout file");
               e.printStackTrace();
           }
       }
   }
    private static void fileWriteDetail(int accountNumber, String accountName, int adminID) {
        File myObj=new File("Admin_" + accountNumber + ".txt");
        if (myObj.exists()) {
            try{
                FileWriter writerObj=new FileWriter(myObj);
                writerObj.write(accountName + "\n" );
                writerObj.write(adminID + "\n" );
                writerObj.write(Admin.adminPhoneNumber);
                writerObj.close();
                System.out.println("Saved Information");
            }
            catch(IOException e){
                System.out.println("!!! Error !!!-cannot update accout file");
                e.printStackTrace();
            }
        }
    }
    protected static void AccountFileCreate(int accountNumber,String accountName,int userPassword,Double accountBalance,String userPhoneNumber) {
        try {
            File myObj = new File(String.valueOf(accountNumber)+".txt");
            if (myObj.createNewFile()) {
                FileWriter writerObj=new FileWriter(myObj);
                writerObj.write(accountName + "\n" + userPassword + "\n" + accountBalance+ "\n" +userPhoneNumber);
                writerObj.close();

                File myObjForSaveDetail=new File("allAcountReport.txt");
                FileWriter myWriteForSaveDetail=new FileWriter(myObjForSaveDetail,true);

                myWriteForSaveDetail.append("\n"+String.valueOf(accountNumber)+"-"+accountName);

                myWriteForSaveDetail.close();
                System.out.println("New account created & Saved Information");

            }else {
                System.out.println("Account file already exist");
            }
            //create file detail
                File myObj2=new File(String.valueOf(accountNumber)+"_Details"+".txt");
                if(myObj2.createNewFile()){
                    System.out.println("New detail file created");
                    FileWriter writerObj2=new FileWriter(myObj2);
                    writerObj2.write("User name - "+accountName+"\n"+"Deposit amount : "+accountBalance);
                    writerObj2.close();
                }else {
                    System.out.println("Couldn't create new detail file");
                }
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
   }
    protected static void AccountFileCreate(int accountNumber,String accountName,int adminID,String adminPhoneNumber) {
        try {
            File myObj = new File("Admin_"+accountNumber+".txt");
            if (myObj.createNewFile()) {
                FileWriter writerObj=new FileWriter(myObj);
                writerObj.write(accountName + "\n" + adminID + "\n" + adminPhoneNumber);
                writerObj.close();
                File myObjForSaveDetail=new File("allAdminAccReport.txt");
                FileWriter myWriteForSaveDetail=new FileWriter(myObjForSaveDetail,true);

                myWriteForSaveDetail.append("\n"+accountNumber+"-"+accountName);

                myWriteForSaveDetail.close();
                System.out.println("New account created & Saved Information");

            }else {
                System.out.println("Account file already exist");
            }
            //create file detail
            File myObj2=new File("AdminDetails_"+String.valueOf(accountNumber)+".txt");
            if(myObj2.createNewFile()){
                System.out.println("New detail file created");
                FileWriter writerObj2=new FileWriter(myObj2);
                writerObj2.write("Admin name - "+accountName);
                writerObj2.close();
            }else {
                System.out.println("Couldn't create new detail file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    protected static String[][] getInfoInAllAccReportToArray(int count,String[][] allAccountReportArray){
        String[] value=new String[count];
        String[] name=new String[count];
        int i=0;
        File myObj=new File("allAcountReport.txt");
        if (myObj.exists()) {
            try {
                Scanner inputF=new Scanner(myObj);
                while (inputF.hasNextLine()){
                    String Line=inputF.nextLine();
                    String[] parts=Line.split(":");
                    value[i]=parts[0].split("-")[0];
                    name[i]=parts[1];
                    for(int k=0;k<2;++k) {
                        if(k==0) {
                            allAccountReportArray[k][i]=value[i];
                        }
                        else {
                            allAccountReportArray[k][i]=name[i];
                        }
                    }
                    ++i;
                }
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return allAccountReportArray;
   }
    protected static String[][] getInfoInAllAccReportToArray(int count,String[][] allAccountReportArray,String nullValue){
        String[] value=new String[count];
        String[] name=new String[count];
        int i=0;
        File myObj=new File("allAdminAccReport.txt");
        if (myObj.exists()) {
            try {
                Scanner inputF=new Scanner(myObj);
                while (inputF.hasNextLine()){
                    String Line=inputF.nextLine();
                    String[] parts=Line.split(":");
                    value[i]=parts[0].split("-")[0];
                    name[i]=parts[1];
                    for(int k=0;k<2;++k) {
                        if(k==0) {
                            allAccountReportArray[k][i]=value[i];
                        }
                        else {
                            allAccountReportArray[k][i]=name[i];
                        }
                    }
                    ++i;
                }
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return allAccountReportArray;
    }
    protected static int countAllAccountReport(){
        File myObj = new File("allAcountReport.txt");
        try {
            Scanner inputF = new Scanner(myObj);
            int count=0;
            while (inputF.hasNextLine()){
                inputF.nextLine();
                count++;
            }
            inputF.close();
            return count;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected static int countAllAdminAccountReport(){
        File myObj = new File("allAdminAccReport.txt");
        try {
            Scanner inputF = new Scanner(myObj);
            int count=0;
            while (inputF.hasNextLine()){
                inputF.nextLine();
                count++;
            }
            inputF.close();
            return count;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected static int getPin(int accountNumber) {
        File myObj = new File(String.valueOf(accountNumber+".txt"));
        String linePin = String.valueOf(0);
        if (myObj.exists()){
            try {
                Scanner inputF = new Scanner(myObj);

                String lineName = inputF.nextLine();
                linePin = inputF.nextLine();
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return Integer.valueOf(linePin);
    }
    protected static int getPin(int accountNumber,String nullValue) {
        File myObj = new File(String.valueOf("Admin_" + accountNumber + ".txt"));
        int linePin = 0;
        if (myObj.exists()){
            try {
                Scanner inputF = new Scanner(myObj);

                String lineName = inputF.nextLine();
                linePin = inputF.nextInt();
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return linePin;
    }
    protected static String getPhoneNumber(){
        File myObj = new File(String.valueOf(User.accountNumber)+".txt");
        String linePhoneNumber = String.valueOf(0);
        if (myObj.exists()){
            try {
                Scanner inputF = new Scanner(myObj);

                String lineName = inputF.nextLine();
                String linePin = inputF.nextLine();
                String balance = inputF.nextLine();
                linePhoneNumber = String.valueOf(inputF.nextInt());
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("File does not exist");
        }
       return linePhoneNumber;
    }
    protected static String getPhoneNumber(String nullValue){
        File myObj = new File("Admin_" + Admin.accountNumberAdmin + ".txt");
        String linePhoneNumber = String.valueOf(0);
        if (myObj.exists()){
            try {
                Scanner inputF = new Scanner(myObj);

                String lineName = inputF.nextLine();
                String linePin = inputF.nextLine();
                linePhoneNumber =  String.valueOf(inputF.nextInt());
                inputF.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("File does not exist");
        }
        return linePhoneNumber;
    }
    protected static boolean changePassword(int accountNumber){
        boolean returnValue=false;
        File myObj = new File(String.valueOf(accountNumber+".txt"));
        if (myObj.exists()) {
            try {
                Scanner inputF=new Scanner(myObj);

                String accountName=inputF.nextLine();
                int userPassword=inputF.nextInt();
                Double accountBalance=inputF.nextDouble();
                inputF.close();
                FileHandling.fileWriteDetail(accountNumber,accountName,User.userPassword,accountBalance);
                returnValue=true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnValue;
    }
    protected static boolean changePassword(int accountNumber,String nullValue){
        boolean returnValue=false;
        File myObj = new File("Admin_" + accountNumber + ".txt");
        if (myObj.exists()) {
            try {
                Scanner inputF=new Scanner(myObj);

                String accountName=inputF.nextLine();
                int userPassword=inputF.nextInt();
                inputF.close();
                FileHandling.fileWriteDetail(accountNumber,accountName,Admin.adminID);
                returnValue=true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnValue;
    }
    protected static boolean updateAccount(int updateUserAccountNumber){
        boolean returnValue=false;
        File myObj = new File(String.valueOf(updateUserAccountNumber+".txt"));
        if(myObj.exists()){
            try {
                Scanner inputF=new Scanner(myObj);
                String lineName = inputF.nextLine();
                int linePin = inputF.nextInt();
                Double balance = inputF.nextDouble();
                String linePhoneNumber = String.valueOf(inputF.nextInt());//does not use this one
                inputF.close();
                FileHandling.fileWriteDetail(updateUserAccountNumber,lineName,linePin,balance);

                File myObj2=new File("AdminDetails_"+String.valueOf(Admin.accountNumberAdmin)+".txt");
                if(myObj2.exists()){
                    try {
                        FileWriter writerObj2=new FileWriter(myObj2,true);
                        writerObj2.append("\n"+lineName+"-"+updateUserAccountNumber+"-"+linePhoneNumber+"-"+User.userPhoneNumber);
                        writerObj2.close();
                        returnValue=true;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }else {
                    System.out.println("Couldn't write in detail file");
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return returnValue;
    }
    public static int recodeCount(String nullValue){
        int count=0;
        File myObj=new File("AdminDetails_"+String.valueOf(Admin.accountNumberAdmin)+".txt");
        if (myObj.exists()) {
            try {
                Scanner myReader = new Scanner(myObj);
                String lineName = myReader.nextLine();
                while (myReader.hasNextLine()){
                    ++count;
                    myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("cannot open file");
        }
        return count;
    }
    public static int recodeCount(){
        int count=0;
        File myObj=new File(String.valueOf(User.accountNumber)+"_Details"+".txt");
        if (myObj.exists()) {
            try {
                Scanner myReader = new Scanner(myObj);
                String lineName = myReader.nextLine();
                while (myReader.hasNextLine()){
                    myReader.nextLine();
                    ++count;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return count;
    }
}
