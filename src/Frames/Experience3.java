/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import babylabaididellal.LancerExp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Timer;


import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import model.Config;
import model.Question;
import model.QuestionCommentaire;
import model.QuestionImage;
import model.Reponse;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author Aidi Othmane
 */
public class Experience3 extends javax.swing.JFrame {

    /**
     * Creates new form Experience1
     */
     JTextField t ;
    List<JCheckBox> listCh = new ArrayList<>();
    MediaPlayerFactory mediaPlayerFactory;
                 EmbeddedMediaPlayer mediaPlayer; 
   public Timer timer;
   public Timer timerEcran;
    public LancerExp lancer;
    public static Element racine = new Element("experiences");
    public static  org.jdom2.Document document = new Document(racine);
    List<Element> experiences;
    
    Config cfg=new Config();
    List<Question> reponses = new ArrayList<>();
    public int next =0;
    public String resultat="";
    public int nbrClick=0;
    private String rer="Rien";
    public Reponse reponseT;
    public Long chrono;
     public Experience3(LancerExp l, Config f) {
    
        
        initComponents();
   this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
   
      
        
        jPanel4.setVisible(false);
        jPanel2.setVisible(true);
        reponseT=new Reponse();
        this.lancer=l;
         initialiserColeur();
        reponseT.setTypeExp(l.getTypeExp());
        reponseT.setIdExp(l.getId());
        try {
            experiences=getExperiences(l.getTypeExp(),l.getId());
            
        } catch (DataConversionException ex) {
           
        }
        cfg = f;
        initialiserConfig();
       initialiserExperience();
      
            this.timer = new Timer(lancer.verifierDurer(),new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
           reponseT.addTmpReponse((Long.parseLong(""+lancer.getDuree())));
           reponseT.addReponse(rer); 
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
            
            
            
            
            this.timerEcran = new Timer(cfg.verifierDurer(),new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
              
               next++;
        if(next < reponses.size()){
            
            initaliserQuestRep();
        }else{
            timerEcran.stop();
            timer.stop();
            test t = new test(reponseT);
            t.setVisible(true);
            exit();
        }
           
           }
       });
           
