/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.curriculumdigital;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Gui and Rodrigo
 */
public class BlockChain implements Serializable {
    
    private ArrayList<Block> chain;
    
    //construtor da blockchain
    public BlockChain(){
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }
    
    //método para a criação do primeiro bloco da blockchain
    private Block createGenesisBlock(){
        return new Block("0", "Bloco gênesis", 0);
    }
    
    
    private Block getLatestBlock(){
        return chain.get(chain.size() - 1);
    }
    
    //método para adicionar um novo bloco
    public void addBlock(String data, int dificulty){
        Block previousBlock = getLatestBlock();
        int nonce = Miner.getNonce(previousBlock.getCurrentHash() + data, dificulty);
        Block newBlock = new Block(previousBlock.getCurrentHash(), data, nonce);
        chain.add(newBlock);
        
    }
    
    //método para verifica se a chain é válida
    public boolean isChainValid(){
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getCurrentHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())) {
                return false;
            }
        }
        return true;
    }
    
    //método toString
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Block block : chain) {
            result.append(block.toString()).append("\n");
        }
        return result.toString();
    }
    
    public ArrayList<Block> getChain() {
       return chain;
    }
}
