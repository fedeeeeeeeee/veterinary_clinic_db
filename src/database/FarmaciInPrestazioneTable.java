package database;

import java.util.List;

import model.FarmacoInPrestazione;

public interface FarmaciInPrestazioneTable {

	List<FarmacoInPrestazione> getFarmaciDiAnimale(int codAnimale);

}
