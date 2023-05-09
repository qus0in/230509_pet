package io.playdata.pet.controller;

import io.playdata.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "빨리 빨리 등록하세요!");
        return "index";
    }
}
