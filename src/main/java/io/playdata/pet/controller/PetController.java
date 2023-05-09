package io.playdata.pet.controller;
import io.playdata.pet.model.Pet;

import io.playdata.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("msg", "빨리 빨리 등록하세요!");
        model.addAttribute("pets", petService.getAllPets());
        return "index";
        // http://localhost:8080
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("pet", new Pet());
        // add.html에 form을 만들건데, 그 form에 그릇이 될 빈 객체를 전달
        return "add"; // static/templates/add.html
        // http://localhost:8080/add
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("pet") Pet pet,
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("voiceFile") MultipartFile voiceFile,
            Model model
    ) throws IOException {
        petService.savePet(pet, imageFile, voiceFile);
        return "redirect:/";
    }
}
