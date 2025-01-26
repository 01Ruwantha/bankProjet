import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserPages {
    private final static InterfaceUserAdmin interUserObj=new User();
    private final static InterfaceSecurity interSecurityObj=new Security();
    private final static InterfaceAccount interAccountObj=new Account();
    private static int userPasswordErrorCount=0;
    public void SignInUserWindow(){

        JFrame frame =new JFrame();
        frame.setTitle("Bank Sign In User Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign In As A User");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,350,35);


        JLabel label1=new JLabel("Enter User Account Number :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,100,400,25);

        JTextField textField1 =new JTextField();
        textField1.setPreferredSize(new Dimension(250,40));
        textField1.setBounds(50,150,500,35);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter User Password :");
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
                    Boolean UserPasswordValue=false;
                    if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter User Account Number : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (!textField1.getText().trim().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter User Account Number : \" ", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            boolean valueAccount=interSecurityObj.checkAccount(Integer.parseInt(textField1.getText().trim()));
                            if(valueAccount){
                                AccountValue=true;
                                User.accountNumber=Integer.parseInt(textField1.getText().trim());

                                if (textField2.getText().isEmpty()){
                                    JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter User Password : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                }else {
                                    if (!textField2.getText().matches("^[0-9]+$")) {
                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter User Password : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        boolean valuePassword = interSecurityObj.checkPassword(Integer.parseInt(textField1.getText().trim()), Integer.parseInt( textField2.getText()));
                                        if(valuePassword){
                                            UserPasswordValue=true;
                                        }else {
                                            ++userPasswordErrorCount;
                                            if(userPasswordErrorCount<3) {
                                                JOptionPane.showMessageDialog(frame, "Invalid User Password" + "\nYou have " + (3 - userPasswordErrorCount) + " times remaining", "Invalid", JOptionPane.WARNING_MESSAGE);
                                            }
                                        }
                                    }
                                }

                            }else {
                                JOptionPane.showMessageDialog(frame, "Invalid account number", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    if(userPasswordErrorCount<3){
                        if (UserPasswordValue == true && AccountValue == true) {
                            if (interUserObj.signIn()) {
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
                                UserPages userPages = new UserPages();
                                userPages.UserWindow();


                            }else {

                                JOptionPane.showMessageDialog(frame, "signIn process failed", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }else {
                        frame.dispose();
                        UserPages userPages=new UserPages();
                        userPages.SignInUserForgotUserPasswordWindow();

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
    public void SignUpUserWindow(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Sign Up User Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign Up As A User");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(175,0,350,40);


        JLabel label1=new JLabel("Enter your full name :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,50,400,30);

        JTextField textField1 =new JTextField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,80,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter your phone number :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,120,400,30);

        JTextField textField2 =new JTextField();
        textField2.setPreferredSize(new Dimension(250,30));
        textField2.setBounds(50,150,500,30);
        textField2.setBackground(Color.LIGHT_GRAY);

        JLabel label3=new JLabel("Enter your first deposit amount :");
        label3.setFont(new Font("Arial",Font.PLAIN,20));
        label3.setBounds(50,190,400,30);

        JTextField textField3 =new JTextField();
        textField3.setPreferredSize(new Dimension(250,30));
        textField3.setBounds(50,220,500,30);
        textField3.setBackground(Color.LIGHT_GRAY);

        JLabel label4=new JLabel("Enter new User Password :");
        label4.setFont(new Font("Arial",Font.PLAIN,20));
        label4.setBounds(50,260,400,30);

        JPasswordField textField4 =new JPasswordField();
        textField4.setPreferredSize(new Dimension(250,30));
        textField4.setBounds(50,290,500,30);
        textField4.setBackground(Color.LIGHT_GRAY);

        JLabel label5=new JLabel("Re-Enter User Password :");
        label5.setFont(new Font("Arial",Font.PLAIN,20));
        label5.setBounds(50,330,400,30);

        JPasswordField textField5 =new JPasswordField();
        textField5.setPreferredSize(new Dimension(250,30));
        textField5.setBounds(50,360,500,30);
        textField5.setBackground(Color.LIGHT_GRAY);

        JButton button1= new JButton();
        button1.setText("Submit");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.black);
        button1.setFocusable(false);
        button1.setBounds(300,400,150,40);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    if (textField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(frame,"Please enter string value for \" Enter your full name : \" "+"\nYou can only use [ \"a-z\" , \"A-Z\" , \"  \" , \".\" ]"+"\nDon\'t use other symbols ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        if (!textField1.getText().trim().matches("^[a-zA-Z .]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter string value for \" Enter your full name : \" "+"\nYou can only use [ \"a-z\" , \"A-Z\" , \"  \" , \".\" ]"+"\nDon\'t use other symbols ", "Error", JOptionPane.ERROR_MESSAGE);

                        } else {
                            User.accountName=textField1.getText().trim().toUpperCase();
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
                                                User.userPhoneNumber=textField2.getText();

                                                if(textField3.getText().isEmpty()){
                                                    JOptionPane.showMessageDialog(frame, "Please enter integer value for \" Enter your first deposit amount : \" ", "Info", JOptionPane.INFORMATION_MESSAGE);
                                                }else {
                                                    if(!textField3.getText().matches("^[0-9.]+$")){
                                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter your first deposit amount : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else {
                                                        User.accountBalance= Double.valueOf(textField3.getText());
                                                        if (textField4.getText().isEmpty()) {
                                                            JOptionPane.showMessageDialog(frame, "Please enter integer value for \" Enter new User Password : \" ", "Info", JOptionPane.INFORMATION_MESSAGE);
                                                        } else {
                                                            if (!textField4.getText().matches("^[0-9]+$")) {
                                                                JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new User Password : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                            } else {
                                                                String NewUserPassword = textField4.getText();
                                                                if (textField5.getText().isEmpty()) {
                                                                    JOptionPane.showMessageDialog(frame, "Please enter integer value for \" Re-Enter User Password : \" ", "Info", JOptionPane.INFORMATION_MESSAGE);
                                                                } else {
                                                                    if (!textField5.getText().matches("^[0-9]+$")) {
                                                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter User Password : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                                    } else {
                                                                        String ReEnteredUserPassword = textField5.getText();

                                                                        if (!NewUserPassword.equals(ReEnteredUserPassword)) {
                                                                            JOptionPane.showMessageDialog(frame, "Invalid User Password\n\nUser Password didn\'t match to Re-Entered User Password ", "Invalid", JOptionPane.ERROR_MESSAGE);
                                                                        } else {
                                                                            User.userPassword = Integer.parseInt(NewUserPassword);
                                                                            User.accountNumber = interAccountObj.accountNumberGenerate();
                                                                            JOptionPane.showMessageDialog(frame, "Your user account number is :  " + User.accountNumber, "Info", JOptionPane.INFORMATION_MESSAGE);

                                                                            interUserObj.signUp();
                                                                            frame.dispose();

                                                                            ImageIcon iconTick=new ImageIcon("tick.jpg");

                                                                            JOptionPane.showOptionDialog(frame,
                                                                                    "Successfully create User Account",
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
                    }
                }
        );

        JButton button2= new JButton();
        button2.setText("Back");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.black);
        button2.setFocusable(false);
        button2.setBounds(150,400,150,40);
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
        panel.add(label5);
        panel.add(textField5);
        panel.add(button1);
        panel.add(button2);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public void SignInUserForgotUserPasswordWindow(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank SignIn User Forgot User Password Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Sign In User Forgot User Password");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(48,0,504,35);

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
        button2.setText("Forgot User Password");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.black);
        button2.setFocusable(false);
        button2.setBounds(130,195,345,40);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    UserPages userPages=new UserPages();
                    userPages.ForgotUserPassword();
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

    private static int errorCountForgotUserPassword=0;
    public void ForgotUserPassword(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Forgot User Password Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label0=new JLabel("Forgot UserPassword");
        label0.setFont(new Font("Arial",Font.BOLD,30));
        label0.setBounds(143,0,314,40);

        JLabel label1=new JLabel("Enter your phone number :");
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label1.setBounds(50,100,400,30);

        JPasswordField textField1 =new JPasswordField();
        textField1.setPreferredSize(new Dimension(250,30));
        textField1.setBounds(50,140,500,30);
        textField1.setBackground(Color.LIGHT_GRAY);

        JLabel label2=new JLabel("Enter new UserPassword :");
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setBounds(50,180,400,30);

        JPasswordField textField2 =new JPasswordField();
        textField2.setPreferredSize(new Dimension(250,30));
        textField2.setBounds(50,220,500,30);
        textField2.setBackground(Color.LIGHT_GRAY);

        JLabel label3=new JLabel("Re-Enter UserPassword :");
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
                    boolean valueAccount = interSecurityObj.checkAccount(User.accountNumber);//Account.accountNumber danna
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
                                    JOptionPane.showMessageDialog(null, "Invalid phone number \n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Invalid", JOptionPane.ERROR_MESSAGE);

                                } else {
                                    if (!(textField1.getText().substring(0, 1).equals("7"))) {
                                        JOptionPane.showMessageDialog(frame, "First digit must be 7\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        if (!(textField1.getText().substring(1, 2).equals("0")) && !(textField1.getText().substring(1, 2).equals("1")) && !(textField1.getText().substring(1, 2).equals("2")) && !(textField1.getText().substring(1, 2).equals("4")) && !(textField1.getText().substring(1, 2).equals("6")) && !(textField1.getText().substring(1, 2).equals("7")) && !(textField1.getText().substring(1, 2).equals("8"))) {
                                            JOptionPane.showMessageDialog(frame, "Second digit must be [ \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n\nEnter nine digits for \" Enter your phone number : \" " + "\nDon\'t use other symbols " + "\nEx:- 7#zzzzzzz \n[ # - can be \' 0 \' , \' 1 \' ,\' 2 \' ,\' 4 \' ,\' 6 \' ,\' 7 \' , \' 8 \' ]\n[ zzzzzzz - is other seven digits ]", "Error", JOptionPane.ERROR_MESSAGE);
                                        } else {
                                            User.userPhoneNumber = textField1.getText();

                                            boolean valuePassword = interSecurityObj.checkPhoneNumber();
                                            if (!valuePassword) {
                                                ++errorCountForgotUserPassword;

                                                if(errorCountForgotUserPassword<3) {
                                                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number"+"\nYou have "+(3-errorCountForgotUserPassword)+" times remaining"+"\n\nEntered phone number didn\'t match with Account phone number", "Warning", JOptionPane.WARNING_MESSAGE);
                                                }else{
                                                    frame.dispose();
                                                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number\n\nMeet the Admin to change Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
                                                    LaunchPages launchPages=new LaunchPages();
                                                    launchPages.SignInWindow();
                                                }
                                            }else{
                                                if(textField2.getText().isEmpty()){
                                                    JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter new UserPassword : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                }else {
                                                    if (!textField2.getText().matches("^[0-9]+$")) {
                                                        JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new UserPassword : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                    }else{
                                                        String NewUserPassword2=textField2.getText();

                                                        if(textField3.getText().isEmpty()){
                                                            JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Re-Enter UserPassword : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                                                        }else{
                                                            if (!textField3.getText().matches("^[0-9]+$")) {
                                                                JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter UserPassword : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                                            }else{
                                                                String ReEnteredUserPassword2=textField3.getText();

                                                                if(NewUserPassword2.equals(ReEnteredUserPassword2)){
                                                                    User.userPassword= Integer.parseInt(NewUserPassword2);
                                                                    User user=new User();
                                                                    boolean value=user.forgotPassword();
                                                                    if(value){

                                                                        ImageIcon iconTick=new ImageIcon("tick.jpg");

                                                                        JOptionPane.showOptionDialog(frame,
                                                                                "Successfully change UserPassword",
                                                                                "info",
                                                                                JOptionPane.CLOSED_OPTION,
                                                                                JOptionPane.INFORMATION_MESSAGE,
                                                                                iconTick,
                                                                                null,
                                                                                0);
                                                                        JOptionPane.showMessageDialog(frame, "You will automatically logout via System\nPlease log in after this", "Alert", JOptionPane.CLOSED_OPTION);

                                                                    }else {
                                                                        JOptionPane.showMessageDialog(frame, "Could not change UserPassword", "Error", JOptionPane.ERROR_MESSAGE);

                                                                    }
                                                                    frame.dispose();
                                                                    System.exit(0);

                                                                }else{
                                                                    JOptionPane.showMessageDialog(frame, "Invalid UserPassword\n\nUserPassword didn\'t match to Re-Entered UserPassword ", "Invalid", JOptionPane.ERROR_MESSAGE);
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
    public void UserWindow(){

        JFrame frame =new JFrame();
        frame.setTitle("Bank User Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(0,30,800,508);

        ImageIcon image2=new ImageIcon("Bank.png");
        ImageIcon image3=new ImageIcon("UserAdmin.png");
        JLabel label=new JLabel();
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

        JMenu edit = new JMenu("Edit");
        JMenu history = new JMenu("History");
        JMenu exit = new JMenu("Exit");

        edit.setMnemonic(KeyEvent.VK_E);
        history.setMnemonic(KeyEvent.VK_H);
        exit.setMnemonic(KeyEvent.VK_F4);

        JMenuItem changePasswordItem = new JMenuItem("CHANGE PASSWORD");
        JMenuItem deleteAccountItem = new JMenuItem("DELETE ACCOUNT");
        JMenuItem historyItem = new JMenuItem("HISTORY Report");
        JMenuItem exitItem = new JMenuItem("EXIT");

        changePasswordItem.setMnemonic(KeyEvent.VK_C);
        deleteAccountItem.setMnemonic(KeyEvent.VK_D);
        historyItem.setMnemonic(KeyEvent.VK_H);
        exitItem.setMnemonic(KeyEvent.VK_E);


        ImageIcon Icon4 = new ImageIcon("Icon//passwordChange.png");
        changePasswordItem.setIcon(Icon4);

        ImageIcon Icon5 = new ImageIcon("Icon//AccDelete.png");
        deleteAccountItem.setIcon(Icon5);

        ImageIcon Icon6 = new ImageIcon("Icon//history.png");
        historyItem.setIcon(Icon6);

        ImageIcon Icon7 = new ImageIcon("Icon//exit.png");
        exitItem.setIcon(Icon7);

        changePasswordItem.addActionListener(
                e -> {
                    UserPages.changePasswordItem();
                }
        );
        historyItem.addActionListener(
                e -> {
                    UserPages.historyItem();
                }
        );
        exitItem.addActionListener(
                e -> {
                    System.exit(0);
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
                        if(User.accountBalance>0){
                            JOptionPane.showMessageDialog(frame1, "Cannot delete account \n\nYour account hsa "+User.accountBalance+" Balance \nPlease withdraw this amount before delete", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            boolean returnedValue = interUserObj.deleteAccount();
                            if(returnedValue){
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
                            }else {
                                JOptionPane.showMessageDialog(frame1, "Cannot deleted your account", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    frame1.dispose();
                }
        );

        edit.add(changePasswordItem);
        edit.addSeparator();
        edit.add(deleteAccountItem);
        history.add(historyItem);
        exit.add(exitItem);

        menuBar.add(edit);
        menuBar.add(history);
        menuBar.add(exit);

        JButton button1= new JButton();
        button1.setText("Check Balance");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.BLACK);
        button1.setFocusable(false);
        button1.setBounds(12,0,200,50);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    JFrame frame1 =new JFrame();
                    frame1.setTitle("Bank Check Balance Window");
                    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame1.setResizable(false);
                    frame1.getContentPane().setBackground(Color.CYAN);
                    frame1.setBounds(284,145,800,518);
                    frame1.setAlwaysOnTop(true);

                    ImageIcon image1=new ImageIcon("BankLogo.png");
                    frame1.setIconImage(image1.getImage());
                    frame1.setVisible(true);
                    frame1.setVisible(true);
                    JOptionPane.showMessageDialog(frame1,"Your account balance is : "+User.accountBalance,"Balance",JOptionPane.CLOSED_OPTION,null);
                    frame1.dispose();
                }
        );

        JButton button2= new JButton();
        button2.setText("Deposit");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.BLACK);
        button2.setFocusable(false);
        button2.setBounds(12,100,200,50);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e ->{
                    UserPages.depositItem();
                }
        );

        JButton button3= new JButton();
        button3.setText("Withdraw");
        button3.setForeground(new Color(252,249,245));
        button3.setBackground(Color.BLACK);
        button3.setFocusable(false);
        button3.setBounds(12,200,200,50);
        button3.setFont(new Font("Bell MT",Font.BOLD,25));
        button3.addActionListener(
                e ->{
                    UserPages.withdrawItem();
                }
        );
        
        JPanel panel= new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(25,125,225,250);
        panel.setLayout(null);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.add(panel);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
        frame.add(label);
        frame.add(label1);

    }
    public static  void changePasswordItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Change Password Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel("Enter new User Password");
        label.setFont(new Font("Arial",Font.PLAIN,20));
        label.setBounds(0,40,400,30);

        JLabel label1 =new JLabel("Re-Enter User Password");
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
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter new User Password : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (!textField.getText().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter new User Password : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            String NewUserPassword=textField.getText();

                            if(textField1.getText().isEmpty()){
                                JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Re-Enter User Password : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if (!textField1.getText().matches("^[0-9]+$")) {
                                    JOptionPane.showMessageDialog(frame, "Enter integer value for \" Re-Enter User Password : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                                }else{

                                    String ReEnteredUserPassword=textField1.getText();

                                    if(NewUserPassword.equals(ReEnteredUserPassword)){
                                        User.userPassword= Integer.parseInt(NewUserPassword);
                                        boolean value = interUserObj.changePassword();
                                        if(value){

                                            ImageIcon iconTick=new ImageIcon("tick.jpg");

                                            JOptionPane.showOptionDialog(frame,
                                                    "Successfully change UserPassword",
                                                    "info",
                                                    JOptionPane.CLOSED_OPTION,
                                                    JOptionPane.INFORMATION_MESSAGE,
                                                    iconTick,
                                                    null,
                                                    0);

                                        }else {
                                            JOptionPane.showMessageDialog(frame, "Could not change UserPassword", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        frame.dispose();
                                    }else{
                                        JOptionPane.showMessageDialog(frame, "Invalid UserPassword\n\n New UserPassword didn\'t match to Re-Entered UserPassword ", "Invalid", JOptionPane.ERROR_MESSAGE);
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

    public static  void depositItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Deposit Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel("Enter deposit amount ");
        label.setFont(new Font("Arial",Font.PLAIN,20));
        label.setBounds(0,40,400,30);

        JTextField textField =new JTextField();
        textField.setPreferredSize(new Dimension(500,30));
        textField.setBounds(50,80,500,30);
        textField.setBackground(Color.LIGHT_GRAY);


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
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter deposit amount : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (!textField.getText().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter deposit amount : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                        }else{
                            int value =JOptionPane.showConfirmDialog(frame,"Your Deposit amount is : "+textField.getText(),"Confirm",JOptionPane.YES_NO_OPTION);
                            if(value==0){
                                Double dipositeAmount = Double.valueOf(textField.getText());
                                boolean returnedValue = interAccountObj.cashDeposit(dipositeAmount);
                                if (returnedValue) {
                                    ImageIcon iconTick = new ImageIcon("tick.jpg");

                                    JOptionPane.showOptionDialog(frame,
                                            "Successfully Deposit your amount",
                                            "info",
                                            JOptionPane.CLOSED_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE,
                                            iconTick,
                                            null,
                                            0);
                                    JOptionPane.showMessageDialog(frame, "Your account balance is : " + User.accountBalance, "Balance", JOptionPane.CLOSED_OPTION, null);
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Couldn't write in detail file  ", "Error", JOptionPane.ERROR_MESSAGE, null);
                                }
                                frame.dispose();
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
        panel.add(textField);
        panel.add(button1);

        frame.add(panel);
        frame.setVisible(true);

    }
    public static  void withdrawItem(){
        JFrame frame =new JFrame();
        frame.setTitle("Bank Withdraw Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setBounds(284,145,800,518);
        frame.setAlwaysOnTop(true);
        frame.setLayout(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JLabel label = new JLabel("Enter withdraw amount ");
        label.setFont(new Font("Arial",Font.PLAIN,20));
        label.setBounds(0,40,400,30);

        JTextField textField =new JTextField();
        textField.setPreferredSize(new Dimension(500,30));
        textField.setBounds(50,80,500,30);
        textField.setBackground(Color.LIGHT_GRAY);


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
                        JOptionPane.showMessageDialog(frame,"Please enter integer value for \" Enter withdraw amount : \" ","Info",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        if (!textField.getText().matches("^[0-9]+$")) {
                            JOptionPane.showMessageDialog(frame, "Enter integer value for \" Enter withdraw amount : \" ", "Error", JOptionPane.ERROR_MESSAGE);
                        }else {
                            int value = JOptionPane.showConfirmDialog(frame, "Your Withdraw amount is : " + textField.getText(), "Confirm", JOptionPane.YES_NO_OPTION);
                            if (value == 0){
                                int withdrawAmount = Integer.parseInt(textField.getText());
                                if (withdrawAmount > User.accountBalance) {
                                    JOptionPane.showMessageDialog(frame, "You do not have sufficient balance to proceed this", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    User.accountBalance -= withdrawAmount;
                                    boolean returnedValue = interAccountObj.cashWithdraw(withdrawAmount);
                                    if (returnedValue) {
                                        ImageIcon iconTick = new ImageIcon("tick.jpg");

                                        JOptionPane.showOptionDialog(frame,
                                            "Successfully Withdraw your amount",
                                            "info",
                                            JOptionPane.CLOSED_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE,
                                            iconTick,
                                            null,
                                            0);
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Couldn't write in detail file about withdraw", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                frame.dispose();
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
        panel.add(textField);
        panel.add(button1);

        frame.add(panel);
        frame.setVisible(true);

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

        int count=FileHandling.recodeCount();
        String[][] ListData = new String[count][2];

        String list[] = new String[count];
        list=interUserObj.history();

        for(int i=0;i<count;++i){
            String tempList[]=list[i].split(":",2);
            String tempList2[]=tempList[0].split(" ",2);
            ListData[i][0]=tempList2[0];
            ListData[i][1]=tempList[1];
        }
        String[] columnNames={"Type","Amount"};
        DefaultTableModel model =new DefaultTableModel(ListData,columnNames);
        JTable table =new JTable(model);

        frame.add(new JScrollPane(table));

        frame.setVisible(true);
    }

}
