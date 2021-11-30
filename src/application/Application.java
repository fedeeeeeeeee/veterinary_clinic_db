package application;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;

import view.IconImage;
import view.MainPage;

public class Application {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setTitle("Clinica Veterinaria");
                frame.setBounds(100, 100, 1000, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setIconImage(IconImage.RICOVERO.getImageIcon().getImage());
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                int w = frame.getSize().width;
                int h = frame.getSize().height;
                int x = (dim.width-w)/2;
                int y = (dim.height-h)/2;
                frame.setLocation(x, y);
                frame.add(new MainPage(frame));
                frame.setVisible(true);
            }
        });
    }
}
