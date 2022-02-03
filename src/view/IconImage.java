package view;

import java.nio.file.FileSystems;

import javax.swing.ImageIcon;

public enum IconImage {
	
	BACK("back.png"),
	ARROW("arrows-sort.png"),
	INFO("info.png"),
	SEARCH("search.png"),
	ADD("add.png"),
	ADD_RICOVERO("add_ricovero.png"),
	ARROW_SX("arrow_sx.png"),
	ARROW_DX("arrow_dx.png"), 
	GESTIONE_PRESTAZIONI("gestione_prestazioni.png"),
	RICOVERI("ricoveri.png"),
	REGISTRA_PAZIENTE("registra_paziente.png"), 
	PAZIENTI("pazienti.png"),
	MAGAZZINO("magazzino.png"),
	PAGAMENTI("pagamenti.png"),
	LOGO("vet.png"),
	AGGIUNGI_PRESTAZIONI("aggiungi_prestazioni.png"),
	APPUNTAMENTI("appuntamento.png"),
	HOME("home.png"),
	USER("user.png"),
	LOGOUT("logout.png"), 
	REMOVE("meno.png"),;
	
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
