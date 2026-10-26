package collection.compare.test;

public enum Suit {
	SPADE("♠"),	//スペード(♠)
	HEART("♥"),	//ハート(♥)
	DIAMOND("♦"),	//ダイアモンド(♦)
	CLUB("♣");	//クローバー(♣)

	private String icon;

	Suit(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
}
