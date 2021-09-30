package buddy_project.controller;

import buddy_project.entity.Book;
import buddy_project.request.AddBookRequest;
import buddy_project.request.FilterBookRequest;
import buddy_project.request.UpdateBookRequest;
import buddy_project.response.BooleanResponse;
import buddy_project.service.BookService;
import io.micronaut.http.annotation.*;

import java.util.List;

@Controller(value = "/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Get(value = "/getByName/{name}")
    public Book getByName(String name) {
        return bookService.findByName(name);
    }

    @Post(value = "/add")
    public BooleanResponse add(AddBookRequest request){
        try {
            boolean result = bookService.add(request);
            return new BooleanResponse(result, null);
        } catch (Exception e) {
            return new BooleanResponse(false, e.getMessage());
        }
    }

    @Delete(value = "/deleteByName/{name}")
    public BooleanResponse deleteByName(String name) {
        try {
            boolean result = bookService.deleteByName(name);
            return new BooleanResponse(result, null);
        } catch (Exception e) {
            return new BooleanResponse(false, e.getMessage());
        }
    }

    @Put(value = "/update")
    public BooleanResponse update(UpdateBookRequest request) {
        try {
            boolean result = bookService.update(request);
            return new BooleanResponse(result, null);
        } catch (Exception e) {
            return new BooleanResponse(false, e.getMessage());
        }
    }

    @Post(value = "/filter")
    public List<Book> filter(FilterBookRequest filterBookRequest) {
        return bookService.filter(filterBookRequest);
    }

}
