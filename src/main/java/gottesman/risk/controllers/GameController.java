package gottesman.risk.controllers;

import gottesman.risk.Territory;
import gottesman.risk.battle.DeckEmptyException;
import gottesman.risk.map.BoardView;
import gottesman.risk.map.TerritoryView;

public interface GameController {

	void onClickTerritory(BoardView boardView, TerritoryView territoryView, Territory territory)
			throws DeckEmptyException;

	void onClickMap(BoardView boardView);

}
