package collection.compare.test;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private List<Card> hand;

	public String getName() {
		return name;
	}

	public Player(String name) {
		this.name = name;
		this.hand = new ArrayList<>();
	}

	public void addCard(Card card) {
		hand.add(card);
	}

	public void drawCard(Deck deck) {
		hand.add(deck.drawCard());
	}

	public int rankSum() {
		int value = 0;
		for (Card card : hand) {
			value += card.getRank();
		}
		return value;
	}

	public void showHand() {
		hand.sort(null);
		System.out.println(name + "のカード： " + hand + ", 合計： " + rankSum());
	}
}
