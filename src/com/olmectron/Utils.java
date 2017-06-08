/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 *
 * @author olmec
 */
public class Utils {
    public static String getHexString(byte[] array){
        String hex="";
        for(int i=0;i<array.length;i++){
            hex=hex+getHexPair(array[i]);
        }
        return hex;
    }
    private static byte[] reverse(byte[] array) {
      
      if (array == null) {
          return null;
      }
      int i = 0;
      int j = array.length - 1;
      byte tmp;
      while (j > i) {
          tmp = array[j];
          array[j] = array[i];
          array[i] = tmp;
          j--;
          i++;
      }
      return array;
  } 
    public static byte[] getByteArrayFromInt(int entero){
        byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(entero).array();

for (byte b : bytes) {
   System.out.format("0x%02X", b);
}   
return reverse(bytes);
    }
    public static byte[] getByteArrayFromLong(long entero){
        byte[] bytes = ByteBuffer.allocate(Long.BYTES).putLong(entero).array();

for (byte b : bytes) {
   System.out.format("0x%02X", b);
}   
return reverse(bytes);
    }
    public static long getLongFromByteArray(byte[] arreglo){
        return getLongFromByteArray(arreglo,0);
    }
    public static short getShortFromByteArray(byte[] arreglo){
        return getShortFromByteArray(arreglo,0);
    }
    public static short getShortFromByteArray(byte[] arreglo, int start){
        short retorno;
        if(isLittleEndian()){
            retorno=ByteBuffer.wrap(arreglo).order(java.nio.ByteOrder.LITTLE_ENDIAN).getShort(start);
        }
        else{
            retorno=ByteBuffer.wrap(arreglo).getShort(start);
        }
        return retorno;
    }
    public static long getLongFromByteArray(byte[] arreglo, int start){
        long retorno;
        if(isLittleEndian()){
        
            try{
                retorno=ByteBuffer.wrap(arreglo).order(java.nio.ByteOrder.LITTLE_ENDIAN).getLong(start);
            }
            catch(BufferOverflowException ex){
                retorno=(long)ByteBuffer.wrap(arreglo).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt(start);
                
            }
            catch(IndexOutOfBoundsException ex){
                retorno=(long)ByteBuffer.wrap(arreglo).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt(start);
            }
        System.out.println("Little Endian: "+retorno);
        
        }
        else{
            try{
                retorno=ByteBuffer.wrap(arreglo).getLong(start);
            }
            catch(BufferOverflowException ex){
                retorno=(long)ByteBuffer.wrap(arreglo).getInt(start);
            }
            catch(IndexOutOfBoundsException ex){
                retorno=(long)ByteBuffer.wrap(arreglo).getInt(start);
            }
            System.out.println("Big Endian: "+retorno);
        }
        return retorno;
    }
    private static boolean isBigEndian(){
            

if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
    System.out.println("System is Big Endian");
  return true;
} else {
    System.out.println("System is Little Endian");
  return false;
  
}
        }
        private static boolean isLittleEndian(){
            if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
                System.out.println("System is Big Endian");
  return false;
} else {
                System.out.println("System is Little Endian");
  return true;
}
        }
    public static byte[] concatBytes(byte[] one, byte[] two){
//        byte[] one = getBytesForOne();
//byte[] two = getBytesForTwo();
byte[] combined = new byte[one.length + two.length];

System.arraycopy(one,0,combined,0         ,one.length);
System.arraycopy(two,0,combined,one.length,two.length);
return combined;
    }
    public static byte[] decompress(byte[] data){
        try {
            // Decompress the bytes
            Inflater decompresser = new Inflater();
            decompresser.setInput(data);
            byte[] result = new byte[100];
            int resultLength = decompresser.inflate(result);
            decompresser.end();
            return result;
        } catch (DataFormatException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public static int randomInclusive(int max){
        return randomInclusive(0,max);
    }
    public static int randomInclusive(int min, int max){
        if(max<=min){
            return -1;
        }
        return new Random().nextInt(max - min + 1) + min;
    }
    public static String getStringFromHex(String hex){
        if(hex.length()%2!=0){
            System.out.println(hex.length()%2+" "+hex);
            return null;
        }
        else{
            String result="";
            for(int i=0;i<hex.length();i+=2){
                String hexPair=hex.substring(i,i+2);
                if(!hexPair.equals("00")){
                    char newChar=(char)getIntFromHex(hexPair);
                    result=result+String.valueOf(newChar);
                }
            }
            return result;
        }
    }
        public static String trimBinaryPair(String binary){
            String b=binary;
            while(b.startsWith("00")){
              b=b.substring(2);
            }
            while(b.endsWith("00")){
                b=b.substring(0,b.length()-2);
            }
            return b;
        }
     public static double currencyToDouble(String cantidadAux){
        if(!cantidadAux.equals("")){
            cantidadAux=cantidadAux.replace(" ","");
            cantidadAux=cantidadAux.replace("%","");
            cantidadAux=cantidadAux.replace("kg","");
                cantidadAux=cantidadAux.replace("$","");
                cantidadAux=cantidadAux.replace(",","");
try{
                double decimal=Double.parseDouble(cantidadAux);
                return decimal;
}
catch(NumberFormatException ex){
    return 0;
}
        }
        else return 0;
    }
     public static String trim(String texto){
        StringTokenizer tokens=new StringTokenizer(texto.trim());
        String returned="";
        while(tokens.hasMoreTokens()){
            returned=returned+tokens.nextToken()+" ";
        }
        return returned.trim();
    }
    public static String getHexPair(int code){
        if(code<0x10){
            return "0"+Integer.toHexString(code).toUpperCase();
        }
        else{
            return Integer.toHexString(code).toUpperCase();
        }
    }
    public static int getIntFromHex(String hex){
        if(!hex.startsWith("0x")){
            return Integer.decode("0x"+hex);
        }
        else{
            return Integer.decode(hex);
        }
    }

    public static String get48HexFromTextSpaced(String newValue) {
        String ends="";
        for(int i=0;i<newValue.length();i++){
            char ch = newValue.charAt(i);
String hex = String.format("%02x", (int) ch);
        ends=ends+hex+"00";
        }
        while(ends.length()<48){
            ends=ends+"0";
        }
        return ends;

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
