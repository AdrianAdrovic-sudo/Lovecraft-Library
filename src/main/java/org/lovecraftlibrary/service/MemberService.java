package org.lovecraftlibrary.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.lovecraftlibrary.model.Loan;
import org.lovecraftlibrary.model.Member;
import org.lovecraftlibrary.model.MemberBook;
import java.util.List;

@ApplicationScoped
public class MemberService {

    @Inject
    EntityManager em;

    @Transactional
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Loan> findLoansByMember(Long memberId) {
        return em.createQuery("SELECT l FROM Loan l WHERE l.member.id = :memberId", Loan.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<MemberBook> findWishlistByMember(Long memberId) {
        return em.createQuery("SELECT mb FROM MemberBook mb WHERE mb.member.id = :memberId", MemberBook.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public long countAll() {
        return em.createQuery("SELECT COUNT(m) FROM Member m", Long.class).getSingleResult();
    }
}