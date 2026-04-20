package re.java_web_hn_k24_cntt5_lethanhtung_001.controller;

import re.java_web_hn_k24_cntt5_lethanhtung_001.model.Book;
import re.java_web_hn_k24_cntt5_lethanhtung_001.repository.BookRepository;
import re.java_web_hn_k24_cntt5_lethanhtung_001.dto.BookDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        var books = repo.search(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "book-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "book-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("book") BookDto bookDto, BindingResult result) {
        if (result.hasErrors()) {
            return "book-form";
        }
        Book book = new Book(
                bookDto.getId(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getQuantity(),
                bookDto.getCoverImage()
        );
        repo.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Book book = repo.findById(id);

        if (book == null) {
            return "redirect:/books";
        }
        BookDto dto = new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getQuantity(),
                book.getCoverImage()
        );
        model.addAttribute("book", dto);
        return "book-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        repo.delete(id);
        return "redirect:/books";
    }
}