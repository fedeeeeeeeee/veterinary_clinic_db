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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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
        GridBagLayout gbl_panelLogin = new GridBagLayout();
        gbl_panelLogin.columnWidths = new int[]{37, 100, 136, 0};
        gbl_panelLogin.rowHeights = new int[]{26, 26, 0};
        gbl_panelLogin.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panelLogin.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panelLogin.setLayout(gbl_panelLogin);
        
        JLabel lblUsername_1 = new JLabel("Email");
        lblUsername_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_lblUsername_1 = new GridBagConstraints();
        gbc_lblUsername_1.fill = GridBagConstraints.BOTH;
        gbc_lblUsername_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblUsername_1.gridx = 1;
        gbc_lblUsername_1.gridy = 0;
        panelLogin.add(lblUsername_1, gbc_lblUsername_1);
        
        textFieldUsername = new JTextField();
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldUsername.setColumns(10);
        GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
        gbc_textFieldUsername.fill = GridBagConstraints.BOTH;
        gbc_textFieldUsername.insets = new Insets(0, 0, 5, 0);
        gbc_textFieldUsername.gridx = 2;
        gbc_textFieldUsername.gridy = 0;
        panelLogin.add(textFieldUsername, gbc_textFieldUsername);
        
        JLabel lblPassword_1 = new JLabel("Password");
        lblPassword_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbc_lblPassword_1 = new GridBagConstraints();
        gbc_lblPassword_1.fill = GridBagConstraints.BOTH;
        gbc_lblPassword_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblPassword_1.gridx = 1;
        gbc_lblPassword_1.gridy = 1;
        panelLogin.add(lblPassword_1, gbc_lblPassword_1);
        
        passwordField = new JPasswordField();
        GridBagConstraints gbc_passwordField = new GridBagConstraints();
        gbc_passwordField.fill = GridBagConstraints.BOTH;
        gbc_passwordField.gridx = 2;
        gbc_passwordField.gridy = 1;
        panelLogin.add(passwordField, gbc_passwordField);

        
        
       // GROUP LAYOUT
GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
 //GroupLayout groupLayout = new GroupLayout(this);
  groupLayout.setHorizontalGroup(
  	groupLayout.createParallelGroup(Alignment.LEADING)
  		.addGroup(groupLayout.createSequentialGroup()
  			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
  			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(270)
  					.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE))
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(385)
  					.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
  				.addGroup(groupLayout.createSequentialGroup()
  					.addGap(355)
  					.addComponent(loginLabel))))
  );
  groupLayout.setVerticalGroup(
  	groupLayout.createParallelGroup(Alignment.LEADING)
  		.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  		.addGroup(groupLayout.createSequentialGroup()
  			.addGap(58)
  			.addComponent(loginLabel)
  			.addGap(95)
  			.addComponent(panelLogin, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
  			.addGap(35)
  			.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
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
