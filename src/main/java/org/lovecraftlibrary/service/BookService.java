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
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findByTitle(String title) {
        return em.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class)
                .setParameter("title", title)
                .getResultList();
    }

    public long countAll() {
        return em.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
    }
}