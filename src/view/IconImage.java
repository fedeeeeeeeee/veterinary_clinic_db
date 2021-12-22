package view;

import java.nio.file.FileSystems;

import javax.swing.ImageIcon;

public enum IconImage {
	
	BACK("back.png"),
    RICOVERO("ricovero.png"),
	ARROW("arrows-sort.png");
	
	private static String PATH = "resources/images/";
	private String path;
    private String pathImage;
    String fileSeparator;

    IconImage(final String pathImage) {
        this.pathImage = pathImage;
        String fileSeparator = FileSystems.getDefault().getSeparator();
        path = "resources" + fileSeparator + "images"+ fileSeparator;
    }
    
    public ImageIcon getImageIcon() {
    	return new ImageIcon(path + this.pathImage);
    	//return new ImageIcon(this.getClass().getResource(PATH + this.pathImage));
        
    }
}
