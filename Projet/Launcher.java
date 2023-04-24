package Projet;
import Projet.AzulJoker.*;
import java.util.ArrayList;
public class Launcher{
	public static void main(String[]args){
		ArrayList<Piece> tuile = new ArrayList<Piece>();
		for (int i = 0; i < 100; i++){ //On créer les 100 tuiles de différente couleur
			if (i >= 0 && i < 20)
				tuile.add(new Piece("Bleu", "Tuile"));
			if (i >= 20 && i < 40)
				tuile.add(new Piece("Jaune", "Tuile"));
			if (i >= 40 && i < 60)
				tuile.add(new Piece("Rouge", "Tuile"));
			if (i >= 60 && i < 80)
				tuile.add(new Piece("Noir", "Tuile"));
			if (i >= 80 && i < 100)
				tuile.add(new Piece("Blanc", "Tuile"));
		}

		// test rapide
		/*for (int i = 0; i < 10; i++){
			if (i >= 0 && i < 2)
				tuile.add(new Piece("Bleu", "Tuile"));
			if (i >= 2 && i < 4)
				tuile.add(new Piece("Jaune", "Tuile"));
			if (i >= 4 && i < 6)
				tuile.add(new Piece("Rouge", "Tuile"));
			if (i >= 6 && i < 8)
				tuile.add(new Piece("Noir", "Tuile"));
			if (i >= 8 && i < 10)
				tuile.add(new Piece("Blanc", "Tuile"));
		}
		*/
		VueGeneral v = new VueGeneral(tuile);
		v.jouer();
	}
}