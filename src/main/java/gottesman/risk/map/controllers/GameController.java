package gottesman.risk.map.controllers;

import gottesman.risk.Territory;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;

public interface GameController {

	void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory);
	void onClickMap(BoardView boardView);
	
}
