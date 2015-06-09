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
    public String duree;

    public Config() {
    }

    @Override
    public String toString() {
        return "Config{" + "emplacement=" + emplacement + ", font=" + font + ", couleur=" + couleur + ", taille=" + taille + ", consigne=" + consigne + ", font2=" + font2 + ", couleur2=" + couleur2 + ", taille3=" + taille3 + ", ecran=" + ecran + ", couleur3=" + couleur3 + ", typeDure=" + typeDure + ", duree=" + duree + '}';
    }

    public Config(int emplacement, String font, String couleur, int taille, String consigne, String font2, String couleur2, int taille3, boolean ecran, String couleur3, String typeDure, String duree) {
        this.emplacement = emplacement;
        this.font = font;
        this.couleur = couleur;
        this.taille = taille;
        this.consigne = consigne;
        this.font2 = font2;
        this.couleur2 = couleur2;
        this.taille3 = taille3;
        if(ecran){
        this.ecran = ecran;
        this.couleur3 = couleur3;
        this.typeDure = typeDure;
        if(typeDure.equals("aleatoire")){
            this.duree = duree;
           Random r = new Random();
           String[] val = duree.split("/");
                     
           int valeurMin = Integer.parseInt(val[0]);
           int valeurMax = Integer.parseInt(val[1]);
           this.duree = valeurMin + r.nextInt(valeurMax - valeurMin)+"";
        }else{
              this.duree = duree;
        }
      
        
        }
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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

}
