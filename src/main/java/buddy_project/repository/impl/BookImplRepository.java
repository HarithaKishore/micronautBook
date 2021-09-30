package buddy_project.repository.impl;

import buddy_project.entity.Book;
import buddy_project.repository.BookRepository;
import buddy_project.request.FilterBookRequest;
import io.micronaut.data.annotation.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class BookImplRepository implements BookRepository {

    private final EntityManager entityManager;

    public BookImplRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<Book> filter(FilterBookRequest filterBookRequest) {
        boolean firstWhereCaluse = true;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM book ");
        if(filterBookRequest.getName() != null) {
            if(firstWhereCaluse) {
                query.append("where name = :name ");
                firstWhereCaluse = false;
            } else {
                query.append("and name = :name ");
            }
        }
        if(filterBookRequest.getCategory() != null) {
            if(firstWhereCaluse) {
                query.append("where category = :category ");
                firstWhereCaluse = false;
            } else {
                query.append("and category = :category ");
            }
        }
        if(filterBookRequest.getAuthor() != null) {
            if(firstWhereCaluse) {
                query.append("where author = :author ");
                firstWhereCaluse = false;
            } else {
                query.append("and author = :author ");
            }
        }
        if(filterBookRequest.getSellingPrice() != null) {
            if(firstWhereCaluse) {
                query.append("where selling_price = :sellingPrice ");
            } else {
                query.append("and selling_price = :sellingPrice ");
            }
        }
        Query entityManagerNativeQuery = entityManager.createNativeQuery(query.toString(),Book.class);
        if(filterBookRequest.getName()!= null) {
            entityManagerNativeQuery.setParameter("name",filterBookRequest.getName());
        }
        if(filterBookRequest.getAuthor()!= null) {
            entityManagerNativeQuery.setParameter("author",filterBookRequest.getAuthor());
        }
        if(filterBookRequest.getCategory()!= null) {
            entityManagerNativeQuery.setParameter("category",filterBookRequest.getCategory());
        }
        if(filterBookRequest.getSellingPrice()!= null) {
            entityManagerNativeQuery.setParameter("sellingPrice",filterBookRequest.getSellingPrice());
        }
        return entityManagerNativeQuery.getResultList();
    }
}
