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

public class BoardView extends JLabel {

	private BufferedImage boardImage;
	private List<TerritoryView> territoryViews;

	public BoardView(List<Territory> territories) {
		this.territoryViews = new ArrayList<TerritoryView>();
		
		try {
			boardImage = ImageIO.read(getClass().getResourceAsStream("/Images/RiskBoardFinal.jpg"));
			this.setIcon(new ImageIcon(boardImage));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		setLayout(null);

		TerritoryViewListener territoryViewListener = new TerritoryViewListener();
		for (Territory territory : territories) {
			TerritoryView territoryView = new TerritoryView(territory, territory.getX(), territory.getY());
			territoryView.hideBorder();
			territoryView.addMouseListener(territoryViewListener);
			add(territoryView);
			territoryViews.add(territoryView);
		}

		addComponentListener( new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				onResize();
			}

			public void componentMoved(ComponentEvent e) {
				
			}

			public void componentShown(ComponentEvent e) {
				
			}

			public void componentHidden(ComponentEvent e) {
				
			}
			
		});
		
	}
	
	public void paintComponent( Graphics g ) {
		g.drawImage(boardImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	public void onResize() {
		double newWidth = getWidth();
		double newHeight = getHeight();
		double imageWidth = 4500;
		double imageHeight = 3600;
		
		for ( TerritoryView territoryView : territoryViews ) {
			
			Territory territory = territoryView.getTerritory();
			territoryView.setXY(
					(int)(newWidth / imageWidth * territory.getX()), 
					(int)(newHeight / imageHeight * territory.getY()));
			
		}
		
	}

}
