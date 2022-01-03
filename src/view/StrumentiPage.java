package view;

/* completo*/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import controller.StrumentiController;
import controller.StrumentiControllerImpl;
import model.Strumento;
import model.User;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StrumentiPage extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private JTextField searchBar;
	private JTable table;
	private JScrollPane scrollPane;
	private StrumentiController strumentiController;
	private List<Strumento> listStrumenti;
	private int selectedStrumento;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public StrumentiPage(JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.strumentiController = new StrumentiControllerImpl();
		this.selectedStrumento = -1;
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
								.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE)
								.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 1000, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lowerPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
//------MENU BAR------
		middlePanel.setLayout(new BorderLayout(0, 0));
		JPanel upperMiddlePanel = new JPanel();
		upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
		middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
		upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("900px"), },
				new RowSpec[] { RowSpec.decode("25px"), }));
		JMenuBar menuBar = new JMenuBar();
//frame.setJMenuBar(menuBar);
		upperMiddlePanel.add(menuBar, "1, 1, fill, top");
		JMenu menuMostra = new JMenu("Mostra");
		JMenu menuAggiungi = new JMenu("Aggiungi");

		searchBar = new JTextField();
		searchBar.setText("Ricerca Strumento");
		searchBar.addFocusListener((FocusListener) new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchBar.getText().equals("Ricerca Strumento")) {
					searchBar.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchBar.getText().equals("")) {
					searchBar.setText("Ricerca Strumento");
				}
			}
		});
		searchBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//if(searchBar.getText().equals("Ricerca Strumento")){
				searchBar.setText("");
// }
			}
		});
		JButton buttonVai = new JButton("Vai");
		buttonVai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search(e);
			}
		});
		buttonVai.setBackground(Color.LIGHT_GRAY);
		buttonVai.setForeground(Color.BLACK);
		menuBar.add(menuMostra);
		menuBar.add(menuAggiungi);
		menuBar.add(searchBar);
		menuBar.add(buttonVai);
		JButton orderAsc = new JButton(IconImage.ARROW.getImageIcon());
		orderAsc.setBackground(Color.LIGHT_GRAY);
		orderAsc.setForeground(Color.BLACK);
		menuBar.add(orderAsc);
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
		upperPanel.setLayout(null);
		JLabel titleLabel = new JLabel("Magazzino");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		upperPanel.add(titleLabel);
		/*-------------- TABLE */
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);
		
		this.scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		this.initTable();
		this.listStrumenti = strumentiController.getAllStrumenti();
		this.createTable(model, listStrumenti);
//table.setModel(model);
		scrollPane.setViewportView(table);
		
/*------fine-------- table */
// -----inizio ----- MENUU--- listener
		JMenuItem itemScaduti = new JMenuItem("Scaduti"); // , new ImageIcon("cut-java-image.gif"));
		JMenuItem itemInScadenza = new JMenuItem("In Scadenza");
		JMenuItem itemInEsaurimento = new JMenuItem("In Esaurimento");
		JMenuItem all = new JMenuItem("Tutti");
		JMenuItem itemAddStrumento = new JMenuItem("Aggiungi Strumento");
		JMenuItem itemModQuantita = new JMenuItem("Aggiungere Quantita");
// itemModQuantita.setIcon(null);
		itemScaduti.addActionListener((e) -> {
			this.resetAndDraw(strumentiController.getStrumentiScaduti());
		});
		itemInScadenza.addActionListener((e) -> {
			this.resetAndDraw(strumentiController.getStrumentiInScadenza());
		});
		itemInEsaurimento.addActionListener((e) -> {
			this.resetAndDraw(strumentiController.getStrumentiInEsaurimento());
		});
		all.addActionListener((e) -> {
			this.resetAndDraw(strumentiController.getAllStrumenti());
		});
		itemModQuantita.addActionListener((e) -> {
			if (this.selectedStrumento != -1) {
				String nome = listStrumenti.get(selectedStrumento).getNome();
				Integer[] options = { 5, 10, 20, 30, 50, 100 };
				Object q = JOptionPane.showInputDialog(null, "Quanti " + nome + " vuoi aggiungere ?",
						"Update magazzino", JOptionPane.QUESTION_MESSAGE, null, options, 0);
				if (q != null) { // x o annulla
					int quantità = (int) q;
					strumentiController.updateQuantità(listStrumenti.get(selectedStrumento), quantità);
					this.selectedStrumento = -1;
					resetAndDraw(strumentiController.getAllStrumenti());
				}
				this.selectedStrumento = -1;
				this.resetAndDraw(strumentiController.getAllStrumenti());
			} else {
				JOptionPane.showMessageDialog(null, "Non hai selezionato alcuno strumento ");
			}
		});
		itemAddStrumento.addActionListener((e) -> {
			frame.getContentPane().add(new NuovoStrumentoPage(frame, user));
		});
		menuMostra.add(all);
		menuMostra.add(itemScaduti);
		menuMostra.add(itemInScadenza);
		menuMostra.addSeparator();
		menuMostra.add(itemInEsaurimento);
		menuAggiungi.add(itemAddStrumento);
		menuAggiungi.add(itemModQuantita);
//----fine ---- MENUU
		/* LOWER PANEL */
		JButton sendButton = new JButton("BACK");
		sendButton.setHorizontalAlignment(SwingConstants.LEFT);
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().add(new MagazzinoPage(frame, user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(sendButton, BorderLayout.WEST);
//setLayout(groupLayout);
		new GroupLayout(this);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void resetAndDraw(List<Strumento> allStrumenti) {
		this.listStrumenti.clear();
		this.listStrumenti = allStrumenti;
//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listStrumenti);
		scrollPane.setViewportView(table);
	}

	private void createTable(DefaultTableModel model, List<Strumento> list) {
		list.forEach(x -> {
			model.addRow(new Object[] { x.getCodStrumento(), x.getNome(), x.getDescrizione(), x.getDataScadenza(),
					x.getQuantità(), });
		});
	}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Codice");
		model.addColumn("Nome");
		model.addColumn("Descrizione");
		model.addColumn("Scadenza");
		model.addColumn("QuantitÃ ");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				selectedStrumento = event.getFirstIndex();
				System.out.println(selectedStrumento);
			}
		});
	}

	public void search(ActionEvent e) { // action
		List<Strumento> lista = new ArrayList<Strumento>();
//listStrumenti.clear();
		lista.addAll(searchList(searchBar.getText(), listStrumenti));
		this.resetAndDraw(lista);
	}

	private List<Strumento> searchList(String searchWords, List<Strumento> listOfStrumento) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return listOfStrumento.stream().filter(input -> {
			return searchWordsArray.stream()
					.allMatch(word -> input.getNome().toLowerCase().contains(word.toLowerCase()));
		}).collect(Collectors.toList());
	}
}
