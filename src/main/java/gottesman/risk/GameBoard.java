package gottesman.risk;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;

//TODO - GAMEBOARD WORK-IN-PROGRESS
public class GameBoard extends JPanel implements MouseListener {

	private LinkedList<Territory> territoryMap; // Make own Graph?
	private HashMap<Territory, JLabel> battalionMap;
	private ArrayList<Continent> continents;

	/*
	 * Game Board will hold a map of the territories. Each territory on the map will be able to be clicked on in order to stage an attack. The
	 * countries on the map will change colors according to the win. The class holds a hashmap of territories/JLabel of battalion Number to store how
	 * many battalions each territory currently contains. Implements Mouse Listener in order to click on the country.
	 */

	public GameBoard() {
		this.territoryMap = new LinkedList<Territory>();
		this.battalionMap = new HashMap<Territory, JLabel>();
		this.continents = new ArrayList<Continent>(6); // 6 Continents

	}

	// TODO Insert territory values into LinkedList
	// TODO Create 6 continents and insert territiories
	// TODO adjacent territories

	public void changeColor() {

		// TODO Change color of territory to winning color
	}

	public LinkedList<Territory> getTerritoryMap() {
		return territoryMap;
	}

	public HashMap<Territory, JLabel> getBattalionMap() {
		return battalionMap;
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
