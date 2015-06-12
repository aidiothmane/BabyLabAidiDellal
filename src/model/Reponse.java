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
public class Reponse {
    /**
     *
     */
    
    private List<Integer> nbrClick;
    private List<Question> questions;
    private List<String> respones;
    private List<Long> tmpsRepones;

    public Reponse() {
        nbrClick=new ArrayList<>();
        questions=new ArrayList<>();
        respones=new ArrayList<>();
        tmpsRepones=new ArrayList<>();
    }

    public List<Integer> getNbrClick() {
        return nbrClick;
    }

    public void setNbrClick(List<Integer> nbrClick) {
        this.nbrClick = nbrClick;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<String> getRespones() {
        return respones;
    }

    public void setRespones(List<String> respones) {
        this.respones = respones;
    }

    public List<Long> getTmpsRepones() {
        return tmpsRepones;
    }

    public void setTmpsRepones(List<Long> tmpsRepones) {
        this.tmpsRepones = tmpsRepones;
    }
    
    public void addNbrClick(int nbr){
        this.nbrClick.add(nbr);
    }
    public void addTmpReponse(Long tmps){
        this.tmpsRepones.add(tmps);
    }
    public void addQuestion(Question q){
        this.questions.add(q);
    }
    public void addReponse(String reponse){
        this.respones.add(reponse);
    }
    public Long sommeTmpsRep(){
        Long somme=0L;
        for(int i=0 ; i<this.tmpsRepones.size();i++){
            somme=somme+this.tmpsRepones.get(i);
        }
        return somme;
    }
    public String toString(){
       String res="Resultat:\n\n";
        for(int i=0 ;i<this.questions.size();i++){
            res=res+"\nQuestion: "+this.questions.get(i).getQuestion()+"\n image: "+this.questions.get(i).getUrl()+"\n reponse: "+this.respones.get(i)+"\n temps de reponse: "+this.tmpsRepones.get(i)+"ms\n nombre de Clicks: "+this.nbrClick.get(i)+
                    "\n\n_________________________________________________________\n";
        }
        res = res +" ==> Temps Total :"+ sommeTmpsRep()+"ms";
        return res;
    }
    
}
