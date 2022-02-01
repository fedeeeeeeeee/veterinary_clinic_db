package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainPage extends JPanel {

    private static final long serialVersionUID = 1L;
    private JFrame frame;

    public MainPage(final JFrame frame) {
        this.frame = frame;
        frame.getContentPane().removeAll();
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        initialize();
        
    }

    private void initialize() {
        JLabel titleLabel = new JLabel("<html><font color='#56728f'>Clinica Veterinaria</font></html>");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 60));
        
        JButton registerButton = new JButton("Registrazione");
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
        
        JButton loginButton = new JButton("Accesso");
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
        
        JLabel label = new JLabel(IconImage.LOGO.getImageIcon());
        
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(100)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(40)
                    .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.RELATED))
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(209)
                    .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGap(108)
                    .addComponent(registerButton, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addGap(223))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(118)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(22)))
                    .addGap(51)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                    .addGap(125))
        );
        
      
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().add(new Register(frame));
            }
        });
       
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.getContentPane().add(new Login(frame));  
            }

        });
        frame.getContentPane().setLayout(groupLayout);
    }
}
