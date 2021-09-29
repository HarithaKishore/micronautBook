package buddy_project.repository;

import buddy_project.entity.Book;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByName(String name);
    void deleteByName(String name);
}
