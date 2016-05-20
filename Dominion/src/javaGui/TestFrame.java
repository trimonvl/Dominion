package javaGui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import card.*;
import game.*;
import player.*;
import sql.MySQLAccess;
public class TestFrame{
	JButton[] FieldButtons = new JButton[17];
	JButton[] HandButtons;
	JButton next;
	JFrame frame;
	JPanel buttonPane;
	JPanel playerPane;
	Game currentGame;
	String state = "normal";
	JLabel actions;
	JLabel buys;
	JLabel gold;
	ArrayList<Card> cardsInHand;
	public TestFrame(Game currentGame){
		this.currentGame = currentGame;
		currentGame.addPlayerToGame("Player1");
		currentGame.addPlayerToGame("Player2");
		currentGame.addCardsToGame();
		currentGame.setStartingPlayer();
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
		
		MySQLAccess conn = new MySQLAccess();
		try {
			/*
			conn.insertGame();
			conn.insertPlayer("Jan");
			conn.insertPlayerInGame();
			conn.insertPlayer("Nick");
			conn.insertPlayerInGame();
			conn.insertPlayer("Tijs");
			conn.insertPlayerInGame();
			*/
			conn.extractRandomCards();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		actions = new JLabel("Actions: " + player.getActions());
		buys = new JLabel("Buys: " + player.getBuys());
		gold = new JLabel("Gold: " + player.getGold());
		next = new JButton();
		switch(state)
		{
		case "Cellar": paintCellar(player);
		break;
		case "Chapel": paintChapel(player);
		break;
		default: paintNormal(player);
		break;
		}
		frame.setVisible(true);
	}
	
	private void paintNormal(Player player)
	{
		JLabel playerName= new JLabel(player.getName());
		playerPane.add(playerName);
		cardsInHand = player.getHand();
		HandButtons = new JButton[cardsInHand.size()];
		for(int i = 0;i < cardsInHand.size();i++)
		{
			Card card = cardsInHand.get(i);
			HandButtons[i]= new JButton(card.getName() + " | Cost " + card.getCost());
			HandButtons[i].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){HandCardButtonPressed(e);}});
			playerPane.add(HandButtons[i]);
			if((card.getType()!="Action" && player.getPhase()== 1)||(card.getType()!="Treasure" && player.getPhase()== 2) || (player.getPhase()==3))
			{
				HandButtons[i].setEnabled(false);
			}
			if(card.getType()!= "Action" && card.getType()!= "Treasure")
			{
				HandButtons[i].setEnabled(false);
			}
		}
		playerPane.add(actions);
		playerPane.add(buys);
		playerPane.add(gold);
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
	}
	
	private void paintCellar(Player player)
	{
		JLabel playerName= new JLabel(player.getName());
		playerPane.add(playerName);
		cardsInHand = player.getHand();
		HandButtons = new JButton[cardsInHand.size()];
		for(int i = 0;i < cardsInHand.size();i++)
		{
			Card card = cardsInHand.get(i);
			HandButtons[i]= new JButton(card.getName() + " | Cost " + card.getCost());
			HandButtons[i].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){DiscardHandCard(e);}});
			playerPane.add(HandButtons[i]);
		}
		playerPane.add(actions);
		playerPane.add(buys);
		playerPane.add(gold);
		next.setText("end discard");
		next.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){endCellarDiscard(e);}});
		playerPane.add(next);	
	}
	
	private void paintChapel(Player player)
	{
		JLabel playerName= new JLabel(player.getName());
		playerPane.add(playerName);
		cardsInHand = player.getHand();
		HandButtons = new JButton[cardsInHand.size()];
		for(int i = 0;i < cardsInHand.size();i++)
		{
			Card card = cardsInHand.get(i);
			HandButtons[i]= new JButton(card.getName() + " | Cost " + card.getCost());
			HandButtons[i].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){trashHandCard(e);}});
			playerPane.add(HandButtons[i]);
		}
		playerPane.add(actions);
		playerPane.add(buys);
		playerPane.add(gold);
		next.setText("end thrash");
		next.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){endChapelTrash(e);}});
		playerPane.add(next);
	}
	
	private void repaint()
	{
		buttonPane.removeAll();
		buttonPane.repaint();
		playerPane.removeAll();
		playerPane.repaint();
		clearButtons();
		paint();
	}
	
	private void FieldCardButtonPressed(ActionEvent e) 
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
	
	private void HandCardButtonPressed(ActionEvent e)
	{
		System.out.println("clickedHand");
		Player player = currentGame.getCurrentPlayer();
		for(int i = 0;i < HandButtons.length;i++)
		{
			if(e.getSource().equals(HandButtons[i]))
			{
				if(player.getPhase()==1 && cardsInHand.get(i).getType()=="Action" || player.getPhase()==2 && cardsInHand.get(i).getType()=="Treasure")
				{
					player.playCard(cardsInHand.get(i));
					specialAction(cardsInHand.get(i));
				}
			}
		}
		repaint();
	}
	
	private void nextPressed(ActionEvent e)
	{
		System.out.println("clickedNext");
		if(currentGame.getCurrentPlayer().getPhase()==3)
		{
			currentGame.getCurrentPlayer().nextPhase();
			currentGame.nextPlayer();
		}
		else{
			currentGame.getCurrentPlayer().nextPhase();
		}
		repaint();
	}
	private void endCellarDiscard(ActionEvent e)
	{
		currentGame.getCommand().CellarComplete(currentGame.getCurrentPlayer());
		state = "normal";
		repaint();
	}
	private void specialAction(Card card)
	{
		String name = card.getName();
		if(name == "Village" || name == "Woodcutter" || name == "Smithy" || name == "Festival" || name == "Laboratory" || name == "Market")
		{
			state = "normal";
		}
		else
		{
			state = name;
		}
	}
	
	private void DiscardHandCard(ActionEvent e)
	{
		for(int i = 0;i < HandButtons.length;i++)
		{
			if(e.getSource().equals(HandButtons[i]))
			{
				currentGame.getCommand().addDiscard(cardsInHand.get(i));
				HandButtons[i].setEnabled(false);
			}
		}
	}
	private void trashHandCard(ActionEvent e)
	{
		for(int i = 0;i < HandButtons.length;i++)
		{
			if(e.getSource().equals(HandButtons[i]))
			{
				if(state == "Chapel")
				{
					if(currentGame.getCommand().getToUseSize()<4)
					{
						currentGame.getCommand().addChapelTrash(cardsInHand.get(i));
						HandButtons[i].setEnabled(false);	
					}	
				}
			}
		}
	}
	private void endChapelTrash(ActionEvent e)
	{
		currentGame.getCommand().ChapelComplete(currentGame.getCurrentPlayer());
		state = "normal";
		repaint();	
	}
}
