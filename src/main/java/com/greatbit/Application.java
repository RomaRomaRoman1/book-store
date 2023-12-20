package com.greatbit;

import com.greatbit.models.Book;
import com.greatbit.models.BooksStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        BooksStorage.getBooks().add(new Book("Учение Дона Хуана", "Карлос Кастонеле", 1000));
        BooksStorage.getBooks().add(new Book("Funny Animals", "Peter Parker", 199));
        SpringApplication.run(Application.class, args);
    }


}
