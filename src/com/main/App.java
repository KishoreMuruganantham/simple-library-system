package com.main;

import com.model.Author;
import com.model.Book;
import com.model.Member;
import com.service.AuthorService;
import com.service.BookService;
import com.service.MemberService;
import com.enums.BookStatus;
import com.enums.Genre;

import java.util.List;

public class App {

    public static void main(String[] args) {
        AuthorService authorService = new AuthorService();
        MemberService memberService = new MemberService();
        BookService bookService = new BookService();

        // seed 2 authors first (seed-first rule)
        Author author1 = new Author(0, "George Orwell", "orwell@example.com");
        Author author2 = new Author(0, "Jane Austen", "austen@example.com");
        authorService.save(author1);
        authorService.save(author2);

        // seed 2 members
        Member member1 = new Member(0, "John Smith", "john@example.com");
        Member member2 = new Member(0, "Alice Brown", "alice@example.com");
        memberService.save(member1);
        memberService.save(member2);

        // save 3 books (at least one BORROWED with member, at least two AVAILABLE unborrowed)
        Book book1 = new Book(0, "1984", 1949, Genre.FICTION, BookStatus.AVAILABLE, author1, null);
        Book book2 = new Book(0, "Pride and Prejudice", 1813, Genre.FICTION, BookStatus.AVAILABLE, author2, null);
        Book book3 = new Book(0, "Animal Farm", 1945, Genre.FICTION, BookStatus.BORROWED, author1, member1);
        bookService.save(book1);
        bookService.save(book2);
        bookService.save(book3);

        // print one findById
        System.out.println("---------- Book findById (id=1) ----------");
        Book found = bookService.findById(1);
        System.out.println(found);

        // print all three findAlls
        System.out.println("---------- Author findAll ----------");
        List<Author> authors = authorService.findAll();
        authors.forEach(System.out :: println);

        System.out.println("---------- Member findAll ----------");
        List<Member> members = memberService.findAll();
        members.forEach(System.out :: println);

        System.out.println("---------- Book findAll ----------");
        List<Book> books = bookService.findAll();
        books.forEach(System.out :: println);
    }
}