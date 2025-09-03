Library Management System
A simple Java-based Library Management System that allows users to manage book borrowings and returns through a console interface. The system reads from and writes to a CSV file for persistent data storage.

Features
View Available Books: Display all books in the library with their current availability status

Borrow Books: Check out books by entering their book ID

Return Books: Return borrowed books by entering their book ID

Persistent Data Storage: All changes are saved to a CSV file for data persistence between sessions

**Usage**

Run the application

Use the menu to:

View all books (option 1)

Borrow a book by entering its ID (option 2)

Return a book by entering its ID (option 3)

Exit the application (option 4)

Data Format
The system uses a CSV file with the following columns:

book_id: Unique identifier for each book

title: Title of the book

author: Author of the book

available: Availability status ("yes" or "no")
