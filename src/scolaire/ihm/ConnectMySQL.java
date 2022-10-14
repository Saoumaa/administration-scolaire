/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire.ihm;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author obama
 */
public class ConnectMySQL {

    public static Connection seconnecter() {
//         String pilote = "oracle.jdbc.driver.OracleDriver";
        String pilote = "org.postgresql.Driver";
        Connection connexion = null;
        try {
            Class.forName(pilote);
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@137.10.235.110:1521:reprix", "system", "repprix");
//              connexion = DriverManager.getConnection("jdbc:oracle:thin:@10.6.0.216:1521:reprix", "system", "repprix");
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "aurelesenan", "aurelesenan");
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@10.6.0.216:1521:orcl", "rsagbo", "rsagbo");
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@172.16.1.60:1521:orcl", "administrateur", "administrateur");
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@10.6.1.74:1521:orcl", "administrateur", "administrateur");
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/scolaire2_psg", "postgres", "root");
//            connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scolaire2", "scolaire2");
            System.out.println("ok");
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(this,"erreur: ");
            // return 0;
            e.printStackTrace();
        }
        return connexion;
    }

    public static void main(String[] args) {
        seconnecter();
    }

}
