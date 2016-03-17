package gottesman.risk.map;

import gottesman.risk.CombatLogic;
import gottesman.risk.Territory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DiceBattleView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton rollThreeA;
	private JButton rollTwoA;
	private JButton rollOneA;
	private JButton rollTwoD;
	private JButton rollOneD;

	private JButton attackAgain;
	private JButton forfeit;

	private CombatLogic combatLogic;
	private List<JLabel> diceLabels;

	final static int WIDTH = 800;
	final static int HEIGHT = 650;

	public DiceBattleView(final Territory attacker, final Territory defender) throws IOException {

		setTitle("Risk Battle!");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(new BattlePanel(attacker, defender));

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		this.combatLogic = new CombatLogic();
		this.diceLabels = new ArrayList<JLabel>();

		JPanel dicePanel = new JPanel();
		JPanel attackerPanel = new JPanel();
		JPanel defenderPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel diceAPanel = new JPanel();

		dicePanel.setOpaque(false);
		attackerPanel.setOpaque(false);
		defenderPanel.setOpaque(false);
		diceAPanel.setOpaque(false);
		buttonPanel.setOpaque(false);

		attackerPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 20));
		dicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 200));
		defenderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 20));
		diceAPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 20));

		diceAPanel.setPreferredSize(new Dimension(100, 100));
		buttonPanel.setPreferredSize(new Dimension(100, 100));
		defenderPanel.setPreferredSize(new Dimension(200, 30));

		final ArrayList<JButton> buttonsA = new ArrayList<JButton>();
		buttonsA.add(this.rollThreeA = new JButton(new ImageIcon(getClass().getResource("/Images/3Dice.png"))));
		buttonsA.add(this.rollTwoA = new JButton(new ImageIcon(getClass().getResource("/Images/2Dice.png"))));
		buttonsA.add(this.rollOneA = new JButton(new ImageIcon(getClass().getResource("/Images/1Die.png"))));

		buttonPanel
				.add(this.attackAgain = new JButton(new ImageIcon(getClass().getResource("/Images/AttackAgain.png"))));
		buttonPanel.add(this.forfeit = new JButton(new ImageIcon(getClass().getResource("/Images/Forfeit.png"))));

		final ArrayList<JButton> buttonsD = new ArrayList<JButton>();
		buttonsD.add(this.rollTwoD = new JButton(new ImageIcon(getClass().getResource("/Images/2Dice.png"))));
		buttonsD.add(this.rollOneD = new JButton(new ImageIcon(getClass().getResource("/Images/1Die.png"))));
		rollTwoD.setEnabled(false);
		rollOneD.setEnabled(false);

		for (int i = 0; i < 6; i++) {
			diceLabels.add(new JLabel(""));
		}
		for (JLabel die : diceLabels) {
			dicePanel.add(die);
		}

		for (JButton but : buttonsA) {
			for (JButton b : buttonsD) {
				// but.setPreferredSize(new Dimension(70, 20));
				but.setOpaque(false);
				but.setContentAreaFilled(false);
				but.setBorderPainted(false);
				// b.setPreferredSize(new Dimension(WIDTH / 10, HEIGHT / 6));
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setBorderPainted(false);

			}
		}

		for (JLabel die : diceLabels) {
			die.setOpaque(false);
		}

		attackAgain.setOpaque(false);
		attackAgain.setContentAreaFilled(false);
		attackAgain.setBorderPainted(false);
		forfeit.setOpaque(false);
		forfeit.setContentAreaFilled(false);
		forfeit.setBorderPainted(false);

		add(dicePanel, BorderLayout.CENTER);
		add(attackerPanel, BorderLayout.WEST);
		add(defenderPanel, BorderLayout.EAST);

		ActionListener listener = new ActionListener() {

			ArrayList<Integer> defenderDice;
			ArrayList<Integer> attackerDice;

			public void actionPerformed(ActionEvent e) {
				rollTwoD.setEnabled(true);
				rollOneD.setEnabled(true);

				JButton button = (JButton) e.getSource();
				if (button.equals(rollThreeA) || button.equals(rollTwoA) || button.equals(rollOneA)) {
					if (button.equals(rollOneA)) { // If attacker chooses one die, defender can only roll 1 also
						rollTwoD.setEnabled(false);
					}
					attackerDice = getDiceSet(button, buttonsA);
				}
				if (button.equals(rollTwoD) || button.equals(rollOneD)) {
					defenderDice = getDiceSet(button, buttonsD);
				}
				if (button.equals(forfeit)) {
					forfeit();
				}
				if (e.getSource().equals(rollTwoD) || e.getSource().equals(rollOneD)) {
					combatLogic.calculateWin(attackerDice, defenderDice, attacker, defender);
					repaint();
					if (defender.getBattalions() < 1) {
						JOptionPane.showMessageDialog(null, "Attacker Wins! " + attacker.getName() + " has conquered "
								+ defender.getName());
						dispose();

					}
					if (attacker.getBattalions() <= 1) { // If attacker has 1 battalion, attack is over
						JOptionPane.showMessageDialog(null, "Attacker has been defeated. Battle is forfeited.");
						attackAgain.setEnabled(false);
					}
				}
			}
		};

		attackAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				rollTwoD.setEnabled(false);
				rollOneD.setEnabled(false);
				disableButtons(buttonsA);
				attackAgain(buttonsA, buttonsD);
			}
		});

		for (JButton button : buttonsA) {
			diceAPanel.add(button);
			button.addActionListener(listener);
		}

		for (JButton button : buttonsD) {
			defenderPanel.add(button);
			button.addActionListener(listener);
		}

		attackerPanel.add(diceAPanel, BorderLayout.WEST);
		attackerPanel.add(buttonPanel, BorderLayout.EAST);

		attackAgain.addActionListener(listener);
		forfeit.addActionListener(listener);
	}

	private ArrayList<Integer> getDiceSet(JButton button, ArrayList<JButton> buttons) {

		ArrayList<Integer> diceSet = new ArrayList<Integer>();

		if (button.equals(rollThreeA)) {

			diceLabels.get(2).setVisible(true);
			diceLabels.get(1).setVisible(true);
			disableButtons(buttons);
			diceSet = combatLogic.getThreeDice();
			displayDice(diceSet);

		} else if (button.equals(rollTwoA) || button.equals(rollTwoD)) {
			diceLabels.get(1).setVisible(true);
			diceLabels.get(2).setVisible(false);
			disableButtons(buttons);
			diceSet = combatLogic.getTwoDice();
			displayDice(diceSet);

		} else if (button.equals(rollOneA) || button.equals(rollOneD)) {
			diceLabels.get(2).setVisible(false);
			diceLabels.get(1).setVisible(false);
			disableButtons(buttons);
			diceSet = combatLogic.getOneDie();
			displayDice(diceSet);
		}
		return diceSet;
	}

	private void attackAgain(ArrayList<JButton> A, ArrayList<JButton> D) {

		// Re-enable buttons
		for (JButton a : A) {
			for (JButton d : D) {
				a.setEnabled(true);
				d.setEnabled(true);
			}
		}
	}

	private void forfeit() {
		dispose();
	}

	public void disableButtons(ArrayList<JButton> buttons) {
		for (JButton b : buttons) {
			if (!(b.equals(attackAgain)) && (!(b.equals(forfeit)))) {
				b.setEnabled(false);
			}
		}
	}

	private void displayDice(ArrayList<Integer> diceSet) {

		for (int i = 0; i < diceSet.size(); i++) {

			switch (diceSet.get(i)) {
			case 1:

				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice1.png")));
				break;
			case 2:
				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice2.png")));
				break;
			case 3:
				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice3.png")));
				break;
			case 4:
				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice4.png")));
				break;
			case 5:
				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice5.png")));
				break;
			case 6:
				diceLabels.get(i).setIcon(new ImageIcon(getClass().getResource("/Images/Dice6.png")));
				break;

			}
		}
	}

}
