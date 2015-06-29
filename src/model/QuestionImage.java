/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Xers
 */
public class QuestionImage extends Question {
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public QuestionImage(String question, String url, String media, List<String> i) {
        super(question, url, media);
        images =i;
    }
    
}
