/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Aidi Othmane
 */
public class Config {
    
    public int emplacement;
    public String font;
    public String couleur;
    public int taille;
    public String consigne;
    public String font2;
    public String couleur2;
    public int taille3;
    public boolean ecran;
    public String couleur3;
    public String typeDure;
    public int dureeF;
    public int dureeA1;
    public int dureeA2;
    public int duree;
    
    public int getDureeF() {
        return dureeF;
    }

    public void setDureeF(int dureeF) {
        this.dureeF = dureeF;
    }

    public int getDureeA1() {
        return dureeA1;
    }

    public void setDureeA1(int dureeA1) {
        this.dureeA1 = dureeA1;
    }

    public int getDureeA2() {
        return dureeA2;
    }

    public void setDureeA2(int dureeA2) {
        this.dureeA2 = dureeA2;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }


    public Config() {
    }

    @Override
    public String toString() {
        return "Config{" + "emplacement=" + emplacement + ", font=" + font + ", couleur=" + couleur + ", taille=" + taille + ", consigne=" + consigne + ", font2=" + font2 + ", couleur2=" + couleur2 + ", taille3=" + taille3 + ", ecran=" + ecran + ", couleur3=" + couleur3 + ", typeDure=" + typeDure + ", duree=" + duree + '}';
    }
 public int  verifierDurer(){
        int valeur = 0;
        if(isEcran()){
        if(typeDure.equals("aleatoire")){
           Random r = new Random();
          
           valeur = dureeA1 + r.nextInt(dureeA2 - dureeA1);
        }else{
            valeur = dureeF;
        }
        this.duree=valeur;
        }else{
            valeur = 0;
        }
        System.out.println(valeur +"");
        return valeur;
    }
    
    public int getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(int emplacement) {
        this.emplacement = emplacement;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getConsigne() {
        return consigne;
    }

    public void setConsigne(String consigne) {
        this.consigne = consigne;
    }

    public String getFont2() {
        return font2;
    }

    public void setFont2(String font2) {
        this.font2 = font2;
    }

    public String getCouleur2() {
        return couleur2;
    }

    public void setCouleur2(String couleur2) {
        this.couleur2 = couleur2;
    }

    public int getTaille3() {
        return taille3;
    }

    public void setTaille3(int taille3) {
        this.taille3 = taille3;
    }

    public boolean isEcran() {
        return ecran;
    }

    public void setEcran(boolean ecran) {
        this.ecran = ecran;
    }

    public String getCouleur3() {
        return couleur3;
    }

    public void setCouleur3(String couleur3) {
        this.couleur3 = couleur3;
    }

    public String getTypeDure() {
        return typeDure;
    }

    public void setTypeDure(String typeDure) {
        this.typeDure = typeDure;
    }

    

}
