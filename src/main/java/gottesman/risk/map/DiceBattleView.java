package gottesman.risk.map;

import gottesman.risk.CombatLogic;
import gottesman.risk.Territory;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DiceBattleView extends JFrame {

	private JButton rollThreeA;
	private JButton rollTwoA;
	private JButton rollOneA;
	private JButton rollTwoD;
	private JButton rollOneD;

	private JButton attackAgain;
	private JButton forfeit;

	private CombatLogic combatLogic;
	private List<JLabel> diceLabels;

	public DiceBattleView(final Territory attacker, final Territory defender) {

		setTitle("Risk Battle!");
		setSize(1000, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		this.combatLogic = new CombatLogic();
		this.diceLabels = new ArrayList<JLabel>();

		JPanel dicePanel = new JPanel();
		JPanel attackerPanel = new JPanel();
		JPanel defenderPanel = new JPanel();

		dicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // TODO Change to BoxLayout
		attackerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // TODO Change to BoxLayout
		defenderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));// TODO Change to BoxLayout

		final ArrayList<JButton> buttonsA = new ArrayList<JButton>();
		buttonsA.add(this.rollThreeA = new JButton("Roll Three Dice")); // TODO Change to Images
		buttonsA.add(this.rollTwoA = new JButton("Roll Two Dice"));
		buttonsA.add(this.rollOneA = new JButton("Roll One Die"));
		buttonsA.add(this.attackAgain = new JButton("Attack Again"));
		buttonsA.add(this.forfeit = new JButton("Forfiet"));

		final ArrayList<JButton> buttonsD = new ArrayList<JButton>();
		buttonsD.add(this.rollTwoD = new JButton("Roll Two Dice"));
		buttonsD.add(this.rollOneD = new JButton("Roll One Die"));

		for (int i = 0; i < 6; i++) {
			diceLabels.add(new JLabel(""));
		}
		for (JLabel die : diceLabels) {
			dicePanel.add(die);
		}

		add(dicePanel, BorderLayout.CENTER);
		add(attackerPanel, BorderLayout.WEST);
		add(defenderPanel, BorderLayout.EAST);

		// Just For Testing purposes
		defender.setBattalions(4);
		attacker.setBattalions(4);

		ActionListener listener = new ActionListener() {

			ArrayList<Integer> defenderDice;
			ArrayList<Integer> attackerDice;

			public void actionPerformed(ActionEvent e) {

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
				if (button.equals(attackAgain)) {
					if (attacker.getBattalions() <= 1) { // If attacker has 1 battalion, attack is over
						JOptionPane.showMessageDialog(null, "Not Enough Battalions! Cannot Attack Anymore!");
						disableButtons(buttonsA);
						attackAgain.setEnabled(false);
					} else {
						attackAgain(buttonsA, buttonsD);
					}
				}
				if (button.equals(forfeit)) {
					forfeit();
				}
				if (e.getSource().equals(rollTwoD) || e.getSource().equals(rollOneD)) {
					combatLogic.calculateWin(attackerDice, defenderDice, attacker, defender);
				}
			}
		};

		for (JButton button : buttonsA) {
			attackerPanel.add(button);
			button.addActionListener(listener);
		}

		for (JButton button : buttonsD) {
			defenderPanel.add(button);
			button.addActionListener(listener);
		}
	}

	private ArrayList<Integer> getDiceSet(JButton button, ArrayList<JButton> buttons) {

		ArrayList<Integer> diceSet = new ArrayList<Integer>();

		if (button.equals(rollThreeA)) {
			disableButtons(buttons);
			diceSet = combatLogic.getThreeDice();
			displayDice(diceSet);

		} else if (button.equals(rollTwoA) || button.equals(rollTwoD)) {
			disableButtons(buttons);
			diceSet = combatLogic.getTwoDice();
			displayDice(diceSet);

		} else if (button.equals(rollOneA) || button.equals(rollOneD)) {
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
		dispose(); // ?
	}

	public void disableButtons(ArrayList<JButton> buttons) {
		for (JButton b : buttons) {
			if (!(b.equals(attackAgain)) && (!(b.equals(forfeit)))) {
				b.setEnabled(false);
			}
		}
	}

	private void displayDice(ArrayList<Integer> diceSet) {
		for (int j = 0; j < diceLabels.size(); j++) {
			diceLabels.get(j).setText("");
		}
		for (int i = 0; i < diceSet.size(); i++) {

			System.out.println(diceSet.get(i));

			switch (diceSet.get(i)) {
			// TODO Change text to ImageIcons
			case 1:
				diceLabels.get(i).setText("one");
				break;
			case 2:
				diceLabels.get(i).setText("two");
				break;
			case 3:
				diceLabels.get(i).setText("three");
				break;
			case 4:
				diceLabels.get(i).setText("Four");
				break;
			case 5:
				diceLabels.get(i).setText("five");
				break;
			case 6:
				diceLabels.get(i).setText("six");
				break;

			}
		}
	}

	// Just for testing the gui
	public static void main(String[] args) {
		Territory A = new Territory("Testing", 1, 2);
		Territory B = new Territory("Testing", 1, 4);
		new DiceBattleView(A, B).setVisible(true);
	}
}
