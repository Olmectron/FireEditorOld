/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.utils.BackgroundTask;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 *
 * @author olmec
 */
public abstract class SkillSearchBox extends VBox{
    public abstract void onSkillSelected(EmblemSkill item);
    private SkillList list;
    public void unhide(){
        menu.unhide();
    }
    private MaterialDropdownMenu menu;
    public SkillSearchBox(Region origen){
        super();
        menu=new MaterialDropdownMenu(origen,false);
                      
                       MaterialTextField searchField=new MaterialTextField("Skill name");
                       searchField.textField().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                searchSkill(newValue);
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
                       list=new SkillList(null) {

                           @Override
                           public void onItemClick(final EmblemSkill item, MouseEvent event) {
                               onSkillSelected(item);
                               
                                menu.hideMenu();
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       //list.fillList();
                       searchField.setPadding(new Insets(8,8,4,8));
                       getChildren().addAll(searchField,list);
                       menu.addNodeAsItem(this);
                       
                       //menu.unhide();
    }
    private String globalKeyword="";
    private final BackgroundTask queryService=new BackgroundTask<ArrayList<EmblemSkill>>() {
            @Override
            public ArrayList<EmblemSkill> onAction() {
                ArrayList<EmblemSkill> nuevos=SkillController.searchWordSkill(globalKeyword);
return nuevos;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSucceed(ArrayList<EmblemSkill> articles) {
                if(articles!=null){
                    list.clear();
                    list.addItems(articles);
                    if(articles.size()>0){
                        //getTable().getSelectionModel().select(0);
                        //getTable().scrollTo(0);
                    }
                }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };  
    private void searchSkill(String keyword){
        this.globalKeyword=keyword;
        queryService.setDebounce(300);
        queryService.play();
        
        
    }
}
