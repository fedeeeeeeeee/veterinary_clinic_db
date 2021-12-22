package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import model.Farmaco;
import model.User;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import controller.LottoController;
import controller.LottoControllerImpl;

public class NewLotto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private FarmaciController farmaciController;
	private LottoController lottoController;
	/**
	 * Create the panel.
	 */
	public NewLotto(JFrame frame, User user)  {

		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.farmaciController = new FarmaciControllerImpl();
		this.lottoController = new LottoControllerImpl();
		
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel centerPanel = new JPanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
        //GroupLayout groupLayout = new GroupLayout(this);
          groupLayout.setHorizontalGroup(
          	groupLayout.createParallelGroup(Alignment.LEADING)
          		.addGroup(groupLayout.createSequentialGroup()
          			.addContainerGap()
          			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
          				.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
          				.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
          				.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
          			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );
          groupLayout.setVerticalGroup(
          	groupLayout.createParallelGroup(Alignment.LEADING)
          		.addGroup(groupLayout.createSequentialGroup()
          			.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
          			.addPreferredGap(ComponentPlacement.RELATED)
          			.addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
          			.addPreferredGap(ComponentPlacement.RELATED)
          			.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
          			.addContainerGap())
          );
          centerPanel.setLayout(new BorderLayout(0, 0));
          
          JPanel formPanel = new JPanel();
          formPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
          centerPanel.add(formPanel, BorderLayout.NORTH);
          formPanel.setLayout(new FormLayout(new ColumnSpec[] {
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,
          		FormSpecs.RELATED_GAP_COLSPEC,
          		ColumnSpec.decode("default:grow"),
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,
          		FormSpecs.RELATED_GAP_COLSPEC,
          		FormSpecs.DEFAULT_COLSPEC,},
          	new RowSpec[] {
          		FormSpecs.RELATED_GAP_ROWSPEC,
          		RowSpec.decode("default:grow"),
          		FormSpecs.RELATED_GAP_ROWSPEC,
          		RowSpec.decode("default:grow"),}));
          
          JPanel farmacoPanel = new JPanel();
          farmacoPanel.setBorder(new TitledBorder(
  				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
  				"Farmaco", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

          formPanel.add(farmacoPanel, "8, 2, fill, fill");
          farmacoPanel.setLayout(new GridLayout(2, 2, 0, 0));
          
          JLabel lblFarmaco = new JLabel("Farmaco");
          farmacoPanel.add(lblFarmaco);
          
      	JComboBox<String> comboBox = new JComboBox<>();
      	
          farmacoPanel.add(comboBox);
      	List<Farmaco> farmaci = lottoController.getAllDistinctFarmaci();
      	for (Farmaco farmaco : farmaci ) {
      		comboBox.addItem(farmaco.getCodFarmaco() + " - " + farmaco.getNomeFarmaco());
      	}
	
          JLabel lblDescrizione = new JLabel("Descrizione");
          farmacoPanel.add(lblDescrizione);
          //comboBox.getSelectedItem().get
          
          JTextPane textDescrizione = new JTextPane();
          JScrollPane jsp = new JScrollPane(textDescrizione);
          farmacoPanel.add(jsp);
          
      	comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Farmaco farmacoSelected = farmaci.get(comboBox.getSelectedIndex());
				/*String farmacoSelected = comboBox.getSelectedItem().toString(); String[] parts = farmacoSelected.split(" - "); String part1 = parts[0]; // codFarmaco String part2 = parts[1]; // cod*/
			textDescrizione.setText(farmacoSelected.getDescrizione());
			}
		});
          JPanel lottoPanel = new JPanel();
          lottoPanel.setBorder(new TitledBorder(
    				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
    				"Lotto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
          formPanel.add(lottoPanel, "8, 4, fill, fill");
          lottoPanel.setLayout(new GridLayout(3, 2, 0, 0));
          
          JLabel lblConfezioni = new JLabel("Confezioni");
          lottoPanel.add(lblConfezioni);
          
          JSpinner spinConfezioni = new JSpinner();
          lottoPanel.add(spinConfezioni);
          
          JLabel lblQuantita = new JLabel("Quantità in Confezione");
          lottoPanel.add(lblQuantita);
          
          JSpinner spinner = new JSpinner();
          lottoPanel.add(spinner);
          
          JLabel chooserDataScadenza = new JLabel("Data di Scadenza");
          lottoPanel.add(chooserDataScadenza);
          
          JDateChooser dateChooser = new JDateChooser();
          lottoPanel.add(dateChooser);
          
          JPanel panel = new JPanel();
          centerPanel.add(panel, BorderLayout.SOUTH);
          
          JButton btnSave = new JButton("Salva");
          panel.add(btnSave);

		
          upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
  		JLabel titleLabel = new JLabel("Magazzino");
  		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
  		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
  		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		
		/*LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.getContentPane().add(new HomeSegretaria(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
		
		//setLayout(groupLayout);
	new GroupLayout(this);
	frame.getContentPane().setLayout(groupLayout);
	}
}
