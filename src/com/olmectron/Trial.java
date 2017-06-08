/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 *
 * @author olmec
 */
public class Trial {
      
    public static void main (String[] args){
        //System.out.println("Estoy comenzando yaaaaaa...................................................................");
        byte[] array = new byte[4];
	array[0] = 1; // Lowest
	array[1] = 64;
	array[2] = 0;
	array[3] = 0; // Sign bit
        long c=Utils.getLongFromByteArray(array);
        System.out.println("Resultado short: "+c
        );
        
        byte[] f=Utils.getByteArrayFromInt((int)c);
        for(int i=0;i<f.length;i++){
            System.out.println("s: "+f[i]);
        }
    }
}
