package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainPage extends CommonView {

    private static final long serialVersionUID = 1L;
    private JFrame frame;

    /**
     * Create the application.
     * @param frame 
     */
    public MainPage(final JFrame frame) {
        this.frame = frame;
        frame.getContentPane().removeAll();
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        JLabel lblDentalData = new JLabel("Clinica Veterinaria");
        lblDentalData.setHorizontalAlignment(SwingConstants.CENTER);
        lblDentalData.setForeground(Color.DARK_GRAY);
        lblDentalData.setFont(new Font("Caladea", Font.PLAIN, 90));
        
        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 40));
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
        
        JLabel label = new JLabel("");
       // label.setIcon(IconImage.TOOTH.getImageIcon());
        
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(191)
                    .addComponent(lblDentalData, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(200))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(209)
                    .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addGap(108)
                    .addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addGap(223))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(118)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblDentalData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(22)))
                    .addGap(51)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
                    .addGap(125))
        );
        
         /*
       btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().add(new Register(frame));
            }
        });*/
       
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().add(new Login(frame));
            }

        });
        frame.getContentPane().setLayout(groupLayout);
    }
}
