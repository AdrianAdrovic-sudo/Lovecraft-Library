package org.lovecraftlibrary.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lovecraftlibrary.model.Book;
import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    EntityManager em;

    @Transactional
    public void save(Book book) {
        em.persist(book);
    }

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}