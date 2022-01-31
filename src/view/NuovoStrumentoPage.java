package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import model.Strumento;
import model.StrumentoImpl;
import model.User;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import utility.ValidityFieldsChecker;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import controller.StrumentiController;
import controller.StrumentiControllerImpl;

import javax.swing.JSpinner;

public class NuovoStrumentoPage extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private StrumentiController strumentiController;
	private JTextField textNomeFarmaco;
	private JTextField textFieldPrezzo;
	private ValidityFieldsChecker checker = new ValidityFieldsChecker();
	
	public NuovoStrumentoPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.strumentiController = new StrumentiControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		middlePanel.setLayout(new BorderLayout(0, 0));

		JPanel panelForm = new JPanel();
		middlePanel.add(panelForm, BorderLayout.CENTER);
		panelForm.setLayout(new MigLayout("", "[grow][474px,grow,fill][grow]", "[grow][201px][grow]"));

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		Border border = panel.getBorder();
		Border margin = new EmptyBorder(20, 10, 20, 10);
		panel.setBorder(new CompoundBorder(border, margin));
		panelForm.add(panel, "cell 1 1,alignx center,aligny top");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, -40, 0, 52, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 10, 0, 10, 0, 0, 10, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 5.0, 1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_1 = new JLabel("Nome Strumento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textNomeFarmaco = new JTextField();
		GridBagConstraints gbc_textNomeFarmaco = new GridBagConstraints();
		gbc_textNomeFarmaco.gridwidth = 3;
		gbc_textNomeFarmaco.anchor = GridBagConstraints.NORTH;
		gbc_textNomeFarmaco.insets = new Insets(0, 0, 5, 5);
		gbc_textNomeFarmaco.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNomeFarmaco.gridx = 2;
		gbc_textNomeFarmaco.gridy = 1;
		panel.add(textNomeFarmaco, gbc_textNomeFarmaco);
		textNomeFarmaco.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Descrizione");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));

		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 7;
		gbc_scrollPane.gridy = 1;
		panel.add(scrollPane, gbc_scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JLabel lblDataScadenza = new JLabel("Data Scadenza");
		lblDataScadenza.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblDataScadenza.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblDataScadenza = new GridBagConstraints();
		gbc_lblDataScadenza.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataScadenza.gridx = 1;
		gbc_lblDataScadenza.gridy = 3;
		panel.add(lblDataScadenza, gbc_lblDataScadenza);

		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 3;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 3;
		panel.add(dateChooser, gbc_dateChooser);

		JLabel lblNewLabel = new JLabel("Disponibilità");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 7;
		panel.add(spinner, gbc_spinner);

		JLabel lblNewLabel_4 = new JLabel("Prezzo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));

		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 7;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		textFieldPrezzo = new JTextField();
		GridBagConstraints gbc_textFieldPrezzo = new GridBagConstraints();
		gbc_textFieldPrezzo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrezzo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrezzo.gridx = 5;
		gbc_textFieldPrezzo.gridy = 7;
		panel.add(textFieldPrezzo, gbc_textFieldPrezzo);
		textFieldPrezzo.setColumns(10);

		JPanel panelSave = new JPanel();
		middlePanel.add(panelSave, BorderLayout.SOUTH);

		JButton btnSave = new JButton("Save");

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textFieldPrezzo.getText().isEmpty() && spinner.getValue() !=null 
						&&  dateChooser.getDate() != null && !textArea.getText().isEmpty() && !textNomeFarmaco.getText().isEmpty()) {
					if(checker.checkFloatFieldsValue(textFieldPrezzo.getText())) {
						Strumento stru = new StrumentoImpl();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String date = df.format(dateChooser.getDate());
						stru.setDataScadenza(date);
						stru.setDescrizione(textArea.getText());
						stru.setNome(textNomeFarmaco.getText());
						stru.setQuantita( (Integer)spinner.getValue());
						stru.setPrezzo(Float.valueOf(textFieldPrezzo.getText()));
						strumentiController.addNewStrumento(stru);
						frame.getContentPane().add(new StrumentiPage(frame, user));
					} else {
						JOptionPane.showMessageDialog(frame, "Controllarre la validità dei campi! Ad esempio che il prezzo sia un valore decimale (es 2.30) ",
								"Attenzione!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Controllare che tutti i campi compilati tutti i campi ",
							"Attenzione!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelSave.add(btnSave);

		/* UPPER PANEL AND TITLE */
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel =new JLabel("<html><font color='#56728f '>Nuovo Strumento</font></html>");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/* LOWER PANEL */
		  Icon icon = IconImage.BACK.getImageIcon();
				JButton backButton = new JButton(icon);
				backButton.setBorder(BorderFactory.createEmptyBorder());
				backButton.setContentAreaFilled(false);
				backButton.setHorizontalAlignment(SwingConstants.LEFT);
				backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new StrumentiPage(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);

		//setLayout(groupLayout);
		//new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}
