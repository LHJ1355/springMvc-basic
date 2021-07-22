package hello.springmvc.controller;

import hello.springmvc.domain.Item;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute(new Item());
        return "message/addForm";
    }
}
