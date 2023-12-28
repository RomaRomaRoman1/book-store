package com.greatbit.controllers;

import com.greatbit.models.Book;
import com.greatbit.models.BooksStorage;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class BooksController {
    @GetMapping("/")
    public String booksList(Model model){
        model.addAttribute("books", BooksStorage.getBooks());
        return "books-list";
    }
    @GetMapping("/create-form")
    public String createForm(){
        return "create-form";
    }
    @PostMapping("/create")//получение объекта из формы с помощью Spring MVC - "Data Binding" (привязка данных). Так как данные из тела запроса сходятся, то автоматически подставятся значения
    //@PostMapping:
    //Используется, когда вы хотите обработать данные, отправленные с клиента, обычно через HTML-форму с методом POST.
    //Пример: <form method="post" action="/create">.
    public String create(Book book) {
        book.setId(UUID.randomUUID().toString());// null param isn't allowed
        BooksStorage.getBooks().add(book);//добавление книги из параметра метода в список книг
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")//Spring will understand, that this param {} is mutable
    public String delete(@PathVariable("id") String id){//из значение "id" Spring подставит значение в переменную String id {"id"}
        Book bookToDelete = BooksStorage.getBooks().stream().
                filter(book -> book.getId().equals(id)).findFirst().
                orElseThrow(RuntimeException::new);//находим с помощью филтрации нашу книгу
        BooksStorage.getBooks().remove(bookToDelete);//удаляем книгу из списка книг
        return "redirect:/";
    }
    @GetMapping("/edit-form/{id}")//переход на форму редактирования
    //@GetMapping:
    //Используется, когда вы хотите обработать GET-запросы, например, запросы, инициированные кликом по ссылке или введенные в адресной строке браузера.
    //Пример: <a th:href="@{'/edit-form/' + ${debtor.id}}">Edit</a>.
    public String editForm(@PathVariable("id") String id, Model model){//id книги будет подставлен String id
        Book bookToEdit = BooksStorage.getBooks().stream().
                filter(book -> book.getId().equals(id)).findFirst().
                orElseThrow(RuntimeException::new);//мы находим книгу фильтрацией потока
        model.addAttribute("book", bookToEdit);//все данные передаем в модель, чтобы передать далее их в форму для отрисовки шаблона
        return "edit-form";//тут происходит отправка данных на шаблон edit-form.html - то есть, просто передача данных из книги в форму
    }
    @PostMapping("/update")//метод POST указывается потому, что форма имеет в action POST метод.
    public String update(Book book) {//к нам приходят все данные книги из шаблона, на основе которого мы и создаем экземпляр(передаем на вход методу в качестве параметра)
        //экземпеляр строится на основе параметров имен полей "name", они должны совпадать или указывать атрибут отдельно
       delete(book.getId());// Удаляем старую версию книги по идентификатору
       BooksStorage.getBooks().add(book); // Добавляем обновленную книгу
        return "redirect:/";// Перенаправляем пользователя на главную страницу
    }

}
