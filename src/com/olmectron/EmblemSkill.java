/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialTooltip;
import javafx.scene.image.Image;

/**
 *
 * @author olmec
 */
public class EmblemSkill {
    private String name, description, imageURL;
    private int code;
    private Image image;
    public EmblemSkill(String name, String description, int code){
        this.name=name;
        this.description=description;
        this.code=code;
        this.imageURL=Utils.trim(name).replace(" ", "_").replace("'", "").toLowerCase()+".png";
        try{
        //System.out.println(MaterialDesign.customPath+"/skills_images/"+imageURL);
        this.image=new Image(MaterialDesign.customPath+"/skills_images/"+imageURL);
        }
        catch(IllegalArgumentException ex){
            System.out.println("Error CARGANDO "+imageURL);
        }
    }
    public String getHelp(){
        return description;
    }
    public String getName(){
        return name;
    }
    
    public int getCode(){
        return code;
    }
    
    public EmblemSkill(String name, int code){
        this(name,null,code);
    }
    public Image getImage(){
        return image;
    }
    public String getImageURL(){
        return this.imageURL;
    }
    
}