           initaliserQuestRep();
          
           
        
        
    }
      private void initialiserColeur() {
        
       jPanel11.setBackground(lancer.getColor());
                jPanel2.setBackground(lancer.getColor());
                jPanel3.setBackground(lancer.getColor());
                jPanel4.setBackground(lancer.getColor());
                jPanel5.setBackground(lancer.getColor());
                jPanel6.setBackground(lancer.getColor());
             
        }
     public void exit(){
         timer.stop();
         
         this.setVisible(false);
     }
     public void initialiserExperience(){
         for(int i=0;i<experiences.size();i++){
          
                 List<Element> childs = experiences.get(i).getChildren();
                 List<Element> reps= experiences.get(i).getChildren("img");
                 List<String> reponsee = new ArrayList<>();
               
                 for(int j=0;j<reps.size();j++){
                     reponsee.add(reps.get(j).getValue());
                 }
                 
                 QuestionImage r = new QuestionImage(childs.get(0).getValue(), "","image", reponsee);
                 this.reponses.add(r);
                         
             
              
         }
         
     }
      public void initialiserConfig(){
          
        Font font1 = new Font(cfg.getFont(), Font.PLAIN, cfg.getTaille());
              
              jLabel6.setFont(font1);
             
             
              String[] cl = cfg.getCouleur().split("/");
             
              jLabel6.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
              jLabel6.setHorizontalAlignment(SwingConstants.CENTER);
           
          
                   
      }
    public void AffichageEcr(){
       if(cfg.ecran){
        
       JLabel p = new JLabel();
             
       
       
       
       
   jPanel4.setLayout(new BorderLayout());
    
    ImageIcon ii = new ImageIcon("x.png");
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(jPanel4.getWidth(),jPanel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           p.setIcon(a);
   
   
    jPanel4.add(p);
   
    
    
      jPanel4.add(p);
       jPanel2.setVisible(false);        
       jPanel4.setVisible(true);
       timer.stop();
       timerEcran.stop();
       timerEcran.setDelay(cfg.verifierDurer());
       timerEcran.start();
    }else{
           next++;
           initaliserQuestRep();
       }
    }
    public final void initaliserQuestRep(){
        if(next == (this.reponses.size()-1)) jButton1.setText("Fin !");
      
        jPanel2.setVisible(true);
        jPanel4.setVisible(false);
        chrono = java.lang.System.currentTimeMillis() ; 
        nbrClick=0;
           reponseT.setQuestions(reponses);
          jPanel3.removeAll();
          jPanel3.repaint();
         
               jLabel6.setText(this.reponses.get(next).getQuestion());
           
          
                
               
          
          JLabel d = new JLabel();
          d.setSize(100, 100);
                    Border blackline;
blackline = BorderFactory.createLineBorder(Color.black);
d.setBorder(blackline);
d.setText("");
jPanel3.add(d);
           final List<JLabel> listJl = new ArrayList<>();
           QuestionImage q = (QuestionImage) this.reponses.get(next);
           for (int i=0;i<q.getImages().size();i++){
               final JLabel jl = new JLabel();
               ImageIcon imgic= new ImageIcon(q.getImages().get(i));
               
                Image img1 = imgic.getImage(); 
                 Image newimg1 = img1.getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT);
                  ImageIcon aa = new ImageIcon(newimg1); 
                  aa.setDescription(q.getImages().get(i));
                     jl.setIcon(aa);
                     jPanel3.setLayout(new GridLayout(2,6,0,0));
                     jPanel3.add(jl, java.awt.BorderLayout.CENTER);
                     listJl.add(jl);
                     jl.addMouseListener(new MouseListener() {

                   @Override
                   public void mouseClicked(MouseEvent e) {
                       nbrClick++;
                      // Border blackline;

//blackline = BorderFactory.createLineBorder(Color.black);
        jl.setBorder(BorderFactory.createMatteBorder(
                                    2, 2, 2, 2, Color.red));
        ImageIcon icon = (ImageIcon) jl.getIcon();
        
                       rer=icon.getDescription();
                     for(int j =0 ; j<listJl.size();j++){
                         if(!listJl.get(j).equals(jl)){
                             listJl.get(j).setBorder(null);
                         }
                     }
                      
                         }

                   @Override
                   public void mousePressed(MouseEvent e) {
                         }

                   @Override
                   public void mouseReleased(MouseEvent e) {
                       }

                   @Override
                   public void mouseEntered(MouseEvent e) {
                       }

                   @Override
                   public void mouseExited(MouseEvent e) {
                        }
               });
           }
          
           
           this.pack();
           timerEcran.stop();
           timer.stop();
           if(this.reponses.get(next).getMedia().equals("image")){
           timer.setDelay(lancer.verifierDurer());
           
           timer.start();
           }
        
       
       
           
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
                   
               }
               
           }
          
           }
         
          
       }
    
            
        }
      return childs;
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
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanel4, "card3");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel11.setPreferredSize(new java.awt.Dimension(1200, 1000));

        jLabel6.setText("ddddd");

        jPanel6.setLayout(new java.awt.CardLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setText("Suivant");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Relancer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addGap(479, 479, 479)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(442, 442, 442)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(201, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel5.add(jPanel2, "card2");

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        timer.stop();
        timerEcran.stop();
        if( this.reponses.get(next).getMedia().equals("son")){

            mediaPlayer.stop();
        }if( this.reponses.get(next).getMedia().equals("video")){
            mediaPlayer.stop();
        }
        Experience3 e=new Experience3(lancer, cfg);
        e.setVisible(true);
        e.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanel3.setVisible(true);

        reponseT.addTmpReponse(java.lang.System.currentTimeMillis()- chrono);
        if(!listCh.isEmpty()){
            rer="";
            for(int i=0; i< listCh.size();i++){
                if(listCh.get(i).isSelected()){
                    rer=rer+listCh.get(i).getText()+"/";
                }
            }
            if(rer.isEmpty())rer="Rien";
            reponseT.addReponse(rer);
        }
        else if(reponses.get(next)instanceof QuestionCommentaire) reponseT.addReponse(t.getText());
        else reponseT.addReponse(rer);
        reponseT.addNbrClick(nbrClick);

        if( this.reponses.get(next).getMedia().equals("son")){

            mediaPlayer.stop();
        }if( this.reponses.get(next).getMedia().equals("video")){
            mediaPlayer.stop();
        }
        if(next +1  < reponses.size()){

            timerEcran.stop();
            timer.stop();

            AffichageEcr();

        }else{
            test t = new test(reponseT);
            t.setVisible(true);
            t.setLocationRelativeTo(null);
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
            java.util.logging.Logger.getLogger(Experience3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Experience3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Experience3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Experience3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               Experience3 ep= new Experience3(null,null);
                ep.setVisible(true);
                ep.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables

 

  
}
