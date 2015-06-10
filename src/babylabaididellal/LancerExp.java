/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babylabaididellal;

import java.util.Random;

/**
 *
 * @author Aidi Othmane
 */
public class LancerExp {
    public String typeExp;
    public String id;
    public String typeDure;
    public int dureeF;
    public int dureeA1;
    public int dureeA2;
    public int duree;

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
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

    public LancerExp(String typeExp, String id, String typeDure) {
        this.typeExp = typeExp;
        this.id = id;
        this.typeDure = typeDure;
      
    }
    public int  verifierDurer(){
        int valeur;
        if(typeDure.equals("aleatoire")){
           Random r = new Random();
          
           valeur = dureeA1 + r.nextInt(dureeA2 - dureeA1);
        }else{
            valeur = dureeF;
        }
        this.duree=valeur;
        return valeur;
    }

    public String getTypeExp() {
        return typeExp;
    }

    public void setTypeExp(String typeExp) {
        this.typeExp = typeExp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeDure() {
        return typeDure;
    }

    public void setTypeDure(String typeDure) {
        this.typeDure = typeDure;
    }

    
    
}
