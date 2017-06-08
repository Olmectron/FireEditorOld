/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.MaterialDesign;
import java.util.ArrayList;

/**
 *
 * @author olmec
 */
public class EmblemPortrait {
    private String name;
    private String hexCode;
    private static String[] names=new String[]{
    "01","02","03","04","05",
    "Marth","Eirika","Katarina",
        "Roy","Micaiah","Leif","Alm","Seliph",
        "Elincia","Lyn","Ephraim","Celica","Ike"
    };
     private static String[] codes=new String[]{
    "00","01","02","03","04",
    "0B","5B","9B",
        "10","18","38","43","48",
        "50","60","69","72","79"
    };
     public static int getDLCUnitPositionFromCode(int code){
         for(int i=0;i<getDLCPortraits().size();i++){
                    if(getDLCPortraits().get(i).getHexCode().equals(Utils.getHexPair(code))){
                        return i;
                    }
                }
                return -1;
     }
     public static int getDLCUnitPositionFromCode(String code){
         for(int i=0;i<getDLCPortraits().size();i++){
                    if(getDLCPortraits().get(i).getHexCode().equals(code)){
                        return i;
                    }
                }
                return -1;
     }
    private static ArrayList<EmblemPortrait> dlcPortraits;
    public static  ArrayList<EmblemPortrait> getDLCPortraits(){
        if(dlcPortraits==null){
            dlcPortraits=new ArrayList<EmblemPortrait>();
            for(int i=0;i<names.length;i++){
                String nombre=names[i];
                String codigo=codes[i];
                dlcPortraits.add(new EmblemPortrait(nombre,codigo));
            }
        }
        return dlcPortraits;
    }
    public EmblemPortrait(String name, String hexCode){
        this.name=name;
        this.hexCode=hexCode;
    }
    public String getHexCode(){
        return hexCode;
    }
    public String getName(){
        return name;
    }
    
    
    
    public static String getPortraitURL(String hexCode,boolean dlc){
        if(dlc){
            return MaterialDesign.customPath+"/character_images/spotpass/dlc"+hexCode+".png";
        }
        else{
            return MaterialDesign.customPath+"/character_images/spotpass/"+hexCode+".png";
        }
    }
}
