package collection.compare.test;

public class Card implements Comparable<Card>{
	private final int rank;	//カードの数字
	private final Suit suit;	//カードのマーク

	public Card(int rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public int compareTo(Card anotherCard) {
		//数字を比較し、数字が同じ場合、カードのマークで比較する
		if (this.rank != anotherCard.rank) {
			return Integer.compare(this.rank, anotherCard.rank);
		} else {
			return this.suit.compareTo(anotherCard.suit);
		}
	}

	@Override
	public String toString() {
		return rank + "(" + suit.getIcon() + ")";
	}
}
