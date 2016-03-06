package gottesman.risk.map;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gottesman.risk.Territory;

public class BoardView extends JLabel implements ComponentListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private static final String IMAGE_FILENAME = "/Images/RiskBoardFinal.jpg";
	private static final int IMAGE_HEIGHT = 3600;
	private static final int IMAGE_WIDTH = 4500;
	private BufferedImage boardImage;
	private List<TerritoryView> territoryViews;
	private GameController gameController;

	public BoardView(List<Territory> territories, GameController gameController) {
		// null LayoutManager so we can set the positions of the TerritoryViews
		setLayout(null);
		
		loadBoardImage();

		this.gameController = gameController;
		createTerritoryViews(territories, gameController);

		addComponentListener(this);
	}

	private void createTerritoryViews(List<Territory> territories, GameController gameController) {
		this.territoryViews = new ArrayList<TerritoryView>();
		TerritoryViewListener territoryViewListener = new TerritoryViewListener(gameController);
		for (Territory territory : territories) {
			TerritoryView territoryView = new TerritoryView(territory, territory.getX(), territory.getY());
			territoryView.addMouseListener(territoryViewListener);
			add(territoryView);
			territoryViews.add(territoryView);
		}
	}

	private void loadBoardImage() {
		try {
			boardImage = ImageIO.read(getClass().getResourceAsStream(IMAGE_FILENAME));
		} catch (IOException e) {
			// If this happens, crash the app
			throw new RuntimeException(e);
		}
	}
	
	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	/**
	 * When the BoardView is resized, reposition all the TerritoryViews
	 */
	private void moveTerritories() {
		double newWidth = getWidth();
		double newHeight = getHeight();
		
		for ( TerritoryView territoryView : territoryViews ) {
			Territory territory = territoryView.getTerritory();
			territoryView.setXY(
					(int)(newWidth / IMAGE_WIDTH * territory.getX()), 
					(int)(newHeight / IMAGE_HEIGHT * territory.getY()));
			
		}
		
	}

	public void componentResized(ComponentEvent e) {
		moveTerritories();
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		gameController.onClickMap(this);
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
