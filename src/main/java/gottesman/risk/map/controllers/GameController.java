package gottesman.risk.map.controllers;

import gottesman.risk.DeckEmptyException;
import gottesman.risk.Territory;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;

public interface GameController {

	void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory)
			throws DeckEmptyException;

	void onClickMap(BoardView boardView);

}
