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
import javax.swing.JOptionPane;
import scolaire.ihm.Maconnexion;

/**
 *
 * @author obama
 */
public class GeneratePDFCahierNotes {

    InputStream flux = null;
    Connection cx = null; //declaration d'une connexion

    String ressource = "C:\\reports\\cahierDeNotesTrimestre.jrxml";

    static String pointDeChutte = "C:\\reports\\cahierDeNotesTrimestre.pdf";

    public GeneratePDFCahierNotes(int ID_BULLETIN) //public static void main(String[] args)
    {

        try {
            cx = Maconnexion.seconnecter();

            JasperDesign jasperDesign = JRXmlLoader.load(ressource);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - ParamÃ¨tres Ã  envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("ID_BULLETIN", ID_BULLETIN);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, cx);

            // - CrÃ©ation du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, pointDeChutte);
            cx.close();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void open(String toOpen) {
        if (toOpen == null) {
            throw new NullPointerException();
        }
        if (!Desktop.isDesktopSupported()) {
            return;
        }
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.open(new File(toOpen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args)
//    {
//        GeneratePDFBilletin myReport = new GeneratePDFBilletin();
//        //GeneratePDFCool.open("C:\\Documents and Settings\\Administrateur\\Bureau\\Etats\\TYPES.pdf");
//        GeneratePDFBilletin.open(pointDeChutte);
//    }
//    
}
