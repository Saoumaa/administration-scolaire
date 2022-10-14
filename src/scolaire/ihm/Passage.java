/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire.ihm;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author obama
 */
public class Passage extends javax.swing.JFrame {

    /**
     * Creates new form AnneeJFrame
     */
    public Passage() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("log.png")));
        compliment();
    }

    private void compliment() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        btn_voir.setEnabled(false);
        connectComboClasse();
        connectComboAnnee();
        /*
         String anneePrecSelected = comboAnneePrec.getSelectedItem().toString();
         String classePrecSelected = comboClassePrec.getSelectedItem().toString().trim();
         int idGpPrec = returnIdGroupPedagByLibGp(classePrecSelected);
         int idAnneePrec = returnIdAnneeByAnneeDebut(anneePrecSelected.substring(0, 4));
         affichetableElevePrec(idGpPrec, idAnneePrec);

         String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
         String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
         int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
         int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));
         affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
         */
    }

    //méthode permettant de remplir le combo Année Préc
    private void connectComboAnnee() {
        comboAnneePrec.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        try {
            try (Connection connexion = Maconnexion.seconnecter();
                    Statement instruction = connexion.createStatement()) {
                ResultSet resultat = instruction.executeQuery("SELECT * FROM ANNEE_SCOLAIRE");
                while (resultat.next()) {
                    String anneeDebut = resultat.getString("ANNEE_DEBUT");
                    String anneeFin = resultat.getString("ANNEE_FIN");
                    String libAnnee = anneeDebut + " - " + anneeFin;
                    Object obj = (Object) libAnnee;
                    comboAnneePrec.addItem(obj);
                    comboAnneeSuiv.addItem(obj);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erreur: " + e);
        }
    }

    //méthode permettant de remplir le combo classe
    private void connectComboClasse() {
        comboClassePrec.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        try {
            try (Connection connexion = Maconnexion.seconnecter()) {
                Statement instruction = connexion.createStatement();
                ResultSet resultatGp = instruction.executeQuery("SELECT LIB_GROUP_PEDAG FROM GROUP_PEDAG");
                while (resultatGp.next()) {
                    String classe = resultatGp.getString("LIB_GROUP_PEDAG");
                    Object obj2 = (Object) classe;
                    comboClassePrec.addItem(obj2);
                    comboClasseSuiv.addItem(obj2);
                }
                //fermeture de la connexion
                instruction.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erreur: " + e);
        }
    }

    //méthode pour l'affichge du tableau Eleve'
    private void affichetableElevePrec(int idGp, int idAnnee) {
        DefaultTableModel model = new DefaultTableModel();
        eleveTablePrec.setModel(model); //affectation du model au tableau
//        JOptionPane.showMessageDialog(this, " A l'entrée de la méthode  affichetableEleve");
        model.addColumn("N°");
        model.addColumn("Matricule");
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Sexe");
        model.addColumn("Régime");
        model.addColumn("Statut");
        model.addColumn("N°");
//        model.addColumn("Inscrit(e) le");
        TableColumn column, column1, column2, column3, column4, column5, column6, column7 = null;
        column = eleveTablePrec.getColumnModel().getColumn(0);
        column.setPreferredWidth(10); //
        column1 = eleveTablePrec.getColumnModel().getColumn(1);
        column1.setPreferredWidth(100); //
        column2 = eleveTablePrec.getColumnModel().getColumn(2);
        column2.setPreferredWidth(200); //
        column3 = eleveTablePrec.getColumnModel().getColumn(3);
        column3.setPreferredWidth(200); //
        column4 = eleveTablePrec.getColumnModel().getColumn(4);
        column4.setPreferredWidth(40); //
        column5 = eleveTablePrec.getColumnModel().getColumn(5);
        column5.setPreferredWidth(70); //
        column6 = eleveTablePrec.getColumnModel().getColumn(6);
        column6.setPreferredWidth(70); //
        column7 = eleveTablePrec.getColumnModel().getColumn(7);
        column7.setPreferredWidth(10); //
//        column5 = eleveTableSuiv.getColumnModel().getColumn(5);
//        column5.setPreferredWidth(200); //
//        column6 = eleveTableSuiv.getColumnModel().getColumn(6);
//        column6.setPreferredWidth(40); //
//        column7 = eleveTableSuiv.getColumnModel().getColumn(7);
//        column7.setPreferredWidth(200); //
//        JOptionPane.showMessageDialog(this, " Juste avant le premier try ");
        try {
//            JOptionPane.showMessageDialog(this, " Juste après le premier try ");
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
//            ResultSet resultat = instruction.executeQuery("select ELEVE_GP.ID_ELEVE_GP,ELEVE_GP.ID_ELEVE ,MATRICULE_EL, NOM_EL, PRENOM_EL, SEXE,DATE_NAIS,LIEU_NAIS,DATE_INSCRI"
//                    + " from ELEVE, ELEVE_GP"
//                    + " where ELEVE_GP.ID_ELEVE = ELEVE.ID_ELEVE "
//                    + " and ELEVE_GP.ID_GROUP_PEDAG = '" + idGp + "'"
//                    + " and ELEVE_GP.ID_ANNEE_SCOLAIRE = '" + idAnnee + "'");

            ResultSet resultat = instruction.executeQuery("select ELEVE_GP.ID_ELEVE_GP,ELEVE_GP.ID_ELEVE ,MATRICULE_EL, NOM_EL, PRENOM_EL, SEXE, ELEVE_GP.REGIME_ELEVE, ELEVE_GP.STATUT_ELEVE"
                    + " from ELEVE, ELEVE_GP"
                    + " where ELEVE_GP.ID_ELEVE = ELEVE.ID_ELEVE "
                    + " and ELEVE_GP.ID_GROUP_PEDAG = '" + idGp + "'"
                    + " and ELEVE_GP.ID_ANNEE_SCOLAIRE = '" + idAnnee + "'");

//            int i = 0;                     + " and ELEVE_GP.ID_GROUP_PEDAG = GROUP_PEDAG.ID_GROUP_PEDAG"
//            JOptionPane.showMessageDialog(this, " Juste avant le while (resultat.next())");
            while (resultat.next()) {
//                JOptionPane.showMessageDialog(this, " Juste après le while (resultat.next())");
                String idEl = resultat.getString("ID_ELEVE");
                String mleEl = resultat.getString("MATRICULE_EL");
                String nomEl = resultat.getString("NOM_EL");
                String prenomEl = resultat.getString("PRENOM_EL");
                String sexeEl = resultat.getString("SEXE");
                String regimeEl = resultat.getString("REGIME_ELEVE");
                String statutEl = resultat.getString("STATUT_ELEVE");
                String idElGP = resultat.getString("ID_ELEVE_GP");
                model.addRow(new Object[]{idEl, mleEl, nomEl, prenomEl, sexeEl, regimeEl, statutEl, idElGP});
//                i++;
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, " Aucun élève inscrit dans cette classe " + e);
        }
    }

    //méthode pour l'affichge du tableau Eleve'
    private void affichetableEleveSuiv(int idGp, int idAnnee) {
        DefaultTableModel model = new DefaultTableModel();
        eleveTableSuiv.setModel(model); //affectation du model au tableau
//        JOptionPane.showMessageDialog(this, " A l'entrée de la méthode  affichetableEleve");
        model.addColumn("N°");
        model.addColumn("Matricule");
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Sexe");
        model.addColumn("Régime");
        model.addColumn("Statut");
        model.addColumn("N°");
//        model.addColumn("Inscrit(e) le");
        TableColumn column, column1, column2, column3, column4, column5, column6, column7 = null;
        column = eleveTableSuiv.getColumnModel().getColumn(0);
        column.setPreferredWidth(10); //
        column1 = eleveTableSuiv.getColumnModel().getColumn(1);
        column1.setPreferredWidth(100); //
        column2 = eleveTableSuiv.getColumnModel().getColumn(2);
        column2.setPreferredWidth(200); //
        column3 = eleveTableSuiv.getColumnModel().getColumn(3);
        column3.setPreferredWidth(200); //
        column4 = eleveTableSuiv.getColumnModel().getColumn(4);
        column4.setPreferredWidth(40); //
        column5 = eleveTableSuiv.getColumnModel().getColumn(5);
        column5.setPreferredWidth(70); //
        column6 = eleveTableSuiv.getColumnModel().getColumn(6);
        column6.setPreferredWidth(70); //
        column7 = eleveTableSuiv.getColumnModel().getColumn(7);
        column7.setPreferredWidth(10); //
        try {
//            JOptionPane.showMessageDialog(this, " Juste après le premier try ");
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();

            ResultSet resultat = instruction.executeQuery("select ELEVE_GP.ID_ELEVE_GP,ELEVE_GP.ID_ELEVE ,MATRICULE_EL, NOM_EL, PRENOM_EL, SEXE, ELEVE_GP.REGIME_ELEVE, ELEVE_GP.STATUT_ELEVE"
                    + " from ELEVE, ELEVE_GP"
                    + " where ELEVE_GP.ID_ELEVE = ELEVE.ID_ELEVE "
                    + " and ELEVE_GP.ID_GROUP_PEDAG = '" + idGp + "'"
                    + " and ELEVE_GP.ID_ANNEE_SCOLAIRE = '" + idAnnee + "'");

//            int i = 0;                     + " and ELEVE_GP.ID_GROUP_PEDAG = GROUP_PEDAG.ID_GROUP_PEDAG"
//            JOptionPane.showMessageDialog(this, " Juste avant le while (resultat.next())");
            while (resultat.next()) {
//                JOptionPane.showMessageDialog(this, " Juste après le while (resultat.next())");
                String idEl = resultat.getString("ID_ELEVE");
                String mleEl = resultat.getString("MATRICULE_EL");
                String nomEl = resultat.getString("NOM_EL");
                String prenomEl = resultat.getString("PRENOM_EL");
                String sexeEl = resultat.getString("SEXE");
                String regimeEl = resultat.getString("REGIME_ELEVE");
                String statutEl = resultat.getString("STATUT_ELEVE");
                String idElGP = resultat.getString("ID_ELEVE_GP");
                model.addRow(new Object[]{idEl, mleEl, nomEl, prenomEl, sexeEl, regimeEl, statutEl, idElGP});
//                i++;
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, " Aucun élève inscrit dans cette classe " + e);
        }
    }

    public int returnIdGroupPedagByLibGp(String libGp) {
        // Récupération de l'id du groupe pédagogique de l'élève sélectionné
        String reqRameneGpEleve = "select ID_GROUP_PEDAG  from  GROUP_PEDAG"
                + " where LIB_GROUP_PEDAG = '" + libGp + "' ";
        int idGp = 0;
        try {
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
            ResultSet idGpTrouve = instruction.executeQuery(reqRameneGpEleve);
            while (idGpTrouve.next()) {
                idGp = idGpTrouve.getInt("ID_GROUP_PEDAG");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "erreur returnIdGroupPedagByLibGp : " + e);
        }
        return idGp;
    }

    public int returnIdAnneeByAnneeDebut(String anneeDebut) {
        // Récupération de l'id de l'année à partir de son champ  ANNEE_DEBUT
        int idAnnee = 0;
        String reqRameneIdAnnee = "select ID_ANNEE_SCOLAIRE from ANNEE_SCOLAIRE where ANNEE_DEBUT = '" + anneeDebut + "'";
        try {
            Connection connexion = Maconnexion.seconnecter();
            Statement instruction = connexion.createStatement();
            ResultSet idAnneeTrouve = instruction.executeQuery(reqRameneIdAnnee);
            while (idAnneeTrouve.next()) {
                idAnnee = idAnneeTrouve.getInt("ID_ANNEE_SCOLAIRE");
            }
            //fermeture de la connexion 
            instruction.close();
            connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(this, "erreur: " + e);
        }
        return idAnnee;
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
        jPanel1 = new javax.swing.JPanel();
        jPanelChamps = new javax.swing.JPanel();
        jPanelBoutons4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboClassePrec = new javax.swing.JComboBox();
        comboAnneePrec = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        eleveTablePrec = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanelChamps3 = new javax.swing.JPanel();
        jPanelBoutons5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboClasseSuiv = new javax.swing.JComboBox();
        comboAnneeSuiv = new javax.swing.JComboBox();
        btn_voir1 = new javax.swing.JButton();
        jPanelBoutons6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        eleveTableSuiv = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelEntete.setBackground(new java.awt.Color(0, 102, 51));

        jLabel1.setFont(new java.awt.Font("Wide Latin", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PASSAGE EN CLASSE SUPERIEURE");

        javax.swing.GroupLayout jPanelEnteteLayout = new javax.swing.GroupLayout(jPanelEntete);
        jPanelEntete.setLayout(jPanelEnteteLayout);
        jPanelEnteteLayout.setHorizontalGroup(
            jPanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEnteteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelEnteteLayout.setVerticalGroup(
            jPanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEnteteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Passage en classe supérieure", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanelChamps.setBackground(new java.awt.Color(204, 255, 204));
        jPanelChamps.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Classe Précédente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanelBoutons4.setBackground(new java.awt.Color(204, 255, 204));
        jPanelBoutons4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paramètres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel2.setText("Année Scolaire");

        jLabel3.setText("Classe");

        comboClassePrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClassePrecActionPerformed(evt);
            }
        });

        comboAnneePrec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAnneePrecActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scolaire/ihm/icones/ic_voir.png"))); // NOI18N
        jButton3.setText("Afficher");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoutons4Layout = new javax.swing.GroupLayout(jPanelBoutons4);
        jPanelBoutons4.setLayout(jPanelBoutons4Layout);
        jPanelBoutons4Layout.setHorizontalGroup(
            jPanelBoutons4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutons4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboAnneePrec, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboClassePrec, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBoutons4Layout.setVerticalGroup(
            jPanelBoutons4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutons4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboClassePrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboAnneePrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3))
        );

        eleveTablePrec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Elève", "Matricule", "Nom", "Prénom", "Sexe", "Régime", "Statut", "N°Inscription"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eleveTablePrec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eleveTablePrecMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(eleveTablePrec);

        javax.swing.GroupLayout jPanelChampsLayout = new javax.swing.GroupLayout(jPanelChamps);
        jPanelChamps.setLayout(jPanelChampsLayout);
        jPanelChampsLayout.setHorizontalGroup(
            jPanelChampsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBoutons4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );
        jPanelChampsLayout.setVerticalGroup(
            jPanelChampsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChampsLayout.createSequentialGroup()
                .addComponent(jPanelBoutons4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scolaire/ihm/icones/ic_suivt.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanelChamps3.setBackground(new java.awt.Color(204, 255, 204));
        jPanelChamps3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Classe Suivante", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanelBoutons5.setBackground(new java.awt.Color(204, 255, 204));
        jPanelBoutons5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paramètres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 51))); // NOI18N

        jLabel4.setText("Année Scolaire");

        jLabel5.setText("Classe");

        comboClasseSuiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClasseSuivActionPerformed(evt);
            }
        });

        comboAnneeSuiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAnneeSuivActionPerformed(evt);
            }
        });

        btn_voir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scolaire/ihm/icones/ic_voir.png"))); // NOI18N
        btn_voir1.setIconTextGap(10);
        btn_voir1.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btn_voir1.setMaximumSize(new java.awt.Dimension(49, 20));
        btn_voir1.setMinimumSize(new java.awt.Dimension(49, 20));
        btn_voir1.setPreferredSize(new java.awt.Dimension(49, 20));
        btn_voir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_voir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoutons5Layout = new javax.swing.GroupLayout(jPanelBoutons5);
        jPanelBoutons5.setLayout(jPanelBoutons5Layout);
        jPanelBoutons5Layout.setHorizontalGroup(
            jPanelBoutons5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutons5Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboAnneeSuiv, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboClasseSuiv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_voir1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelBoutons5Layout.setVerticalGroup(
            jPanelBoutons5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutons5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboClasseSuiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboAnneeSuiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(btn_voir1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelBoutons6.setBackground(new java.awt.Color(204, 255, 204));
        jPanelBoutons6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 51))); // NOI18N

        jButton2.setText("Supprimer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Fermer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelBoutons6Layout = new javax.swing.GroupLayout(jPanelBoutons6);
        jPanelBoutons6.setLayout(jPanelBoutons6Layout);
        jPanelBoutons6Layout.setHorizontalGroup(
            jPanelBoutons6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBoutons6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanelBoutons6Layout.setVerticalGroup(
            jPanelBoutons6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBoutons6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelBoutons6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        eleveTableSuiv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°Elève", "Matricule", "Nom", "Prénom", "Sexe", "Régime", "Statut", "N°Inscription"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eleveTableSuiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eleveTableSuivMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(eleveTableSuiv);

        javax.swing.GroupLayout jPanelChamps3Layout = new javax.swing.GroupLayout(jPanelChamps3);
        jPanelChamps3.setLayout(jPanelChamps3Layout);
        jPanelChamps3Layout.setHorizontalGroup(
            jPanelChamps3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChamps3Layout.createSequentialGroup()
                .addComponent(jPanelBoutons5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBoutons6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2)
        );
        jPanelChamps3Layout.setVerticalGroup(
            jPanelChamps3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChamps3Layout.createSequentialGroup()
                .addGroup(jPanelChamps3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelBoutons5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBoutons6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelChamps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelChamps3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelChamps3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelChamps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(225, 225, 225))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelEntete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelEntete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eleveTablePrecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eleveTablePrecMouseClicked
        // TODO add your handling code here:
//        jButtonNouveau.setEnabled(true);
//        jButtonEnregistrer.setEnabled(false);
//        matriculeTextField.setText("");
        jButton3.setEnabled(true);
        idEleve = 0;
//        matricule = "";
//        nomEleve = "";
//        prenomEleve = "";
//        Sexe = "";
        regime = "INTERNE";
        statut = "PASSANT";
//        lieuNaisEleve = "";
        int ligne = eleveTablePrec.getSelectedRow();
        Object idEleveObj = eleveTablePrec.getValueAt(ligne, 0);
        if (idEleveObj != null) {
            String idEleveStr = idEleveObj.toString();
            idEleve = Integer.parseInt(idEleveStr);
        }
        Object matriculeObj = eleveTablePrec.getValueAt(ligne, 1);
        if (matriculeObj != null) {
//            matricule = matriculeObj.toString();
        }
        Object nomObj = eleveTablePrec.getValueAt(ligne, 2);
        if (nomObj != null) {
//            nomEleve = nomObj.toString();//String.valueOf(nomObj)
//            nomTextField.setText(nom);
        }
        Object prenomObj = eleveTablePrec.getValueAt(ligne, 3);
        if (prenomObj != null) {
//            prenomEleve = prenomObj.toString();//String.valueOf(prenomObj)
//            prenomTextField.setText(prenom);
        }
        Object sexeObj = eleveTablePrec.getValueAt(ligne, 4);
        if (sexeObj != null) {
//            Sexe = sexeObj.toString();//String.valueOf(sexeObj)
//            sexeComboBox.setSelectedItem(sexe);
        }

//        Object neLeObj = eleveTablePrec.getValueAt(ligne, 5);
//        if (neLeObj != null) {
//            String neLe = neLeObj.toString();
//        }
        Object neAObj = eleveTablePrec.getValueAt(ligne, 5);
        if (neAObj != null) {
//            lieuNaisEleve = neAObj.toString();
    }//GEN-LAST:event_eleveTablePrecMouseClicked

    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        // Contrôle de selection d'une ligne
        int lgneEleve = eleveTablePrec.getSelectedRow();
//        Object idEleveObj = eleveTablePrec.getValueAt(lgneEleve, 0);

        if (lgneEleve != -1) {

            String anneePrecSelected = comboAnneePrec.getSelectedItem().toString();
            String classePrecSelected = comboClassePrec.getSelectedItem().toString();
            int idGpPrec = returnIdGroupPedagByLibGp(classePrecSelected);
            int idAnneePrec = returnIdAnneeByAnneeDebut(anneePrecSelected.substring(0, 4));

            String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
            String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
            int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
            int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));

            // l'IdElve à passer en paramètre
            Object idElevObj = eleveTablePrec.getValueAt(lgneEleve, 0);
            String idElevStr = String.valueOf(idElevObj);
            int idElve = Integer.parseInt(idElevStr);

            // le Régime à passer en paramètre
            Object regElevObj = eleveTablePrec.getValueAt(lgneEleve, 5);
            String regElevStr = String.valueOf(regElevObj);

            // le Statut à passer en paramètre
            Object statElevObj = eleveTablePrec.getValueAt(lgneEleve, 6);
            String statElevStr = String.valueOf(statElevObj);

            if (idGpPrec != idGpSuiv) {
                ajouterEleveInGp(idElve, regElevStr, statElevStr, idGpSuiv, idAnneeSuiv);
                affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
                affichetableElevePrec(idGpPrec, idAnneePrec);
            } else {
                JOptionPane.showMessageDialog(this, "Vous ne pouvez inscrire un élève plus d'une fois dans la même classe ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas choisi un élève ");
        }

//        if (!controleDoublonEleveGpAnnee(idElve, idGpSuiv, idAnneeSuiv).isEmpty()) {
//            ajouterEleveInGp(idElve, regElevStr, statElevStr, idGpSuiv, idAnneeSuiv);
//            affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
//            affichetableElevePrec(idGpPrec, idAnneePrec);
//        } else {
//            JOptionPane.showMessageDialog(this, "Vous ne pouvez inscrire un élève plus d'une fois dans la classe ");
//        }
//        String req = "insert into ELEVE_GP (REGIME_ELEVE,ID_ANNEE_SCOLAIRE,ID_ELEVE,ID_GROUP_PEDAG) values ('" + regimeSelected + "','" + idAnnee + "','" + idElev + "','" + idGp + "')";
//        String req1 = "insert into ELEVE(ID_ELEVE,LIEU_NAIS,MATRICULE_EL,NOM_EL,PRENOM_EL,SEXE )values('" + ligne + "')";

    }//GEN-LAST:event_jButton4ActionPerformed

    private void eleveTableSuivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eleveTableSuivMouseClicked

    }//GEN-LAST:event_eleveTableSuivMouseClicked

    private void comboAnneePrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnneePrecActionPerformed
        // TODO add your handling code here:
        /*
         String classePrecSelected = comboClassePrec.getSelectedItem().toString();
         //        if (!classePrecSelected.isEmpty()) {
         String anneePrecSelected = comboAnneePrec.getSelectedItem().toString();
         int idGpPrec = returnIdGroupPedagByLibGp(classePrecSelected);
         int idAnneePrec = returnIdAnneeByAnneeDebut(anneePrecSelected.substring(0, 4));
         affichetableElevePrec(idGpPrec, idAnneePrec);
         */
//            JOptionPane.showMessageDialog(this, " L'année précédent est différent de null ");
//        }

    }//GEN-LAST:event_comboAnneePrecActionPerformed

    private void comboAnneeSuivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAnneeSuivActionPerformed
        // TODO add your handling code here:
        /*
         String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
         String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
         int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
         int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));
         affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
         */
    }//GEN-LAST:event_comboAnneeSuivActionPerformed

    private void comboClasseSuivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClasseSuivActionPerformed
        // TODO add your handling code here:
