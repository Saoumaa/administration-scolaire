/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire.ihm;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author obama
 */
public class TrimestreJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AnneeJFrame
     */
    public TrimestreJFrame() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("log.png")));
        affichetableAnnee();
//        affichetableTrimestre();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelEntete = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelChamps = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        anneeTable = new javax.swing.JTable();
        jPanelTrimestre = new javax.swing.JPanel();
        jPanelChamps1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        trimestreComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jButtonSupprimer1 = new javax.swing.JButton();
        jButtonFermer1 = new javax.swing.JButton();
        jButtonEnregistrer1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        trimestreTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelEntete.setBackground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TRIMESTRES ");

        javax.swing.GroupLayout jPanelEnteteLayout = new javax.swing.GroupLayout(jPanelEntete);
        jPanelEntete.setLayout(jPanelEnteteLayout);
        jPanelEnteteLayout.setHorizontalGroup(
            jPanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnteteLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelEnteteLayout.setVerticalGroup(
            jPanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnteteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelChamps.setBackground(new java.awt.Color(204, 255, 204));
        jPanelChamps.setBorder(javax.swing.BorderFactory.createTitledBorder("Année Scolaire"));

        anneeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Code", "Début", "Fin"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        anneeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anneeTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(anneeTable);

        javax.swing.GroupLayout jPanelChampsLayout = new javax.swing.GroupLayout(jPanelChamps);
        jPanelChamps.setLayout(jPanelChampsLayout);
        jPanelChampsLayout.setHorizontalGroup(
            jPanelChampsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChampsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(250, 250, 250))
        );
        jPanelChampsLayout.setVerticalGroup(
            jPanelChampsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChampsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelChamps1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Libellé");

        trimestreComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1er Trimestre", "2ème Trimestre", "3ème Trimestre", "4ème Trimestre" }));

        javax.swing.GroupLayout jPanelChamps1Layout = new javax.swing.GroupLayout(jPanelChamps1);
        jPanelChamps1.setLayout(jPanelChamps1Layout);
        jPanelChamps1Layout.setHorizontalGroup(
            jPanelChamps1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChamps1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(trimestreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelChamps1Layout.setVerticalGroup(
            jPanelChamps1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChamps1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelChamps1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(trimestreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButtonSupprimer1.setText("Supprimer");
        jButtonSupprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimer1ActionPerformed(evt);
            }
        });

        jButtonFermer1.setText("Fermer");
        jButtonFermer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonFermer1MouseClicked(evt);
            }
        });
        jButtonFermer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFermer1ActionPerformed(evt);
            }
        });

        jButtonEnregistrer1.setText("Enregistrer");
        jButtonEnregistrer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnregistrer1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonEnregistrer1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSupprimer1)
                .addGap(18, 18, 18)
                .addComponent(jButtonFermer1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonSupprimer1)
                .addComponent(jButtonFermer1)
                .addComponent(jButtonEnregistrer1))
        );

        trimestreTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Code", "Libellé"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        trimestreTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(trimestreTable);
        trimestreTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanelTrimestreLayout = new javax.swing.GroupLayout(jPanelTrimestre);
        jPanelTrimestre.setLayout(jPanelTrimestreLayout);
        jPanelTrimestreLayout.setHorizontalGroup(
            jPanelTrimestreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTrimestreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelTrimestreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelTrimestreLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanelChamps1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelTrimestreLayout.setVerticalGroup(
            jPanelTrimestreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrimestreLayout.createSequentialGroup()
                .addComponent(jPanelChamps1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelChamps, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelTrimestre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelEntete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelEntete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelChamps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTrimestre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFermer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFermer1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonFermer1MouseClicked

    private void jButtonFermer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFermer1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonFermer1ActionPerformed

    private void jButtonEnregistrer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnregistrer1ActionPerformed
        // TODO add your handling code here:
        // Récupération de l'id de l'année depuis la table anneeTable
        int ligne = anneeTable.getSelectedRow();
        Object idAnneeObj = anneeTable.getValueAt(ligne, 0);
        String idAnneeStr = String.valueOf(idAnneeObj);
        int idAnnee = Integer.parseInt(idAnneeStr);
        String trimestre = trimestreComboBox.getSelectedItem().toString();
        if (!controleDoublonLibTrimestreByAnnee(trimestre, idAnnee)) {
            String req = "insert into TRIMESTRE (ID_TRIMES, LIB_TRIMES,ID_ANNEE_SCOLAIRE) values (1,'" + trimestre + "','" + idAnnee + "')";
            try {
                //Class.forName(pilote);
                Connection connexion = Maconnexion.seconnecter();
                Statement instruction = connexion.createStatement();
                int Conf = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment enregistrer ce trimestre ?");
                if (Conf == 0) {
                    instruction.executeQuery(req);
                    JOptionPane.showMessageDialog(this, "Enregistrement effectué avec succès");
                    affichetableTrimestre(idAnnee);
                } else {
                    JOptionPane.showMessageDialog(this, "Enregistrement annulé");
                }
                connexion.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "erreur: " + e);

            }
        } else {
            JOptionPane.showMessageDialog(this, "Il ne peut avoir deux fois le même trimestre dans la même année ");
        }
    }//GEN-LAST:event_jButtonEnregistrer1ActionPerformed

    private void jButtonSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimer1ActionPerformed
        // TODO add your handling code here:
        // Récupération de l'id de l'année depuis la table anneeTable
        int ligneAnnee = anneeTable.getSelectedRow();
        Object idAnneeObj = anneeTable.getValueAt(ligneAnnee, 0);
        String idAnneeStr = String.valueOf(idAnneeObj);
        int idAnnee = Integer.parseInt(idAnneeStr);
        // Récupération de l'id de l'année depuis la table anneeTable
        int ligne = trimestreTable.getSelectedRow();
        Object idTrimesObj = trimestreTable.getValueAt(ligne, 0);
        String idTrimesStr = String.valueOf(idTrimesObj);
        int idTrimes = Integer.parseInt(idTrimesStr);
        String req = "delete from TRIMESTRE where  ID_TRIMES='" + idTrimes + "'";
        try {
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
            int Conf = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce trimestre?");
            if (Conf == 0) {
                instruction.executeQuery(req);
                affichetableTrimestre(idAnnee);
                JOptionPane.showMessageDialog(this, "Suppression effectuée avec succès");
            } else {
                JOptionPane.showMessageDialog(this, "Suppression annulée");
            }

            connexion.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Impossible de supprimer ce Trimestre il est associé à d'autres enregistrements");
        }

    }//GEN-LAST:event_jButtonSupprimer1ActionPerformed

    private void anneeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anneeTableMouseClicked
        // TODO add your handling code here:
        // Récupération de l'id de l'année depuis la table anneeTable
        int ligneAnnee = anneeTable.getSelectedRow();
        Object idAnneeObj = anneeTable.getValueAt(ligneAnnee, 0);
        String idAnneeStr = String.valueOf(idAnneeObj);
        int idAnnee = Integer.parseInt(idAnneeStr);
        affichetableTrimestre(idAnnee);
    }//GEN-LAST:event_anneeTableMouseClicked

    //méthode pour l'affichge de anneeTable'
    private void affichetableAnnee() {
        DefaultTableModel model = new DefaultTableModel();
        anneeTable.setModel(model); //affectation du model au tableau
        model.addColumn("Code");
        model.addColumn("Début");
        model.addColumn("Fin");
        TableColumn column, column1, column2;
        column = anneeTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(90); //
        column1 = anneeTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(100); //
        column2 = anneeTable.getColumnModel().getColumn(2);
        column2.setPreferredWidth(100); //
        try {
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
            ResultSet resultat = instruction.executeQuery("select * from ANNEE_SCOLAIRE");
            while (resultat.next()) {
                int codeAnnnee = resultat.getInt("ID_ANNEE_SCOLAIRE");
                int anneeDebut = resultat.getInt("ANNEE_DEBUT");
                int anneeFin = resultat.getInt("ANNEE_FIN");
                model.addRow(new Object[]{codeAnnnee, anneeDebut, anneeFin});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erreur affichetableAnnee: " + e);
        }
    }

    //méthode pour l'affichge de anneeTable'
    private void affichetableTrimestre(int idAnnee) {
        DefaultTableModel model = new DefaultTableModel();
        trimestreTable.setModel(model); //affectation du model au tableau
        model.addColumn("Code");
        model.addColumn("Libellé");
        TableColumn column, column1 = null;
        column = trimestreTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(90); //
        column1 = trimestreTable.getColumnModel().getColumn(1);
        column1.setPreferredWidth(300); //

        try {
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
            ResultSet resultat = instruction.executeQuery("select * from TRIMESTRE where ID_ANNEE_SCOLAIRE ='" + idAnnee + "'");
//            int i = 0;
            while (resultat.next()) {
                int codeTrim = resultat.getInt("ID_TRIMES");
                String libTrim = resultat.getString("LIB_TRIMES");
                model.addRow(new Object[]{codeTrim, libTrim});
//                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erreur affichetableTrimestre: " + e);
        }
    }

    public boolean controleDoublonLibTrimestreByAnnee(String libTrim, int idAnnee) {
        String libTrimestreCourant;
        boolean doublon = false;
        String reqRameneGpEleve = "select  LIB_TRIMES  from  TRIMESTRE "
                + " where ID_ANNEE_SCOLAIRE = '" + idAnnee + "'";
        try {
            try (Connection connexion = Maconnexion.seconnecter();
                    Statement instruction = connexion.createStatement()) {
                ResultSet lesEleve = instruction.executeQuery(reqRameneGpEleve);
                while (lesEleve.next()) {
                    libTrimestreCourant = lesEleve.getString("MATRICULE_EL");
                    if (libTrimestreCourant.equalsIgnoreCase(libTrim)) {
                        doublon = true;
                        JOptionPane.showMessageDialog(this, " doublon ! ");
                    } else {
                        JOptionPane.showMessageDialog(this, " pas de doublon ! ");
                    }
                }
            }
        } catch (Exception e) {
        }
        return doublon;
    }

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
            java.util.logging.Logger.getLogger(TrimestreJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrimestreJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrimestreJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrimestreJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrimestreJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable anneeTable;
    private javax.swing.JButton jButtonEnregistrer1;
    private javax.swing.JButton jButtonFermer1;
    private javax.swing.JButton jButtonSupprimer1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelChamps;
    private javax.swing.JPanel jPanelChamps1;
    private javax.swing.JPanel jPanelEntete;
    private javax.swing.JPanel jPanelTrimestre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox trimestreComboBox;
    private javax.swing.JTable trimestreTable;
    // End of variables declaration//GEN-END:variables
}