/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import babylabaididellal.LancerExp;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
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
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/**
 *
 * @author Aidi Othmane
 */
public class Experience2 extends javax.swing.JFrame {

    /**
     * Creates new form Experience1
     */
     final List<JLabel> listJl = new ArrayList<>();
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
     public Experience2(LancerExp l, Config f) {
    
        
        initComponents();

    this.setDefaultLookAndFeelDecorated(true);
 this.setExtendedState(this.MAXIMIZED_BOTH);
        jPanel1.setVisible(true);
        
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
        jPanel1.setBackground(lancer.getColor());
       jPanel11.setBackground(lancer.getColor());
                jPanel2.setBackground(lancer.getColor());
                jPanel3.setBackground(lancer.getColor());
                jPanel4.setBackground(lancer.getColor());
                jPanel5.setBackground(lancer.getColor());
               
             
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
                 
                 QuestionImage r = new QuestionImage(childs.get(1).getValue(), childs.get(0).getValue(),experiences.get(i).getAttribute("media").getValue(), reponsee);
                 if (experiences.get(i).getChild("url2") != null){
                     r.setUrl2(experiences.get(i).getChild("url2").getValue());
                 }else{
                     r.setUrl2("rien");
                 }
                         
                 this.reponses.add(r);
                         
             
              
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
           p.setSize(jPanel4.getWidth(),jPanel4.getHeight());
           p.setText("X");
           p.setHorizontalAlignment(SwingConstants.CENTER);
           p.setFont(new Font("Arial",Font.PLAIN, 120));
           String[] cl = cfg.getCouleur3().split("/");
           p.setForeground(new Color(Integer.parseInt(cl[0]), Integer.parseInt(cl[1]), Integer.parseInt(cl[2])));
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
                
               
                son.setVisible(false);
                image.setVisible(true);
                video.setVisible(false);
               if(this.reponses.get(next).getUrl2().equals("rien")){
                    jLabel5.setVisible(false);
                   jLabel4.setSize(450, jLabel4.getHeight());
           ImageIcon ii = new ImageIcon(this.reponses.get(next).getUrl());
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel4.setIcon(a);
           jLabel4.setText("");
               }else{
                   jLabel5.setVisible(true);
                   jLabel4.setSize(209, jLabel4.getHeight());
           ImageIcon ii = new ImageIcon(this.reponses.get(next).getUrl());
           Image img = ii.getImage(); 
           Image newimg = img.getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel4.setIcon(a);
           jLabel4.setText("");
           
           ImageIcon ii1 = new ImageIcon(this.reponses.get(next).getUrl2());
           Image img1 = ii1.getImage(); 
           Image newimg1 = img1.getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a1 = new ImageIcon(newimg1); 
           jLabel5.setIcon(a1);
           jLabel5.setText("");
                   
               }
           }else if(this.reponses.get(next).getMedia().equals("video")){
               son.setVisible(false);
               image.setVisible(false);
               video.setVisible(true);
               if(this.reponses.get(next).getUrl2().equals("rien")){
                   jLabel8.setVisible(false);
               canvas1.setSize(450, jLabel4.getHeight());
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
           
            
               }else{
                   jLabel8.setVisible(true);
                   canvas1.setSize(240, jLabel4.getHeight());
                  
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
            ImageIcon ii1 = new ImageIcon(this.reponses.get(next).getUrl2());
           Image img1 = ii1.getImage(); 
           Image newimg1 = img1.getScaledInstance(209,canvas1.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a1 = new ImageIcon(newimg1); 
           jLabel8.setIcon(a1);
           jLabel8.setText("");
            
               }
               
           }else{
               
               
                son.setVisible(true);
                image.setVisible(false);
                video.setVisible(false);
              if(this.reponses.get(next).getUrl2().equals("rien")){
                jLabel3.setSize(450, jLabel3.getHeight());
                jLabel9.setVisible(false);
                
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
           Image newimg = img.getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel3.setIcon(a);
           jLabel3.setText("");
             
            mediaPlayer.play();
              }else{
               // sss
                  jLabel3.setSize(209, jLabel3.getHeight());
                  jLabel9.setVisible(true);
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
           Image newimg = img.getScaledInstance(220,jLabel3.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a = new ImageIcon(newimg); 
           jLabel3.setIcon(a);
           jLabel3.setText("");
               ImageIcon ii1 = new ImageIcon(this.reponses.get(next).getUrl2());
           Image img1 = ii1.getImage(); 
           Image newimg1 = img1.getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(), java.awt.Image.SCALE_DEFAULT);
           ImageIcon a1 = new ImageIcon(newimg1); 
           jLabel9.setIcon(a1);
           jLabel9.setText("");
            mediaPlayer.play();
            
                  
           }
           }
          listJl.clear();
           QuestionImage q = (QuestionImage) this.reponses.get(next);
           for (int i=0;i<q.getImages().size();i++){
               final JLabel jl = new JLabel();
               ImageIcon imgic= new ImageIcon(q.getImages().get(i));
               
                Image img1 = imgic.getImage(); 
                 Image newimg1 = img1.getScaledInstance(100, 100, java.awt.Image.SCALE_DEFAULT);
                  ImageIcon aa = new ImageIcon(newimg1); 
                  aa.setDescription(q.getImages().get(i));
                     jl.setIcon(aa);
                     jPanel3.setLayout(new GridLayout(0,6,0,0));
                     jPanel3.add(jl);
                     jl.setBorder(null);
                     listJl.add(jl);
                     jl.addMouseListener(new MouseListener() {

                   @Override
                   public void mouseClicked(MouseEvent e) {
                       nbrClick++;
                  
                       if(jl.getBorder()==null){
        jl.setBorder(BorderFactory.createMatteBorder(
                                    2, 2, 2, 2, Color.red));
        ImageIcon icon = (ImageIcon) jl.getIcon();
        
                      
                       }else{
                         jl.setBorder(null);
                                 
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
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        image = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        son = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        video = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(729, 818));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel5.add(jPanel4, "card3");

        jPanel2.setPreferredSize(new java.awt.Dimension(729, 700));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel11.setPreferredSize(new java.awt.Dimension(729, 818));

        jLabel6.setText("ddddd");

        jLabel1.setText("jLabel1");
        jLabel1.setToolTipText("");

        jLabel7.setText("ddddd");

        jLabel2.setText("jLabel1");
        jLabel2.setToolTipText("");

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

        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel4.setText("jLabel4");
        jLabel4.setAlignmentX(0.1F);
        jLabel4.setPreferredSize(new java.awt.Dimension(310, 250));

        jLabel5.setText("jLabel5");
        jLabel5.setPreferredSize(new java.awt.Dimension(310, 250));

        javax.swing.GroupLayout imageLayout = new javax.swing.GroupLayout(image);
        image.setLayout(imageLayout);
        imageLayout.setHorizontalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageLayout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, imageLayout.createSequentialGroup()
                    .addContainerGap(13, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(20, Short.MAX_VALUE)))
        );
        imageLayout.setVerticalGroup(
            imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(imageLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel1.add(image, "card5");

        jLabel3.setText("jLabel3");

        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout sonLayout = new javax.swing.GroupLayout(son);
        son.setLayout(sonLayout);
        sonLayout.setHorizontalGroup(
            sonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sonLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 10, Short.MAX_VALUE))
            .addGroup(sonLayout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        sonLayout.setVerticalGroup(
            sonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sonLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(sonLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(son, "card7");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout videoLayout = new javax.swing.GroupLayout(video);
        video.setLayout(videoLayout);
        videoLayout.setHorizontalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoLayout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(videoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        videoLayout.setVerticalGroup(
            videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(videoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(videoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.add(video, "card6");

        jButton3.setText("Arrêter l'experience");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel2, "card2");

        getContentPane().add(jPanel5);

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
        Experience2 e=new Experience2(lancer, cfg);
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
        else {
            rer="";
        for(int i=0; i<listJl.size();i++){
            if(listJl.get(i).getBorder()!=null){
               ImageIcon icon =(ImageIcon) listJl.get(i).getIcon();
               rer =icon.getDescription()+"\n"+rer;
            }
                 }
        reponseT.addReponse(rer);
        }
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
            java.util.logging.Logger.getLogger(Experience2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Experience2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Experience2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Experience2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
               Experience2 ep= new Experience2(null,null);
                ep.setVisible(true);
                ep.setLocationRelativeTo(null);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.Canvas canvas1;
    private javax.swing.JPanel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel son;
    private javax.swing.JPanel video;
    // End of variables declaration//GEN-END:variables

 

  
}
