/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import com.olmectron.material.MaterialDesign;
import com.olmectron.material.components.FlatButton;
import com.olmectron.material.components.MaterialButton;
import com.olmectron.material.components.MaterialCircleImageView;
import com.olmectron.material.components.MaterialConfirmDialog;
import com.olmectron.material.components.MaterialDisplayText;
import com.olmectron.material.components.MaterialDropdownMenu;
import com.olmectron.material.components.MaterialLabel;
import com.olmectron.material.components.MaterialMenu;
import com.olmectron.material.components.MaterialProgressBar;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialStandardList;
import com.olmectron.material.components.MaterialTextField;
import com.olmectron.material.components.MaterialToast;
import com.olmectron.material.components.MaterialToolbar;
import com.olmectron.material.components.MaterialTooltip;
import com.olmectron.material.components.RaisedButton;
import com.olmectron.material.components.menu.MaterialMenuItem;
import com.olmectron.material.constants.MaterialColor;
import com.olmectron.material.layouts.MaterialPane;
import com.olmectron.material.layouts.MaterialStandardLayout;
import com.olmectron.material.utils.BackgroundTask;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;


/**
 *
 * @author olmec
 */
public class Main extends Application {
    private File archivo=null;
    private BorderPane borderPane;
    private UnitsPane unitsPane;
    private MiscPane miscPane;
    private ExperimentalPane experimentalPane;
    private BackgroundTask<ArrayList<Unit>> mainTask;
    private MaterialProgressBar mainLoadBar;
    private void loadFile(){
         editedHex=null;
                EmblemController.setFullHex("");
                FileChooser f=new FileChooser();
                archivo=f.showOpenDialog(MaterialDesign.primary);
                printHexFile(archivo);
    }
    private MaterialToolbar theToolbar;
   
       
    private MaterialDisplayText fileText;
    @Override
    public void start(Stage primaryStage) {
        
        initStage(primaryStage);
        
        MaterialDesign.setCustomPath("/com/olmectron/resources");
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
            System.exit(0);    
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        /*MaterialStandardLayout l=new MaterialStandardLayout("FE:A Editor",false,false,false) {

            @Override
            public void onMenuButtonPressed() {
                new MaterialToast("Function to be added").unhide();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onUpdateButtonPressed(Button button) {
                
                new MaterialToast("Function to be added").unhide();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        l.setToolbarColor(MaterialColor.TRANSPARENT);
        l.setScrollable(true);
        l.setShowDrawerOnClick(false);
        */
        /*MaterialPane pane=new MaterialPane() {

            @Override
            public void onShown() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onKeyPressed(KeyEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };*/
        mainTask=new BackgroundTask<ArrayList<Unit>>() {

            @Override
            public ArrayList<Unit> onAction() {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
  
                                      
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                loadFileData(EmblemController.getFullHex());
                loadDifficulty(EmblemController.getFullHex());
                
                return loadCharacters(EmblemController.getFullHex());
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSucceed(ArrayList<Unit> valores) {
                //loadFileData(EmblemController.getFullHex());
                //loadDifficulty(EmblemController.getFullHex());
                unitsPane.setItems(valores);
                //unitsPane.getCharacterList().clear();
                //unitsPane.getCharacterList().addItems(valores);
                miscPane.getDifficultySelector().getSelectionModel().select(difficulty);
                 theToolbar.addMiddleNode(archivo.getName(),archivo.getPath());
                //MaterialTooltip fileTooltip=new MaterialTooltip(archivo.getPath(),fileText);
                 new Timer().schedule(
                new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                  mainLoadBar.hide();  
                //backData();
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                //mainTask.play();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, 1200);
                 
                new MaterialToast("Save file loaded succesfully").unhide();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        //pane.setRootComponent(rootBox);
        //rootBox.setAlignment(Pos.TOP_CENTER);
       
         //MaterialDisplayText contentText=new MaterialDisplayText(
         //        "1. Press the button, and choose your decompressed Chapter file",
         //MaterialDisplayText.HOUR_SIZE);
         //contentText.setWrapText(true);
         //contentText.setColor(MaterialColor.BLACK_87);
        //contentText.setPadding(new  Insets(0,0,16,0));
        RaisedButton btn = new RaisedButton("Load File");
        fileText=new MaterialDisplayText("");
        fileText.setColor(MaterialColor.WHITE);
        //fileText.setHelperText("Press the button and select a Chapter_dec save");
        fileText.setPadding(new Insets(0,8,0,0));
        fileText.setFontSize(14);
        fileText.setWrapText(true);
        fileText.setColor(MaterialColor.BLACK_54);
        //fileText.textField().setEditable(false);
        fileText.setPrefWidth(1000);
        //VBox btnBox=new VBox(btn);
        //btnBox.setAlignment(Pos.BASELINE_CENTER);
        //HBox selectBox=new HBox(fileText, btnBox);
        //btn.setAlignment(Pos.BASELINE_CENTER);
        //selectBox.setAlignment(Pos.CENTER_LEFT);
        //selectBox.setPadding(new Insets(0,0,16,0));
    
        //checkBox.setAlignment(Pos.CENTER);
       /* HBox buttonBox=new HBox();
        String positiveButton="Save";
        String negativeButton="Exit";
        MaterialButton positive=null;
        if(positiveButton!=null){
            positive=new MaterialButton(positiveButton);
            positive.getStyleClass().add("dialog");
            positive.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    //if(fullItems.isSelected()){
                        
                    //}
                    //else{
                    //    new MaterialToast("No option was selected").unhide();
                    //}
//nPositiveButton();
                    
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        
        }
        MaterialButton negative=null;
        if(negativeButton!=null){
            negative=new MaterialButton(negativeButton);
            negative.getStyleClass().add("dialog");
            negative.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    System.exit(0);
//dismiss();
                    //onNegativeButton();
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        HBox positiveBox=new HBox();
        if(positive!=null){
            positiveBox.getChildren().add(positive);
            positiveBox.setPadding(new Insets(0,0,0,8));
        
        }
        HBox negativeBox=new HBox();
        if(negative!=null){
            negativeBox.getChildren().add(negative);
        }
        negative.setColor(MaterialColor.BLACK_87);
        buttonBox.setPadding(new Insets(8));
        buttonBox.getChildren().addAll(negativeBox,positiveBox);*/
        //pane.getContentCard().setCardPadding(new Insets(0));
        
        //miscRootBox.setAlignment(Pos.TOP_CENTER);
        
        //btn.setText("Say 'Hello World'");
        unitsPane=new UnitsPane(this);
        miscPane=new MiscPane(this);
        experimentalPane=new ExperimentalPane(this);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               //loadFile();
                //setupAndDecompressFileArray();
            }
        });
        
        borderPane=new  BorderPane();
       
        VBox menuBox=new  VBox();
        theToolbar=new MaterialToolbar("Units","EMBLEM") {

            @Override
            public void onMenuButton(Button button) {
                //onMenuButtonPressed();
                //if(showMenuOnClick){
                //    drawer.unhide();
                
                //}
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void onUpdateButton(Button button){
                //loadFile();
                //FileChooser f=new FileChooser();
                //archivo=f.showOpenDialog(MaterialDesign.primary);
                loadFile();
                //setupAndDecompressFileArray(archivo);
                //onUpdateButtonPressed(button);
            }
            @Override
            public void onOptionsButton(){
               saveSelectedChanges();
            }
        };
        mainLoadBar=new MaterialProgressBar();
        mainLoadBar.hide();
        theToolbar.getChildren().add(mainLoadBar);
         borderPane.setTop(theToolbar);
        menuBox.setPrefHeight(1000);
        menuBox.setMinWidth(250);
        MaterialMenu drawerMenu=new MaterialMenu(menuBox);
        drawerMenu.addItem(new MaterialMenuItem("Units",null){
            @Override
            public void onItemClick(){
               // borderPane.setCenter(unitsPane);
                setRootBox(unitsPane);
                theToolbar.setTitle("Units");
            }
        
        });
        drawerMenu.addItem(new MaterialMenuItem("Miscellaneous",null){
            @Override
            public void onItemClick(){
                //borderPane.setCenter(miscPane);
                setRootBox(miscPane);
                theToolbar.setTitle("Miscellaneous");
            }
        
        });
        
        /*drawerMenu.addItem(new MaterialMenuItem("Experimental",null){
            @Override
            public void onItemClick(){
                //borderPane.setCenter(miscPane);
                setRootBox(experimentalPane);
                theToolbar.setTitle("Experimental");
            }
        
        });
        */
        
        menuBox.getChildren().add(drawerMenu);
        //borderPane.setCenter(pane.getContentCard().getCoreCard());
        borderPane.setLeft(menuBox);
        setRootBox(unitsPane);
//borderPane.setCenter(unitsPane);
        //l.setRootView(pane);
        //StackPane root = new StackPane();
        //root.getChildren().add(borderPane);
        
        //root.getStyleClass().add("principal-container");
        Scene scene = new Scene(borderPane, 800, 480);
        scene.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });
        
        // Dropping over surface
        scene.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    String filePath = null;
                    for (File file:db.getFiles()) {
                        if(file!=null){
                            //archivo=file;
                            loadFile();
                            //setupAndDecompressFileArray(file);
                        }
                        
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
        setOutsideListeners(scene);
        MaterialDesign cs=new MaterialDesign();

        cs.setScene(scene);
        
                primaryStage.setTitle("FE:A Editor v0.5-35-alpha");
        primaryStage.setScene(scene);
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                
                //primaryStage.setMaximized(true);
                //cs.setWindowCoords(scene);
                trial();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
      /*  Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

primaryStage.setX(bounds.getMinX());
primaryStage.setY(bounds.getMinY());
primaryStage.setWidth(bounds.getWidth());
primaryStage.setHeight(bounds.getHeight());
*/  //primaryStage.setResizable(false);
R.principal=this;
        primaryStage.show();
        
    }
    private void trial(){
        //System.out.println(EmblemController.getCharacter(0x2D).getName());
        //EmblemController.getHexPair(10);
        //EmblemController.getHexPair(20);
        //byte[] c=new byte[]{'a',21,1,(byte)0xFF};
        System.out.println(Utils.get48HexFromTextSpaced("Eirika"));
        //System.out.println(byteArrayToInt(new byte[]{0, 0, 0, -64},0)+"");
    }
    
    public void setRootBox(Node container){
        VBox vbox=new VBox(container);
        container.getStyleClass().removeAll("material-card","material-plain-card");
        
        vbox.getStyleClass().removeAll("material-card","material-plain-card");
        vbox.getStyleClass().add("material-plain-card");
        vbox.setPadding(new Insets(0));
        ((Region)container).setPadding(new Insets(0));
        borderPane.setCenter(vbox);
        
    }
    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }
    private byte[] fileArray;
    
    
    private Uint8Array decompress(byte[] com){
        /*try {
            //return Utils.decompress(com);
            //return HuffmanDecompress.decompress(com);
            ByteArrayInputStream input=new ByteArrayInputStream(com);
            ByteArrayOutputStream output=new ByteArrayOutputStream();
            Huffman.decompress(input, output);
            input.close();
            output.close();
            
            return output.toByteArray();
            //return Huffman8.decod(ecom,0x94DC);
            //return AdaptiveHuffmanDecompress.decompress(com);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return decompressArray(com);
        //return new SciresHuff().Decompress(com);
    }
    private byte[] compress(byte[] com){
        return null;
    }
    public void setUnitsPane(){
        setRootBox(unitsPane);
    }
    
    
    long CMD_CODE = 0x28; // 8-bit Huffman magic number
        long HUF_LNODE = 0;
        long HUF_RNODE = 1;

        long HUF_SHIFT = 1;
        long HUF_MASK = 0x80;
        long HUF_MASK4 = 0x80000000;

        long HUF_LCHAR = 0x80;
        long HUF_RCHAR = 0x40;
        long HUF_NEXT = 0x3F;

        // const uint RAW_MINIM = 0x0;
        // const uint RAW_MAXIM = 0xFFFFFF;

        // const uint HUF_MINIM = 0x4;
        int HUF_MAXIM = 0x1400000;
           private class StringArray{
        private HashMap arregloMap;
        //private byte[] arreglo;
        private long size;
        public StringArray(long length){
            arregloMap=new HashMap();
            for(long i=0;i<length;i++){
                arregloMap.put(i,"");
            }
            size=length;
        }
    
    public void set(long pos, String val){
       
        arregloMap.put(pos,val);
    }
    public String get(long i){
        return arregloMap.get(i).toString();
    }
    public long length(){
            return arregloMap.size();
        }
}
    private class Uint8Array{
        private HashMap arregloMap;
        //private byte[] arreglo;
        private long size;
        public Uint8Array(long length){
            arregloMap=new HashMap();
            for(long i=0;i<length;i++){
                arregloMap.put(i,0);
            }
            size=length;
        }
    public Uint8Array(byte[] s){
        arregloMap=new HashMap();
        
        //arreglo=new byte[s.length];
        for(long i=0;i<s.length;i++){
            int part=s[(int)i];
            if(part<0){
                        part= part & 0xff;
                    }
            arregloMap.put(i, part);
            
            //arreglo[i]=(byte)part;
        }
        size=s.length;
        
    }
    public void set(long pos, long val){
        if(val<0){
            val=val & 0xff;
        }
        arregloMap.put(pos,(byte)val);
    }
    public long get(long i){
        return Long.parseLong(arregloMap.get(i).toString());
    }
    public long length(){
            return arregloMap.size();
        }
}
    private class DataView{
        private int offset=0;
        private byte[] arreglo;
    public DataView(byte[] array, int offset){
        this.offset=offset;
        arreglo=new byte[array.length];
        for(int i=0;i<array.length;i++){
            int part=array[i];
            if(part<0){
                        part= part & 0xff;
                    }
            arreglo[i]=(byte)part;
        }
                
    
    }
     public long byteAsULong(byte b) {
            return ((long)b) & 0x00000000000000FFL; 
        }
    public long getUint32(int offset) {
    
        long value = byteAsULong(arreglo[0+offset]) | (byteAsULong(arreglo[1+offset]) << 8) | (byteAsULong(arreglo[2+offset]) << 16) | (byteAsULong(arreglo[3+offset]) << 24);
    //System.out.println("arreglo 0 "+ byteAsULong(arreglo[0+offset]));
    //System.out.println("arreglo 1 "+ byteAsULong(arreglo[1+offset]));
    //System.out.println("arreglo 2 "+ byteAsULong(arreglo[2+offset]));
    //System.out.println("arreglo 3 "+ byteAsULong(arreglo[3+offset]));
    
    return value;
}
   
}
 
    private Uint8Array decompressArray(byte[] s){
    /*var byteArray=getByteArrayFromString(s);
	var fullHex="";
	for(i=0;i<byteArray.length;i++){
		fullHex=fullHex+(binaryToHex(byteArray[i]).result);
	}
	console.log(fullHex);*/
	///console.log(s.byteLength+" byte length");
Uint8Array u = new Uint8Array(s);
System.out.println("Uint8 array created");
        long i = u.length();
       StringArray a=new StringArray(u.length());
       
System.out.println("String array created");
	DataView dv = new DataView(s, 0);
	
	while (i>0){ // map to hex
	i--;
//= (u.get(i) < 16 ? '0' : '') + u.get(i).toString(16);
		
       String hexPart=Integer.toHexString((int)u.get(i));
                    if(hexPart.length()==1){
                        hexPart="0"+hexPart;
                    }
                    a.set(i,hexPart);
	}
        System.out.println("String array filled");
    //u = null; // free memory
	long x=dv.getUint32(0);
	System.out.println(x+"x");
///console.log(x);
long header=x;
long num_bits = header & 0xF;
System.out.println("num bits "+num_bits);
///console.log("numbits: "+num_bits);
            long UncompressedLength = header >>> 8;
            System.out.println(UncompressedLength+" uncompressed length");
            Uint8Array Uncompressed=new Uint8Array(UncompressedLength);
			long pak_pos = 4;
			/*System.out.println("pak_0: "+u.get(0));
			System.out.println("pak_1: "+u.get(1));
                        
			System.out.println("pak_2: "+u.get(2));
			System.out.println("pak_3: "+u.get(3));
                        
			System.out.println("pak_4: "+u.get(4));
                        
			System.out.println("pak_5: "+u.get(5));*/
			///			console.log("pak_2: "+u[2]);
			///console.log("pak_3: "+u[3]);
						//console.log("pak_4: "+u[4]);
			///console.log("pak_5: "+u[5]);
			
			///console.log("pak_alone: "+u[pak_pos]);
            ///console.log("pak_before: "+(u[pak_pos]+1));
			pak_pos = pak_pos + ((u.get(pak_pos) + 1) << 1);
			
			//System.out.println("pak_position: "+pak_pos);
			///console.log("pak_position: "+pak_pos);
            long raw_pos = 0;
            long tree_ofs = 4;
            long nbits = 0;
            long pos = u.get(tree_ofs + 1);
			
			System.out.println("pos: "+pos);
            long next = 0, mask4 = 0;
            //int counter=0;
            long code = dv.getUint32((int)pak_pos);
	System.out.println(code+" code");
			while (raw_pos < Uncompressed.length())
            {
				//console.log("msask4 "+mask4);
                

                    mask4 >>>= HUF_SHIFT;
                    
                if ((mask4) == 0)
                {
                    //counter++;
                    if ((pak_pos + 3) >= u.length()){ break;}
                    code = dv.getUint32((int)pak_pos);
                    pak_pos += 4;
                    mask4 = Math.abs(HUF_MASK4);
					
                }
                                    //System.out.println("mask4 "+mask4);
				//else{
					//console.log("no_mask");
				//}

                next = next + (((pos & HUF_NEXT) + 1) << 1);
				//console.log(next+" nextp");
                long ch;
                if ((code & mask4) == 0)
                {
					//console.log("Gac");
                    ch = pos & HUF_LCHAR;
                    pos = u.get(tree_ofs + next);
					//console.log("tree: "+(tree_ofs+next));
                }
                else
                {
                    ch = pos & HUF_RCHAR;
                    pos = u.get(tree_ofs + next + 1);
					//console.log("tree + 1: "+(tree_ofs+next+1));
                }

                if (ch != 0)
                {	
			//console.log(" zzasd, pos "+pos+" nbits "+nbits);
                    Uncompressed.set(raw_pos,Uncompressed.get(raw_pos) | (pos << nbits));
                    ////  *raw = (*raw << num_bits) | pos; 
                    nbits = ((nbits + num_bits) & 7);
					//console.log(nbits+" nbits");
                    if (nbits == 0){ raw_pos++;}
					
                    pos = u.get(tree_ofs+1);
                    next = 0;
                }
				//console.log(raw_pos+" raw");
            }
                        
                //System.out.println("Counter "+counter);
            //return Uncompressed;
			///console.log(a);
			u=null;
			a=null;
			
			
			//var byteArray=getByteArrayFromString(s);
	
			//console.log(Uncompressed);
			
			
//dv.setInt16(1, 42);
//dv.getInt16(1); //42
	return Uncompressed;
     // work with this
}
    private static String PMOC="504D4F43";
    private void setupAndDecompressFileArray(File archivo){
        try {
            
            fileArray= Files.readAllBytes(archivo.toPath());
            
            if(Utils.getHexString(Arrays.copyOfRange(fileArray,0xC0,0xD0)).startsWith(PMOC)){
                this.writeArrayListToStream(Arrays.copyOf(fileArray, 0xC0)
                    ,decompress((Arrays.copyOfRange(fileArray, 0xD0, fileArray.length))),"decompressed_"+archivo.getName());
            }
            else{
                new MaterialToast("The selected file isn't a valid Chapter file",MaterialToast.LENGTH_LONG).unhide();
            }
            //byte[] chapterDecompressed=Utils.concatBytes(Arrays.copyOf(fileArray,0xC0),decompress((Arrays.copyOfRange(fileArray, 0xD0, fileArray.length))));
            //Huffman.expand((Arrays.copyOfRange(fileArray, 0xD1, fileArray.length)));
            
            
            //Chapters_Dec[i] = Chapters[i].Take(0xC0).Concat(Decompress(Chapters[i].Skip(0xD0).ToArray())).ToArray();
            
        } 
        catch(NullPointerException ex){
            System.err.println("You forgot to select a file");
        }
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void printHexByteArray(byte[] stream){
        ByteArrayInputStream fin = null;
        try {
            fin = new ByteArrayInputStream(stream);
            int len;
            byte data[] = new byte[16];
            // Read bytes until EOF is encountered.
            /*do {
                len = fin.read(data);
                
                    System.out.println(bytesToHex(data)); 
                    fullHex=fullHex+bytesToHex(data);
                    
                    
            } while (len != -1);
            */
            
            do {
      len = fin.read(data);
      for (int j = 0; j < len; j++)
          EmblemController.setFullHex(EmblemController.getFullHex()+String.format("%02X", data[j]));
        //System.out.printf("%02X ", data[j]);
    } while (len != -1);
            
            
            //System.out.println(fullHex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ex){
                System.out.println("No cargaste ningún archivo");
            } finally {
            try {
                fin.close();
                
                //fileText.setText(archivo.getName());
                loadData();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                System.out.println("No cargaste ningún archivo");
                clearData();
            }
        }
        
    }
    private void printHexFile(File file){
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            int len;
            byte data[] = new byte[16];
            // Read bytes until EOF is encountered.
            /*do {
                len = fin.read(data);
                
                    System.out.println(bytesToHex(data)); 
                    fullHex=fullHex+bytesToHex(data);
                    
                    
            } while (len != -1);
            */
            
            do {
      len = fin.read(data);
      for (int j = 0; j < len; j++)
          EmblemController.setFullHex(EmblemController.getFullHex()+String.format("%02X", data[j]));
        //System.out.printf("%02X ", data[j]);
    } while (len != -1);
            
            
            //System.out.println(fullHex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NullPointerException ex){
                System.out.println("No cargaste ningún archivo");
            } finally {
            try {
                fin.close();
                
                //fileText.setText(archivo.getName());
                loadData();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch(NullPointerException ex){
                System.out.println("No cargaste ningún archivo");
                clearData();
            }
        }
        
    }
    
    private void loadData(){
        loadFileData(EmblemController.getFullHex());
                loadDifficulty(EmblemController.getFullHex());
                unitsPane.setItems(loadCharacters(EmblemController.getFullHex()));
                //unitsPane.getCharacterList().clear();
                //unitsPane.getCharacterList().addItems(valores);
                miscPane.getDifficultySelector().getSelectionModel().select(difficulty);
                 theToolbar.addMiddleNode(archivo.getName(),archivo.getPath());
                
        //mainLoadBar.unhide();
        new Timer().schedule(
                new TimerTask(){

            @Override
            public void run() {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                  //mainTask.play();     
                 /*       loadFileData(EmblemController.getFullHex());
                loadDifficulty(EmblemController.getFullHex());
                unitsPane.setItems(loadCharacters(EmblemController.getFullHex()));
                //unitsPane.getCharacterList().clear();
                //unitsPane.getCharacterList().addItems(valores);
                miscPane.getDifficultySelector().getSelectionModel().select(difficulty);
                 theToolbar.addMiddleNode(archivo.getName(),archivo.getPath());
                */
                //backData();
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
                //mainTask.play();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, 500);
        
    }
    public ArrayList<Unit> loadCharacters(String hex){
        //characterList.clear();
        return EmblemController.parseCharacterBlock(EmblemController.getCharacterBlock(hex));
        //characterList.addItems(
    }
    private int getEditedCharactersCount(){
        int edited=0;
        for(int i=0;i<unitsPane.getCharacterList().size();i++){
           if(unitsPane.getCharacterList().getItem(i).getEdited()){
               edited++;
           }
        }
        return edited;
    }
    private void clearData(){
        unitsPane.getCharacterList().clear();
        theToolbar.addMiddleNode("", "");
    }
    
    private String saveEditedCharacters(String hex){
        //String emblemBlock=EmblemController.getCharacterBlock(hex);
        String firstBytes=EmblemController.getCharacterBlock(hex).substring(0,6);
        String emblemBlock="";
        String emblemReplace=EmblemController.characterHeader+EmblemController.getCharacterBlock(hex)+EmblemController.characterFooter;
        for(int i=0;i<unitsPane.getCharacterList().size();i++){
            Unit emblem=unitsPane.getCharacterList().getItem(i);
            
            emblemBlock=emblemBlock+emblem.getEditedHexBlock();
            /*if(emblem.getEdited()){
                
                
                int start=emblem.getHexPosition();
                emblemBlock=emblemBlock.substring(0,start)+emblem.getEditedHexBlock()+
                        emblemBlock.substring(start+emblem.getEditedHexBlock().length());
                
            }*/
        }
        System.out.println(unitsPane.getCharacterList().size()+" units of "+EmblemController.getCharacterCount());
        if(unitsPane.getCharacterList().size()==EmblemController.getCharacterCount()){
            firstBytes=firstBytes.substring(0,4)+Utils.getHexPair(EmblemController.getCharacterCountFromHex(emblemBlock));
        
        }
        emblemBlock=EmblemController.characterHeader+firstBytes+emblemBlock+EmblemController.characterFooter;
        //System.out.println(emblemReplace.length()+" vs "+ EmblemController.characterHeader+emblemBlock+EmblemController.characterFooter);
        return hex.replace(emblemReplace,emblemBlock);
        
        
    }
    public void saveSelectedChanges(){
        boolean willEdit=false;
        if(archivo!=null){
            if(miscPane.getFullItemsCheckBox().isSelected()){
            
        EmblemController.setFullHex(setFullItems(EmblemController.getFullHex(),miscPane.getConvoyQuantity(),miscPane.includesForge()));
        willEdit=true;
        
            }
            if(difficulty!=miscPane.getDifficultySelector().getSelectionModel().getSelectedItem().getIntDifficulty()
        ){
                EmblemController.setFullHex(saveDifficulty(EmblemController.getFullHex()));
                willEdit=true;
            }
            if(getEditedCharactersCount()>0){
                EmblemController.setFullHex(saveEditedCharacters(EmblemController.getFullHex()));
                willEdit=true;
            }
                //System.out.println(fullHex);
            if(willEdit){
                writeSaveFile(hexStringToByteArray(EmblemController.getFullHex()));
            }
            else{
                new MaterialToast("No option was selected").unhide();
            }


//writeSaveFile(hexStringToByteArray(editedHex));
        }
        else new MaterialToast("Choose a save file first").unhide();
    }
    public void writeArrayListToStream(byte[] header, Uint8Array intArray, String name){
        File f = null;
        ByteArrayOutputStream out=null;
        try {
            //File myFoo = new File("foo.log");
            f=new File(archivo.getAbsolutePath().replace(archivo.getName(), name));
            out=new ByteArrayOutputStream(); // true to append
            // false to overwrite.
//byte[] myBytes = "New Contents\n".getBytes()
            //for (byte[] myByte : myBytes) {
            out.write(header);
           //System.out.println(intArray.length()+" length");
            for(long i=0;i<intArray.length();i++){
                out.write((int)intArray.get(i));
                //System.out.println(intArray.get(i)+"as");
            }
                
            //}
           // archivoStream.write(myBytes);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            printHexByteArray(out.toByteArray());
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Success decompressing");
            //new MaterialToast("Decompressed successfully: "+f.getAbsolutePath()).unhide();
        }
    }
    public void writeArrayListToFile(byte[] header, Uint8Array intArray, String name){
        File f = null;
        try {
            //File myFoo = new File("foo.log");
            f=new File(archivo.getAbsolutePath().replace(archivo.getName(), name));
            FileOutputStream archivoStream = new FileOutputStream(f, false); // true to append
            // false to overwrite.
//byte[] myBytes = "New Contents\n".getBytes()
            //for (byte[] myByte : myBytes) {
            archivoStream.write(header);
           //System.out.println(intArray.length()+" length");
            for(long i=0;i<intArray.length();i++){
                archivoStream.write((int)intArray.get(i));
                //System.out.println(intArray.get(i)+"as");
            }
                
            //}
           // archivoStream.write(myBytes);
            archivoStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            System.out.println("Success decompressing");
            new MaterialToast("Decompressed successfully: "+f.getAbsolutePath()).unhide();
        }
    }
    public void writeToFile(byte[] myBytes, String name){
        File f = null;
        try {
            //File myFoo = new File("foo.log");
            f=new File(archivo.getAbsolutePath().replace(archivo.getName(), name));
            FileOutputStream archivoStream = new FileOutputStream(f, false); // true to append
            // false to overwrite.
//byte[] myBytes = "New Contents\n".getBytes()
            
            archivoStream.write(myBytes);
            archivoStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            System.out.println("Success decompressing");
            new MaterialToast("Decompressed successfully: "+f.getAbsolutePath()).unhide();
        }
    }
    public void writeSaveFile(byte[] myBytes){
        try {
            //File myFoo = new File("foo.log");
            FileOutputStream archivoStream = new FileOutputStream(archivo, false); // true to append
            // false to overwrite.
//byte[] myBytes = "New Contents\n".getBytes()
            archivoStream.write(myBytes);
            archivoStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            System.out.println("Success editing");
            new MaterialToast("Saved succesfully: "+archivo.getAbsolutePath()).unhide();
     //this.printHexFile(archivo);
    this.loadFileData(EmblemController.getFullHex());
     unitsPane.setItems(loadCharacters(EmblemController.getFullHex()));
            
//unitsPane.getCharacterList().clear();
             //   unitsPane.getCharacterList().addItems(loadCharacters(fullHex));
                
        }
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
         return data;
    }
    private String editedHex=null;
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    //NART 4E 41 52 54 04 60 01
    //62UD 36 32 55 44
    private int difficulty=-1;

    private String difficultyString=null;
    public void loadFileData(String hex){
        System.out.println(hex.substring(32,80));
        EmblemController.setAvatarNameCode(hex.substring(32,80));
        
    }
    public String loadDifficulty(String hex){
        difficultyString=hex.substring(22,28);
       difficulty =Integer.parseInt(difficultyString.substring(4));
        //difficultySelector.getSelectionModel().select(difficulty);
        //System.out.println(hex.substring(22, 28));
       
                return null;
        }
    public String saveDifficulty(String hex){
        int selectedDif=miscPane.getDifficultySelector().getSelectionModel().getSelectedItem().getIntDifficulty();
        if(difficulty!=selectedDif){
            String edited=hex.substring(0, 26)+"0"+selectedDif+hex.substring(28);
            int start=edited.indexOf(difficultyString);
            int end=start+difficultyString.length();
            edited=edited.substring(0,start)+
                    difficultyString.substring(0, 4)+"0"+selectedDif+edited.substring(end);
            return edited;
        }
        return hex;
    }
    public String setFullItems(String hex, int quant, boolean forge){
        try{
        int start=hex.indexOf("4E415254046001")+"4E415254046001".length();//NART.'.
        
        int end=hex.indexOf("36325544");//62UD
        String toEdit=hex.substring(start,end);
        System.out.println("toEdit "+toEdit.length());
        String f="";
        String quantityHexInverted=Utils.getHexPair(quant);
        while(quantityHexInverted.length()<4){
            quantityHexInverted="0"+quantityHexInverted;
        }
        String quantityHex=quantityHexInverted.substring(2, 4)+quantityHexInverted.substring(0,2);
        if(forge){
            while(f.length()<toEdit.length()){
                
                    f=f+quantityHex;


            }
        if(f.length()>=toEdit.length()){
            f=f.substring(0,toEdit.length());
        }
        }
        else{
            while(f.length()<984){
                
                    f=f+quantityHex;


            }
            if(f.length()==984){
                  toEdit=toEdit.substring(0,984);
            }
        }
        
        
        System.out.println("f "+f.length());
        
        if(f.length()<=984){
                return (hex.replace("4E415254046001"+toEdit, "4E415254046001"+f));
        
        }
        else{
                return (hex.replace("4E415254046001"+toEdit+"36325544", "4E415254046001"+f+"36325544"));
        
        }
            
        }
        catch(Exception ex){
            new MaterialToast("Your save file is corrupted, please select a valid one").unhide();
            return null;
        }

    }
    
    
    /**
     * @param args the command line arguments
     */
    private void setOutsideListeners(Scene scene){
            
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    
                    @Override
                    public void handle(MouseEvent event) {
                            //System.out.println("juerngviansgvijasndsv");
                            MaterialDropdownMenu m=MaterialDropdownMenu.getLastMenu();
                            if(m!=null){
                                m.hideMenu();
                            }
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
               
            
        }
    public static void main(String[] args) {
        launch(args);
    }
        private void initStage(Stage stage){
        
        //System.setProperty("prism.order","sw");
        System.setProperty("prism.text","t2k");
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("javafx.animation.fullspeed","true");
        System.setProperty("prism.vsync","false");
        //stage.initStyle(StageStyle.TRANSPARENT);
        MaterialDesign.setWindowOwner(stage);
        //MaterialDesign.setCustomPath(R.resourcesPath);
        
    }
    
}
