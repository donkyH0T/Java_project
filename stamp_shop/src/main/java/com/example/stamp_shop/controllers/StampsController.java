package com.example.stamp_shop.controllers;

import com.example.stamp_shop.models.Stamps;
import com.example.stamp_shop.repository.StampsRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
public class StampsController {

    @Autowired
    StampsRepository stampsRepository;

    @GetMapping("/stamps/{category}")
    public String techController(@PathVariable(value = "category") String category, HttpServletResponse response, Model model) throws IOException {
        switch (category){
            case "1":model.addAttribute("center","Отечественные марки");break;
            case "2":model.addAttribute("center","Марки по странам");break;
            case "3":model.addAttribute("center","Спортивные марки");break;
            case "4":model.addAttribute("center","Наборы и комплекты марок");break;
        }
        List<Stamps> stampsList= stampsRepository.findByCategory(Integer.parseInt(category));
        model.addAttribute("stampsList",stampsList);
        return "stamps";
    }

}
