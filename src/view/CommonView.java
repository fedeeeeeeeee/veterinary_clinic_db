package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import model.User;
import model.UserImpl;

public abstract class CommonView extends JPanel {
    
    protected JMenuBar menuBar;
    
    private static final long serialVersionUID = 1L;
    private User user;
    
    public void initCommonView(final JFrame frame, final User user) {
        this.user = new UserImpl();
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu mnMenu = new JMenu("Account");
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(mnMenu);
        
        JMenuItem mntmAccount = new JMenuItem("Utente", IconImage.USER.getImageIcon());
        mnMenu.add(mntmAccount);
        
        mntmAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().add(new UserInfo(frame, user));
            }
        });
      
        JSeparator separator = new JSeparator();
        mnMenu.add(separator);

    //	Icon iconLogOut = IconImage.LOGOUT.getImageIcon();
       // JMenuItem mntmLogout = new JMenuItem(iconLogOut);
        JMenuItem mntmLogout = new JMenuItem("Disconnetti", IconImage.LOGOUT.getImageIcon());
		//mntmLogout.setFont(new Font("Tahoma", Font.PLAIN, 25));
		//mntmLogout.setText("Logout");
        mnMenu.add(mntmLogout);
        mntmLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setJMenuBar(null);
                frame.getContentPane().add(new MainPage(frame));
            }
        });        
    }
}
