import javax.swing.*;
import java.awt.*;
public class LaunchPages{

    public void MainWindow(){

        ImageIcon image2=new ImageIcon("Bank.png");

        JLabel label1=new JLabel();
        label1.setText("   BANKING SYSTEM");
        label1.setIcon(image2);
        label1.setHorizontalTextPosition(JLabel.CENTER);
        label1.setVerticalTextPosition(JLabel.BOTTOM);
        label1.setFont(new Font("Segoe UI Black",Font.PLAIN,50));
        label1.setBounds(15,25,570,300);


        JFrame frame=new JFrame();
        frame.setTitle("Bank Home Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());


        JButton button1= new JButton();
        button1.setText("Sign up");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.BLACK);
        button1.setFocusable(false);
        button1.setBounds(15,350,200,50);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages.SignUpWindow();
                }
        );

        JButton button2= new JButton();
        button2.setText("Sign In");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.BLACK);
        button2.setFocusable(false);
        button2.setBounds(385,350,200,50);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages.SignInWindow();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(label1);
        panel.add(button1);
        panel.add(button2);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static void SignUpWindow(){

        JFrame frame=new JFrame();
        frame.setTitle("Bank Sign Up Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());

        JButton button1= new JButton();
        button1.setText("sign Up as a User");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.BLACK);
        button1.setFocusable(false);
        button1.setBounds(15,150,270,50);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    frame.dispose();
                    UserPages userPages=new UserPages();
                    userPages.SignUpUserWindow();
                }
        );

        JButton button2= new JButton();
        button2.setText("sign Up as a Admin");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.BLACK);
        button2.setFocusable(false);
        button2.setBounds(300,150,280,50);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    AdminPages adminPages=new AdminPages();
                    adminPages.SignUpAdminLoginWindow();
                }
        );
        JButton button3= new JButton();
        button3.setText("Back");
        button3.setForeground(new Color(252,249,245));
        button3.setBackground(Color.BLACK);
        button3.setFocusable(false);
        button3.setBounds(150,250,280,50);
        button3.setFont(new Font("Bell MT",Font.BOLD,25));
        button3.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages LaunchMainWindow=new LaunchPages();
                    LaunchMainWindow.MainWindow();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static void SignInWindow(){

        JFrame frame=new JFrame();
        frame.setTitle("Bank Sign In Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN);
        frame.setLocationRelativeTo(null);

        ImageIcon image=new ImageIcon("BankLogo.png");
        frame.setIconImage(image.getImage());


        JButton button1= new JButton();
        button1.setText("sign In as a User");
        button1.setForeground(new Color(252,249,245));
        button1.setBackground(Color.BLACK);
        button1.setFocusable(false);
        button1.setBounds(15,150,270,50);
        button1.setFont(new Font("Bell MT",Font.BOLD,25));
        button1.addActionListener(
                e -> {
                    frame.dispose();
                    UserPages userPages=new UserPages();
                    userPages.SignInUserWindow();
                }
        );

        JButton button2= new JButton();
        button2.setText("sign In as a Admin");
        button2.setForeground(new Color(252,249,245));
        button2.setBackground(Color.BLACK);
        button2.setFocusable(false);
        button2.setBounds(300,150,280,50);
        button2.setFont(new Font("Bell MT",Font.BOLD,25));
        button2.addActionListener(
                e -> {
                    frame.dispose();
                    AdminPages adminPages=new AdminPages();
                    adminPages.SignInAdminWindow();
                }
        );
        JButton button3= new JButton();
        button3.setText("Back");
        button3.setForeground(new Color(252,249,245));
        button3.setBackground(Color.BLACK);
        button3.setFocusable(false);
        button3.setBounds(150,250,280,50);
        button3.setFont(new Font("Bell MT",Font.BOLD,25));
        button3.addActionListener(
                e -> {
                    frame.dispose();
                    LaunchPages LaunchMainWindow=new LaunchPages();
                    LaunchMainWindow.MainWindow();
                }
        );

        JPanel panel= new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(100,50,600,450);
        panel.setLayout(null);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.add(panel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
