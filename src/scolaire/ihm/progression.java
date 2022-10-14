package scolaire.ihm;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 
public class progression implements ActionListener{
    JProgressBar progress;
    Thread monThread;
    JButton boutonCancel;
    int rapidite;
    JFrame cadre;
        public static void main(String[] args){
            progression prgs= new progression();
            prgs.go(50);
	}
	public void go(int rapid){
            rapidite=rapid;
            // Cr�ation de l'interface
            cadre = new JFrame("En cours d'ouverture");
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            cadre.setLocation((screen.width - cadre.getSize().width)/2,(screen.height - cadre.getSize().height)/2);
//            cadre.setIconImage(Toolkit.getDefaultToolkit().getImage("src/logoprix.gif"));
            JPanel panneau = new JPanel();
            JLabel texte = new JLabel("Ouverture du cahier des notes...");
            progress = new JProgressBar(0, 100); 
            boutonCancel=new JButton("Cancel");
            boutonCancel.addActionListener(this);
            panneau.add("Center", progress); 
            panneau.add("Center", texte); 
            panneau.add(boutonCancel);
            cadre.getContentPane().add(BorderLayout.CENTER, panneau);
            cadre.setSize(275,115);
            cadre.setVisible(true);
            cadre.setResizable(false);
            cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Cr�ation de thread
            monThread= new Thread(new MonRunnable());
            monThread.start();
	}
        public void actionPerformed(ActionEvent evt) {
            // TODO add your handling code here:
            if(evt.getSource()==boutonCancel){
                cadre.dispose();
                System.exit(0);
                }
        }
	public class MonRunnable  implements Runnable{
            public void run(){
                // on fait une boucle pour que la JProgressBar "avance"
//                 FnControle con=new FnControle();
                
                for (int j = 1; j < 100; j++){
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
//               con.setVisible(true);
                
            }
	}
} 