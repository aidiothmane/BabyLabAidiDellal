/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Aidi Othmane**/
public class QuestionCheckBox extends Question {
public List<String> reponse;

    public QuestionCheckBox(String question, String url, String media ,List<String> reponse ) {
        super(question, url, media);
        this.reponse=reponse;
    }

    public List<String> getReponse() {
        return reponse;
    }
}