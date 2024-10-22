/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.curriculumdigital;

import java.io.Serializable;

/**
 *
 * @author Gui and Rodrigo
 */
public class Block implements Serializable {

    //atributos do bloco
    String previousHash;
    String data;
    int nonce;
    String currentHash;

    //Construtor da classe
    public Block(String previousHash, String data, int nonce) {
        this.previousHash = previousHash;
        this.data = data;
        this.nonce = nonce;
        this.currentHash = calculateHash();
    }

    public String getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }

    //calcula a hash do bloco atual
    public String calculateHash() {
        return Hash.getHash(nonce + previousHash + data);
    }

    //método get para ir buscar a hash atual
    public String getCurrentHash() {
        return currentHash;
    }

    //método get para ir buscar a hash do bloco anterior
    public String getPreviousHash() {
        return previousHash;
    }

    //ToString
    public String toString() {
        return String.format("[ %8s", previousHash) + " <- "
                + String.format("%-10s", data) + String.format(" %7d ] = ", nonce)
                + String.format("%8s", currentHash);

    }

    public boolean isValid() {
        return currentHash.equals(calculateHash());
    }

}
