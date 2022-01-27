package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

import controller.AnimaleController;
import controller.AnimaleControllerImpl;
import controller.FarmaciController;
import controller.FarmaciControllerImpl;
import controller.PrestazioneController;
import controller.PrestazioneControllerImpl;
import controller.ProprietarioController;
import controller.ProprietarioControllerImpl;

public class ScaricaRefertoPrestazione {
	
	private ProprietarioController proprietarioController = new ProprietarioControllerImpl();
	private PrestazioneController prestazioneController = new PrestazioneControllerImpl();
	private AnimaleController animaleController = new AnimaleControllerImpl();
	private FarmaciController  farmaciController = new  FarmaciControllerImpl();
	public ScaricaRefertoPrestazione(JFrame frame, Appuntamento app) {

		String path = "";
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int x = j.showSaveDialog(frame);
		if (x == JFileChooser.APPROVE_OPTION) {
			path = j.getSelectedFile().getPath();
		}

		Document doc = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(path + ".pdf"));
			doc.open();

			// TITOLO 2 PROVA
			com.itextpdf.text.Font courier9RedFont = new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 25.0f, com.itextpdf.text.Font.BOLD,
					BaseColor.BLACK);
			Chunk c2 = new Chunk("Referto -  Clinica Veterinaria RagnPol", courier9RedFont);
			c2.setBackground(BaseColor.CYAN);
			Paragraph parTitolo = new Paragraph(c2);
			//parTitolo.setLeading(0);

			//parTitolo.setMultipliedLeading(0.9F);
			//parTitolo.setSpacingAfter(72f);
			PdfPTable taba = new PdfPTable(1);
			PdfPCell cellImportantNote = new PdfPCell(parTitolo);
			cellImportantNote.setBorderWidth(PdfPCell.NO_BORDER);
			taba.setWidthPercentage(100);
			taba.setHorizontalAlignment(Element.ALIGN_LEFT);
			taba.addCell(cellImportantNote);
			doc.add(taba);

			// TITOLO INFO Ricovero
			Paragraph parInfo = new Paragraph("");
			String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Paragraph parOggi = new Paragraph(data + " Cesena");
			doc.add(parInfo);
			parOggi.setSpacingAfter(40f);
			doc.add(parOggi);
			
			com.itextpdf.text.Font fontBlocchi = new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12.0f, com.itextpdf.text.Font.ITALIC, 
					BaseColor.BLACK);
			// DATI PAZIENTI E PROPRIETARI
			com.itextpdf.text.Font fontDati = new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 15.0f, com.itextpdf.text.Font.BOLD,
					BaseColor.BLACK);
			Chunk cDati = new Chunk("Dati paziente e proprietario:", fontDati);
			Paragraph parPaziente = new Paragraph(cDati);
			
			Person proprietario = proprietarioController
					.getProprietario(app.getAnimale().getCf());
			com.itextpdf.text.List listPaziente = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
			listPaziente.setListSymbol("");
			listPaziente.add(new ListItem(
					"Prestazioni per il paziente " + proprietario.getNome() + " il cui proprietario è : "));
			listPaziente.add(new ListItem(proprietario.getNome() + " " + proprietario.getCognome()));
			listPaziente.add(new ListItem("Telefono: " + proprietario.getTelefono()));
			listPaziente.add(new ListItem("Email: " + proprietario.getEmail()));
			listPaziente.add(new ListItem("Indirizzo di residenza: " + proprietario.getIndirizzoVia() + " "
					+ proprietario.getIndirizzoNumero() + " " + proprietario.getIndirizzoCap() + " ,"
					+ proprietario.getIndirizzoCitta()));
			parPaziente.add(listPaziente);
			parPaziente.setSpacingAfter(30f);
			doc.add(parPaziente);

			// ANAMESI MEDICA E PATOLOGICA
						com.itextpdf.text.Font fontRiepilogo = new com.itextpdf.text.Font(
								com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 15.0f, com.itextpdf.text.Font.BOLD,
								BaseColor.BLACK);
						Chunk cMedPat = new Chunk("Anamnesi medica e farmacologica prima della prestazione: ", fontRiepilogo);
						Paragraph parRiepilogo = new Paragraph(cMedPat);
						
						Chunk cBloccoPregresse = new Chunk("Si tengano in considerazione le seguenti patologie pregresse e i farmaci attualmente in uso : ", fontBlocchi);
						Paragraph parBloccoMedicine = new Paragraph(cBloccoPregresse);
						//Paragraph parBloccoMedicine = new Paragraph("Si tengano in considerazione le seguenti patologie pregresse e i farmaci attualmente in uso : ");
						parBloccoMedicine.setSpacingAfter(5f);
						doc.add(parBloccoMedicine);
						
						com.itextpdf.text.List listFarmaciPrecedenti = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
						listFarmaciPrecedenti.setListSymbol("");
					
						List<FarmacoInPrestazione> filteredList =  farmaciController.getFarmaciDiAnimaleTipoFarmaco(app.getAnimale().getCodAnimale()).stream().filter(f-> !f.getDataInizioSomministrazione().equals(app.getData())).collect(Collectors.toList());
						listFarmaciPrecedenti.add(new ListItem( "FARMACI"));
						for (FarmacoInPrestazione far :filteredList) {
						listFarmaciPrecedenti.add(new ListItem( far.getFarmaco().getNomeFarmaco() + " da " + far.getDataInizioSomministrazione() + " a " + far.getDataFineSomministrazione()));
						}
						List<Patologia> filteredList2 = prestazioneController.getPatologieAttiveDiAnimale(app).stream().filter(f-> !f.getDataInizio().equals(app.getData())).collect(Collectors.toList());
						listFarmaciPrecedenti.add(new ListItem( "PATOLOGIE"));
						for (Patologia pat :filteredList2) {
						listFarmaciPrecedenti.add(new ListItem( pat.getNomePatologia() + " diagnosticata il  " + pat.getDataInizio()));
						}
						parRiepilogo.add(listFarmaciPrecedenti);
						parRiepilogo.setSpacingAfter(30f);
						doc.add(parRiepilogo);
						
			// RIEPILOGO PRESTAZIONE 
			Chunk cTitoloPrestazione = new Chunk("Dettagli Prestazione:", fontDati);
			Paragraph parPrestazioneTitolo = new Paragraph(cTitoloPrestazione);
			com.itextpdf.text.Font fontParametri = new com.itextpdf.text.Font(
					com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12.0f, com.itextpdf.text.Font.BOLD,
					BaseColor.BLACK);
			doc.add(parPrestazioneTitolo);
			
			Chunk cParametroData = new Chunk(app.getData(), fontParametri);
			Paragraph parPrestazione = new Paragraph("In data " +cParametroData + " e ora " + app.getOra().substring(0,5) + " è stata eseguita un " + app.getTipoPrestazione() 
			+ " precisamente un " + app.getPrestazione() + ".  La prestazione ha avuto una durata " + app.getDurata() + " e ha previsto " + app.getDescrizionePrestazione());
			parPrestazione.setSpacingAfter(10f);
			doc.add(parPrestazione);
			
			Chunk cBloccoEsito = new Chunk("La prestazione ha avuto il seguento esito : ", fontBlocchi);
			Paragraph parBlocco = new Paragraph(cBloccoEsito);
			//Paragraph parBlocco = new Paragraph("La prestazione ha avuto il seguento esito : ");
			parBlocco.setSpacingAfter(5f);
			doc.add(parBlocco);
			// COMMENTO VETERINARIO
			Paragraph parCommento = new Paragraph("Il dottore " + app.getVeterinario().getCognome() + " " +  app.getVeterinario().getNome() + " riporta la seguente analisi " + 
			app.getDescrizioneReferto());
			parCommento.setSpacingAfter(30f);
			doc.add(parCommento);
			
			Chunk cBloccoNew = new Chunk("A seguito della seguente prestazione sono stati prescritti i seguenti farmaci e diagnosticate le seguenti patologie:  ", fontBlocchi);
			Paragraph parBloccoNuoviFarmaci = new Paragraph(cBloccoNew);
			parBloccoMedicine.setSpacingAfter(5f);
			doc.add(parBloccoNuoviFarmaci);
			
			//PRESCRITTI I SEGUENTI
			Chunk cMedPatPre = new Chunk("Farmaci prescritti e Patologie diagnosticate : ", fontRiepilogo);
			Paragraph parMedPatPre = new Paragraph(cMedPatPre);
			com.itextpdf.text.List listFarPatNew = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
			listFarPatNew.setListSymbol("");
			List<Farmaco> filteredList1 =	prestazioneController.getFarmaciInPrestazione(app);
			listFarPatNew.add(new ListItem( "FARMACI"));
			for (Farmaco far :filteredList1) {
				listFarPatNew.add(new ListItem( far.getNomeFarmaco() + " da " + far.getDataInizioSomministrazione() + " a " + far.getDataFineSomministrazione()) + 
						" con le seguenti indicazioni: " + far.getIndicazioni());
			}
			Paziente paz = new PazienteImpl();
			paz.setAnimale(app.getAnimale());
			filteredList2 =animaleController.getAllPatologiePaziente(paz);
			listFarPatNew.add(new ListItem( "PATOLOGIE"));
			for (Patologia pat :filteredList2) {
				listFarPatNew.add(new ListItem( pat.getNomePatologia() + " diagnosticata il  " + pat.getDataInizio()));
				}
			parMedPatPre.add(listFarPatNew);
			parMedPatPre.setSpacingAfter(30f);
			doc.add(parMedPatPre);
			
			// DOTTOR TIZIO 
			doc.close();
			writer.close();
		} catch (FileNotFoundException | DocumentException e1) {
			System.out.println("ERRORE - creazione pdf");
			e1.printStackTrace();
		}
	}

}
