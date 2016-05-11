import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class TestFrame{
	JButton[] FieldButtons = new JButton[17];
	JButton[] HandButtons;
	JFrame frame;
	JPanel buttonPane;
	JPanel playerPane;
	Game currentGame;
	ArrayList<Card> cardsInHand;
	public TestFrame(Game currentGame){
		this.currentGame = currentGame;
		frame = new JFrame("DominionFrame");
		buttonPane = new JPanel();
		playerPane = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 600);
		frame.add(buttonPane);
		frame.add(playerPane,BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout());
		playerPane.setLayout(new FlowLayout());
		paint();
	}
	private void clearButtons()
	{
		for(int i = 0;i < 17;i++)
		{
			FieldButtons[i] = null;
		}
		for(int i = 0;i < HandButtons.length;i++)
		{
			HandButtons[i] = null;
		}
	}
	private void paint()
	{
		Player player = currentGame.getCurrentPlayer();
		for(int i = 0;i < 17;i++)
		{
			FieldCards fieldCard = currentGame.getFieldCard(i);
			Card card = fieldCard.getCard();
			FieldButtons[i] = new JButton(card.getName() + " | Cost " + card.getCost() + " | " + fieldCard.getAmount() + " Cards");
			FieldButtons[i].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){FieldCardButtonPressed(e);}});
			buttonPane.add(FieldButtons[i]);
			if(fieldCard.getAmount()<=0 || player.getPhase()!= 3 || card.getCost()>player.getGold()||player.getBuys()<=0)
			{
				FieldButtons[i].setEnabled(false);
			}
		}
		JLabel playerName= new JLabel(player.getName());
		playerPane.add(playerName);
		cardsInHand = player.getHand();
		HandButtons = new JButton[cardsInHand.size()];
		for(int i = 0;i < cardsInHand.size();i++)
		{
			Card card = cardsInHand.get(i);
			//System.out.println(card.toString());
			HandButtons[i]= new JButton(card.getName() + " | Cost " + card.getCost());
			HandButtons[i].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){HandCardButtonPressed(e);}});
			playerPane.add(HandButtons[i]);
			if((card.getType()!="Action" && player.getPhase()== 1)||(card.getType()!="Treasure" && player.getPhase()== 2))
			{
				HandButtons[i].setEnabled(false);
			}
			if(card.getType()!= "Action" && card.getType()!= "Treasure")
			{
				HandButtons[i].setEnabled(false);
			}
		}
		JLabel actions= new JLabel("Actions: " + player.getActions());
		JLabel buys= new JLabel("Buys: " + player.getBuys());
		JLabel gold= new JLabel("Gold: " + player.getGold());
		playerPane.add(actions);
		playerPane.add(buys);
		playerPane.add(gold);
		JButton next = new JButton();
		switch(player.getPhase())
		{
		case 1: next.setText("End actions");
			break;
		case 2:next.setText("End treasure");
			break;
		case 3:next.setText("End turn");
			break;	
		}
		next.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){nextPressed(e);}});
		playerPane.add(next);
		frame.setVisible(true);
	}
	public void repaint()
	{
		buttonPane.removeAll();
		buttonPane.repaint();
		playerPane.removeAll();
		playerPane.repaint();
		clearButtons();
		paint();
	}
	public void FieldCardButtonPressed(ActionEvent e) 
	{
		System.out.println("clickedField");
		for(int i = 0;i < 17;i++)
		{
			if(e.getSource().equals(FieldButtons[i]))
			{
				currentGame.getCurrentPlayer().buyCard(currentGame.getFieldCard(i));
			}
		}
		repaint();
	}
	public void HandCardButtonPressed(ActionEvent e)
	{
		System.out.println("clickedHand");
		for(int i = 0;i < HandButtons.length;i++)
		{
			if(e.getSource().equals(HandButtons[i]))
			{
				currentGame.getCurrentPlayer().playCard(cardsInHand.get(i));
			}
		}
		repaint();
	}
	public void nextPressed(ActionEvent e)
	{
		System.out.println("clickedNext");
		if(currentGame.currentPlayer.getPhase()==3)
		{
			currentGame.currentPlayer.nextPhase();
			currentGame.nextPlayer();
		}
		else{
			currentGame.currentPlayer.nextPhase();
		}
		repaint();
	}
}
