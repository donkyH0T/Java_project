package com.example.internetshop.bean;


import com.example.internetshop.models.comment;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
@SessionScope
@Component
public class HttpSessionBean {
    List<Integer> technique;
   public HttpSessionBean(){
        technique=new ArrayList<>();
    }

    public void delTechnique(int id){technique.remove(technique.indexOf(id));}
    public void addTechnique(int technique1){
        technique.add(technique1);
    }

}
