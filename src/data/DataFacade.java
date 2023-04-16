package data;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DataFacade {

    private static DataFacade dataFacade;

    List<User> users = new ArrayList<>();
    List<Address> addresses = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    List<CheckoutBooks> checkoutBooks = new ArrayList<>();

    public static DataFacade getInstance() {
        if (dataFacade == null) {
            dataFacade = new DataFacade();
        }

        return dataFacade;
    }

    private DataFacade() {
        //populate test data
        users.addAll(TestData.getTestUsers());
        addresses.addAll(TestData.getAddresses());
        authors.addAll(TestData.getAuthors());
        books.addAll(TestData.getBooks());
        members.addAll(TestData.getMembers());
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

    public List<Book> getBooks(){
        return this.books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<CheckoutBooks> getCheckoutBooks() {
        return checkoutBooks;
    }

    public void addCheckoutBook(int id, Book book, Member member) {
        this.checkoutBooks.add(new CheckoutBooks(book, member));
    }

    public void removeCheckout(CheckoutBooks selectedCheckout) {
        this.checkoutBooks.remove(selectedCheckout);
    }
}