//        String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
//        String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
//        int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
//        int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));
//        affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
    }//GEN-LAST:event_comboClasseSuivActionPerformed

    private void comboClassePrecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClassePrecActionPerformed
        // TODO add your handling code here:

//        String classeSelected = comboClassePrec.getSelectedItem().toString();
//        int idGp = returnIdGroupPedagByLibGp(classeSelected);
//        String anneeSelected = comboClassePrec.getSelectedItem().toString();
//        int idAnnee = returnIdAnneeByAnneeDebut(anneeSelected.substring(0, 4));
//        
//        affichetableElevePrec(idGp, idAnnee);
    /*    
         String classePrecSelected = comboClassePrec.getSelectedItem().toString();
         JOptionPane.showMessageDialog(this, "classePrecSelected " + classePrecSelected);
         //        if (!classePrecSelected.equalsIgnoreCase("")) {
         String anneePrecSelected = comboAnneePrec.getSelectedItem().toString();
         int idGpPrec = returnIdGroupPedagByLibGp(classePrecSelected);
         JOptionPane.showMessageDialog(this, "idGpPrec " + idGpPrec);
         int idAnneePrec = returnIdAnneeByAnneeDebut(anneePrecSelected.substring(0, 4));
         JOptionPane.showMessageDialog(this, "idAnneePrec " + idAnneePrec);
         JOptionPane.showMessageDialog(this, "Avant affichetableElevePrec ");
         affichetableElevePrec(idGpPrec, idAnneePrec);
         JOptionPane.showMessageDialog(this, "Après affichetableElevePrec ");
         //        }
         */
    }//GEN-LAST:event_comboClassePrecActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
        int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
        String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
        int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));

        // Récupération de l'id de l'élève depuis la table eleveTable
        int ligne = eleveTableSuiv.getSelectedRow();
        if (ligne != -1) {
            Object idElevObj = eleveTableSuiv.getValueAt(ligne, 7);
            String idElevStr = String.valueOf(idElevObj);
            int idElev = Integer.parseInt(idElevStr);
            String req1 = "delete from ELEVE_GP"
                    + " where  ID_ELEVE_GP = '" + idElev + "'"
                    + " and  ID_ANNEE_SCOLAIRE ='" + idAnneeSuiv + "'"
                    + " and ID_GROUP_PEDAG ='" + idGpSuiv + "'";
            JOptionPane.showMessageDialog(this, " idElev " + idElev);
            JOptionPane.showMessageDialog(this, " idAnneeSuiv " + idAnneeSuiv);
            JOptionPane.showMessageDialog(this, " idGpSuiv " + idGpSuiv);
            try {
                Connection connexion = Maconnexion.seconnecter();
                Statement instruction = connexion.createStatement();
                int Conf = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ces informations? ");
                if (Conf == 0) {
                    instruction.executeQuery(req1);
                    JOptionPane.showMessageDialog(this, "Suppression effectuée avec succès ");
                    affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
                } else {
                    JOptionPane.showMessageDialog(this, "Suppression annulée ");
                }
                connexion.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Impossible de supprimer cette Inscription car elle est associée à d'autres enregistrements ");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vous n'avez pas choisi un élève ");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_voir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_voir1ActionPerformed
        // TODO add your handling code here:
        String anneeSuivSelected = comboAnneeSuiv.getSelectedItem().toString();
        String classeSuivSelected = comboClasseSuiv.getSelectedItem().toString();
        int idGpSuiv = returnIdGroupPedagByLibGp(classeSuivSelected);
        int idAnneeSuiv = returnIdAnneeByAnneeDebut(anneeSuivSelected.substring(0, 4));
        affichetableEleveSuiv(idGpSuiv, idAnneeSuiv);
    }//GEN-LAST:event_btn_voir1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String anneePrecSelected = comboAnneePrec.getSelectedItem().toString();
        String classePrecSelected = comboClassePrec.getSelectedItem().toString().trim();
        int idGpPrec = returnIdGroupPedagByLibGp(classePrecSelected);
        int idAnneePrec = returnIdAnneeByAnneeDebut(anneePrecSelected.substring(0, 4));
        affichetableElevePrec(idGpPrec, idAnneePrec);

    }//GEN-LAST:event_jButton3ActionPerformed
    int idEleve;
    String regime;
    String statut;

    public void ajouterEleveInGp(int idElev, String regime, String statut, int idGp, int idAnnee) {

        String req = "insert into ELEVE_GP (REGIME_ELEVE,STATUT_ELEVE,ID_ANNEE_SCOLAIRE,ID_ELEVE,ID_GROUP_PEDAG) values ('" + regime + "','" + statut + "','" + idAnnee + "','" + idElev + "','" + idGp + "')";
        try {
            try (Connection connexion = Maconnexion.seconnecter()) {
                Statement instruction = connexion.createStatement();
                instruction.executeQuery(req);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Erreur de ajouterEleveInGp  " + e);
        }
    }
    /*
     //méthode pour l'affichge du tableau Eleve'
     private void affichetableEleve(int idGp, int idAnnee) {
     DefaultTableModel model = new DefaultTableModel();
     eleveTableSuiv.setModel(model); //affectation du model au tableau
     //        JOptionPane.showMessageDialog(this, " A l'entrée de la méthode  affichetableEleve");
     model.addColumn("N°");
     model.addColumn("Matricule");
     model.addColumn("Nom");
     model.addColumn("Prénom");
     model.addColumn("Sexe");
     model.addColumn("Régime");
     model.addColumn("Statut");
     model.addColumn("N°");
     //        model.addColumn("Inscrit(e) le");
     TableColumn column, column1, column2, column3, column4, column5, column6, column7 = null;
     column = eleveTableSuiv.getColumnModel().getColumn(0);
     column.setPreferredWidth(10); //
     column1 = eleveTableSuiv.getColumnModel().getColumn(1);
     column1.setPreferredWidth(100); //
     column2 = eleveTableSuiv.getColumnModel().getColumn(2);
     column2.setPreferredWidth(200); //
     column3 = eleveTableSuiv.getColumnModel().getColumn(3);
     column3.setPreferredWidth(200); //
     column4 = eleveTableSuiv.getColumnModel().getColumn(4);
     column4.setPreferredWidth(40); //
     column5 = eleveTableSuiv.getColumnModel().getColumn(5);
     column5.setPreferredWidth(40); //
     column6 = eleveTableSuiv.getColumnModel().getColumn(6);
     column6.setPreferredWidth(40); //
     column7 = eleveTableSuiv.getColumnModel().getColumn(7);
     column7.setPreferredWidth(10); //
     //        column5 = eleveTableSuiv.getColumnModel().getColumn(5);
     //        column5.setPreferredWidth(200); //
     //        column6 = eleveTableSuiv.getColumnModel().getColumn(6);
     //        column6.setPreferredWidth(40); //
     //        column7 = eleveTableSuiv.getColumnModel().getColumn(7);
     //        column7.setPreferredWidth(200); //
     //        JOptionPane.showMessageDialog(this, " Juste avant le premier try ");
     try {
     //            JOptionPane.showMessageDialog(this, " Juste après le premier try ");
     Connection connexion = Maconnexion.seconnecter();
     Statement instruction = connexion.createStatement();
     ResultSet resultat = instruction.executeQuery("select ELEVE_GP.ID_ELEVE_GP,ELEVE_GP.ID_ELEVE ,MATRICULE_EL, NOM_EL, PRENOM_EL, SEXE, ELEVE_GP.REGIME_ELEVE, ELEVE_GP.STATUT_ELEVE"
     + " from ELEVE, ELEVE_GP"
     + " where ELEVE_GP.ID_ELEVE = ELEVE.ID_ELEVE "
     + " and ELEVE_GP.ID_GROUP_PEDAG = '" + idGp + "'"
     + " and ELEVE_GP.ID_ANNEE_SCOLAIRE = '" + idAnnee + "'");
     //            int i = 0;                     + " and ELEVE_GP.ID_GROUP_PEDAG = GROUP_PEDAG.ID_GROUP_PEDAG"
     //            JOptionPane.showMessageDialog(this, " Juste avant le while (resultat.next())");
     while (resultat.next()) {
     //                JOptionPane.showMessageDialog(this, " Juste après le while (resultat.next())");
     String idEl = resultat.getString("ID_ELEVE_GP");
     String mleEl = resultat.getString("MATRICULE_EL");
     String nomEl = resultat.getString("NOM_EL");
     //                JOptionPane.showMessageDialog(this, "MATRICULE_EL " + mleEl + "NOM_EL" + nomEl);
     String prenomEl = resultat.getString("PRENOM_EL");
     String sexeEl = resultat.getString("SEXE");
     String regimeEl = resultat.getString("REGIME_ELEVE");
     String statutEl = resultat.getString("STATUT_ELEVE");
     String idElGP = resultat.getString("ID_ELEVE_GP");
     model.addRow(new Object[]{idEl, mleEl, nomEl, prenomEl, sexeEl, regimeEl, statutEl, idElGP});
     //                i++;
     }
     } catch (HeadlessException | SQLException e) {
     JOptionPane.showMessageDialog(this, " Aucun élève inscrit dans cette classe " + e);
     }
     }
     */

    public String controleDoublonEleveGpAnnee(int idEleve, int idGp, int idAnnee) {
        String matriculeTrouvail = "";
        String reqRameneGpEleve = "select  ID_ELEVE_GP  from  ELEVE_GP "
                + "where ID_ANNEE_SCOLAIRE = '" + idAnnee + "'"
                + "and ID_ELEVE = '" + idEleve + "'"
                + "and ID_GROUP_PEDAG = '" + idGp + "'";
        try {
            try (Connection connexion = Maconnexion.seconnecter();
                    Statement instruction = connexion.createStatement()) {
                ResultSet lesEleve = instruction.executeQuery(reqRameneGpEleve);
                while (lesEleve.next()) {
                    matriculeTrouvail = lesEleve.getString("ID_ELEVE_GP");
                }
            }
        } catch (Exception e) {
            return "";
        }
        return matriculeTrouvail;
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
            java.util.logging.Logger.getLogger(Passage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Passage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Passage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Passage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Passage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_voir1;
    private javax.swing.JComboBox comboAnneePrec;
    private javax.swing.JComboBox comboAnneeSuiv;
    private javax.swing.JComboBox comboClassePrec;
    private javax.swing.JComboBox comboClasseSuiv;
    private javax.swing.JTable eleveTablePrec;
    private javax.swing.JTable eleveTableSuiv;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBoutons4;
    private javax.swing.JPanel jPanelBoutons5;
    private javax.swing.JPanel jPanelBoutons6;
    private javax.swing.JPanel jPanelChamps;
    private javax.swing.JPanel jPanelChamps3;
    private javax.swing.JPanel jPanelEntete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
