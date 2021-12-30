package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import model.Lotto;
import model.LottoImpl;
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
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.Sizes;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class NewLotto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private FarmaciController farmaciController;
	private LottoController lottoController;
	private Lotto lotto;
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
          		ColumnSpec.decode("max(100dlu;pref)"),
          		FormSpecs.RELATED_GAP_COLSPEC,
          		new ColumnSpec(ColumnSpec.CENTER, Sizes.bounded(Sizes.PREFERRED, Sizes.constant("277dlu", true), Sizes.constant("200dlu", true)), 1),
          		FormSpecs.RELATED_GAP_COLSPEC,
          		ColumnSpec.decode("max(100dlu;pref)"),},
          	new RowSpec[] {
          		FormSpecs.RELATED_GAP_ROWSPEC,
          		RowSpec.decode("default:grow"),
          		FormSpecs.RELATED_GAP_ROWSPEC,
          		RowSpec.decode("default:grow"),}));
          
          JPanel farmacoPanel = new JPanel();
          farmacoPanel.setBorder(new TitledBorder(
  				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
  				"Farmaco", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

          formPanel.add(farmacoPanel, "4, 2, center, fill");
          GridBagLayout gbl_farmacoPanel = new GridBagLayout();
          gbl_farmacoPanel.columnWidths = new int[]{149, 145, 0};
          gbl_farmacoPanel.rowHeights = new int[]{23, 49, 0};
          gbl_farmacoPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
          gbl_farmacoPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
          farmacoPanel.setLayout(gbl_farmacoPanel);
      	
      	JLabel lblFarmaco = new JLabel("Farmaco");
      	lblFarmaco.setHorizontalAlignment(SwingConstants.CENTER);
      	GridBagConstraints gbc_lblFarmaco = new GridBagConstraints();
      	gbc_lblFarmaco.fill = GridBagConstraints.BOTH;
      	gbc_lblFarmaco.insets = new Insets(0, 0, 5, 5);
      	gbc_lblFarmaco.gridx = 0;
      	gbc_lblFarmaco.gridy = 0;
      	farmacoPanel.add(lblFarmaco, gbc_lblFarmaco);
          
      	JComboBox<String> comboBox = new JComboBox<>();
      	
          GridBagConstraints gbc_comboBox = new GridBagConstraints();
          gbc_comboBox.fill = GridBagConstraints.BOTH;
          gbc_comboBox.insets = new Insets(0, 0, 5, 0);
          gbc_comboBox.gridx = 1;
          gbc_comboBox.gridy = 0;
          farmacoPanel.add(comboBox, gbc_comboBox);
          
    
          
          JLabel lblDescrizione = new JLabel("Descrizione");
          lblDescrizione.setHorizontalAlignment(SwingConstants.CENTER);
          GridBagConstraints gbc_lblDescrizione = new GridBagConstraints();
          gbc_lblDescrizione.fill = GridBagConstraints.BOTH;
          gbc_lblDescrizione.insets = new Insets(0, 0, 0, 5);
          gbc_lblDescrizione.gridx = 0;
          gbc_lblDescrizione.gridy = 1;
          farmacoPanel.add(lblDescrizione, gbc_lblDescrizione);
      	List<Farmaco> farmaci = lottoController.getAllDistinctFarmaci();
      	for (Farmaco farmaco : farmaci ) {
      		comboBox.addItem(farmaco.getCodFarmaco() + " - " + farmaco.getNomeFarmaco());
      	}
          //comboBox.getSelectedItem().get
          
          JTextPane textDescrizione = new JTextPane();
        	comboBox.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				Farmaco farmacoSelected = farmaci.get(comboBox.getSelectedIndex());
    				/*String farmacoSelected = comboBox.getSelectedItem().toString(); String[] parts = farmacoSelected.split(" - "); String part1 = parts[0]; // codFarmaco String part2 = parts[1]; // cod*/
    			textDescrizione.setText(farmacoSelected.getDescrizione());
    			}
    		});
          JScrollPane jsp = new JScrollPane(textDescrizione);
          GridBagConstraints gbc_jsp = new GridBagConstraints();
          gbc_jsp.fill = GridBagConstraints.BOTH;
          gbc_jsp.gridx = 1;
          gbc_jsp.gridy = 1;
          farmacoPanel.add(jsp, gbc_jsp);
          JPanel lottoPanel = new JPanel();
          lottoPanel.setBorder(new TitledBorder(
    				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
    				"Lotto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
          formPanel.add(lottoPanel, "4, 4, center, fill");
          lottoPanel.setLayout(new MigLayout("", "[217.00px][112.00][64.00px][-10.00]", "[20px][20px][20px]"));
          
          JLabel lblConfezioni = new JLabel("Confezioni");
          lblConfezioni.setHorizontalAlignment(SwingConstants.CENTER);
          lottoPanel.add(lblConfezioni, "cell 0 0,grow");
          
          JSpinner spinConfezioni = new JSpinner();
          lottoPanel.add(spinConfezioni, "cell 2 0,grow");
          
          JLabel lblQuantita = new JLabel("Quantità in Confezione");
          lblQuantita.setHorizontalAlignment(SwingConstants.CENTER);
          lottoPanel.add(lblQuantita, "cell 0 1,alignx center,growy");
          
          JSpinner spinner = new JSpinner();
          lottoPanel.add(spinner, "cell 2 1,grow");
          
          JLabel chooserDataScadenza = new JLabel("Data di Scadenza");
          chooserDataScadenza.setHorizontalAlignment(SwingConstants.CENTER);
          lottoPanel.add(chooserDataScadenza, "cell 0 2,grow");
          
          JDateChooser dateChooser = new JDateChooser();
          lottoPanel.add(dateChooser, "cell 1 2 2 1,grow");
          
          JPanel panel = new JPanel();
          centerPanel.add(panel, BorderLayout.SOUTH);
          
          JButton btnSave = new JButton("Salva");
          btnSave.addActionListener(new ActionListener(){
        		public void actionPerformed(ActionEvent e) {
        			if(dateChooser.getDate()!= null) {
        				lotto = new LottoImpl();
        				lotto.setCodLotto(0);
        				lotto.setNumeroConfezioni((int) spinConfezioni.getValue());
        				lotto.setQuantitaPerConfezione((int)spinner.getValue());
        			
        				lotto.setCodFarmaco(farmaci.get(comboBox.getSelectedIndex()).getCodFarmaco());
        				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    					String date = format.format(dateChooser.getDate());
        				lotto.setDataScadenza(date);
        				
        				lottoController.addNewLotto(lotto);
        				
        				 frame.getContentPane().add(new FarmaciPage(frame, user));
        			}
        		}
          });
          panel.add(btnSave);

		
          upperPanel.setLayout(new GridLayout(0, 1, 0, 0));
  		JLabel titleLabel = new JLabel("Nuovo Lotto");
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
