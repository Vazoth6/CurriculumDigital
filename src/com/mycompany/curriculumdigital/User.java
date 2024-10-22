/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.curriculumdigital;

import java.awt.RenderingHints.Key;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.SecretKey;


/**
 *
 * @author Gui
 */
public class User {
    
    String nome;
    
    private PublicKey pub;
    private PrivateKey priv;
    private SecretKey sim;
    
    
    public User(String nome) {
        this.nome = nome;
        this.pub = null;
        this.priv = null;
        this.sim = null;
    }
    
    public User() throws Exception {
        this.nome = "noName";
        this.pub = null;
        this.priv = null;
        this.sim = null;
    }
    
    public void generateKeys() throws Exception {
        this.sim = (SecretKey) SecurityUtils.generateAESKey(256);
        KeyPair kp = SecurityUtils.generateECKeyPair(256);
        this.pub = kp.getPublic();
        this.priv = kp.getPrivate();
    }

    public void save(String password) throws Exception {
        //encriptar a chave privada
        byte[] secret = SecurityUtils.encrypt(priv.getEncoded(), password);
        Files.write(Path.of(this.nome + ".priv"), secret);
        //encrptar a chave simetrica
        byte[] simData = SecurityUtils.encrypt(sim.getEncoded(), password);
        Files.write(Path.of(this.nome + ".sim"), simData);
        //guardar a public
        Files.write(Path.of(this.nome + ".pub"), pub.getEncoded());
    }

    public void load(String password) throws Exception {
        //desencriptar a privada
        byte[] privData = Files.readAllBytes(Path.of(this.nome + ".priv"));
        privData = SecurityUtils.decrypt(privData, password);
        //desencriptar a privada
        byte[] simData = Files.readAllBytes(Path.of(this.nome + ".sim"));
        simData = SecurityUtils.decrypt(simData, password);
        //ler a publica
        byte[] pubData = Files.readAllBytes(Path.of(this.nome + ".pub"));
        this.priv = SecurityUtils.getPrivateKey(privData);
        this.pub = SecurityUtils.getPublicKey(pubData);
        this.sim = (SecretKey) SecurityUtils.getAESKey(simData);
    }
    
    public void loadPublic() throws Exception{
         //ler a publica
        try {
        byte[] pubData = Files.readAllBytes(Path.of(this.nome + ".pub"));
        this.pub = SecurityUtils.getPublicKey(pubData);
        } catch (Exception e) {
        throw new Exception("Erro ao carregar usuário: " + e.getMessage());
    }
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public PublicKey getPub() {
        return pub;
    }

    public void setPub(PublicKey pub) {
        this.pub = pub;
    }

    public PrivateKey getPriv() {
        return priv;
    }

    public void setPriv(PrivateKey priv) {
        this.priv = priv;
    }

    public SecretKey getSim() {
        return sim;
    }

    public void setSim(SecretKey sim) {
        this.sim = sim;
    }
    
}
