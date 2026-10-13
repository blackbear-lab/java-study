package exception.basic.unchecked;

/**
 * Unchecked例外は
 * 例外をキャッチしたり、スローしたりする必要はない。
 * 例がをキャッチしないと自動的に外に投げる。
 */
public class Service {

	Client client = new Client();

	/**
	 * 必要な場合例外をキャッチして処理できる。
	 */

	public void callCatch() {
		try {
			client.call();
		} catch (MyUncheckedException e) {
			//例外処理ロジック
			System.out.println("例外処理, message=" + e.getMessage());
		}
		System.out.println("正常ロジック");
	}

	/**
	 * 例外をキャッチしなくても大丈夫。自然に上位に渡す
	 * 検査例外と違ってthrows例外宣言をしなくても大丈夫。
	 */
	public void callThrow() {
		client.call();
	}
}
