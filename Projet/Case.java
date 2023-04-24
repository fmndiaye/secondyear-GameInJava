package Projet;
import Projet.AzulJoker.*;
public class Case{
	protected Piece element;

	public Case(Piece p){
		this.element = p;
	}

	public Case(){
		this.element = null;
	}

	public void removeEle(){
		this.element = null;
	}

	public String toString(){
		if (this.element != null)
			return "[" + this.element.toString() + "]";
		else
			return "[vide]";
	}

}