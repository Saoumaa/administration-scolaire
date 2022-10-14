/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package scolaire.ihm;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 
/**
 *
 * @author Administrateur
 */
public class FermerProgress {
        JProgressBar progress;
    Thread monThread;
    int rapidite;
    JFrame cadre;
//        public static void main(String[] args){
//            FermerProgress prgs= new FermerProgress();
//            prgs.go(50);
//	}
	public void go(int rapid){
            rapidite=rapid;
            // Cr�ation de l'interface
            cadre = new JFrame("Fermeture des cahiers de notes ...");
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            cadre.setLocation((screen.width - cadre.getSize().width)/2,(screen.height - cadre.getSize().height)/2);
//            cadre.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logoprix.gif"));
            JPanel panneau = new JPanel();
            JLabel texte = new JLabel("Arrêt des notes encours ... ");
            progress = new JProgressBar(0, 100); 
            panneau.add("Center", progress); 
            panneau.add("Center", texte); 
            cadre.getContentPane().add(BorderLayout.CENTER, panneau);
            cadre.setSize(275,115);
            cadre.setVisible(true);
            cadre.setResizable(false);
            cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Cr�ation de thread
            monThread= new Thread(new MonRunnable());
            monThread.start();
	}
	public class MonRunnable  implements Runnable{
            public void run(){
                // on fait une boucle pour que la JProgressBar "avance"
                for (int j = 100; j >=1; j--){
                    progress.setValue(j);
                    progress.setString(j+" %");
                    progress.setStringPainted(true);
                    try{
                        Thread.sleep(rapidite);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
		}
                //on ferme le cadre (le chargement est fini!)
		cadre.dispose();              
            }
	}
}
