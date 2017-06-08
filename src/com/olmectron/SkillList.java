/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.components.MaterialCircleImageView;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialStandardList;
import com.olmectron.material.components.MaterialStandardListItem;
import com.olmectron.material.constants.MaterialColor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author olmec
 */
public abstract class SkillList extends MaterialStandardList<EmblemSkill> {

    public SkillList(Pane container) {
        super(container,true);
        
    }
    @Override
    public void onItemClick(EmblemSkill skill, MouseEvent event){
        
    }
    private Unit character;
    public SkillList(Pane container, Unit character){
        super(container,false);
        
        this.character=character;
        initSkills();
    }
    private void initSkills(){
        for (EmblemSkill skillSet : character.getSkillSet()) {
            if(skillSet!=null){
                
            }
        }
    }
    public void fillList(){
        this.addItems(EmblemController.getCharacterSkills());
    }

    

    @Override
    public Node itemConverter(EmblemSkill item, MaterialStandardListItem<EmblemSkill> itemContainer) {
        MaterialCircleImageView skillImage=new MaterialCircleImageView(item.getImage(),20,true);
                //userImage.setMySQLImage(item.getImageURL());
//VBox b=new VBox();
                skillImage.toSquareImage();
                MaterialDisplayText nameText=new MaterialDisplayText(item.getName());
                
                nameText.setColor(MaterialColor.BLACK_87);
                nameText.setFontSize(15);
                MaterialDisplayText permisosText=new MaterialDisplayText(item.getHelp());
                permisosText.setWrapText(true);
                
                permisosText.setColor(MaterialColor.BLACK_54);
                permisosText.setFontSize(12);
                VBox dataBox=new VBox(nameText, permisosText);
                
                dataBox.setAlignment(Pos.CENTER_LEFT);
                
                //b.getChildren().add(nameText);
                HBox userImageBox=new HBox(skillImage);
                userImageBox.setAlignment(Pos.CENTER_LEFT);
                
                StackPane box=new StackPane(userImageBox,dataBox);
                userImageBox.setPadding(new Insets(0,0,0,16));
                dataBox.setPadding(new Insets(0,0,0,40));
                box.setAlignment(Pos.CENTER_LEFT);
                //box.setMinHeight(200);
                return box;
                
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    
}
