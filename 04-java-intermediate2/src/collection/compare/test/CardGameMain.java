package collection.compare.test;

public class CardGameMain {
	public static void main(String[] args) {
		Deck deck = new Deck();
		Player player1 = new Player("プレイヤー1");
		Player player2 = new Player("プレイヤー2");

		for (int i = 0; i < 5; i++) {
			player1.drawCard(deck);
			player2.drawCard(deck);
		}

		player1.showHand();
		player2.showHand();

		Player winner = getWinner(player1, player2);

		if (winner != null) {
			System.out.println(winner.getName() + "勝利");
		} else {
			System.out.println("引き分け");
		}
	}

	private static Player getWinner(Player player1, Player player2) {
		Player winner;
		int sum1 = player1.rankSum();
		int sum2 = player2.rankSum();

		if (sum1 > sum2) {
			return player1;
		} else if (sum1 == sum2) {
			return null;
		} else {
			return player2;
		}
	}
}
