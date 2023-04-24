package Projet;
import Projet.AzulJoker.*;
import java.util.ArrayList;
public class Joueur{
	protected String nom;
	protected Plateau plateau;
	protected int score;
	protected ArrayList<Piece> pioche;

	public Joueur(String n){
		this.nom = n;
		this.plateau = new Plateau();
		this.pioche = new ArrayList<Piece>();
		this.score = 0;
	}

	public Joueur(String n, String s){
		if (s.equals("Incolor")){
			this.plateau = new PlateauIncolor();
			this.nom = n;
			this.pioche = new ArrayList<Piece>();
			this.score = 0;
		} else {
			this.nom = n;
			this.plateau = new Plateau();
			this.pioche = new ArrayList<Piece>();
			this.score = 0;
		}
	}

	public int getScore(){
		return this.score;
	}

	public Plateau getPlateau(){
		return this.plateau;
	}

	public void setPlateau(Plateau p){
		this.plateau = p;
	}

	public void addPioche(Piece p){
		this.pioche.add(p);
	}

	public void showPioche(){
		for (int i = 0; i < this.pioche.size(); i++){
			System.out.println(this.pioche.get(i));
		}
	}

	public boolean possede(String s){
		for (int i = 0; i < this.pioche.size(); i++){
			if (this.pioche.get(i).couleur == s)
			return true;
		}
		return false;
	}

	public String toString(){
		return "Nom: " + this.nom;
	}

	public void addScore(int x){
		this.score += x;
	}

	public void reduceScore(int x){
		this.score -= x;
	}
}