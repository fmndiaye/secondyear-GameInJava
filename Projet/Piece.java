package Projet;
import Projet.AzulJoker.*;
public class Piece{
	protected String nom;
	protected String couleur;

	public Piece(String c, String n){
		this.nom = n;
		this.couleur = c;
	}

	public String toString(){
		if (this.nom != null)
			return this.nom + ": " + this.couleur;
		return " vide";
	}
}