package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Animale;
import model.AnimaleImpl;
import model.ParametriPaziente;
import model.ParametriPazienteImpl;
import model.Patologia;
import model.PatologiaImpl;
import model.Paziente;
import model.PazienteImpl;
import model.Person;
import model.Proprietario;

public class PazientiTableImpl implements PazientiTable {

	private DatabaseConnection dataSource;
	private String pazientiTableName;
	
	public PazientiTableImpl() {
		this.dataSource = new DatabaseConnection();
		this.pazientiTableName = "animale";
	}
	
	
	
	@Override
	public List<Paziente> getAllPazienti() {
		List<Paziente> list = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "SELECT * FROM " + pazientiTableName + " INNER JOIN proprietario on animale.cf = proprietario.cf";

		try {			
			statement = Optional.of(connection.prepareStatement(query));

			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				Paziente paziente = new PazienteImpl();
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setAltezza(result.getFloat("altezza"));
				animale.setCodAnimale(result.getInt("codAnimale"));
				animale.setGenere(result.getString("genere"));
				animale.setPeso(result.getFloat("peso"));
				animale.setRazza(result.getString("razza"));
				animale.setSpecie(result.getString("specie"));
				animale.setDataDiNascita(result.getString("dataDiNascita"));

				Person proprietario = new Proprietario();
				proprietario.setCF(result.getString("cf"));
				proprietario.setNome(result.getString("proprietario.nome"));
				proprietario.setCognome(result.getString("cognome"));
				proprietario.setTelefono(result.getLong("telefono"));
				proprietario.setIndirizzoVia(result.getString("ind_via"));
				proprietario.setIndirizzoNumero(result.getInt("ind_numero"));
				proprietario.setIndirizzoCitta(result.getString("ind_citta"));
				proprietario.setIndirizzoCap(result.getInt("ind_cap"));
				proprietario.setEmail(result.getString("e_mail"));
				
				animale.setProprietario(proprietario);	
				paziente.setAnimale(animale);
				list.add(paziente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return list;
	}



	@Override
	public List<Patologia> getAllPatologiePaziente(Paziente paziente) {
		List<Patologia> list = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "SELECT * FROM patologia "
				+ "INNER JOIN patologia_in_prestazione "
				+ "on patologia.codPatologia = patologia_in_prestazione.codPatologia "
				+ "where codAnimale = ? "
				+ "order by data ";
//CONTROLLA 
		try {			
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, paziente.getAnimale().getCodAnimale());
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
			Patologia pat = new PatologiaImpl();
			pat.setCodPatologia(0);
			pat.setCodTipoPrestazione(result.getString("patologia.codPatologia"));
			pat.setDataPrestazione(result.getString("patologia_in_prestazione.data"));
			pat.setDataInizio(result.getString("patologia_in_prestazione.dataInizio"));
		if(result.getString("patologia_in_prestazione.dataFine")==null) {
			pat.setDataFine(" ");
		}else { 
			pat.setDataFine(result.getString("patologia_in_prestazione.dataFine"));
		}
			//pat.setDataFine(result.getString("patologia_in_prestazione.dataFine"));
			pat.setDescrizione(result.getString("patologia.descrizione"));
			pat.setNomePatologia(result.getString("patologia.nome"));
			pat.setTipoPatologia(result.getString("patologia.tipoPatologia"));
			
		
				list.add(pat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return list;
	}


//SELECT * FROM clinicaveterinaria.prestazione_su_animale where codAnimale = 1 and rilevazioneAltezza IS NOT NULL and rilevazionePeso IS NOT NULL;
	@Override
	public List<ParametriPaziente> getAllParametriPaziente(Paziente paziente) {
		List<ParametriPaziente> list = new ArrayList<>();

		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		final String query = "SELECT * FROM prestazione_su_animale "
				+ "where codAnimale = ? "
				+ "and rilevazioneAltezza IS NOT NULL "
				+ "and rilevazionePeso IS NOT NULL ";

		try {			
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, paziente.getAnimale().getCodAnimale());
			final ResultSet result = statement.get().executeQuery();

			while (result.next()) {
				ParametriPaziente pat = new ParametriPazienteImpl();
				pat.setData(result.getString("data"));
				pat.setAltezza(result.getFloat("rilevazioneAltezza"));
				pat.setPeso(result.getFloat("rilevazionePeso"));
		
				list.add(pat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return list;
	}


	
}
