/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.LancementExperience.document;
import static Frames.LancementExperience.racine;
import babylabaididellal.LancerExp;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import java.util.List;
import javax.swing.Timer;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import model.Config;
import model.Question;
import model.QuestionRadioButton;
import model.Reponse;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Aidi Othmane
 */
public class Experience1 extends javax.swing.JFrame {

    /**
     * Creates new form Experience1
     */
   public Timer timer;
   public Timer timerEcran;
    public LancerExp lancer;
    public static Element racine = new Element("experiences");
    public static  org.jdom2.Document document = new Document(racine);
    List<Element> experiences;
    Element config;
    Config cfg=new Config();
    List<Question> reponses = new ArrayList<>();
    public int next =0;
    public String resultat="";
    public int nbrClick=0;
    private String rer="";
    public Reponse reponseT;
    public Long chrono;
     public Experience1(LancerExp l) {
    
        
        initComponents();
        jPanel4.setVisible(false);
        jPanel2.setVisible(true);
        reponseT=new Reponse();
        this.lancer=l;
        try {
            experiences=getExperiences("Exp1","aidi");
            config =  getConf("Exp1","aidi");
            
            
        } catch (DataConversionException ex) {
           
        }
        initialiserConfig();
       initialiserExperience();
      
            this.timer = new Timer(Integer.parseInt(lancer.getDuree()),new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               reponseT.addTmpReponse((Long.parseLong(lancer.getDuree())));
           reponseT.addReponse("Aucune reponse"); 
           reponseT.addNbrClick(nbrClick);
         if(next + 1< reponses.size()){
              AffichageEcr();
           }else{
            timerEcran.stop();
            timer.stop();
            test t = new test(reponseT);
            t.setVisible(true);
            exit();
        }
           }
       });
            
            
            
            
            this.timerEcran = new Timer(3000,new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
              
               next++;
        if(next < reponses.size()){
            
            initaliserQuestRep(next);
        }else{
            timerEcran.stop();
            timer.stop();
            test t = new test(reponseT);
            t.setVisible(true);
            exit();
        }
           
           }
       });
           
           initaliserQuestRep(next);
            //timer.start();
           
        
        
    }
     public void exit(){
         timer.stop();
         
         this.setVisible(false);
     }
     public void initialiserExperience(){
         for(int i=0;i<experiences.size();i++){
             if(experiences.get(i).getAttribute("type").getValue().equals("RadioButton")){
                 List<Element> childs = experiences.get(i).getChildren();
                 List<Element> reps= experiences.get(i).getChildren("reponse");
                 List<String> reponsee = new ArrayList<>();
               
                 for(int j=0;j<reps.size();j++){
                     reponsee.add(reps.get(j).getValue());
                 }
                 QuestionRadioButton r = new QuestionRadioButton(childs.get(1).getValue(), childs.get(0).getValue(), experiences.get(i).getAttribute("media").getValue(), reponsee);
                 this.reponses.add(r);
             }
         }
         
     }
      public void initialiserConfig(){
           if(config!=null){
            
            cfg.setEmplacement(Integer.parseInt(config.getChildren().get(0).getChildren().get(0).getValue()));
          cfg.setFont(config.getChildren().get(0).getChildren().get(1).getValue());
            cfg.setCouleur(config.getChildren().get(0).getChildren().get(2).getValue());
            cfg.setTaille(Integer.parseInt(config.getChildren().get(0).getChildren().get(3).getValue()));
            cfg.setConsigne(config.getChildren().get(1).getChildren().get(0).getValue());
            cfg.setFont2(config.getChildren().get(1).getChildren().get(1).getValue());
            cfg.setCouleur2(config.getChildren().get(1).getChildren().get(2).getValue());
            cfg.setTaille3(Integer.parseInt(config.getChildren().get(1).getChildren().get(3).getValue()));
            
            if(config.getChildren().size() > 2){
        
               
        }else{
               cfg.setEcran(false);
               cfg.setDuree(null);
               cfg.setCouleur3(null);
               cfg.setTypeDure(null);
            }
        }
        Font font1 = new Font(cfg.getFont(), Font.PLAIN, cfg.getTaille());
              jLabel7.setFont(font1);
              jLabel6.setFont(font1);
              jLabel1.setFont(font1);
              jLabel2.setFont(font1);
              String[] cl = cfg.getCouleur().split("/");
              jLabel7.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
              jLabel6.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
              jLabel1.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
              jLabel2.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
           
           if(cfg.getEmplacement()==1){
              jLabel6.setHorizontalAlignment(SwingConstants.LEFT);
              jLabel2.setVisible(false);
              jLabel1.setVisible(false);
              jLabel7.setVisible(false);
           }else if(cfg.getEmplacement()==2){
               jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
              jLabel2.setVisible(false);
              jLabel1.setVisible(false);
              jLabel7.setVisible(false);
           }else if(cfg.getEmplacement()==3){
               jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
              jLabel2.setVisible(false);
              jLabel1.setVisible(false);
              jLabel7.setVisible(false);
           }else if(cfg.getEmplacement()==4){
               jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
              jLabel6.setVisible(false);
              jLabel1.setVisible(false);
              jLabel7.setVisible(false);
           }else if(cfg.getEmplacement()==5){
               jLabel7.setHorizontalAlignment(SwingConstants.LEFT);
              jLabel6.setVisible(false);
              jLabel1.setVisible(false);
              jLabel2.setVisible(false);
           }else if(cfg.getEmplacement()==6){
                jLabel7.setHorizontalAlignment(SwingConstants.CENTER);
              jLabel6.setVisible(false);
              jLabel1.setVisible(false);
              jLabel2.setVisible(false);
           }else if(cfg.getEmplacement()==7){
                 jLabel7.setHorizontalAlignment(SwingConstants.RIGHT);
              jLabel6.setVisible(false);
              jLabel1.setVisible(false);
              jLabel2.setVisible(false);
           }else {
               jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
              jLabel6.setVisible(false);
              jLabel7.setVisible(false);
              jLabel2.setVisible(false);
           }
                   
      }
    public void AffichageEcr(){
       jPanel2.setVisible(false);
       JLabel p = new JLabel();
             
       
       
       
       
   jPanel4.setLayout(new BorderLayout());
    
    ImageIcon ii = new ImageIcon("x.png");
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(jPanel4.getWidth(),jPanel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           p.setIcon(a);
   
   
    jPanel4.add(p);
   
    
    this.setVisible(true);
               jPanel4.add(p);
               
       jPanel4.setVisible(true);
       timer.stop();
       timerEcran.stop();
       timerEcran.start();
    }
    public final void initaliserQuestRep(int step){
        jPanel2.setVisible(true);
        jPanel4.setVisible(false);
        chrono = java.lang.System.currentTimeMillis() ; 
        nbrClick=0;
           reponseT.setQuestions(reponses);
          jPanel3.removeAll();
          jPanel3.repaint();
           if(cfg.getEmplacement()==1 || cfg.getEmplacement()==2 || cfg.getEmplacement()==3){
               jLabel6.setText(this.reponses.get(step).getQuestion());
           }else if(cfg.getEmplacement()==4){
               jLabel2.setText(this.reponses.get(step).getQuestion());
           }else if(cfg.getEmplacement()==8){
               jLabel1.setText(this.reponses.get(step).getQuestion());
           }else{
               jLabel7.setText(this.reponses.get(step).getQuestion());
           }
          ImageIcon ii = new ImageIcon(this.reponses.get(step).getUrl());
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(230, 310, java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel4.setIcon(a);
           jLabel4.setText("");
           
           if(reponses.get(step)instanceof QuestionRadioButton){
               
           QuestionRadioButton aa = (QuestionRadioButton) reponses.get(step);
           ButtonGroup b = new ButtonGroup();
           List<String> pp = aa.getReponse();
      
             System.out.println(pp.size());
           for(int i=0 ;i< pp.size();i++){
               final JRadioButton j = new JRadioButton();
                 j.setText(pp.get(i));
               j.addActionListener(new ActionListener() {

                   @Override
                   public void actionPerformed(ActionEvent e) {
                       rer=j.getText();
                       
                       nbrClick++;
                   }
               });
             
               b.add(j);
           
               jPanel3.add(j);
           }
           }else {
               // autre instances ....
           }
           
           this.pack();
           timerEcran.stop();
           timer.stop();
           timer.start();
            
        
       
       
           
    }
    private Element getConf(String chemain,String id) throws DataConversionException{
        Element child = null;
        File f = new File(chemain+"/Experiences.xml");
        if ( f.exists()){
           
            SAXBuilder sxb = new SAXBuilder();
      try
      {
    
         document = sxb.build(new File(chemain+"/Experiences.xml"));
      }
      catch(Exception e){}
       racine = document.getRootElement();
       if( ! racine.getChildren("experience").isEmpty()){
           //System.out.println(racine.getChildren("experience").size());
           for(int i=0 ; i<racine.getChildren("experience").size();i++){
               if(racine.getChildren("experience").get(i).hasAttributes())
                   
               if(racine.getChildren("experience").get(i).getAttribute("id").getValue().equals(id)){
                   child = racine.getChildren("experience").get(i).getChild("config");
                   
               }
               
           }
          
           
         
          
       }
    
            
        }
      return child;
    }
    
    private List<Element> getExperiences(String chemain,String id) throws DataConversionException{
        List<Element> childs = null;
        File f = new File(chemain+"/Experiences.xml");
        if ( f.exists()){
            
            SAXBuilder sxb = new SAXBuilder();
      try
      {
    
         document = sxb.build(new File(chemain+"/Experiences.xml"));
      }
      catch(Exception e){}
       racine = document.getRootElement();
       if( ! racine.getChildren("experience").isEmpty()){
           for(int i=0 ; i<racine.getChildren("experience").size();i++){
               
               if(racine.getChildren("experience").get(i).hasAttributes()){
                   
               if(racine.getChildren("experience").get(i).getAttribute("id").getValue().equals(id)){
               
                   childs = racine.getChildren("experience").get(i).getChildren("stimulé");
                   System.out.println(childs.size());
               }
               
           }
          
           }
         
          
       }
    
            
        }
      return childs;
    }
   private Element getConfig(List<Element> experiences) {
        Element conf = null;
        //conf = experiences.get(experiences.size()-1);
        System.out.println(experiences.size()-1);
        return conf;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel4, "card3");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("ddddd");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("ddddd");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("jLabel1");
        jLabel2.setToolTipText("");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setText("Suivant");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel4");
        jLabel4.setAlignmentX(0.1F);
        jLabel4.setPreferredSize(new java.awt.Dimension(310, 250));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)
                                .addGap(154, 154, 154))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jButton1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reponseT.addTmpReponse(java.lang.System.currentTimeMillis()- chrono);
       reponseT.addReponse(rer);
       reponseT.addNbrClick(nbrClick);
        next++;
        if(next < reponses.size()){
           timer.stop();
           initaliserQuestRep(next);
            timer.start();
        }else{
            test t = new test(reponseT);
            t.setVisible(true);
            exit();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Experience1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Experience1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Experience1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Experience1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Experience1 ep= new Experience1(new LancerExp("ss", "aidi", "fixe", "5000"));
                ep.setVisible(true);
                ep.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

 

  
}
