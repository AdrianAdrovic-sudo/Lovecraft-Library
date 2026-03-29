package org.lovecraftlibrary.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lovecraftlibrary.service.BookService;
import org.lovecraftlibrary.service.MemberService;

@ApplicationScoped
public class LibraryScheduler {

    @Inject
    BookService bookService;

    @Inject
    MemberService memberService;

    @Scheduled(every = "10s")
    void reportCounts() {
        long books = bookService.countAll();
        long members = memberService.countAll();
        System.out.println("[Lovecraft Library] Books in catalogue: " + books + " | Members registered: " + members);
    }
}