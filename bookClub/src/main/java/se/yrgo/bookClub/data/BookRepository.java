package se.yrgo.bookClub.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.bookClub.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    public List<Book> findByGenre(String genre);
}
