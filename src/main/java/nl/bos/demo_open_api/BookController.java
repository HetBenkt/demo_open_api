package nl.bos.demo_open_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return repository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @GetMapping("/")
    public List<Book> findBooks() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable("id") final long id, @RequestBody final Book book) {
        Book bookResult = repository.findById(id)
                .orElseThrow(BookNotFoundException::new);

        bookResult.setTitle(book.getTitle());
        bookResult.setAuthor(book.getAuthor());
        repository.save(bookResult);

        return bookResult;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Book createBook(@RequestBody final Book book) {
        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") final long id) {
        Book book = repository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        repository.delete(book);
    }
}
