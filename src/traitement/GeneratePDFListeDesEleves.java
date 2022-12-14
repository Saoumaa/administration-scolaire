/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traitement;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.awt.Desktop;
import java.io.File;
import scolaire.ihm.Maconnexion;

/**
 *
 * @author obama
 */
public class GeneratePDFListeDesEleves {
    
    InputStream flux = null;
    Connection cx = null; //declaration d'une connexion
    //String ressource = "src/com/reports/FAMILLES.jrxml";
//    String ressource = "src/Operation/listeProduits.jrxml";
    String ressource = "C:\\reports\\listeEleves.jrxml";
//    static String  pointDeChutte = "src/Operation/listeProduits.pdf";
    static String  pointDeChutte = "C:/reports/listeEleves.pdf";
    //static String  pointDeChutte = "src/com/reports/FAMILLES.pdf";

    public GeneratePDFListeDesEleves() //public static void main(String[] args)
    {
            try
            {
               //flux = this.getClass().getResourceAsStream(ressource);
//               DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//               cx = DriverManager.getConnection("jdbc:oracle:thin:@10.6.0.211:1521:orcl", "aurelesenan", "aurelesenan");
                cx = Maconnexion.seconnecter();
                // - Chargement et compilation du rapport
               //JasperDesign jasperDesign = JRXmlLoader.load("C:\\Documents and Settings\\Administrateur\\Bureau\\Etats\\TYPES.jrxml");
               JasperDesign jasperDesign = JRXmlLoader.load(ressource);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                // - Param????tres ???? envoyer au rapport
                Map parameters = new HashMap();
//                parameters.put("Titre", "Titre");

                // - Execution du rapport
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cx);

                // - Cr????ation du rapport au format PDF
                JasperExportManager.exportReportToPdfFile(jasperPrint, pointDeChutte);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Documents and Settings\\Administrateur\\Bureau\\Etats\\TYPES.pdf");
                cx.close();
            }
            catch (JRException e)
            {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
      }

   public static void open (String toOpen)
   {
        if (toOpen == null)
            throw new NullPointerException ();
        if (!Desktop.isDesktopSupported ())
            return;
        Desktop desktop = Desktop.getDesktop ();

        try
        {
            desktop.open (new File (toOpen));
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
  }

//    public static void main(String[] args)
//    {
//        GeneratePDFListeDesEleves myReport = new GeneratePDFListeDesEleves();
//        //GeneratePDFCool.open("C:\\Documents and Settings\\Administrateur\\Bureau\\Etats\\TYPES.pdf");
//        GeneratePDFListeDesEleves.open(pointDeChutte);
//    }
}
