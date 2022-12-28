package com.example.stamp_shop.bean;


import com.example.stamp_shop.models.Stamps;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
@SessionScope
@Component
public class HttpSessionBean {
    List<Stamps> stampsList;
    int size;

    public HttpSessionBean() {
        stampsList=new ArrayList<>();
        size=0;
    }

    public List<Stamps> getStampsList() {
        return stampsList;
    }

    public boolean exists(Stamps stamps){
        for(Stamps stamps1:stampsList){
            if(stamps1.getId().equals(stamps.getId())){
                return true;
            }
        }
       return false;
    }

    public void delStamps(Stamps stamps){
        stampsList.removeIf(stamps1 -> stamps.getId().equals(stamps1.getId()));

    }

    public void addStamp(Stamps stamps){
        stampsList.add(stamps);
    }
}
