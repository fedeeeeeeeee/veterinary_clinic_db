package controller;

import java.util.List;

import database.LottoTable;
import database.LottoTableImpl;
import model.Farmaco;
import model.Lotto;

public class LottoControllerImpl implements LottoController {
	
private LottoTable lottoTable;
	
	public LottoControllerImpl() {
		this.lottoTable = new LottoTableImpl();
	}
	@Override
	public List<Farmaco> getAllDistinctFarmaci() {
		return this.lottoTable.getAllDistinctFarmaci();
	}

	@Override
	public void addNewLotto(Lotto lotto) {
		this.lottoTable.addNewLotto(lotto);

	}

}
