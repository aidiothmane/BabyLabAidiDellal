/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.QuestionCheckBox;
import model.QuestionCommentaire;
import model.QuestionEchelle;
import model.QuestionImage;
import model.QuestionRadioButton;
import model.Reponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author Aidi Othmane
 */
public class test extends javax.swing.JFrame {


    /**
     * Creates new form test
     */
    public Reponse reponse;
    public test(Reponse r) {
        initComponents();
    this.reponse=r;
      jTextArea1.setText(r.toString());
      jTextArea1.setEditable(false);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Imprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Retour au programme principal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Enregistrer dans un fichier excel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Relancer une autre expérience");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Résultats :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(43, 43, 43)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        BabyLab b = new BabyLab();
        b.setVisible(true);
        b.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            jTextArea1.print();
        } catch (PrinterException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
   


  
    
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("Résultats");
    sheet.setColumnWidth(0,50*256);
    sheet.setColumnWidth(1,50*256);
    sheet.setColumnWidth(2,24*256);
    sheet.setColumnWidth(3,16*256);
    sheet.setColumnWidth(4,16*256);
    HSSFCellStyle cellStyle = null;
    
    HSSFRow row2 = sheet.createRow(3);
     HSSFRow row0 = sheet.createRow(0);
    HSSFRow row1 = sheet.createRow(1);
    
    cellStyle = wb.createCellStyle();
    
cellStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
cellStyle.setBottomBorderColor(HSSFColor.RED.index);
cellStyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
cellStyle.setLeftBorderColor(HSSFColor.RED.index);
cellStyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
cellStyle.setRightBorderColor(HSSFColor.RED.index);
cellStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
cellStyle.setTopBorderColor(HSSFColor.RED.index);

    
   HSSFCell cc= row0.createCell(0, HSSFCell.CELL_TYPE_STRING);
   cc.setCellValue(new HSSFRichTextString("ID"));
   cc.setCellStyle(cellStyle);
   HSSFCell cc1 = row0.createCell(1, HSSFCell.CELL_TYPE_STRING);
   cc1.setCellValue(new HSSFRichTextString("Type Experience"));
   cc1.setCellStyle(cellStyle);
    row1.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getIdExp()));
    row1.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getTypeExp()));
    row2.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Url du Média"));
    row2.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Question"));
    row2.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Réponse"));
    row2.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Temps de réponse"));
    row2.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Nombre de Clicks"));
    row2.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Type de réponse"));
    int j=4;
    for(int i=0;i<reponse.getQuestions().size();i++){
        HSSFRow row3 = sheet.createRow(j);
        row3.createCell(0, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getQuestions().get(i).getUrl()));
        row3.createCell(1, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getQuestions().get(i).getQuestion()));
        row3.createCell(2, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getRespones().get(i)));
        row3.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getTmpsRepones().get(i)+" ms"));
        row3.createCell(4, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.getNbrClick().get(i)+""));
       if(reponse.getQuestions().get(i)instanceof QuestionEchelle) {
        String de,a;
                   QuestionEchelle q = (QuestionEchelle) reponse.getQuestions().get(i);
                   de=q.getDe()+"";
                   a=q.getA()+"";
               
        row3.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Echelle avec taille :"));
         row3.createCell(6, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(de));
          row3.createCell(7, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(a));
        
    }else if(reponse.getQuestions().get(i)instanceof QuestionCheckBox){
        row3.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("CheckBox"));
    }else if(reponse.getQuestions().get(i)instanceof QuestionCommentaire){
        row3.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Commentaire"));
    }else if(reponse.getQuestions().get(i)instanceof QuestionImage){
        row3.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("Image"));
    }else if(reponse.getQuestions().get(i)instanceof QuestionRadioButton){
        row3.createCell(5, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString("RadionButton"));
    }
       j++;
    }
    HSSFRow row4 = sheet.createRow(j);
    HSSFCell c =row4.createCell(2, HSSFCell.CELL_TYPE_STRING);
            c.setCellValue(new HSSFRichTextString("durée total:"));
            c.setCellStyle(cellStyle);
    row4.createCell(3, HSSFCell.CELL_TYPE_STRING).setCellValue(new HSSFRichTextString(reponse.sommeTmpsRep()+" ms"));
    FileOutputStream fileOut;
    try {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd HH mm ss");
        String texte_date = sdf.format(new Date());
      fileOut = new FileOutputStream("Resultat-"+reponse.getTypeExp()+"-"+reponse.getIdExp()+" "+texte_date+".xls");
      wb.write(fileOut);
      fileOut.close();  
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  
        JOptionPane.showMessageDialog(rootPane, "Fichier Excel Créé");

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        LancementExperience e = new LancementExperience();
        e.setVisible(true);
        e.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new test(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
