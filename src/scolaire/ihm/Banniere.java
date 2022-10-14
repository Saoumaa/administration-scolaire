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
import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class Banniere extends JLabel {

	private static final long serialVersionUID = 3735654525346318621L;
	private final static String ESPACE = " ";
	
	private String texte;
	private int tailleTampon;
	private int position;
	private Animateur animateur;
	
	public Banniere(String texte, int tailleTampon){
		super();
		this.texte = texte;
		this.tailleTampon = tailleTampon;
		position=0;
		setBorder(BorderFactory.createEtchedBorder());
		this.animateur=new Animateur(this);
	}

	public void avancer(){
		if(position++>tailleTampon+texte.length()){
			position=0;
		}
		dessiner();
	}
	
	private void dessiner(){
		int init=0;
		if(tailleTampon-position<0){
			init=Math.min(position-tailleTampon, texte.length());
		}
		String debut=(texte.substring(init, Math.min(position, texte.length())));
		setText(espaces(Math.max(0, tailleTampon-position))+debut);
		repaint();
	}
	
	private static String espaces(int nombre){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<nombre; i++){
			sb.append(ESPACE);
		}
		return sb.toString();
	}
	
	public void start(){
		animateur.start();
	}
	
	
}
