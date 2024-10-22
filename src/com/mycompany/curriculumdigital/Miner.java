/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.curriculumdigital;

/**
 *
 * @author Gui and Rodrigo
 */
public class Miner {
    
    //valor máximo do nonce (1 bilhão)
    public static int Max_Nonce = (int)1E9;
    
    
    // Método para encontrar um nonce que gere um hash válido
    // data: os dados que serão usados para gerar o hash (o tal evento que criámos)
    // difficulty: o número de zeros que o hash deve ter no final
    public static int getNonce(String data, int dificulty){
        String zeros = String.format("%0"+dificulty+"d",0); //format(String format, Object... args)
        
        int nonce = 0;
        
        while(nonce < Max_Nonce){
            String hash = Hash.getHash(nonce + data);
            
            if(hash.endsWith(zeros)){
                return nonce;
            }
            
            nonce++;
        }
        return nonce;
    }
    
    
}
