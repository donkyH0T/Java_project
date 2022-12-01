package com.example.internetshop.Controllers;

import com.example.internetshop.bean.HttpSessionBean;
import com.example.internetshop.models.*;
import com.example.internetshop.repo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    HttpSessionBean httpSessionBean;

    @Autowired
    private techniqueRepository techniqueRepository;


    @Autowired
    private categoryRepository categoryRepository;

    @Autowired
    private commentRepository commentRepository;

    //private techniqueRepository repository;

    @GetMapping("/")
   public String mainController(Model model){
        List<comment> tmp= commentRepository.findAll();
        model.addAttribute("otziv",tmp);
        return "main";
    }

        @GetMapping("/technique/{category}")
        public String techController(  @PathVariable(value = "category") String category, HttpServletResponse response, Model model) throws IOException {
        switch (category){
            case "1":model.addAttribute("center","Крупная бытовая техника");model.addAttribute("title","Купить крупную бытовую технику");break;
            case "2":model.addAttribute("center","Техника для кухни");model.addAttribute("title","Купить технику для кухни");break;
            case "3":model.addAttribute("center","Техника для дома");model.addAttribute("title","Купить технику для дома");break;
            case "4":model.addAttribute("center","Кондиционеры");model.addAttribute("title","Купить кондиционеры");break;
            case "5":model.addAttribute("center","Аудиотехника");model.addAttribute("title","Купить аудиотехнику");break;
            case "6":model.addAttribute("center","Фототехника");model.addAttribute("title","Купить фототехнику");break;
        }
            List<com.example.internetshop.models.category> categories= categoryRepository.findByCategory(Integer.parseInt(category));
        model.addAttribute("techniques",categories);
        return "technique";
        }


    @GetMapping("/hubBasket")
    public String hubBasket(){
        return "basket";
    }

    @GetMapping("/admin/addTechnique")
    public String Add(Model model){
        return "addTechnique";
    }

    @PostMapping("/admin/addTechnique")
    public String AddTech(Model model,HttpServletResponse response,HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        technique technique1 =new technique();
        technique1.setName(request.getParameter("name"));
        technique1.setPrice(request.getParameter("price"));
        technique1.setId(Long.valueOf(request.getParameter("id")));
        category category1=categoryRepository.findById(Long.valueOf(request.getParameter("category"))).get();
        String tmp="";
        switch (category1.getCategory()){
            case 1:tmp="large_home_appliances";break;
            case 2:tmp="kitchen_appliances";break;
            case 3:tmp="home_appliances";break;
            case 4:tmp="air_conditioners";break;
            case 5:tmp="Audio";break;
            case 6:tmp="photo_equipment";break;
        }
        byte[] data=file.getBytes();
        File file1=new File("src/main/resources/static/img/"+tmp+"/"+ technique1.getName()+".jpg");
        file1.createNewFile();
        FileOutputStream file2 = new FileOutputStream(file1);
        file2.write(data);
        file2.close();
        technique1.setURL(tmp+"/"+ technique1.getName()+".jpg");
        techniqueRepository.saveAndFlush(technique1);
        category1.getTechniques().add(technique1);
        technique1.setCategory(category1);
        techniqueRepository.saveAndFlush(technique1);
        return "addTechnique";
    }
    @GetMapping("/admin/deleteTechnique")
    public String Del(){
        return "deleteTechnique";
    }

    @PostMapping("/admin/deleteTechnique")
    public String Del(@RequestParam("id") String id){

        technique technique = techniqueRepository.findById(Long.valueOf(id)).get();
        category category1= technique.getCategory();
        List<technique> tmp=category1.getTechniques();
        tmp.remove(tmp.indexOf(technique));
        categoryRepository.save(category1);
        technique.setCategory(null);
        techniqueRepository.save(technique);
        techniqueRepository.delete(technique);
        return "deleteTechnique";
    }

}







