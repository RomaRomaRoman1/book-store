package com.greatbit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name1", required=false, defaultValue="World") String name1, Model model) {//нужен эксперимент!!!
        model.addAttribute("name", name1);// используется для добавления атрибутов в модель.
        // В данном случае, атрибут "name" устанавливается равным значению параметра name из запроса.
        return "greeting";
    }

}