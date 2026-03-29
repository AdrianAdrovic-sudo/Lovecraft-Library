package org.lovecraftlibrary.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lovecraftlibrary.model.Author;
import org.lovecraftlibrary.model.Book;
import java.util.List;

@ApplicationScoped
public class AuthorService {

    @Inject
    EntityManager em;

    @Transactional
    public Author save(Author author) {
        em.persist(author);
        return author;
    }

    public List<Author> findAll() {
        return em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }

    public Author findById(Long id) {
        return em.find(Author.class, id);
    }

    public List<Author> findByName(String name) {
        return em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Book> findBooksByAuthor(Long authorId) {
        return em.createQuery("SELECT b FROM Book b WHERE b.author.id = :authorId", Book.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }
}