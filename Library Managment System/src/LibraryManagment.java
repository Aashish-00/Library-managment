import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    static ArrayList<Book> books = new ArrayList<Book>();

    static class Book {
        private boolean isIssued = false;
        private final int bookId;
        private final String bookName;
        private final String bookAuthor;

        Book(int bookId, String bookName, String bookAuthor) {
            this.bookId = bookId;
            this.bookName = bookName;
            this.bookAuthor = bookAuthor;
        }

        public int getBookId() { return bookId; }
        public String getBookName() { return bookName; }
        public String getBookAuthor() { return bookAuthor; }
        public boolean isIssued() { return isIssued; }
        public void setIssued(boolean issued) { this.isIssued = issued; }
    }

    public static void addBooks(Scanner sc) {
        System.out.print("Enter the Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the Book Name: ");
        String name = sc.nextLine();
        System.out.print("Enter the Author Name: ");
        String author = sc.nextLine();

        Book newBook = new Book(id, name, author);
        books.add(newBook);

        System.out.println("\nBook \"" + name + "\" by " + author + " added successfully!\n");
    }

    public static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNo books available.\n");
        } else {
            System.out.println("\n------ List of Books ------");
            for (Book b : books) {
                System.out.println("Book ID   : " + b.getBookId());
                System.out.println("Book Name : " + b.getBookName());
                System.out.println("Author    : " + b.getBookAuthor());
                System.out.println("Status    : " + (b.isIssued() ? "Issued" : "Available"));
                System.out.println("---------------------------");
            }
        }
    }

    public static void searchBooks(int booksId) {
        boolean found = false;
        for (Book b : books) {
            if (b.getBookId() == booksId) {
                System.out.println("\n------ Book Details ------");
                System.out.println("Book ID   : " + b.getBookId());
                System.out.println("Book Name : " + b.getBookName());
                System.out.println("Author    : " + b.getBookAuthor());
                System.out.println("Status    : " + (b.isIssued() ? "Issued" : "Available"));
                System.out.println("--------------------------\n");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\nBook not found with ID: " + booksId + "\n");
        }
    }

    public static void issueBooks(int bookId) {
        boolean found = false;
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                found = true;
                if (!b.isIssued()) {
                    b.setIssued(true);
                    System.out.println("\nBook \"" + b.getBookName() + "\" issued successfully.\n");
                } else {
                    System.out.println("\nBook \"" + b.getBookName() + "\" is currently unavailable.\n");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("\nBook not found with ID: " + bookId + "\n");
        }
    }

    public static void returnBooks(int bookId) {
        boolean found = false;
        for (Book b : books) {
            if (b.getBookId() == bookId) {
                found = true;
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("\nBook \"" + b.getBookName() + "\" returned successfully.\n");
                } else {
                    System.out.println("\nThis book is not issued.\n");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("\nBook not found with ID: " + bookId + "\n");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("====== LIBRARY MENU ======");
            System.out.println("1. Add Books");
            System.out.println("2. View Books");
            System.out.println("3. Search Books");
            System.out.println("4. Issue Books");
            System.out.println("5. Return Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBooks(sc);
                case 2 -> viewBooks();
                case 3 -> {
                    System.out.print("Enter Book ID to search: ");
                    int Id = sc.nextInt();
                    searchBooks(Id);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to issue: ");
                    int booksId = sc.nextInt();
                    issueBooks(booksId);
                }
                case 5 -> {
                    System.out.print("Enter Book ID to return: ");
                    int bookId = sc.nextInt();
                    returnBooks(bookId);
                }
                case 6 -> System.out.println("\nThanks for using the Library System! Goodbye!\n");
                default -> System.out.println("\nPlease enter a valid choice.\n");
            }
        } while (choice != 6);

        sc.close();
    }
}
