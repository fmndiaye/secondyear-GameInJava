package Projet;
import Projet.AzulJoker.*;
public class CaseMur extends Case{
	protected String couleur;

	public CaseMur(Piece p, String c){
		super(p);
		this.couleur = c;
	}	

	public CaseMur(String c){
		super(null);
		this.couleur = c;
	}

	public String toString(){
		if (this.element != null)
			return "[" + this.element.toString() + "]";
		else
			return "["+ this.couleur + " vide]";
	}
}