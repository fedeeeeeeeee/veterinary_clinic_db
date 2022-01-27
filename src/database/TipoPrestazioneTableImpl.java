package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.Prestazione;
import model.PrestazioneImpl;
import model.Strumento;
import model.StrumentoImpl;
import model.Utilizzo;
import model.UtilizzoImpl;

public class TipoPrestazioneTableImpl implements TipoPrestazioneTable {
	
	private DatabaseConnection dataSource;
	
	public TipoPrestazioneTableImpl() {
		this.dataSource = new DatabaseConnection();
	}
	
	@Override
	public List<Prestazione> getAllTipiDiPrestazione() {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select codPrestazione, nome, tipo, durataMedia from prestazione"; 

		List<Prestazione> prestazioni = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Prestazione p = new PrestazioneImpl();
				p.setCodPrestazione(result.getInt(1));
				p.setNomePrestazione(result.getString(2));
				p.setTipoPrestazione(result.getString(3));
				p.setDurataMedia(result.getString(4));
				prestazioni.add(p);
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
		return prestazioni;
	}

	@Override
	public List<Utilizzo> getAllUtilizziDiPrestazione(Prestazione prestazione) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();

		final String query = "select * " 
				+ "from utilizzo, strumento " 
				+ "where utilizzo.codStrumento = strumento.codStrumento "
				+ "and utilizzo.codPrestazione = ? "; 

		List<Utilizzo> utilizzi = new ArrayList<>();
		try {
			statement = Optional.of(connection.prepareStatement(query));
			statement.get().setInt(1, prestazione.getCodPrestazione());
			final ResultSet result = statement.get().executeQuery();
			
			while (result.next()) {
				Utilizzo u = new UtilizzoImpl();
				Strumento s = new StrumentoImpl();
				s.setCodStrumento(result.getInt("codStrumento"));
				s.setNome(result.getString("strumento.nome"));
				s.setDescrizione(result.getString("strumento.descrizione"));	
				s.setQuantita(result.getInt("strumento.quantitaInMagazzino"));
				u.setQuantita(result.getInt("utilizzo.quantita"));
				u.setStrumento(s);
				utilizzi.add(u);
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
		return utilizzi;
	}

	@Override
	public void addPrestazione(Prestazione prestazione) {
		final Connection connection = this.dataSource.getMySQLConnection();
		Optional<PreparedStatement> statement = Optional.empty();
		
		String query = "insert into prestazione (tipo, nome, durataMedia, prezzo, descrizione) values (?,?,?,?,?)";

		try {
			statement = Optional.of(connection.prepareStatement(query));	
			statement.get().setString(1, prestazione.getTipoPrestazione());
			statement.get().setString(2, prestazione.getNomePrestazione());
			statement.get().setString(3, prestazione.getDurataMedia());
			statement.get().setFloat(4, prestazione.getPrezzo());
			statement.get().setString(5, prestazione.getDescrizione());
	
			statement.get().executeUpdate();
		} catch (SQLException e) {
			System.out.println("eccezione INSERIMENTO Prestazione " + e);
		} finally {
			try {
				if (statement.isPresent()) {
					statement.get().close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("eccezione INSERIMENTO Prestazione statement" + e);
			}
		}
	}

}
