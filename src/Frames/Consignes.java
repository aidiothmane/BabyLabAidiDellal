/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import static Frames.Experience1.document;
import static Frames.Experience1.racine;
import babylabaididellal.LancerExp;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Config;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Aidi Othmane
 */
public class Consignes extends javax.swing.JFrame {

    /**
     * Creates new form Consignes
     */
    
     Element config;
    Config cfg=new Config();
    public LancerExp lancer;
    public Consignes(LancerExp l) {
        initComponents();
        jTextArea1.setEditable(false);
        this.lancer = l;
         try {
             config = getConf(l.getTypeExp(), l.getId());
         } catch (DataConversionException ex) {
             
         }
        initialiserCfg();
        
        jTextArea1.setText(cfg.getConsigne());
    }
public void initialiserCfg(){
    if(config!=null){
            

            
        if(config.getChildren().size() > 2){
                   cfg.setEmplacement(Integer.parseInt(config.getChildren().get(0).getChildren().get(0).getValue()));
          cfg.setFont(config.getChildren().get(0).getChildren().get(1).getValue());
            cfg.setCouleur(config.getChildren().get(0).getChildren().get(2).getValue());
            cfg.setTaille(Integer.parseInt(config.getChildren().get(0).getChildren().get(3).getValue()));
            cfg.setConsigne(config.getChildren().get(2).getChildren().get(0).getValue());
            cfg.setFont2(config.getChildren().get(2).getChildren().get(1).getValue());
            cfg.setCouleur2(config.getChildren().get(2).getChildren().get(2).getValue());
            cfg.setTaille3(Integer.parseInt(config.getChildren().get(2).getChildren().get(3).getValue()));
            cfg.setEcran(true);
            cfg.setCouleur3(config.getChildren().get(1).getChildren().get(0).getValue());
            cfg.setTypeDure(config.getChildren().get(1).getAttribute("type").getValue());
            if(cfg.getTypeDure().equals("fixe")){
                cfg.setDureeF(Integer.parseInt(config.getChildren().get(1).getChildren().get(1).getValue()));
                cfg.verifierDurer();
                cfg.setDureeA1(0);
                cfg.setDureeA2(0);
            }else{
                cfg.setDureeF(0);
                cfg.setDureeA1(Integer.parseInt(config.getChildren().get(1).getChildren().get(1).getChildren().get(0).getValue()));
                cfg.setDureeA2(Integer.parseInt(config.getChildren().get(1).getChildren().get(1).getChildren().get(1).getValue()));
                cfg.verifierDurer();
            }
               
        }else{
                 cfg.setEmplacement(Integer.parseInt(config.getChildren().get(0).getChildren().get(0).getValue()));
          cfg.setFont(config.getChildren().get(0).getChildren().get(1).getValue());
            cfg.setCouleur(config.getChildren().get(0).getChildren().get(2).getValue());
            cfg.setTaille(Integer.parseInt(config.getChildren().get(0).getChildren().get(3).getValue()));
            cfg.setConsigne(config.getChildren().get(1).getChildren().get(0).getValue());
            cfg.setFont2(config.getChildren().get(1).getChildren().get(1).getValue());
            cfg.setCouleur2(config.getChildren().get(1).getChildren().get(2).getValue());
            cfg.setTaille3(Integer.parseInt(config.getChildren().get(1).getChildren().get(3).getValue()));
            cfg.setEcran(true);
            cfg.setCouleur3(config.getChildren().get(1).getChildren().get(0).getValue());
               cfg.setEcran(false);
               cfg.setTypeDure(null);
               cfg.setDuree(0);
               cfg.setCouleur3(null);
               cfg.setTypeDure(null);
            }
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BabyLab");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Consignes");

        jButton1.setText("Commencer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(331, 331, 331)
                            .addComponent(jLabel1))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Experience1 exp = new Experience1(lancer,cfg);
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(Consignes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consignes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consignes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consignes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consignes(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
