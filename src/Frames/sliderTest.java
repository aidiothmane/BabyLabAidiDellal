/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

/**
 *
 * @author Xers
 */
import java.awt.BorderLayout;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class sliderTest {
  public static void main(String args[]) {
    JFrame frame = new JFrame("Tick Slider");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JSlider jSliderOne = new JSlider();

    jSliderOne.setPaintLabels(true);
    
    Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
    table.put (1, new JLabel("Wow!"));
    table.put (2, new JLabel("Ten"));
    table.put (3, new JLabel("Twenty-Five"));
    table.put (4, new JLabel("Thirty-Four"));
    table.put (5, new JLabel("Fifty-Two"));
    table.put (6, new JLabel("Seventy"));
    table.put (7, new JLabel("Eighty-Two"));
    jSliderOne.setLabelTable (table);
    //ss
       jSliderOne.setMinimum(1);
      
      
  jSliderOne.setMajorTickSpacing(1);
        jSliderOne.setMaximum(7);
        jSliderOne.setPaintLabels(true);
        
        jSliderOne.setValue(5);
        jSliderOne.setValueIsAdjusting(true);
    //ss
    frame.add(jSliderOne, BorderLayout.NORTH);
    frame.setSize(600, 200);
    frame.setVisible(true);
  }
}