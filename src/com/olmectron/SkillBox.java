/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.utils.BackgroundTask;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author olmec
 */
public class SkillBox extends FlowPane {
    private EmblemSkill[] skillArray;
    private ArrayList<ImageView> imageViews;
    private final IntegerProperty p=new SimpleIntegerProperty(), p2=new SimpleIntegerProperty();
    private final Unit unit;
    private MaterialTooltip t1,t2,t3,t4,t5;
    public SkillBox(EmblemSkill[] skillArray, Unit character){
        
        this.unit=character;
        this.skillArray=skillArray;
        imageViews=new ArrayList<ImageView>();
            
            
           unit.emblemSkillOneProperty().addListener(new ChangeListener<EmblemSkill>(){

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                    imageViews.get(0).setImage(newValue.getImage());
                t1.setText(newValue.getName());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
        unit.emblemSkillTwoProperty().addListener(new ChangeListener<EmblemSkill>(){

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                    imageViews.get(1).setImage(newValue.getImage());
                    t2.setText(newValue.getName());
                
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        unit.emblemSkillThreeProperty().addListener(new ChangeListener<EmblemSkill>(){

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                   imageViews.get(2).setImage(newValue.getImage());
                t3.setText(newValue.getName());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        unit.emblemSkillFourProperty().addListener(new ChangeListener<EmblemSkill>(){

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                    imageViews.get(3).setImage(newValue.getImage());
                t4.setText(newValue.getName());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        unit.emblemSkillFiveProperty().addListener(new ChangeListener<EmblemSkill>(){

                @Override
                public void changed(ObservableValue<? extends EmblemSkill> observable, EmblemSkill oldValue, EmblemSkill newValue) {
                    imageViews.get(4).setImage(newValue.getImage());
                t5.setText(newValue.getName());
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
       
            if(skillArray!=null){
                initSkills();
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                //t.setShortDistance(true);
            }
            
            
            
        
        
    }
    private void initSkills(){
        initSkillOne();
        initSkillTwo();
        initSkillThree();
        initSkillFour();
        initSkillFive();
        
    }
    private void initSkillOne(){
        ImageView im=new ImageView(skillArray[0].getImage());
                imageViews.add(im);
                HBox imBox=new HBox(im);
                imBox.setPadding(new Insets(0,4,0,0));
                this.getChildren().add(imBox);
                t1=new MaterialTooltip(skillArray[0].getName(),imBox);
                im.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                       SkillSearchBox skillSearch=new SkillSearchBox(imBox) {

                           @Override
                           public void onSkillSelected(EmblemSkill item) {
                               unit.setSkillOne(item);
                               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       skillSearch.unhide();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }
    private void initSkillTwo(){
        ImageView im=new ImageView(skillArray[1].getImage());
                imageViews.add(im);
                HBox imBox=new HBox(im);
                imBox.setPadding(new Insets(0,4,0,0));
                this.getChildren().add(imBox);
                t2=new MaterialTooltip(skillArray[1].getName(),imBox);
                im.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                       SkillSearchBox skillSearch=new SkillSearchBox(imBox) {

                           @Override
                           public void onSkillSelected(EmblemSkill item) {
                               unit.setSkillTwo(item);
                               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       skillSearch.unhide();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }
    private void initSkillThree(){
        ImageView im=new ImageView(skillArray[2].getImage());
                imageViews.add(im);
                HBox imBox=new HBox(im);
                imBox.setPadding(new Insets(0,4,0,0));
                this.getChildren().add(imBox);
                t3=new MaterialTooltip(skillArray[2].getName(),imBox);
                im.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                       SkillSearchBox skillSearch=new SkillSearchBox(imBox) {

                           @Override
                           public void onSkillSelected(EmblemSkill item) {
                               unit.setSkillThree(item);
                               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       skillSearch.unhide();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }
    private void initSkillFour(){
        ImageView im=new ImageView(skillArray[3].getImage());
                imageViews.add(im);
                HBox imBox=new HBox(im);
                imBox.setPadding(new Insets(0,4,0,0));
                this.getChildren().add(imBox);
                t4=new MaterialTooltip(skillArray[3].getName(),imBox);
                im.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                       SkillSearchBox skillSearch=new SkillSearchBox(imBox) {

                           @Override
                           public void onSkillSelected(EmblemSkill item) {
                               unit.setSkillFour(item);
                               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       skillSearch.unhide();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }
    private void initSkillFive(){
        ImageView im=new ImageView(skillArray[4].getImage());
                imageViews.add(im);
                HBox imBox=new HBox(im);
                imBox.setPadding(new Insets(0,4,0,0));
                this.getChildren().add(imBox);
                t5=new MaterialTooltip(skillArray[4].getName(),imBox);
                im.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                       SkillSearchBox skillSearch=new SkillSearchBox(imBox) {

                           @Override
                           public void onSkillSelected(EmblemSkill item) {
                               unit.setSkillFive(item);
                               //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                           }
                       };
                       skillSearch.unhide();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }
    
}
