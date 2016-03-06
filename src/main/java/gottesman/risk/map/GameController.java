package gottesman.risk.map;

import gottesman.risk.Territory;

public interface GameController {

	void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory);
	void onClickMap(BoardView boardView);
	
}
