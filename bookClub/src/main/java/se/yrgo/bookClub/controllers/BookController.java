package se.yrgo.bookClub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.bookClub.data.BookRepository;
import se.yrgo.bookClub.domain.Book;

import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    private BookRepository data;

    public BookController(BookRepository data){
        this.data = data;
    }

    @GetMapping("/home")
    public String getHome(Model model){
        Date now = new Date();
        model.addAttribute("time", now);
        return "home";
    }

    @GetMapping("/genre")
    public String getGenre(@RequestParam(required = false) String type, Model model){
        List<Book> books;
        String message = "";
        if(type == null){
            books = data.findAll();
        }
        else {
            books = data.findByGenre(type);
            if(books.isEmpty()){
                message = "No entries found for genre " + type;
            }
        }
        model.addAttribute("books", books);
        model.addAttribute("message", message);
        return "booklist";
    }

    @GetMapping("/books")
    public String getBooks(Model model){
        List<Book> books = data.findAll();
        List<Book> topBooks = books.subList(0, 4);
        model.addAttribute("books", topBooks);
        return "books";
    }

}
