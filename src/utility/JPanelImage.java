package utility;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.awt.AlphaComposite;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private String image = "";
	public JPanelImage(String image) {
		this.image = image;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		String fileSeparator = FileSystems.getDefault().getSeparator();
		File pathToFile = new File("resources" + fileSeparator +"images" + fileSeparator + this.image);
		Image image;
		try {
			image = ImageIO.read(pathToFile);
			//float opacity = 0.5f;
			g2d.drawImage(image, 0, 0, null);
			//g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
