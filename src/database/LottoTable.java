package database;

import java.util.List;

import model.Farmaco;
import model.Lotto;

public interface LottoTable {

	List<Farmaco> getAllDistinctFarmaci();
	
	boolean addNewLotto(Lotto lotto); // dentro bisogna fare un update delle quantit�
	
	boolean updateQuantitaAFarmaci(Lotto lotto);
}
