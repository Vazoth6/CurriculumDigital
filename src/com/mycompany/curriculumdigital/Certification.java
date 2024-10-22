/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.curriculumdigital;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import static javax.management.Query.value;

/**
 *
 * @author Gui and Rodrigo
 */
public class Certification implements Serializable {
    

    
    private String from;

    private String to;

    private String fromPub;
    private String toPub;

    private String evento;
    private String signature;

    public Certification(User from, User to, String evento) throws Exception {
        
        this.fromPub = Base64.getEncoder().encodeToString(from.getPub().getEncoded());
        this.to = to.getName();
        this.toPub = Base64.getEncoder().encodeToString(to.getPub().getEncoded());
        this.evento = evento;
        sign(from.getPriv());
    }

    public void sign(PrivateKey priv) throws Exception {
        byte[] dataSign = SecurityUtils.sign(
                (fromPub + toPub + evento).getBytes(),
                priv);
        this.signature = Base64.getEncoder().encodeToString(dataSign);

    }
    
    
    

    public boolean isValid() {
        try {
            PublicKey pub = SecurityUtils.getPublicKey(Base64.getDecoder().decode(fromPub));
            byte[] data = (fromPub + toPub + evento).getBytes();
            byte[] sign = Base64.getDecoder().decode(signature);
            return SecurityUtils.verifySign(data, sign, pub);
        } catch (Exception ex) {
            return false;
        }
    }

    public String getEvent() {
        return evento;
    }

    public void setValue(String evento) {
        this.evento = evento;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        
        return String.format("%-10s -> %s %s -> %s", from, evento, isValid() + "", to);
        //return from + "\t : " + to + "\t -> " + value;
    }

    public static long serialVersionUID = 123;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getFromPub() {
        return fromPub;
    }

    public void setFromPub(String fromPub) {
        this.fromPub = fromPub;
    }

    public String getToPub() {
        return toPub;
    }

    public void setToPub(String toPub) {
        this.toPub = toPub;
    }

}
