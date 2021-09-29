package buddy_project.service.impl;

import buddy_project.entity.Book;
import buddy_project.repository.BookRepository;
import buddy_project.request.AddBookRequest;
import buddy_project.request.UpdateBookRequest;
import buddy_project.service.BookService;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Singleton
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name).get();
    }

    @Override
    public Boolean add(AddBookRequest request) throws Exception{
        Optional<Book> book = bookRepository.findByName(request.getName());
        if(book.isPresent()) {
            log.error("Book with name {} already exists", request.getName());
            throw new Exception("Book with name " + request.getName() + " already exist");
        }
        Book newBook = Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .category(request.getCategory())
                .sellingPrice(request.getSellingPrice())
                .publishDate(request.getPublishDate()).build();
        bookRepository.save(newBook);
        return true;
    }

    @Override
    public Boolean deleteByName(String name) throws Exception{
        Optional<Book> book = bookRepository.findByName(name);
        if(!book.isPresent()) {
            log.error("Book with name {} not available", name);
            throw new Exception("Book with name:" + name + " not available");
        }
        log.info("Deleting the book with name {}", name);
        bookRepository.deleteByName(name);
        return true;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean update(UpdateBookRequest request) throws Exception {
        Optional<Book> bookOptional = bookRepository.findByName(request.getName());
        if(!bookOptional.isPresent()) {
            log.error("Book with name {} not available", request.getName());
            throw new Exception("Book with name:" + request.getName() + " not available");
        }
        Book book = bookOptional.get();
        log.info("Updating the book with name {}", request.getName());
        book.setCategory(request.getCategory());
        book.setSellingPrice(request.getSellingPrice());
        return true;
    }
}
