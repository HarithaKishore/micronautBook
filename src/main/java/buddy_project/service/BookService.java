package buddy_project.service;

import buddy_project.entity.Book;
import buddy_project.request.AddBookRequest;
import buddy_project.request.UpdateBookRequest;

public interface BookService {
    Book findByName(String name);
    Boolean add(AddBookRequest request) throws Exception;
    Boolean deleteByName(String name) throws Exception;
    Boolean update(UpdateBookRequest request) throws Exception;
}
