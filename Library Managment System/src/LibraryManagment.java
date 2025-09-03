import java.io.*;
import java.util.*;


class Book{
    String bookId;
    String title;
    String author;
   boolean available;

    Book( String bookID, String title, String author, boolean available){
        this.bookId = bookID;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    @Override
    public String toString(){
        return bookId + ", " + title + " by" + author + " -" + (available ? "Available" : "Borrowed");
    }
}

public class LibraryManagment {


    private static final String FILE = "data/books.csv";

    public static List<Book> loadBooks(){
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))){
                String line = br.readLine();
                while((line = br.readLine())!=null){
                    String[] parts = line.split(",");
                    if(parts.length == 4){
                        boolean available = parts[3].trim().equalsIgnoreCase("yes")
                                || parts[3].trim().equalsIgnoreCase("true");
                        books.add(new Book(parts[0].trim(), parts[1].trim(), parts[2].trim(), available));
                    }
                }
            }
         catch (Exception e) {
            System.out.println("Error reading file" + e.getMessage());
        }
        return books;
    }

    public static void saveBooks(List<Book> books){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))){
                bw.write("book_id, title, author, available");
                bw.newLine();
                for (Book b : books) {
                    String availability = b.available ? "yes" : "no";
                    bw.write(String.join(",", b.bookId,b.title,b.author,availability));
                    bw.newLine();
                }
        }catch(IOException e){
            System.out.println("Error writing file:" +e.getMessage());
        }
    }

    public static void displayBooks(List<Book> books){
        System.out.println("\nAvailable books:");
        for (Book b : books) {
            System.out.println(b);
            
        }
    }

    public static void borrowBook(List<Book> books, String bookId){
        for (Book b : books) {
            if (b.bookId.equals(bookId)) {
                if (b.available) {
                    b.available = false;
                    saveBooks(books);
                    System.out.println("You borrowed "+b.title+ " '");
                    return;
                }else{
                    System.out.println("Sorry this book is already borrowed.");
                    return;
                }
            }
            
        }
        System.out.println("Book not Found.");
    }
        public static void returnBook(List<Book> books, String bookId){
            for(Book b : books){
                if (b.bookId.equals(bookId)) {
                    if (b.available) {
                        b.available = true;
                        saveBooks(books);
                        System.out.println("You returned '" + b.title + "'");
                        return;
                    }else{
                        System.out.println("This Book was not borrowed.");
                        return;
                    }
                    
                }
            }
        System.out.println("Book not found");
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        List<Book> books = loadBooks();

        while (true) {
            System.out.println("\n---Library Menu---");
            System.out.println("1. Display Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.println("Enter choice");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    displayBooks(books);
                    break;
                case "2":
                    System.out.println("Enter Book ID to borrow");
                    borrowBook(books, sc.nextLine());
                    break;
                case "3":
                    System.out.println("Enter Book ID to return");
                    returnBook(books, sc.nextLine());
                    break;
                case "4":
                    System.out.println("Goodbye");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try Again");
            }
        }
    }
    
}
