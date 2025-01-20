// Question: Library Management System

// You are tasked with designing a Library Management System using the Facade Design Pattern. The system should provide a unified interface for the library's core functionalities, such as borrowing books, returning books, and searching for books. Internally, the library system consists of various subsystems to handle these functionalities.
// Requirements

//     Subsystems:
//         Inventory: Keeps track of available books.
//         Borrower Records: Manages information about borrowers and the books they have borrowed.
//         Fines: Tracks overdue fines for borrowers.

//     Unified Interface:
//         Implement a LibraryFacade that provides the following methods:
//             borrowBook(String bookName, String borrowerName)
//             returnBook(String bookName, String borrowerName)
//             searchBook(String bookName)

//     Subsystem Responsibilities:
//         Inventory:
//             Check if a book is available.
//             Update inventory when a book is borrowed or returned.
//         Borrower Records:
//             Add or remove books from a borrower's record.
//         Fines:
//             Calculate overdue fines (assume a fixed fine per day for simplicity).

//     Client Code:
//         Use the LibraryFacade to borrow a book, return it, and search for books without directly interacting with the subsystems.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class LibraryDao {
  Map<String, Integer> bookList;
  Map<String, ArrayList<String>> borrowerList;

  LibraryDao() {
    bookList = new HashMap<>();
    borrowerList = new HashMap<>();
  }

  public void addBooks(String bookName, int copies) {
    bookList.put(bookName, copies);
  }

  public boolean isBookAvailable(String bookName) {
    if (bookList.containsKey(bookName) && bookList.get(bookName) > 0) {
      return true;
    }
    return false;
  }

  public void borrowBook(String borrowerName, String bookName) {
    if (!borrowerList.containsKey(bookName)) {
      borrowerList.put(bookName, new ArrayList<>());
    }
    borrowerList.get(bookName).add(borrowerName);
    bookList.put(bookName, bookList.get(bookName) - 1);
  }

  public void returnBook(String bookName, String borrowerName) {
    borrowerList.get(bookName).remove(borrowerName);
    bookList.put(bookName, bookList.get(bookName) + 1);
  }
}

class LibraryFacade {
  LibraryDao libraryDao;
  LibraryFacade() {
    this.libraryDao = new LibraryDao();
  }
  public void addBooks(String bookName, int copies) {
    this.libraryDao.addBooks(bookName, copies);
  }
  public boolean searchBook(String bookName) {
    return this.libraryDao.isBookAvailable(bookName);
  }

  public boolean borrowBook(String borrowerName, String bookName) {
    if (this.libraryDao.isBookAvailable(bookName)) {
      this.libraryDao.borrowBook(borrowerName, bookName);
      return true;
    }
    return false;
  }

  public void returnBook(String bookName, String borrowerName) {
    this.libraryDao.returnBook(bookName, borrowerName);
  }
}

public class FacadeDesignPattern {
  public static void main(String []args) {
    LibraryFacade libFacade = new LibraryFacade();
    libFacade.addBooks("Pirates of the Carribean", 2);
    libFacade.addBooks("Harry Potter", 4);
    boolean bookFound = libFacade.searchBook("Pirates of the Carribean");
    if (bookFound) {
      System.out.println("Found the book");
    }
    else {
      System.out.println("Did not find the book");
    }
    boolean canBorrowBook = libFacade.borrowBook("Abhishek Chauhan", "Pirates of the Carribean");
    if (canBorrowBook) {
      System.out.println("Book borrowed, you can take it now");
    }
    else {
      System.out.println("This book cannot be borrowed at the moment");
    }
    libFacade.returnBook("Pirates of the Carribean", "Abhishek Chauhan");
  }
}
