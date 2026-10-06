package nested.test.ex1;

public class Library {
	private Book[] books;
	private int bookCount;

	public Library(int size) {
		books = new Book[4];
		bookCount = 0;
	}



	public void addBook(String title, String author) {

		//検証ロジックを処理する
		if (bookCount >= books.length) {
			System.out.println("図書館の保存空間が足りておりません。");
			return;
		}

		//正常ロジックを実装する
		books[bookCount++] = new Book(title, author);
	}

	public void showBooks() {
		System.out.println("== 書籍一覧出力 ==");
		for (int i = 0; i < bookCount; i++) {
			Book book = books[i];
			System.out.println("書名： "+book.title+ " 著者： " + book.author);
		}
	}

	private static class Book {
		private String title;
		private String author;

		public Book(String title, String author) {
			this.title = title;
			this.author = author;
		}
	}


}
