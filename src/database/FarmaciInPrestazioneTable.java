package database;

import java.util.List;

import model.Farmaco;
import model.FarmacoInPrestazione;

public interface FarmaciInPrestazioneTable {

	List<FarmacoInPrestazione> getFarmaciDiAnimale(int codAnimale);

	List<String> getFarmaciAssuntiByAnimale(int codAnimale);

}
