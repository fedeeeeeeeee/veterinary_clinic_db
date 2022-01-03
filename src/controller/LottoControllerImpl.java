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
		if(this.lottoTable.addNewLotto(lotto)) {
			if(this.lottoTable.updateQuantitaAFarmaci(lotto)) {
				System.out.println("aggiorna quantita farmaci OK ");
			}else { 
				System.out.println("aggiorna quantita farmaci ERRORE ");
			}
		} else {
			System.out.println("'aggiunta del lotto ERRORE");
		}
		

	}

}
