/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aidi Othmane
 */
public class QuestionEchelle extends Question {
  private int de;
  private int a ;
  private int pas;
  private String type;
  private String affichage;
  private List<String> detailText;
  private List<String> affichageText;
          public QuestionEchelle(String question, String url, String media) {
        super(question, url, media);
        detailText = new ArrayList<>();
        affichageText=new ArrayList<>();
    }
public void ajouterList(String x){
    detailText.add(x);
}

    public List<String> getAffichageText() {
        return affichageText;
    }

    public void setAffichageText(List<String> affichageText) {
        this.affichageText = affichageText;
    }
public void remplir(){
    int cpt = (a-de+1)/pas;
    if(type.equals("numerique")){
        if(affichage.equals("avec")){
            int rem = de;
            for(int i = 0 ;i < cpt ; i++){
                affichageText.add(rem+"");
                rem=rem +pas;
                
            }
        }else{
            affichageText.add(de+"");
            cpt= cpt-2 ;
            for(int i = 0 ;i < cpt ; i++){
                 affichageText.add("");
            }
            affichageText.add(a+"");
        }
        
    }else{
          if(affichage.equals("avec")){
              for(int i = 0 ;i < cpt ; i++){
                  affichageText.add(detailText.get(i));
              }
          }else{
              affichageText.add(detailText.get(0)+"");
            cpt= cpt-2 ;
            for(int i = 0 ;i < cpt ; i++){
                 affichageText.add("");
            } affichageText.add(detailText.get(1)+"");
            
          }
        
    }
}
    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getPas() {
        return pas;
    }

    public void setPas(int pas) {
        this.pas = pas;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAffichage() {
        return affichage;
    }

    public void setAffichage(String affichage) {
        this.affichage = affichage;
    }

    public List<String> getDetailText() {
        return detailText;
    }

    public void setDetailText(List<String> detailText) {
        this.detailText = detailText;
    }

    
}
