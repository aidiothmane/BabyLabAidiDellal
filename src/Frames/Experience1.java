/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.LancementExperience.document;
import static Frames.LancementExperience.racine;
import babylabaididellal.LancerExp;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Config;
import model.Question;
import model.QuestionCheckBox;
import model.QuestionCommentaire;
import model.QuestionEchelle;
import model.QuestionRadioButton;
import model.Reponse;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/**
 *
 * @author Aidi Othmane
 */
public class Experience1 extends javax.swing.JFrame {

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
     public Experience1(LancerExp l, Config f) {
    
        
        initComponents();
        jPanel1.setVisible(true);
        jPanel8.setVisible(false);
        jPanel7.setVisible(false);
        jPanel4.setVisible(false);
        jPanel2.setVisible(true);
        reponseT=new Reponse();
        this.lancer=l;
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
             if(experiences.get(i).getAttribute("type").getValue().equals("CheckBox")){
                 List<Element> childs = experiences.get(i).getChildren();
                 List<Element> reps= experiences.get(i).getChildren("reponse");
                 List<String> reponsee = new ArrayList<>();
               
                 for(int j=0;j<reps.size();j++){
                     reponsee.add(reps.get(j).getValue());
                 }
                 
                 QuestionCheckBox r = new QuestionCheckBox(childs.get(1).getValue(), childs.get(0).getValue(), experiences.get(i).getAttribute("media").getValue(), reponsee);
                 this.reponses.add(r);
             }
             if(experiences.get(i).getAttribute("type").getValue().equals("Commentaire")){
                 List<Element> childs = experiences.get(i).getChildren();
                 
                
                 
                 QuestionCommentaire r = new QuestionCommentaire(childs.get(1).getValue(), childs.get(0).getValue(), experiences.get(i).getAttribute("media").getValue());
                 this.reponses.add(r);
             }
              if(experiences.get(i).getAttribute("type").getValue().equals("Echelle")){
                 List<Element> childs = experiences.get(i).getChildren();
                 QuestionEchelle echelle = new QuestionEchelle(childs.get(1).getValue(), childs.get(0).getValue(), experiences.get(i).getAttribute("media").getValue());
                 echelle.setDe(Integer.parseInt(childs.get(2).getText()));
                 echelle.setA(Integer.parseInt(childs.get(3).getText()));
                echelle.setPas(Integer.parseInt(childs.get(4).getText()));
                echelle.setAffichage(childs.get(5).getAttribute("affichage").getValue());
                echelle.setType(childs.get(5).getAttribute("textnumerique").getValue());
                if(echelle.getType().equals("text")){
                    for(int j=0;j<childs.get(5).getChildren().size();j++){
                        echelle.ajouterList(childs.get(5).getChildren().get(j).getValue());
                    }
                }
                this.reponses.add(echelle);
             }
         }
         
     }
      public void initialiserConfig(){
          
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
      listCh.clear();
        jPanel2.setVisible(true);
        jPanel4.setVisible(false);
        chrono = java.lang.System.currentTimeMillis() ; 
        nbrClick=0;
           reponseT.setQuestions(reponses);
          jPanel3.removeAll();
          jPanel3.repaint();
           if(cfg.getEmplacement()==1 || cfg.getEmplacement()==2 || cfg.getEmplacement()==3){
               jLabel6.setText(this.reponses.get(next).getQuestion());
           }else if(cfg.getEmplacement()==4){
               jLabel2.setText(this.reponses.get(next).getQuestion());
           }else if(cfg.getEmplacement()==8){
               jLabel1.setText(this.reponses.get(next).getQuestion());
           }else{
               jLabel7.setText(this.reponses.get(next).getQuestion());
           }
           if(this.reponses.get(next).getMedia().equals("image")){
                
               jPanel7.setVisible(false);
               jPanel8.setVisible(false);
               jPanel1.setVisible(true);
           ImageIcon ii = new ImageIcon(this.reponses.get(next).getUrl());
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel4.setIcon(a);
           jLabel4.setText("");
           }else if(this.reponses.get(next).getMedia().equals("video")){
           
                jPanel7.setVisible(true);
                jPanel8.setVisible(false);
                jPanel1.setVisible(false);
                
                
                NativeLibrary.addSearchPath(
                     RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
             Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
             LibXUtil.initialise();
              mediaPlayerFactory = new MediaPlayerFactory();
             //Créer une instance lecteur média
            mediaPlayer =mediaPlayerFactory.newEmbeddedMediaPlayer();
             mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas1));
              mediaPlayer.prepareMedia(this.reponses.get(next).getUrl());
             
             //lire le fichier 
              
             mediaPlayer.play();
           
            
            
            
               
           }else if(this.reponses.get(next).getMedia().equals("son")){
            
               jPanel7.setVisible(false);
               jPanel8.setVisible(true);
               jPanel1.setVisible(false);
               
               NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
             mediaPlayerFactory = new MediaPlayerFactory();
            //Créer une instance lecteur média
           mediaPlayer =mediaPlayerFactory.newEmbeddedMediaPlayer();
        //    mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(canvas1));
             mediaPlayer.prepareMedia(this.reponses.get(next).getUrl());
            
            //lire le fichier 
             ImageIcon ii = new ImageIcon("url.gif");
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(128, 128, java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel3.setIcon(a);
           jLabel3.setText("");
             
            mediaPlayer.play();
           
         
           }
           if(reponses.get(next)instanceof QuestionRadioButton){
               this.rer="Rien";
           QuestionRadioButton aa = (QuestionRadioButton) reponses.get(next);
           ButtonGroup b = new ButtonGroup();
           List<String> pp = aa.getReponse();
      
             
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
           }else if (reponses.get(next)instanceof QuestionCheckBox){
                this.rer="Rien";
                nbrClick = 0;
           QuestionCheckBox aa = (QuestionCheckBox) reponses.get(next);
           
           List<String> pp = aa.getReponse();
           
           
           for(int i=0 ;i< pp.size();i++){
               final JCheckBox j = new JCheckBox();
                 j.setText(pp.get(i));
               j.addActionListener(new ActionListener() {

                   @Override
                   public void actionPerformed(ActionEvent e) {
                       
                       nbrClick++;
                   }
               });
             
              listCh.add(j);
           
               jPanel3.add(j);
           }
           }else if (reponses.get(next)instanceof QuestionCommentaire){
                this.rer="Rien";
                 listCh.clear();
              t= new JTextField();
               
                t.setText("");
             Dimension d = new Dimension();
            d.setSize(60,30 );
            t.setPreferredSize(d);
            t.setAlignmentY(1000);
               jPanel3.add(t);
           }else if (reponses.get(next)instanceof QuestionEchelle){
                
                nbrClick=0;
                 listCh.clear();
              final QuestionEchelle echelle= (QuestionEchelle)reponses.get(next);
           
            final JSlider jSliderOne = new JSlider();

    jSliderOne.setPaintLabels(true);
    jSliderOne.setValueIsAdjusting(true);
    Hashtable<Integer, JLabel> table; 
    
    jSliderOne.setMajorTickSpacing(1);
     table= new Hashtable<Integer, JLabel>();
     echelle.remplir();
   
     for(int i=0;i<echelle.getAffichageText().size();i++){
         table.put(i,new JLabel(echelle.getAffichageText().get(i)));
   
     }
     this.rer=echelle.getAffichageText().get(0);
     jSliderOne.setMinimum(0);
     jSliderOne.setMaximum(echelle.getAffichageText().size()-1);
     jSliderOne.setValue(jSliderOne.getMinimum());
     jSliderOne.addChangeListener(new ChangeListener() {

                    @Override
                    public void stateChanged(ChangeEvent e) {
                       rer= echelle.getAffichageText().get(jSliderOne.getValue());
                       if(rer.isEmpty()){
                           rer = "Valeur ="+(jSliderOne.getValue() + echelle.getDe()) + " dans ["+echelle.getDe()+","+echelle.getA()+"]";
                       }
                        
                    }
                });
     jSliderOne.addMouseListener(new MouseListener() {


                    @Override
                    public void mouseReleased(MouseEvent e) {
                        nbrClick++;
                        }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                      
                        nbrClick++;
                         }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                          }

                    @Override
                    public void mouseExited(MouseEvent e) {
                          }

                });
          
     jSliderOne.setLabelTable(table);
    jSliderOne.setSize(650, 80);
 
          jPanel10.removeAll();
          jPanel10.add(jSliderOne);
          
          jPanel10.repaint();
          jPanel3.setVisible(false);
          jPanel10.setVisible(true);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("ddddd");

        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("");

        jLabel7.setText("ddddd");

        jLabel2.setText("jLabel1");
        jLabel2.setToolTipText("");

        jButton1.setText("Suivant");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel6.setLayout(new java.awt.CardLayout());

        jLabel4.setText("jLabel4");
        jLabel4.setAlignmentX(0.1F);
        jLabel4.setPreferredSize(new java.awt.Dimension(310, 250));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel1, "card2");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, "card3");

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8, "card4");

        jPanel9.setLayout(new java.awt.CardLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.add(jPanel3, "card2");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10, "card3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1))))
                        .addGap(86, 86, 86))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel7)
                .addGap(39, 39, 39)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel2, "card2");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanel4, "card3");

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
          jPanel3.setVisible(true);
          jPanel10.setVisible(false);
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
               Experience1 ep= new Experience1(null,null);
                ep.setVisible(true);
                ep.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.Canvas canvas1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables

 

  
}
