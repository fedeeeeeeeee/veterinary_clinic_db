package view;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import controller.UserController;
import controller.UserControllerImpl;
import model.User;

public class Login extends JPanel {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JTextField textFieldUsername;
    private JPasswordField passwordField;
    private UserController controller;
    private User user;

    public Login(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        this.frame = frame;
        initialize();
    }

    private void initialize() {
    	controller = new UserControllerImpl();        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        
        JLabel loginLabel = new JLabel("Accesso");
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));        

       // JLabel label = new JLabel("");
       // label.setIcon(IconImage.DENTAL_CARE.getImageIcon());
        
        JButton loginButton = new JButton("Accesso");
        loginButton.setEnabled(false);
        
        JButton sendButton = new JButton("Invia");
        sendButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(controller.checkUser(textFieldUsername.getText(), String.valueOf(passwordField.getPassword()))) {
            		//JOptionPane.showMessageDialog(frame, "User found", "OK!", JOptionPane.ERROR_MESSAGE);
            		 user = controller.getUser(textFieldUsername.getText());
            		 //System.out.println(user.getRuolo());
					switch (user.getRuolo()) {
					case "Veterinario":
					case "Infermiere":
						frame.getContentPane().add(new HomeVeterinario(frame, user));
						break;
					case "Segretaria":
					case "tecnico":
						frame.getContentPane().add(new HomeSegretaria(frame, user));
						break;
					default:
					}
            	} else {
            		JOptionPane.showMessageDialog(frame, "User not found", "Error!", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        JPanel panelLogin = new JPanel();
        GridLayout gl_panelLogin = new GridLayout();
        gl_panelLogin.setRows(2);
        gl_panelLogin.setColumns(2);
        gl_panelLogin.setVgap(20);
        gl_panelLogin.setHgap(10);
        panelLogin.setLayout(gl_panelLogin);
        
        JLabel lblUsername_1 = new JLabel("Email");
        lblUsername_1.setHorizontalAlignment(SwingConstants.TRAILING);
        lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelLogin.add(lblUsername_1);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldUsername.setColumns(10);
        panelLogin.add(textFieldUsername);
        
        JLabel lbl1 = new JLabel("");
        panelLogin.add(lbl1);
        
        JLabel lblPassword_1 = new JLabel("Password");
        lblPassword_1.setHorizontalAlignment(SwingConstants.TRAILING);
        lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panelLogin.add(lblPassword_1);
        
        passwordField = new JPasswordField();
        panelLogin.add(passwordField);
        
        JLabel lbl2 = new JLabel("");
        panelLogin.add(lbl2);

        
        
       // GROUP LAYOUT
 //  GroupLayout groupLayout = new GroupLayout(this);
      GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
       groupLayout.setHorizontalGroup(
       	groupLayout.createParallelGroup(Alignment.LEADING)
       		.addGroup(groupLayout.createSequentialGroup()
       			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
       			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
       				.addGroup(groupLayout.createSequentialGroup()
       					.addPreferredGap(ComponentPlacement.UNRELATED)
       					.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE))
       				.addGroup(groupLayout.createSequentialGroup()
       					.addGap(349)
       					.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
       				.addGroup(groupLayout.createSequentialGroup()
       					.addGap(141)
       					.addComponent(loginLabel))
       				//.addGroup(groupLayout.createSequentialGroup()
       					//.addGap(200)
       					//.addComponent(panelProfessione, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)))
       			.addGap(55)))
       );
       groupLayout.setVerticalGroup(
       	groupLayout.createParallelGroup(Alignment.LEADING)
       		.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
       		.addGroup(groupLayout.createSequentialGroup()
       			.addGap(103)
       			.addComponent(loginLabel)
       			.addGap(81)
       			.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
       			.addGap(18)
       			.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
       			.addGap(84))
       );
       
       
       JButton btnRegister = new JButton("Registrazione");
       btnRegister.addActionListener(new ActionListener() {
       	public void actionPerformed(ActionEvent e) {
       		frame.getContentPane().add(new Register(frame));
       	}
       });
       
    
       GroupLayout gl_panel = new GroupLayout(panel);
       gl_panel.setHorizontalGroup(
       	gl_panel.createParallelGroup(Alignment.LEADING)
       		.addGroup(gl_panel.createSequentialGroup()
       			.addContainerGap()
       			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
       				.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
       				.addComponent(loginButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
       			.addContainerGap())
       );
       gl_panel.setVerticalGroup(
       	gl_panel.createParallelGroup(Alignment.LEADING)
       		.addGroup(gl_panel.createSequentialGroup()
       			.addGap(77)
       			.addComponent(loginButton)
       			.addGap(18)
       			.addComponent(btnRegister)
       			.addContainerGap(271, Short.MAX_VALUE))
       );
       panel.setLayout(gl_panel);
      frame.getContentPane().setLayout(groupLayout);
    }
}
