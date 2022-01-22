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
import model.Appuntamento;
import model.Pagamento;
import model.PagamentoImpl;
import model.Posto;
import model.PostoImpl;
import model.Ricovero;
import model.RicoveroImpl;

public class RicoveroTableImpl implements RicoveroTable {
	private DatabaseConnection dataSource;
	
	public RicoveroTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	@Override
	public List<Ricovero> getRicoveriAttivi() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select * " 
		 + "from ricovero, animale, prestazione_su_animale, prestazione, posto " 
		 + "where ricovero.codAnimale = animale.codAnimale " 
		 + "and ricovero.numeroPosto = posto.numero "
		 + "and prestazione_su_animale.codAnimale = ricovero.codAnimale " 
		 + "and prestazione_su_animale.data = ricovero.dataInizio " 
		 + "and prestazione_su_animale.ora = ricovero.oraInizio " 
		 + "and prestazione_su_animale.codTipoPrestazione = prestazione.codPrestazione ";
		 
		 List<Ricovero> ricoveri = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Ricovero ricovero = new RicoveroImpl();
			
				Animale animale = new AnimaleImpl();
				animale.setNome(result.getString("animale.nome"));
				animale.setCf(result.getString("animale.cf"));
				animale.setSpecie(result.getString("animale.specie"));
				animale.setCodAnimale( result.getInt("animale.codAnimale"));
				animale.setAltezza(result.getFloat("animale.altezza"));
				animale.setPeso(result.getFloat("animale.peso"));
				
				ricovero.setAnimale(animale);
				ricovero.setDataInizio(result.getString("ricovero.dataInizio"));
				ricovero.setDurataInGiorni(result.getInt("durataInGiorni"));
				//ricovero.setNumeroPosto(result.getInt("numeroPosto"));
				Posto posto = new PostoImpl();
				posto.setNumero(result.getInt("ricovero.numeroPosto"));
				posto.setDescrizione(result.getString("posto.descrizione"));
				ricovero.setPosto(posto);
				ricovero.setOraInizio(result.getString("oraInizio"));
				ricovero.setPrezzoGiornaliero(result.getFloat("prezzoGiornaliero"));
				ricovero.setMotivoRicovero(result.getString("prestazione.nome"));
				ricoveri.add(ricovero);
			}
			result.close();

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
			}
		}
				return ricoveri;
	}
	@Override
	public List<Posto> getPostiLiberi() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		 final String query = "select numero, descrizione from posto  "
		 		+ "left join ricovero "
		 		+ "on ricovero.numeroPosto = posto.numero"
		 		+ " where ricovero.numeroPosto is null";
		 List<Posto> posti = new ArrayList<>();
		 
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Posto posto = new PostoImpl();
				posto.setNumero(result.getInt("numero"));
				posto.setDescrizione(result.getString("descrizione"));
				posti.add(posto);
			}
			result.close();

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
			}
		}
				return posti;
	}
	@Override
	public void addRicovero(Ricovero ricovero, Appuntamento appuntamento) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		String query = "insert into ricovero (codAnimale, dataInizio, durataInGiorni, prezzoGiornaliero, numeroPosto, oraInizio) values (?,?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, appuntamento.getAnimale().getCodAnimale()); 
			statement.get().setString(2, appuntamento.getData());
			statement.get().setInt(3, ricovero.getDurataInGiorni());
			statement.get().setFloat(4, ricovero.getPrezzoGiornaliero());
			statement.get().setInt(5, ricovero.getPosto().getNumero());
			statement.get().setString(6, appuntamento.getOra());
			
			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO ricovero " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO ricovero statement" + e);
			}
		}
		
	}

}
