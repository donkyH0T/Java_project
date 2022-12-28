package com.example.stamp_shop.restControllers;


import com.example.stamp_shop.basket.Id;
import com.example.stamp_shop.bean.HttpSessionBean;
import com.example.stamp_shop.models.Comments;
import com.example.stamp_shop.models.Stamps;
import com.example.stamp_shop.repository.CommentsRepository;
import com.example.stamp_shop.repository.StampsRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {

    @Autowired
    StampsRepository stampsRepository;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    HttpSessionBean httpSessionBean;

    @PostMapping(value = "basket/add")
    public ResponseEntity<String> getName(@RequestBody Id id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Stamps stamps=stampsRepository.findById(Long.valueOf(id.getId())).get();
        if (!httpSessionBean.exists(stamps)) {
            httpSessionBean.addStamp(stamps);
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping(value = "comment/add")
    public ResponseEntity<Comments> addComment(HttpServletRequest request, HttpServletResponse response){
        Comments comments=new Comments(request.getParameter("name"),request.getParameter("text"));
        comments= commentsRepository.save(comments);
        return new ResponseEntity<Comments>(comments,HttpStatus.OK);
    }
    @GetMapping(value = "comments")
    public List<Comments> getComment(HttpServletRequest request, HttpServletResponse response){
        return commentsRepository.findAll();
    }
    @GetMapping(value = "basketStamps")
    public List<Stamps> getProducts(HttpServletRequest request, HttpServletResponse response){
        return httpSessionBean.getStampsList();
    }
    @PostMapping(value = "basket/del")
    public ResponseEntity<Object> delProduct(@RequestBody Id id){
        httpSessionBean.delStamps(stampsRepository.findById(Long.valueOf(id.getId())).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
