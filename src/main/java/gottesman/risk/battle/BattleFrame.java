package gottesman.risk.battle;

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

public class BattleFrame extends JFrame {

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
	private JLabel battalionsA;
	private JLabel battalionsD;

	private CombatLogic combatLogic;
	private List<JLabel> diceLabels;

	private ArrayList<Integer> attackerDice;
	private ArrayList<Integer> defenderDice;

	final static int WIDTH = 800;
	final static int HEIGHT = 650;

	public BattleFrame(final Territory attacker, final Territory defender) throws IOException {

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
		this.defenderDice = new ArrayList<Integer>();
		this.attackerDice = new ArrayList<Integer>();

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

		buttonPanel.add(this.attackAgain = new JButton(new ImageIcon(getClass().getResource("/Images/AttackAgain.png"))));
		buttonPanel.add(this.forfeit = new JButton(new ImageIcon(getClass().getResource("/Images/Forfeit.png"))));
		buttonPanel.add(this.battalionsA = new JLabel(attacker.getBattalions() + " Battalions"));

		battalionsD = new JLabel(defender.getBattalions() + " Battalions");
		defenderPanel.add(battalionsD);

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
			die.setOpaque(false);
		}

		for (JButton but : buttonsA) {
			for (JButton b : buttonsD) {
				but.setOpaque(false);
				but.setContentAreaFilled(false);
				but.setBorderPainted(false);
				b.setOpaque(false);
				b.setContentAreaFilled(false);
				b.setBorderPainted(false);
			}
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

		for (JButton button : buttonsA) {
			for (JButton b : buttonsD) {
				diceAPanel.add(button);
				defenderPanel.add(b);
			}
		}

		attackerPanel.add(diceAPanel, BorderLayout.WEST);
		attackerPanel.add(buttonPanel, BorderLayout.EAST);

		actionListeners(attacker, defender, buttonsA, buttonsD);
		check(attacker, defender);
	}

	private void actionListeners(final Territory attacker, final Territory defender, final ArrayList<JButton> buttonsA,
			final ArrayList<JButton> buttonsD) {

		rollThreeA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rollTwoD.setEnabled(true);
				rollOneD.setEnabled(true);
				attackerDice = getDiceSet((JButton) e.getSource(), buttonsA);
			}
		});

		rollTwoA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rollTwoD.setEnabled(true);
				rollOneD.setEnabled(true);
				attackerDice = getDiceSet((JButton) e.getSource(), buttonsA);
			}
		});
		rollOneA.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rollTwoD.setEnabled(false);
				rollOneD.setEnabled(true);
				attackerDice = getDiceSet((JButton) e.getSource(), buttonsA);
			}
		});
		rollTwoD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				defenderDice = getDiceSet((JButton) e.getSource(), buttonsD);
				calculateWin(attackerDice, defenderDice, attacker, defender);

			}
		});
		rollOneD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				defenderDice = getDiceSet((JButton) e.getSource(), buttonsD);
				calculateWin(attackerDice, defenderDice, attacker, defender);
			}
		});

		attackAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (JButton a : buttonsA) {
					for (JButton d : buttonsD) {
						a.setEnabled(true);
						d.setEnabled(true);
					}
				}
				check(attacker, defender);
				rollTwoD.setEnabled(true);
				rollOneD.setEnabled(true);

			}
		});

		forfeit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

	}

	private void calculateWin(ArrayList<Integer> attackerDice, ArrayList<Integer> defenderDice, Territory attacker, Territory defender) {
		combatLogic.calculateWin(attackerDice, defenderDice, attacker, defender);
		battalionsA.setText((attacker.getBattalions() + " battalion(s) left"));
		battalionsD.setText((defender.getBattalions() + " battalion(s) left"));

		if (defender.getBattalions() < 1) {
			JOptionPane.showMessageDialog(null, "Attacker Wins! " + attacker.getName() + " has conquered " + defender.getName());
			dispose();
		} else if (attacker.getBattalions() == 1) { // If attacker has 1 battalion, attack is over
			JOptionPane.showMessageDialog(null, "Attacker has been defeated. Battle is forfeited.");
			attackAgain.setEnabled(false);
		}
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

	public void check(Territory attacker, Territory defender) {
		if (attacker.getBattalions() <= 3) {
			rollThreeA.setEnabled(false);
		}
		if (attacker.getBattalions() <= 2) {
			rollTwoA.setEnabled(false);
		}

		if (defender.getBattalions() <= 2) {
			rollTwoD.setEnabled(false);
		}
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
