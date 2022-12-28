package com.example.stamp_shop.controllers;

import com.example.stamp_shop.models.Country;
import com.example.stamp_shop.models.Stamps;
import com.example.stamp_shop.repository.CountryRepository;
import com.example.stamp_shop.repository.StampsRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    StampsRepository stampsRepository;

    @Autowired
    CountryRepository countryRepository;


    @GetMapping("/admin/addStamp")
    public String Add(){
        return "addStamp";
    }

    @PostMapping("/admin/addStamp")
    public String AddTech(Model model, HttpServletResponse response, HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        Stamps stamps=new Stamps();
        stamps.setName(request.getParameter("name"));
        stamps.setCategory(Integer.parseInt(request.getParameter("category")));
        stamps.setPrice(request.getParameter("price"));
        stamps.setId(Long.valueOf(request.getParameter("id")));
        Country country=countryRepository.findByName(request.getParameter("city"));
        String tmp=country.getName();
        switch (stamps.getCategory()){
            case 3:tmp="sports brands";break;
            case 4:tmp="Sets and sets of stamps";break;
            case 1:tmp="domestic brands";break;
        }
        byte[] data=file.getBytes();
        File file1=new File("src/main/resources/static/img/"+tmp+"/"+ stamps.getName()+".jpg");
        System.out.println(file1.getAbsoluteFile());
        file1.createNewFile();
        FileOutputStream file2 = new FileOutputStream(file1);
        file2.write(data);
        file2.close();
        stamps.setURL(tmp+"/"+ stamps.getName()+".jpg");
        stampsRepository.saveAndFlush(stamps);
        country.getStamps().add(stamps);
        stamps.setCountry(country);
        countryRepository.saveAndFlush(country);
        return "addStamp";
    }
    @GetMapping("/admin/deleteStamp")
    public String Del(){
        return "deleteStamp";
    }



    @PostMapping("/admin/deleteStamp")
    public String Del(@RequestParam("id") String id){
        Stamps stamps= stampsRepository.findById(Long.valueOf(id)).get();
        Country country= stamps.getCountry();
        List<Stamps> tmp=country.getStamps();
        tmp.remove(tmp.indexOf(stamps));
        countryRepository.save(country);
        stamps.setCountry(null);
        stampsRepository.save(stamps);
        stampsRepository.delete(stamps);
        return "deleteStamp";
    }
}
