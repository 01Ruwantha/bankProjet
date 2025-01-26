import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdminPages {
    private final static InterfaceAdminOnly interfaceAdminObj=new Admin();
    private final static InterfaceUserAdmin interAdminObj=new Admin();
    private final InterfaceSecurity interSecurityObj=new Security();
    private final InterfaceAccount interAccountObj=new Account();
    private static int AdminIdErrorCount=0;
    public void SignInAdminWindow(){

        JFrame frame =new JFrame();
        frame.setTitle("Bank Sign In Admin Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign In As A Admin");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,350,35);


        JLabel label1=new JLabel("Enter Admin Account Number :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,100,400,25);

        JTextField textField1 =new JTextField();
        textField1.setPreferredSize(new Dimension(250,40));
        textField1.setBounds(50,150,500,35);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter AdminID :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,200,400,25);

        JPasswordField textField2 =new JPasswordField();
        textField2.setPreferredSize(new Dimension(250,40));
        textField2.setBounds(50,250,500,35);
        textField2.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Submit");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(400,350,150,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    Boolean AccountValue=false;
                    Boolean AdminIdValue=false;
                    if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter Admin Account Number : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (!textField1.getText().trim().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter Admin Account Number : \" ", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            boolean valueAccount = interSecurityObj.checkAccount(Integer.parseInt(textField1.getText().trim()), "null");
                            if(valueAccount){
                                AccountValue=true;
                                Admin.accountNumberAdmin=Integer.parseInt(textField1.getText().trim());

                                if (textField2.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter AdminID : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                }else {
                                    if (!textField2.getText().matches("^[0-9]+$")) {
                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter AdminID : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        boolean valuePassword = interSecurityObj.checkPassword(Integer.parseInt(textField1.getText().trim()),Integer.parseInt( textField2.getText()), "null");
                                        if(valuePassword){
                                            AdminIdValue=true;
                                        }else {
                                            ++AdminIdErrorCount;
                                            if(AdminIdErrorCount<3) {
                                                JOptionPane.showMessageDialog(frame, "Invalid adminId" + "\nYou have " + (3 - AdminIdErrorCount) + " times remaining", "Warning", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    }
                                }

                            }else {
                                JOptionPane.showMessageDialog(frame, "Invalid account number", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    if(AdminIdErrorCount<3){
                        if (AdminIdValue == true && AccountValue == true) {
                            if (interAdminObj.signIn()) {
                                ImageIcon iconTick=new ImageIcon("tick.jpg");

                                JOptionPane.showOptionDialog(frame,
                                        "Sign In Successful",
                                        "info",
                                        JOptionPane.CLOSED_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        iconTick,
                                        null,
                                        0);
                                frame.dispose();
                                AdminPages adminPages = new AdminPages();
                                adminPages.AdminWindow();
                            }else {
                                JOptionPane.showMessageDialog(frame, "signIn process failed", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else {
                        frame.dispose();
                        AdminPages adminPages=new AdminPages();
                        adminPages.SignInAdminForgotAdminIdWindow();
                    }
                }
        );

        JButton button2= new JButton();
        button2.setText("Back");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.black);
        button2.setFocusable(false);
        button2.setBounds(50,350,150,40);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages launchPages=new LaunchPages();
                    launchPages.SignInWindow();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label0);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button2);
        panel.add(button1);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public void SignUpAdminWindow(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Sign Up Admin Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign Up As A Admin");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,350,40);


        JLabel label1=new JLabel("Enter your full name :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,60,400,30);

        JTextField textField1 =new JTextField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,90,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter your phone number :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,130,400,30);

        JTextField textField2 =new JTextField();
        textField2.setPreferredSize(new Dimension(250,30));
        textField2.setBounds(50,160,500,30);
        textField2.setBackground(Color.LIGHT_GRAY);

        JLabel label3=new JLabel("Enter new AdminId :");
        label3.setFont(new Font("Arial",Font.PLAIN,20));
        label3.setBounds(50,200,400,30);

        JPasswordField textField3 =new JPasswordField();
        textField3.setPreferredSize(new Dimension(250,30));
        textField3.setBounds(50,230,500,30);
        textField3.setBackground(Color.LIGHT_GRAY);

        JLabel label4=new JLabel("Re-Enter AdminId :");
        label4.setFont(new Font("Arial",Font.PLAIN,20));
        label4.setBounds(50,270,400,30);

        JPasswordField textField4 =new JPasswordField();
        textField4.setPreferredSize(new Dimension(250,30));
        textField4.setBounds(50,300,500,30);
        textField4.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Submit");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(300,370,150,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"Please enter string value for \" Enter your full name : \" "+"\nYou can only use [ \"a-z\" , \"A-Z\" , \"  \" , \".\" ]"+"\nDon\'t use other symbols ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (!textField1.getText().trim().matches("^[a-zA-Z .]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter string value for \" Enter your full name : \" "+"\nYou can only use [ \"a-z\" , \"A-Z\" , \"  \" , \".\" ]"+"\nDon\'t use other symbols ", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            Admin.accountName=textField1.getText().trim().toUpperCase();
                            if (textField2.getText().isEmpty()){
                                JOptionPane.showMessageDialog(frame,"Please enter nine digits for \" Enter your phone number : \" "+"\nDon\'t use other symbols "+"\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]"+"Must include space ","Info",JOptionPane.INFORMATION_MESSAGE);
                            }else {
                                if(textField2.getText().length()!=9){
                                    JOptionPane.showMessageDialog(frame, "Only nine digits\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                }else {
                                    if (!textField2.getText().trim().matches("^[0-9]+$")) {
                                        JOptionPane.showMessageDialog(frame, "Invalid phone number \n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Invalid", JOptionPane.ERROR_MESSAGE);

                                    } else {
                                        if(!(textField2.getText().substring(0, 1).equals("7")) ){
                                            JOptionPane.showMessageDialog(frame, "First digit must be 7\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                        }else{
                                            if (!(textField2.getText().substring(1, 2).equals("0")) && !(textField2.getText().substring(1, 2).equals("1")) && !(textField2.getText().substring(1, 2).equals("2")) &&!(textField2.getText().substring(1, 2).equals("4")) && !(textField2.getText().substring(1, 2).equals("6")) && !(textField2.getText().substring(1, 2).equals("7")) && !(textField2.getText().substring(1, 2).equals("8"))) {
                                                JOptionPane.showMessageDialog(frame, "Second digit must be [ \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                            } else {
                                                Admin.adminPhoneNumber=textField2.getText();
                                                if(textField3.getText().isEmpty()){
                                                    JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter new AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                }else {
                                                    if (!textField3.getText().matches("^[0-9]+$")) {
                                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else {
                                                        String NewAdminId=textField3.getText();
                                                        if(textField4.getText().isEmpty()){
                                                            JOptionPane.showMessageDialog(null,"Please enter integer value for \" Re-Enter AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                        }else{
                                                            if (!textField4.getText().matches("^[0-9]+$")) {
                                                                JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                            }else{
                                                                String ReEnteredAdminId=textField4.getText();

                                                                if(!NewAdminId.equals(ReEnteredAdminId)){
                                                                    JOptionPane.showMessageDialog(frame, "Invalid AdminId\n\nAdminId didn\'t match to Re-Entered AdminId ", "Invalid", JOptionPane.ERROR_MESSAGE);
                                                                }else{
                                                                    Admin.adminID= Integer.parseInt(NewAdminId);
                                                                    Admin.accountNumberAdmin=interAccountObj.accountNumberGenerate("null");
                                                                    JOptionPane.showMessageDialog(frame,"Your admin account number is :  "+Admin.accountNumberAdmin,"Info",JOptionPane.INFORMATION_MESSAGE);

                                                                    interAdminObj.signUp();
                                                                    frame.dispose();

                                                                    ImageIcon iconTick=new ImageIcon("tick.jpg");

                                                                    JOptionPane.showOptionDialog(frame,
                                                                            "Successfully create Admin Account",
                                                                            "info",
                                                                            JOptionPane.CLOSED_OPTION,
                                                                            JOptionPane.INFORMATION_MESSAGE,
                                                                            iconTick,
                                                                            null,
                                                                            0);

                                                                    LaunchPages launchPages=new LaunchPages();
                                                                    launchPages.MainWindow();
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                        }
                    }
                }
        );

        JButton button2= new JButton();
        button2.setText("Back");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.black);
        button2.setFocusable(false);
        button2.setBounds(150,370,150,40);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages launchPages=new LaunchPages();
                    launchPages.SignUpWindow();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label0);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(label4);
        panel.add(textField4);
        panel.add(button1);
        panel.add(button2);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public void SignUpAdminLoginWindow(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Sign Up Admin Login Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign Up Admin Login");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,350,35);


        JLabel label1=new JLabel("Enter Admin Username :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,100,400,25);

        JPasswordField textField1 =new JPasswordField();
        textField1.setPreferredSize(new Dimension(250,40));
        textField1.setBounds(50,150,500,35);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter Admin Login Password :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,200,400,25);

        JPasswordField textField2 =new JPasswordField();
        textField2.setPreferredSize(new Dimension(250,40));
        textField2.setBounds(50,250,500,35);
        textField2.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Confirm");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(200,350,200,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    if(textField1.getText().isEmpty()){
                        JOptionPane.showMessageDialog(frame,"Please enter string value for \" Enter Admin Username : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        String adminUsername=textField1.getText();
                        if(textField2.getText().isEmpty()){
                            JOptionPane.showMessageDialog(frame,"Please enter string value for \" Enter Admin Login Password : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            String adminLoginPassword=textField2.getText();
                            if(!(adminUsername.equals("Admin")&&adminLoginPassword.equals("root"))){
                                JOptionPane.showMessageDialog(frame,"Invalid Admin Username\nInvalid Admin Login Password","Invalid",JOptionPane.ERROR_MESSAGE);
                                frame.dispose();
                                LaunchPages launchPages=new LaunchPages();
                                launchPages.SignUpWindow();
                            }else{
                                frame.dispose();
                                AdminPages adminPages=new AdminPages();
                                adminPages.SignUpAdminWindow();
                            }
                        }
                    }
                }
        );


        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label0);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(button1);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public void SignInAdminForgotAdminIdWindow(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank SignIn Admin Forgot AdminId Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign In Admin Forgot AdminId");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(75,0,450,35);

        JButton button1= new JButton();
        button1.setText("Back");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(175,255,250,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages launchPages=new LaunchPages();
                    launchPages.SignInWindow();
                }
        );

        JButton button2= new JButton();
        button2.setText("Forgot adminID");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.black);
        button2.setFocusable(false);
        button2.setBounds(130,195,345,40);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    AdminPages adminPages=new AdminPages();
                    adminPages.ForgotAdminId();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label0);
        panel.add(button1);
        panel.add(button2);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    private static int errorCountForgotAdminId=0;
    public void ForgotAdminId(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Forgot AdminId Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Forgot AdminId");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,250,40);

        JLabel label1=new JLabel("Enter your phone number :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,100,400,30);

        JPasswordField textField1 =new JPasswordField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,140,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter new AdminId :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,180,400,30);

        JPasswordField textField2 =new JPasswordField();
        textField2.setPreferredSize(new Dimension(250,30));
        textField2.setBounds(50,220,500,30);
        textField2.setBackground(Color.LIGHT_GRAY);

        JLabel label3=new JLabel("Re-Enter AdminId :");
        label3.setFont(new Font("Arial",Font.PLAIN,20));
        label3.setBounds(50,260,400,30);

        JPasswordField textField3 =new JPasswordField();
        textField3.setPreferredSize(new Dimension(250,30));
        textField3.setBounds(50,300,500,30);
        textField3.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Confirm");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(200,370,200,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    boolean valueAccount = interSecurityObj.checkAccount(Admin.accountNumberAdmin, "null");
                    if (!valueAccount){
                        JOptionPane.showMessageDialog(frame, "Invalid account number", "Error", JOptionPane.ERROR_MESSAGE);
                        frame.dispose();
                    }else  {
                        if (textField1.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(frame, "Please enter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]" + "Must include space ", "Info", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (textField1.getText().length() != 9) {
                                JOptionPane.showMessageDialog(frame, "Only nine digits\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                if (!textField1.getText().trim().matches("^[0-9]+$")) {
                                    JOptionPane.showMessageDialog(frame, "Invalid phone number \n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Invalid", JOptionPane.ERROR_MESSAGE);

                                } else {
                                    if (!(textField1.getText().substring(0, 1).equals("7"))) {
                                        JOptionPane.showMessageDialog(frame, "First digit must be 7\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (!(textField1.getText().substring(1, 2).equals("0")) && !(textField1.getText().substring(1, 2).equals("1")) && !(textField1.getText().substring(1, 2).equals("2")) && !(textField1.getText().substring(1, 2).equals("4")) && !(textField1.getText().substring(1, 2).equals("6")) && !(textField1.getText().substring(1, 2).equals("7")) && !(textField1.getText().substring(1, 2).equals("8"))) {
                                            JOptionPane.showMessageDialog(frame, "Second digit must be [ \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            Admin.adminPhoneNumber = textField1.getText();
                                            boolean valuePassword = interSecurityObj.checkPhoneNumber("null");
                                            if (!valuePassword) {
                                                ++errorCountForgotAdminId;

                                                if(errorCountForgotAdminId<3) {
                                                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number"+"\nYou have "+(3-errorCountForgotAdminId)+" times remaining"+"\n\nEntered phone number didn\'t match with Account phone number", "Warning", JOptionPane.WARNING_MESSAGE);
                                                }else{
                                                    frame.dispose();
                                                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number\n\nMeet the Programmer Developer to change Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
                                                    LaunchPages launchPages=new LaunchPages();
                                                    launchPages.SignInWindow();
                                                }
                                            }else{
                                                if(textField2.getText().isEmpty()){
                                                    JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter new AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                }else {
                                                    if (!textField2.getText().matches("^[0-9]+$")) {
                                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else{
                                                        String NewAdminId2=textField2.getText();

                                                        if(textField3.getText().isEmpty()){
                                                            JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Re-Enter AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                        }else{
                                                            if (!textField3.getText().matches("^[0-9]+$")) {
                                                                JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                            }else{
                                                                String ReEnteredAdminId2=textField3.getText();

                                                                if(NewAdminId2.equals(ReEnteredAdminId2)){
                                                                    Admin.adminID= Integer.parseInt(NewAdminId2);
                                                                    Admin admin=new Admin();
                                                                    boolean value=admin.forgotPassword();
                                                                    if(value){

                                                                        ImageIcon iconTick=new ImageIcon("tick.jpg");

                                                                        JOptionPane.showOptionDialog(frame,
                                                                                "Successfully change adminId",
                                                                                "info",
                                                                                JOptionPane.CLOSED_OPTION,
                                                                                JOptionPane.INFORMATION_MESSAGE,
                                                                                iconTick,
                                                                                null,
                                                                                0);
                                                                        JOptionPane.showMessageDialog(frame, "You will automatically logout via System\nPlease log in after this", "Alert", JOptionPane.CLOSED_OPTION);

                                                                    }else {
                                                                        JOptionPane.showMessageDialog(frame, "Could not change adminId", "Error", JOptionPane.ERROR_MESSAGE);

                                                                    }
                                                                    frame.dispose();
                                                                    System.exit(0);

                                                                }else{
                                                                    JOptionPane.showMessageDialog(frame, "Invalid AdminId\n\nAdminId didn\'t match to Re-Entered AdminId ", "Invalid", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label0);
        panel.add(label1);
        panel.add(textField1);
        panel.add(label2);
        panel.add(textField2);
        panel.add(label3);
        panel.add(textField3);
        panel.add(button1);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
    public void AdminWindow(){

        JFrame frame =new JFrame();
        frame.setTitle("Bank Admin Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());


        ImageIcon image2=new ImageIcon("Bank.png");
        ImageIcon image3=new ImageIcon("UserAdmin.png");
        JLabel label=new JLabel();
        label.setIcon(image2);
        label.setBounds(275,125,250,250);

        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setIcon(image2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setIcon(image3);
            }
        });

        JLabel label1=new JLabel();
        label1.setText("Welcome "+Admin.accountName);
        label1.setFont(new Font("Cambria",Font.PLAIN,25));
        label1.setBounds(0,0,620,30);

        JMenuBar menuBar = new JMenuBar();

        JMenu accDetails = new JMenu("Users Account Details");
        JMenu userEdit = new JMenu("User Edit");
        JMenu adminEdit = new JMenu("Admin Edit");
        JMenu history = new JMenu("History");
        JMenu exit = new JMenu("Exit");

        accDetails.setMnemonic(KeyEvent.VK_A);
        userEdit.setMnemonic(KeyEvent.VK_U);
        adminEdit.setMnemonic(KeyEvent.VK_E);
        history.setMnemonic(KeyEvent.VK_H);
        exit.setMnemonic(KeyEvent.VK_F4);

        JMenuItem readAllAccDetailsItem = new JMenuItem("READ ALL ACCOUNT DETAILS REPORT");
        JMenuItem updateUserAccPhoneNumberItem = new JMenuItem("UPDATE USER ACCOUNT PHONE NUMBER");
        JMenuItem changeAdminIDItem = new JMenuItem("CHANGE adminID");
        JMenuItem deleteAccountItem = new JMenuItem("DELETE ACCOUNT");
        JMenuItem historyItem = new JMenuItem("HISTORY REPORT");
        JMenuItem exitItem = new JMenuItem("EXIT");

        ImageIcon Icon1 = new ImageIcon("Icon//readAllAccountDetails.png");
        readAllAccDetailsItem.setIcon(Icon1);

        ImageIcon Icon2 = new ImageIcon("Icon//updatePhoneNumber.png");
        updateUserAccPhoneNumberItem.setIcon(Icon2);

        ImageIcon Icon3 = new ImageIcon("Icon//passwordChange.png");
        changeAdminIDItem.setIcon(Icon3);

        ImageIcon Icon4 = new ImageIcon("Icon//AccDelete.png");
        deleteAccountItem.setIcon(Icon4);

        ImageIcon Icon5 = new ImageIcon("Icon//history.png");
        historyItem.setIcon(Icon5);

        ImageIcon Icon6 = new ImageIcon("Icon//exit.png");
        exitItem.setIcon(Icon6);

        readAllAccDetailsItem.setMnemonic( KeyEvent.VK_R);
        updateUserAccPhoneNumberItem.setMnemonic(KeyEvent.VK_U);
        changeAdminIDItem.setMnemonic(KeyEvent.VK_C);
        deleteAccountItem.setMnemonic(KeyEvent.VK_D);
        historyItem.setMnemonic(KeyEvent.VK_H);
        exitItem.setMnemonic(KeyEvent.VK_E);

        readAllAccDetailsItem.addActionListener(
                e -> {
                    AdminPages.readAllAccDetailsItem();
                }
        );

        updateUserAccPhoneNumberItem.addActionListener(
                e -> {
                    AdminPages adminPages = new AdminPages();
                    adminPages.updateUserAccPhoneNumberItem();
                }
        );
        changeAdminIDItem.addActionListener(
                e -> {
                    AdminPages.changeAdminIDItem();
                }
        );
        deleteAccountItem.addActionListener(
                e -> {
                    JFrame frame1 =new JFrame();
                    frame1.setTitle("Bank Delete Account Window");
                    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame1.setResizable(false);
                    frame1.getContentPane().setBackground(Color.CYAN);
                    frame1.setBounds(284,145,800,518);
                    frame1.setAlwaysOnTop(true);

                    ImageIcon image1=new ImageIcon("BankLogo.png");
                    frame1.setIconImage(image1.getImage());
                    frame1.setVisible(true);

                    int confirmationValue = JOptionPane.showConfirmDialog(frame1,"Confirm DELETE Account","Alert",JOptionPane.YES_NO_OPTION);
                    if(confirmationValue==0){
                        boolean value = interAdminObj.deleteAccount();
                        if(value){
                            ImageIcon iconTick=new ImageIcon("tick.jpg");

                            JOptionPane.showOptionDialog(frame1,
                                    "Successfully DELETE account",
                                    "info",
                                    JOptionPane.CLOSED_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE,
                                    iconTick,
                                    null,
                                    0);
                            frame1.dispose();
                            System.exit(0);
                        }
                    }
                    frame1.dispose();
                }
        );
        historyItem.addActionListener(
                e -> {
                    AdminPages.historyItem();
                }
        );

        exitItem.addActionListener(e -> System.exit(0));

        accDetails.add(readAllAccDetailsItem);
        userEdit.add(updateUserAccPhoneNumberItem);
        adminEdit.add(changeAdminIDItem);
        adminEdit.addSeparator();
        adminEdit.add(deleteAccountItem);
        history.add(historyItem);
        exit.add(exitItem);

        menuBar.add(adminEdit);
        menuBar.add(userEdit);
        menuBar.add(accDetails);
        menuBar.add(history);
        menuBar.add(exit);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.add(label);
        frame.add(label1);

    }
    public static void readAllAccDetailsItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Read All Account Details Report Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        int count=FileHandling.countAllAccountReport();
        String[][] allAccountReportArrayData=new String[2][count];
        String[][] allAccountReportArrayData2=new String[count][2];
        allAccountReportArrayData= interfaceAdminObj.allAccountReport();
        for(int i=0;i<count;++i){
            for(int j=0;j<2;++j){
                allAccountReportArrayData2[i][j]=allAccountReportArrayData[j][i];
            }
        }

        String[] columnNames = {"User Account Number"," User Name"};

        DefaultTableModel model =new DefaultTableModel(allAccountReportArrayData2,columnNames);
        JTable table =new JTable(model);

        frame.add(new JScrollPane(table));

    }
    public static  void historyItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank History Report Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        int count=FileHandling.recodeCount( "nullValue");
        String ListData[][] = new String[count][4];

        String list[] = new String[count];
        list=interAdminObj.history();

        for(int i=0; i<count;++i){
            String instantList[] =list[i].split("-",4);
            for(int j=0;j<4;++j){
                ListData[i][j]=instantList[j];
            }
        }

        String[] columnNames = {"User Account name","Account Number","Old phone number","New phone number"};

        DefaultTableModel model =new DefaultTableModel(ListData,columnNames);
        JTable table =new JTable(model);

        frame.add(new JScrollPane(table));
        frame.setVisible(true);
    }
    public static  void changeAdminIDItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Change AdminID Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel("Enter new AdminId");
        label.setFont(new Font("Arial",Font.PLAIN,20));
        label.setBounds(0,40,400,30);

        JLabel label1 =new JLabel("Re-Enter AdminId");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(0,120,400,30);


        JPasswordField textField =new JPasswordField();
        textField.setPreferredSize(new Dimension(500,30));
        textField.setBounds(50,80,500,30);
        textField.setBackground(Color.LIGHT_GRAY);

        JPasswordField textField1 =new JPasswordField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,160,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Confirm");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(350,240,200,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {

                    if(textField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter new AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (!textField.getText().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            String NewAdminId2=textField.getText();

                            if(textField1.getText().isEmpty()){
                                JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Re-Enter AdminId : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if (!textField1.getText().matches("^[0-9]+$")) {
                                    JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter AdminId : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                }else{

                                    String ReEnteredAdminId2=textField1.getText();

                                    if(NewAdminId2.equals(ReEnteredAdminId2)){
                                        Admin.adminID= Integer.parseInt(NewAdminId2);
                                        boolean value = interAdminObj.changePassword();
                                        if(value){

                                            ImageIcon iconTick=new ImageIcon("tick.jpg");

                                            JOptionPane.showOptionDialog(frame,
                                                    "Successfully change adminId",
                                                    "info",
                                                    JOptionPane.CLOSED_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE,
                                                    iconTick,
                                                    null,
                                                    0);

                                        }else {
                                            JOptionPane.showMessageDialog(frame, "Could not change adminId", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        frame.dispose();
                                    }else{
                                        JOptionPane.showMessageDialog(frame, "Invalid AdminId\n\nAdminId didn\'t match to Re-Entered AdminId ", "Invalid", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,350);
        panel.setLayout(null);
        panel.add(label);
        panel.add(label1);
        panel.add(textField);
        panel.add(textField1);
        panel.add(button1);

        frame.add(panel);
        frame.setVisible(true);

    }

    public  void updateUserAccPhoneNumberItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Update User Account Phone Number Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel("Enter User account number");
        label.setFont(new Font("Arial",Font.PLAIN,20));
        label.setBounds(0,40,400,30);

        JLabel label1 =new JLabel("Enter new User phone number");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(0,120,400,30);


        JTextField textField =new JTextField();
        textField.setPreferredSize(new Dimension(500,30));
        textField.setBounds(50,80,500,30);
        textField.setBackground(Color.LIGHT_GRAY);

        JTextField textField1 =new JTextField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,160,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Confirm");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(350,240,200,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    if (textField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter User Account Number : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (!textField.getText().trim().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter User Account Number : \" ", "Error", JOptionPane.ERROR_MESSAGE);

                        } else{
                            boolean valueAccount=interSecurityObj.checkAccount(Integer.parseInt(textField.getText().trim()));
                            if(valueAccount){
                                User.accountNumber=Integer.parseInt(textField.getText().trim());

                                if (textField1.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(frame,"Please enter nine digits for \" Enter your phone number : \" "+"\nDon\'t use other symbols "+"\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]"+"Must include space ","Info",JOptionPane.INFORMATION_MESSAGE);
                                }else{
                                    if(textField1.getText().length()!=9){
                                        JOptionPane.showMessageDialog(frame, "Only nine digits\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        if (!textField1.getText().trim().matches("^[0-9]+$")) {
                                            JOptionPane.showMessageDialog(frame, "Invalid phone number \n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Invalid", JOptionPane.ERROR_MESSAGE);

                                        } else{
                                            if(!(textField1.getText().substring(0, 1).equals("7")) ){
                                                JOptionPane.showMessageDialog(frame, "First digit must be 7\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                            }else{
                                                if (!(textField1.getText().substring(1, 2).equals("0")) && !(textField1.getText().substring(1, 2).equals("1")) && !(textField1.getText().substring(1, 2).equals("2")) &&!(textField1.getText().substring(1, 2).equals("4")) && !(textField1.getText().substring(1, 2).equals("6")) && !(textField1.getText().substring(1, 2).equals("7")) && !(textField1.getText().substring(1, 2).equals("8"))) {
                                                    JOptionPane.showMessageDialog(frame, "Second digit must be [ \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                                } else{
                                                    User.userPhoneNumber=textField1.getText();
                                                    boolean value = interfaceAdminObj.updateAccount();
                                                    if(value){
                                                        ImageIcon iconTick=new ImageIcon("tick.jpg");

                                                        JOptionPane.showOptionDialog(frame,
                                                                "Successfully UPDATED User Account Phone Number",
                                                                "info",
                                                                JOptionPane.CLOSED_OPTION,
                                                                JOptionPane.INFORMATION_MESSAGE,
                                                                iconTick,
                                                                null,
                                                                0);
                                                    }else {
                                                        JOptionPane.showMessageDialog(frame, "Unsuccessful ", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    frame.dispose();
                                                }
                                            }
                                        }
                                    }
                                }

                            }else {
                                JOptionPane.showMessageDialog(frame, "Invalid account number", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,350);
        panel.setLayout(null);
        panel.add(label);
        panel.add(label1);
        panel.add(textField);
        panel.add(textField1);
        panel.add(button1);

        frame.add(panel);
        frame.setVisible(true);

    }
}
