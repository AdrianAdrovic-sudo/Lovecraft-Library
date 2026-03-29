package org.lovecraftlibrary.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lovecraftlibrary.model.Loan;
import java.util.List;

@ApplicationScoped
public class LoanService {

    @Inject
    EntityManager em;

    @Transactional
    public Loan save(Loan loan) {
        em.persist(loan);
        return loan;
    }

    public List<Loan> findAll() {
        return em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    public Loan findById(Long id) {
        return em.find(Loan.class, id);
    }
}