/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire.ihm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author obama
 */
public class ConDB {
    
    public static Connection connecterDB() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Drive OKi");
            String url = "jdbc:postgresql://localhost:5432/scolaire";
            String user = "scolaire";
            String password = "scolaire";
            Connection cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion bien établie");
            return cnx;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here

        try {
            Connection cnx = connecterDB();
            Statement st;
            ResultSet rst;
            st = cnx.createStatement();

//            String req = "select * from serie";
            System.out.println("juste avant la requette");
            st.executeQuery("select * from serie");
            System.out.println("juste avant le while");
//            while (rst.next()) {
////                System.out.print(rst.getInt("id_annee_scolaire"));
////                System.out.print(rst.getString("annee_debut"));
//                System.out.print(rst.getString("annee_fin"));
//                System.out.println();
//            }
        } catch (Exception e) {
        }
    }
}
