package re.java_web_hn_k24_cntt5_lethanhtung_001.repository;

import re.java_web_hn_k24_cntt5_lethanhtung_001.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {
    private final Map<Long, Book> books = new HashMap<>();
    private Long counter = 1L;

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Book findById(Long id) {
        return books.get(id);
    }

    public void save (Book book) {
        if (book.getId() == null) {
            book.setId(counter++);
        }
        books.put(book.getId(), book);
    }

    public void delete(Long id) {
        books.remove(id);
    }

    public List<Book> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return findAll();
        String lower = keyword.toLowerCase();
        return books.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(lower)
                        || b.getAuthor().toLowerCase().contains(lower))
                .toList();
    }
}