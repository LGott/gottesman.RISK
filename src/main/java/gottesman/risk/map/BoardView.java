package gottesman.risk.map;

import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gottesman.risk.Territory;

public class BoardView extends JLabel implements ComponentListener {

	private static final int IMAGE_HEIGHT = 3600;
	private static final int IMAGE_WIDTH = 4500;
	private BufferedImage boardImage;
	private List<TerritoryView> territoryViews;

	public BoardView(List<Territory> territories) {
		// null LayoutManager so we can set the positions of the TerritoryViews
		setLayout(null);
		
		loadBoardImage();

		createTerritoryViews(territories);

		addComponentListener(this);
	}

	private void createTerritoryViews(List<Territory> territories) {
		this.territoryViews = new ArrayList<TerritoryView>();
		TerritoryViewListener territoryViewListener = new TerritoryViewListener();
		for (Territory territory : territories) {
			TerritoryView territoryView = new TerritoryView(territory, territory.getX(), territory.getY());
			territoryView.hideBorder();
			territoryView.addMouseListener(territoryViewListener);
			add(territoryView);
			territoryViews.add(territoryView);
		}
	}

	private void loadBoardImage() {
		try {
			boardImage = ImageIO.read(getClass().getResourceAsStream("/Images/RiskBoardFinal.jpg"));
			this.setIcon(new ImageIcon(boardImage));
		} catch (IOException e) {
			// If this happens, crash the app
			throw new RuntimeException(e);
		}
	}
	
	public void paintComponent( Graphics g ) {
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

}
