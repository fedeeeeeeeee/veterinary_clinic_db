package view;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Pagamento;
import model.Person;
import model.User;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ListItem;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import controller.PagamentiController;
import controller.PagamentiControllerImpl;
import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;

public class RiepilogoPagamenti extends CommonView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private User user;
	private List<Pagamento> listPagamenti;
	private DefaultTableModel model;
 	private PagamentiController pagamentiController;
 	private ProprietarioController proprietarioController;
	private JTable table;	
	private JScrollPane scrollPane;
	private JTextField textFieldTotale;
	private int selectedPagamento = -1;



	private void resetAndDraw(List<Pagamento> list) {
		this.listPagamenti.clear();
		this.listPagamenti = list;
			//table.clearSelection();
		model.setRowCount(0);
		this.createTable(model, listPagamenti);
		//scrollPane.setViewportView(table);
	}

		private void createTable(DefaultTableModel model, List<Pagamento> listPagamenti) {
			listPagamenti.forEach(x -> {
				model.addRow(new Object[] { 
						x.getAnimale().getNome(), 
						x.getAnimale().getSpecie(),
						x.getAnimale().getCf(),
						x.getDataPrestazione(),
						x.getNomePrestazione(),
						x.getCosto() + "�",
						x.isDuranteRicovero() ? "Si" : "No"
				});
			});
		}
		
		private void createTablePdf(DefaultTableModel model, List<Pagamento> listPagamenti) {
			listPagamenti.forEach(x -> {
				model.addRow(new Object[] { 
						x.getDataPrestazione(),
						x.getNomePrestazione(),
						x.getCosto() + "�",
				});
			});
		}

	private void initTable() {
		this.table = new JTable();
		this.model = (DefaultTableModel) table.getModel();
		model.addColumn("Animale");
		model.addColumn("Specie");
		model.addColumn("Proprietario");
		model.addColumn("Data");
		model.addColumn("Nome Prestazione");
		model.addColumn("Costo"); 
		model.addColumn("Prevede ricovero");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				selectedPagamento = table.getSelectedRow();
			}
		});
	}
	
	public RiepilogoPagamenti(final JFrame frame, User user) {
		frame.getContentPane().removeAll();
		frame.getContentPane().validate();
		frame.getContentPane().repaint();
		this.frame = frame;
		this.user = user;
		this.pagamentiController = new PagamentiControllerImpl();
		this.proprietarioController =  new ProprietarioControllerImpl();
		JPanel upperPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		JPanel lowerPanel = new JPanel();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		//GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(middlePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
							.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)))
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
					.addGap(44)
					.addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(lowerPanel, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
					.addGap(0))
		);
		
		middlePanel.setLayout(new BorderLayout(0, 0));
		JPanel upperMiddlePanel = new JPanel();
		upperMiddlePanel.setBorder(new EmptyBorder(0, 10, 25, 10));
		middlePanel.add(upperMiddlePanel, BorderLayout.NORTH);
		
		upperMiddlePanel.setLayout(new FormLayout(new ColumnSpec[] { ColumnSpec.decode("900px"), },
				new RowSpec[] { RowSpec.decode("25px"), }));
		
		JMenuBar menuBar = new JMenuBar();
		upperMiddlePanel.add(menuBar, "1, 1, fill, top");
		JMenu menuMostra = new JMenu("Mostra");
		JMenu menuAzione = new JMenu("Azioni");
		menuBar.add(menuMostra);
		menuBar.add(menuAzione);
		        
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		middlePanel.add(centerPanel, BorderLayout.CENTER);
		
		upperPanel.setLayout(new BorderLayout(0, 0));
		JLabel titleLabel = new JLabel("Pagamenti");
		titleLabel.setBounds(409, 58, 194, 52);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		upperPanel.add(titleLabel, BorderLayout.CENTER);

		/*------TABLE */
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_centerPanel.rowHeights = new int[]{0, 0, 0};
		gbl_centerPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		centerPanel.setLayout(gbl_centerPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		centerPanel.add(scrollPane, gbc_scrollPane);
		
		this.initTable();
		listPagamenti = pagamentiController.getAllPagamenti();
		this.createTable(model, listPagamenti);
		scrollPane.setViewportView(table);
		
		/*------fine-------- table */
		
		
		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		middlePanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		textFieldTotale = new JTextField();
		textFieldTotale.setEditable(false);
		panel.add(textFieldTotale);
		textFieldTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldTotale.setColumns(10);
		
		JLabel lblTotale = new JLabel("Totale");
		lblTotale.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblTotale);
		lblTotale.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		// -----inizio ----- MENUU--- listener
			JMenuItem itemOggi = new JMenuItem("Oggi"); // , new ImageIcon("cut-java-image.gif"));
			JMenuItem itemMese = new JMenuItem("Mensili");
			JMenuItem itemAnno = new JMenuItem("Annuali");
			JMenuItem itemTutti = new JMenuItem("Tutti");
			JMenuItem itemStampa = new JMenuItem("Stampa");
			JMenuItem itemPeriodo = new JMenuItem("Periodo");
			DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE; //("yyyy-MM-dd")
			String dataformat = formatter.format(LocalDateTime.now());
			textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			itemOggi.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiOggi());
				textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			});
			itemMese.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiMensili());
				textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			});
			itemAnno.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getPagamentiAnnuali());
				textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			});
			
			itemTutti.addActionListener((e) -> {
				this.resetAndDraw(pagamentiController.getAllPagamenti());
				textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			});
			itemPeriodo.addActionListener((e) -> {
			OptionPeriodo panelPeriod =  new OptionPeriodo();
			   int result =  JOptionPane.showConfirmDialog(null,
					   panelPeriod,
                        "Periodo Pagamenti : ",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
			    if (result == JOptionPane.OK_OPTION) {
			        System.out.println("x value: " + panelPeriod.getDataInizio());
			        System.out.println("y value: " + panelPeriod.getDataFine());
			      }
				this.resetAndDraw(pagamentiController.getPagamentiInPeriodo(panelPeriod.getDataInizio(), panelPeriod.getDataFine())); 
				textFieldTotale.setText(String.valueOf(this.listPagamenti.stream().mapToDouble(x-> x.getCosto()).sum())+ " �");
			});
		
				
			itemStampa.addActionListener((e) -> {
				if (this.selectedPagamento != -1) {
				
					String path= ""; 
					JFileChooser j = new JFileChooser();
					j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int x=j.showSaveDialog(this);
					if(x==JFileChooser.APPROVE_OPTION) {
						path=j.getSelectedFile().getPath();
					}
					
					Document doc = new Document();
					try {
						PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path +".pdf"));
						doc.open();
						
						// TITOLO 1 PROVA
						/*
						com.itextpdf.text.Font f = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 25.0f, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
						Chunk c = new Chunk("Total Cost:" ,f);
						c.setBackground(BaseColor.RED);
						Paragraph parTitoloColored = new Paragraph(c);
						doc.add(parTitoloColored);
						*/
						// TITOLO 2 PROVA
						com.itextpdf.text.Font courier9RedFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 25.0f, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
						//Paragraph parTitolo = new Paragraph("Fattura Clinica Veterinaria",courier9RedFont);
						Chunk c2 = new Chunk("Fattura Clinica Veterinaria:" ,courier9RedFont);
						c2.setBackground(BaseColor.CYAN);
						Paragraph parTitolo = new Paragraph(c2);
						parTitolo.setLeading(0);
						
						parTitolo.setMultipliedLeading(0.9F);
						parTitolo.setSpacingAfter(72f);
						PdfPTable taba = new PdfPTable(1);
			            PdfPCell cellImportantNote = new PdfPCell(parTitolo);
			            cellImportantNote.setBorderWidth( PdfPCell.NO_BORDER);
			            taba.setWidthPercentage(100);
			            taba.setHorizontalAlignment(Element.ALIGN_LEFT);
			            taba.addCell(cellImportantNote);
			            doc.add(taba);
			            
			        	// TITOLO INFO FATTIRA
						Paragraph parInfo = new Paragraph("");
						parInfo.getIndentationRight();
						//parInfo.setAlignment(Element.ALIGN_RIGHT);
						String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						 Paragraph parOggi = new Paragraph(data + " Cesena" );
						doc.add(parInfo);
						parOggi.setSpacingAfter(50f);
						doc.add(parOggi);
						/*
						 Paragraph p = new Paragraph("");
						// p.setSpacingAfter(72f);
					        DottedLineSeparator dottedline = new DottedLineSeparator();
					        dottedline.setOffset(-2);
					        dottedline.setGap(2f);
					        p.add(dottedline);
					        doc.add(p);
					        */
						com.itextpdf.text.Font fontDati = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 15.0f, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
						Chunk cDati = new Chunk("Dati paziente e proprietario:" ,fontDati);
						Paragraph parPaziente = new Paragraph(cDati);
						Person proprietario = proprietarioController.getProprietario( listPagamenti.get(selectedPagamento).getAnimale().getCf());
						com.itextpdf.text.List listPaziente =new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
						listPaziente.setListSymbol("");
						listPaziente.add(new ListItem("Prestazioni per il paziente " +  proprietario.getNome() + " il cui proprietario � : "));
						listPaziente.add(new ListItem(proprietario.getNome()
								+" "+  proprietario.getCognome()));
						listPaziente.add(new ListItem("Telefono: " + proprietario.getTelefono()));
						listPaziente.add(new ListItem("Email: " + proprietario.getEmail()));
						listPaziente.add(new ListItem("Indirizzo di residenza: " + proprietario.getIndirizzoVia() + 
								 " " + proprietario.getIndirizzoNumero() + " " + proprietario.getIndirizzoCap()+ " ," 
								+ proprietario.getIndirizzoCitta() ));
						parPaziente.add(listPaziente);
						parPaziente.setSpacingAfter(72f);
						doc.add(parPaziente);

						
						com.itextpdf.text.Font fontRiepilogo = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 15.0f, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
						Chunk cRiepilogo = new Chunk("Riepilogo pagamenti:" ,fontRiepilogo);
						Paragraph parRiepilogo = new Paragraph(cRiepilogo);
						doc.add(parRiepilogo);
						// TABLE RIEPILOGO DATI
						PdfPTable tbl = new PdfPTable(3);
						tbl.setWidthPercentage(105);
						tbl.setSpacingBefore(11f);
						tbl.setSpacingAfter(11f);
						tbl.addCell("Data");
						tbl.addCell("Nome Prestazione");
						tbl.addCell("Costo");
						JTable tableForPDF = new JTable();
						DefaultTableModel modelPDF = new DefaultTableModel();
						modelPDF = (DefaultTableModel) tableForPDF.getModel();
						modelPDF.addColumn("Data");
						modelPDF.addColumn("Nome Prestazione");
						modelPDF.addColumn("Costo");
						List<Pagamento> ricoveri = new ArrayList<>();
						if(listPagamenti.get(selectedPagamento).isDuranteRicovero()) {
							ricoveri = pagamentiController.getPrestazioniDuranteRicovero(listPagamenti.get(selectedPagamento));
						} else { 
							ricoveri.add(listPagamenti.get(selectedPagamento));
						}
						this.createTablePdf(modelPDF, ricoveri);
						for ( int i = 0; i < tableForPDF.getRowCount(); i ++) {
							tbl.addCell(tableForPDF.getValueAt(i,0).toString());
							tbl.addCell(tableForPDF.getValueAt(i,1).toString());
							tbl.addCell(tableForPDF.getValueAt(i,2).toString());
						}
						tbl.addCell(tableForPDF.getValueAt(tableForPDF.getRowCount()-1,0).toString());
						tbl.addCell("Ricovero");
						tbl.addCell(String.valueOf(listPagamenti.get(selectedPagamento).getCosto()- ricoveri.stream().mapToDouble(r-> r.getCosto()).sum()) + "�");
						doc.add(tbl);
						
						// PARAGRAFO TOTALE
						com.itextpdf.text.Font fontTotale = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 15.0f, com.itextpdf.text.Font.BOLD, BaseColor.WHITE);
						Chunk cTotale = new Chunk("Dati paziente e proprietario:" + table.getValueAt(selectedPagamento, 5) ,fontTotale);
						Paragraph parTotale = new Paragraph(cTotale);
						doc.add(parTotale);
						
						
						doc.close();
						writer.close();
					} catch (FileNotFoundException | DocumentException e1) {
						System.out.println("ERRORE - creazione pdf");
						e1.printStackTrace();
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "Non hai selezionato alcun pagamento");
				}
			});
			menuMostra.add(itemTutti);
			menuMostra.add(itemOggi);
			menuMostra.add(itemAnno);
			menuMostra.add(itemMese);
			menuAzione.add(itemStampa);
			menuMostra.add(itemPeriodo);
			
	
			//----fine ---- MENUU
			
		JButton backButton = new JButton("BACK");
		backButton.setHorizontalAlignment(SwingConstants.LEFT);
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  frame.getContentPane().add(new HomeSegretaria(frame,user));
			}
		});
		lowerPanel.setLayout(new BorderLayout(0, 0));
		lowerPanel.add(backButton, BorderLayout.WEST);
				
		//setLayout(groupLayout);
		frame.getContentPane().setLayout(groupLayout);
		this.initCommonView(frame, user);

	}
}
