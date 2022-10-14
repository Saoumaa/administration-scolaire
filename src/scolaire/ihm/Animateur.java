/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scolaire.ihm;

/**
 *
 * @author Zoul
 */
public class Animateur extends Thread {

	private Banniere banniere;
	private boolean actif=true;

	public Animateur(Banniere banniere){
		this.banniere=banniere;
	}

	@Override
	public void run(){
		while(actif){
			try {
				Thread.sleep(100);
				banniere.avancer();
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        
}

