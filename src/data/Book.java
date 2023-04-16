package data;

public class Book {
    public final int ID;
    public final String title;
    public final String ISBN;
    public final Author author;
    private int copyOfBooks;

    public int getCopyOfBooks(){
        return copyOfBooks;
    }

    public void setCopyOfBooks(int copies){
        copyOfBooks = copies;
    }

    public Book(int id, String title, String ISBN, Author author, int copyOfBooks) {
        this.ID = id;
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.copyOfBooks = copyOfBooks;
    }

    @Override
    public String toString() {
        return ID + " " + title + " " + ISBN + " by " + author;
    }

    @Override
    public boolean equals(Object book) {
        if (!(book instanceof Book b)) {
            return false;
        }

        return b.ID == this.ID;
    }
}
