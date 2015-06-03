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
    public String duree;

    public LancerExp(String typeExp, String id, String typeDure, String duree) {
        this.typeExp = typeExp;
        this.id = id;
        this.typeDure = typeDure;
        this.duree = duree;
    }
    public int  verifierDurer(){
        int valeur;
        if(typeDure.equals("aleatoire")){
           Random r = new Random();
           String[] val = duree.split("/");
                     
           int valeurMin = Integer.parseInt(val[0]);
           int valeurMax = Integer.parseInt(val[1]);
           valeur = valeurMin + r.nextInt(valeurMax - valeurMin);
        }else{
            valeur = Integer.parseInt(duree);
        }
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

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }
    
}
