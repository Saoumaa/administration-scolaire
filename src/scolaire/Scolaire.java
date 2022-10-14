/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire;

import javax.swing.UIManager;
import scolaire.ihm.ConnexionJFrame;

/**
 *
 * @author OBAMA
 */
public class Scolaire {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              // TODO code application logic here
        ConnexionJFrame con = new ConnexionJFrame();
        con.setLocationRelativeTo(null);
        con.setVisible(true);
//        Accueil a1 = new Accueil();
//        a1.setTitle("Scolaire");
//        a1.setVisible(true);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
