/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author olmec
 */
public class SkillController {
    public static ArrayList<EmblemSkill> searchWordSkill(String keyword){
        ArrayList<EmblemSkill> skills=new ArrayList<>();
        //articlesStatic.clear();
        
        
        ArrayList<EmblemSkill> skillsCopy=(ArrayList<EmblemSkill>)EmblemController.getCharacterSkills().clone();
        
        //System.out.println("EmblemSkills size: "+articlesCopy.size());
        //for (EmblemSkill article : R.data.article.article_list) {
        int added=0;
        while(!skillsCopy.isEmpty()){
            StringTokenizer keywordToken=new StringTokenizer(keyword);
            boolean containWords=true;
            while(keywordToken.hasMoreTokens()){
                String token=keywordToken.nextToken().toUpperCase();
                String descripcion=skillsCopy.get(0).getName().toUpperCase();
                boolean starts=false;
                if(token.startsWith(".")){
                    starts=true;
                    if(descripcion.startsWith(token.replace(".",""))){
                        token=""+token;
                    }
                    else{
                        token=" "+token;
                    }
                    
                }
                boolean ends=false;
                if(token.endsWith(".")){
                    ends=true;
                    if(descripcion.endsWith(token.replace(".", ""))){
                        token=token+"";
                    }
                    else{
                        token=token+" ";
                    }
                    
                }
                if(starts || ends){
                    token=token.replace(".","");
                }
                    if(!descripcion.contains(token)){
                        containWords=false;
                        break;
                    }
                
                
            }
            //System.out.println(articlesCopy.get(0).getDescripcion()+" contain words: "+containWords);
            if(containWords){
                skills.add(added,skillsCopy.get(0));
                added++;
                
            }
            skillsCopy.remove(0);
        }
        /*R.data.article.article_list.stream().filter((article) -> (!article.getDescripcion().toUpperCase().startsWith(keyword.toUpperCase()))).forEach((article) -> {
            articlesCopy.remove(article);
        });
        */
        return skills;
    }
}
