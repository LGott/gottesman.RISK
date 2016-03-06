package gottesman.risk.map;

import gottesman.risk.Territory;

public class MoveOrAttackController implements GameController {
	
	private TerritoryView selectedTerritory; 
	
	public void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory) {
		selectedTerritory = territoryView;
	}

	public void onClickMap(BoardView boardView) {
		if ( selectedTerritory != null ) {
			selectedTerritory.unselect();
			selectedTerritory = null;
		}
	}

}
