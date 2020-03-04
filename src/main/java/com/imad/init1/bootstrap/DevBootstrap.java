package com.imad.init1.bootstrap;

import com.imad.init1.model.Author;
import com.imad.init1.model.Book;
import com.imad.init1.model.Publisher;
import com.imad.init1.repositories.AuthorRepository;
import com.imad.init1.repositories.BookRepository;
import com.imad.init1.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher gaumont = new Publisher("Société", "Gaumont");
        Book ddd = new Book("Domain Driven Design", "1234" );

        ddd.setPublisher(gaumont);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(gaumont);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher darkitab = new Publisher("dar", "kitab");
        Book noEJB = new Book("J2EE Development without EJB", "23444");

        noEJB.setPublisher(darkitab);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(darkitab);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
