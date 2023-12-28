package com.greatbit.models;

import java.util.*;

public class BooksStorage {
    private static Set<Book> books= new HashSet<>();
    static {//что это за способ?
        books.add(new Book(UUID.randomUUID().toString(),"Учение Дона Хуана", "Карлос Кастонеле", 1000));
        books.add(new Book(UUID.randomUUID().toString(),"Funny Animals", "Peter Parker", 199));
    }
    public static Set<Book> getBooks(){
        return books;
    }//зачем их возвращать?
}
