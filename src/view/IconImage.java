package view;
import javax.swing.ImageIcon;

public enum IconImage {
	
	BACK("back.png"),
    RICOVERO("ricovero.png");

	private static String PATH = "/images/";
    private String pathImage;
    
    IconImage(final String pathImage) {
        this.pathImage = pathImage;
    }
    
    public ImageIcon getImageIcon() {
        return new ImageIcon(this.getClass().getResource(PATH + this.pathImage));
    }
}
