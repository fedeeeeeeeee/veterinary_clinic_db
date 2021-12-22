package controller;

import java.util.List;

import model.Farmaco;
import model.Lotto;

public interface LottoController {

	List<Farmaco> getAllDistinctFarmaci();

	void addNewLotto(Lotto lotto); // dentro bisogna fare un update delle quantità

}
