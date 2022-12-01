package com.example.internetshop.rest;

import com.example.internetshop.basket.addElem;
import com.example.internetshop.basket.deleteElem;
import com.example.internetshop.bean.HttpSessionBean;
import com.example.internetshop.models.comment;
import com.example.internetshop.models.technique;
import com.example.internetshop.repo.commentRepository;
import com.example.internetshop.repo.techniqueRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {

    @Autowired
    HttpSessionBean httpSessionBean;

    @Autowired
    techniqueRepository techniqueRepository;

    @Autowired
    commentRepository commentRepository;

    @PostMapping(value = "basket/add")
    public ResponseEntity<String> getName(@RequestBody addElem elem, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id= elem.getElem();
        httpSessionBean.addTechnique(Integer.parseInt(id));
        return new ResponseEntity<String>(id,HttpStatus.OK);
    }

    @PostMapping(value = "comment/add")
    public ResponseEntity<comment> addComment(HttpServletRequest request, HttpServletResponse response){
        comment comment=new comment(request.getParameter("name"),request.getParameter("text"));
        comment= commentRepository.save(comment);
        return new ResponseEntity<comment>(comment,HttpStatus.OK);
    }
    @GetMapping(value = "comments")
    public List<comment> getComment(HttpServletRequest request, HttpServletResponse response){
        return commentRepository.findAll();
    }
    @GetMapping(value = "products")
    public List<technique> getProducts(HttpServletRequest request, HttpServletResponse response){
        int size=httpSessionBean.getTechnique().size();
        List<technique> techniques = new ArrayList<>();
        for(int i=0;i<size;i++){
            techniques.add(techniqueRepository.findById(Long.valueOf(httpSessionBean.getTechnique().get(i))).get());
        }

        return techniques;
    }
    @PostMapping(value = "product/del")
    public ResponseEntity<Object> delProduct(@RequestBody deleteElem elem){
        httpSessionBean.delTechnique(Integer.parseInt(elem.getId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
