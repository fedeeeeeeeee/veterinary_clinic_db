package view;

import java.nio.file.FileSystems;

import javax.swing.ImageIcon;

public enum IconImage {
	
	BACK("back.png"),
    RICOVERO("ricovero.png"),
	ASCENDING("sort_ascending_icon.png"),
	DESCENDING("sort_descending_icon.png"),
	DESCENDING_ICON("sort_ascending_icon.icns"),
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
