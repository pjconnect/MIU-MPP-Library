package controller;

import data.*;

import java.util.List;
import java.util.Objects;

public class SystemController {

    private DataFacade df = DataFacade.getInstance();
    private User loggedInUser;
    private static SystemController systemController;

    public static SystemController getInstance(){
        if(systemController == null){
           systemController = new SystemController();
        }
        return systemController;
    }

    private SystemController(){

    }



    public User Login(String txtUsername, String txtPassword) {
        // get all users
        for(User user: df.getUsers()){
            if(user.username.equals(txtUsername) && user.password.equals(txtPassword)){
                loggedInUser = user;
                break;
            }
        }

        return loggedInUser;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }

    public void logOut() {
        loggedInUser = null;
    }

    public List<Address> getAddresses() {
        return this.df.getAddresses();
    }

    public List<Member> getMembers() {
        return df.getMembers();
    }

    public void addAuthor(Author author) {
        df.addAuthor(author);
    }

    public List<Author> getAuthors() {
        return df.getAuthors();
    }


    public List<Book> getBooks() {
        return df.getBooks();
    }

    public boolean checkoutBook(int bookIndex, int memberIndex) {
        var book = df.getBooks().get(bookIndex);
        var member = df.getMembers().get(memberIndex);
        //check availability
        var checkoutList = df.getCheckoutBooks();
        var outBooks = 0;
        for(CheckoutBooks b: checkoutList){
            if(b.book.equals(book)){
                outBooks ++;
            }
        }
        if(outBooks >= book.getCopyOfBooks()){
            return false;
        }
        //add to check out
        df.addCheckoutBook(checkoutList.size() + 1, book, member);
        return true;
    }

    public List<CheckoutBooks> getCheckoutBooks() {
        return df.getCheckoutBooks();
    }

    public void returnBook(int selectedCheckoutIndex) {
        var selectedCheckout = df.getCheckoutBooks().get(selectedCheckoutIndex);
        df.removeCheckout(selectedCheckout);
    }

    public String updateBookCopies(int selectedBookIndex, String numberOfCopiesStr) {
        try {
            var selectedBook = df.getBooks().get(selectedBookIndex);
            var numberOfCopies = Integer.parseInt(numberOfCopiesStr);
            df.uploadCopyOfBooks(selectedBook,  numberOfCopies);
            return "Success";
        }catch(Exception ex){
            return ex.getMessage();
        }
    }

    public int getAddressIndex(Address address) {
        var addresses = df.getAddresses();
        var index = 0;
        for(Address a:addresses){
            if(a.equals(address)){
                return index;
            }
            index++;
        }
        return index;
    }

    public void editMember(Member selectedMember, String firstName, String lastName, String phone, int selectedAddressIndex) {
        var address = df.getAddresses().get(selectedAddressIndex);
        selectedMember.setFirstName(firstName);
        selectedMember.setLastName(lastName);
        selectedMember.setPhone(phone);
        selectedMember.setAddress(address);
    }

    public void addBook(String title, String ISBN, Author selectedAuthor, int avaiableBooks, int days) {
        df.addBook(new Book(df.getBooks().size()+1, title, ISBN, selectedAuthor, avaiableBooks, days));
    }

    public Member getSelectedMember(int selectedIndex) {
        return df.getMembers().get(selectedIndex);
    }

    public Address getSelectedAddress(int selectedIndex) {
        return df.getAddresses().get(selectedIndex);
    }

    public void addMember(String firstName, String lastName, String phone, int selectedAddressIndex) {
        df.addMember(new Member((df.getMembers().size() + 1), firstName, lastName, phone, getSelectedAddress(selectedAddressIndex)));
    }

    public void addAddress(String street, String city, String state, String zip) {
        df.addAddress(new Address(street, city, state,zip));
    }

    public Book getBook(int selectedIndex) {
        return df.getBooks().get(selectedIndex);
    }

    public int getMemberIndexById(String idStr) {
        var id = Integer.parseInt(idStr);
        var index = 0;
        var members = df.getMembers();
        for(Member m: members){
            if(m.MemberId == id){
                return index;
            }
            index++;
        }
        return -1;
    }

    public int getBookIndexByISBN(String isbn) {
        var index = 0;
        var books = df.getBooks();
        for(Book m: books){
            if(Objects.equals(m.ISBN, isbn)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String addAuthor(String firstName, String lastName, String phone, int selectedAddressIndex) {
        var address = getSelectedAddress(selectedAddressIndex);
        df.addAuthor(new Author(firstName, lastName, phone, address));
        return "Success";
    }
}
