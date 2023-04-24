package Projet;
import Projet.AzulJoker.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Jeu implements Jeux{
	protected ArrayList<Joueur> joueurs;
	protected ArrayList<Piece> sac;
	protected ArrayList<Piece> defausse;
	protected Piece[][] fabrique;
	protected ArrayList<Piece> centre;
	protected int premier;
	protected VueGeneral Vue;

	public Jeu(ArrayList<Joueur> a, ArrayList<Piece> b, VueGeneral v){
		this.joueurs = a;
		this.sac = b;
		this.defausse = new ArrayList<Piece>();
		this.Vue = v;
		if (this.joueurs.size() == 2)
			this.fabrique = new Piece[5][4];
		if (this.joueurs.size() == 3)
			this.fabrique = new Piece[7][4];
		if (this.joueurs.size() == 4)
			this.fabrique = new Piece[9][4];
		this.centre = new ArrayList<Piece>();
		Random r = new Random();
		this.premier = r.nextInt(this.joueurs.size());
	}

	public void preparation(){
		int x = 0;
		Random r = new Random();
		outerloop:
		for (int i = 0; i < this.fabrique.length; i++){
			for (int j = 0; j < this.fabrique[i].length; j++){
				if (this.sac.isEmpty())
					this.remplir(); //si le sac est vide on le remplit avec les pièces de la défausse
				if (this.sac.isEmpty() && this.defausse.isEmpty())
					break outerloop; //on arrete la boucle exterieur si le sac et la défausse sont tous les deux vides
				if (this.sac.size() > 0){
					x = r.nextInt(this.sac.size());
					this.fabrique[i][j] = this.sac.get(x);
					this.sac.remove(x);
				}
			}
		}
	}

	public void remplir(){
		for (int i = 0; i < this.defausse.size(); i++){
			this.sac.add(this.defausse.get(i));
			this.defausse.remove(i);
			i--; //la fonction remove réduit la taille de 1 donc on réduit de 1 notre iterateur à l'index précédent
		}
	}


	public void offreEtape1a(String s, int i){ //le joueur récupère les pièces sélectionnées au centre
		for (int j = 0; j < this.centre.size(); j++){
			if (this.centre.get(j).couleur.equals(s)){
				this.joueurs.get(i).addPioche(this.centre.get(j));
				if (!this.centre.get(j).couleur.equals("Joker")){
					this.centre.remove(j);
					j--; //la fonction remove réduit la taille de 1 donc on réduit de 1 notre iterateur à l'index précédent
				}
			}
		}
	}

	public void offreEtape1b(String s, int i, int d){ //le joueur récupère les pièces sélectionnées dans la fabrique 
		for (int j = 0; j < this.fabrique[d].length; j++){
			if (this.fabrique[d][j] != null){
				if (this.fabrique[d][j].couleur.equals(s)){
					this.joueurs.get(i).addPioche(this.fabrique[d][j]);
					this.fabrique[d][j] = null;
				}
			}
		}
	}

	public void offreEtape2(String s, int d, int i){ //on pose les pièces sur les lignes en respectant les règles tout en placant le reste sur le plancher ou la défausse
		for (int z = 0; z < this.joueurs.get(i).pioche.size(); z++){
			if (this.joueurs.get(i).plateau.ligneComplete(d))
				this.remplirPlancher(i);	
			else {
				for (int j = 0; j < this.joueurs.get(i).plateau.lignes[d].length; j++){
					if (this.joueurs.get(i).plateau.lignes[d][j].element == null){
						for (int r = 0; r < this.joueurs.get(i).pioche.size(); r++){
							if (this.joueurs.get(i).pioche.get(r).couleur.equals(s)){
								this.joueurs.get(i).plateau.addLignes(d, j, this.joueurs.get(i).pioche.get(r));
								this.joueurs.get(i).pioche.remove(r);
								z--; //la fonction remove réduit la taille de 1 donc on réduit de 1 notre iterateur à l'index précédent
								break;
							}
						}
					}
				}
			}
		}
	}

/*	public void getJokerCentre(int i){
		for (int j = 0; j < this.centre.size(); j++){
			if (this.centre.get(j).couleur.equals("Joker")){
				this.joueurs.get(i).addPioche(this.centre.get(j));
				this.centre.remove(j);
				j--;
			}
		}
	}

	public void getJokerFab(int i, int d){
		for (int j = 0; j < this.fabrique[d].length; j++){
			if (this.fabrique[d][j] != null){
				if (this.fabrique[d][j].couleur.equals("Joker")){
					this.joueurs.get(i).addPioche(this.fabrique[d][j]);
					this.fabrique[d][j] = null;
				}
			}
		}
	}
*/
	public void remplirPlancher(int i){
		for (int r = 0; r < this.joueurs.get(i).pioche.size(); r++){
			if (this.joueurs.get(i).plateau.PlancherPlein()){
				this.defausse.add(this.joueurs.get(i).pioche.get(r));
				this.joueurs.get(i).pioche.remove(r);
				r--; //la fonction remove réduit la taille de 1 donc on réduit de 1 notre iterateur à l'index précédent
			} 
			else {
				this.joueurs.get(i).plateau.addPlancher(this.joueurs.get(i).pioche.get(r));
				this.joueurs.get(i).pioche.remove(r);
				r--; //la fonction remove réduit la taille de 1 donc on réduit de 1 notre iterateur à l'index précédent
			}
		}
	}

	public void newPremier(int i){
		this.premier = i;
		for (int j = 0; j < this.joueurs.get(i).plateau.plancher.length; j++){
			if (this.joueurs.get(i).plateau.plancher[j] == null)
				this.joueurs.get(i).plateau.addPlancher(new Piece("Premier", ""));
		}
	}

	public void FabAuCentre(int i){
		for (int j = 0; j < this.fabrique[i].length; j++){
			if (this.fabrique[i][j] != null){
				this.centre.add(this.fabrique[i][j]);
				this.fabrique[i][j] = null;
			}
		}
	}

	public void AfficheCentre(){
		if (this.centre.size() == 0){
			System.out.println("Rien");
		}
		else{
			for (int i = 0; i < this.centre.size(); i++){
				System.out.println(this.centre.get(i));
			}
		}
	}

	public void AfficheAllFabrique(){
		for (int i = 0; i < this.fabrique.length; i++){
			System.out.println("Fabrique " + i + ":");
			for (int j = 0; j < this.fabrique[i].length; j++){
				if (this.fabrique[i][j] == null)
					System.out.println("vide");
				else
					System.out.println(" " + this.fabrique[i][j]);
			}
		}
	}

	public void AfficheFabrique(int a){
		for (int i = 0; i < this.fabrique[a].length; i++){
			if (this.fabrique[a][i] == null)
				System.out.println("vide");
			else
				System.out.println(this.fabrique[a][i]);

		}
	}

	public boolean AllFabEstVide(){
		for (int i = 0; i < this.fabrique.length; i++){
			for (int j = 0; j < this.fabrique[i].length; j++){
				if (this.fabrique[i][j] != null){
					return false;
				}
			}
		}
		return true;
	}

	public boolean FabEstVide(String a){
		if (!a.matches("\\d+"))
			return false;
		for (int i = 0; i < this.fabrique[Integer.parseInt(a)].length; i++){
			if (this.fabrique[Integer.parseInt(a)][i] != null){
				return true;
			}
		}
		return false;
	}

	public boolean bonneTaille(String s){
		for (int i = 0; i < this.fabrique.length; i++){
			if (s.equals(Integer.toString(i)))
				return true;
		}
		return false;
	}

	public void deco(int x){
		for (int i = 0; i < this.joueurs.get(x).plateau.lignes.length; i++){
			if (this.joueurs.get(x).plateau.ligneComplete(i)){
				for (int r = 0; r < this.joueurs.get(x).plateau.mur[i].length; r++){
					if (this.joueurs.get(x).plateau.mur[i][r].couleur.equals(this.joueurs.get(x).plateau.lignes[i][0].element.couleur) || (this.joueurs.get(x).plateau.mur[i][r].couleur.equals("Incolor") && this.joueurs.get(x).plateau.mur[i][r].element == null)){
						this.joueurs.get(x).plateau.addMur(i, r, this.joueurs.get(x).plateau.lignes[i][0].element);
						this.joueurs.get(x).addScore(1);
						this.joueurs.get(x).plateau.lignes[i][0].element = null;
						if (r < 4){
							if (this.joueurs.get(x).plateau.mur[i][r+1] != null){ //on rajoute les points en fonctions des pièces voisines
								if (this.joueurs.get(x).plateau.mur[i][r+1].element != null)
									this.joueurs.get(x).addScore(1);
							}
						}
						if (r > 0){
							if (this.joueurs.get(x).plateau.mur[i][r-1] != null){
								if (this.joueurs.get(x).plateau.mur[i][r-1].element != null)
								this.joueurs.get(x).addScore(1);
							}
						}
						if (i < 4){
							if (this.joueurs.get(x).plateau.mur[i+1][r] != null){
								if (this.joueurs.get(x).plateau.mur[i+1][r].element != null)
									this.joueurs.get(x).addScore(1);
							}
						}
						if (i > 0){
							if (this.joueurs.get(x).plateau.mur[i-1][r] != null){
								if (this.joueurs.get(x).plateau.mur[i-1][r].element != null)
									this.joueurs.get(x).addScore(1);
							}
						}
						for (int z = 0; z < this.joueurs.get(x).plateau.lignes[i].length; z++){
							this.defausse.add(this.joueurs.get(x).plateau.lignes[i][z].element);
							this.joueurs.get(x).plateau.lignes[i][z].element = null;
						}
						break;
					}
				}
			}
		}
	}

	public boolean isOver(){
		for (int i = 0; i < this.joueurs.size(); i++){
			for (int j = 0; j < this.joueurs.get(i).plateau.mur.length; j++){
				if (this.joueurs.get(i).plateau.ligneMurComplete(j))
					return true;
			}
		}
		return false;
	}

	public void calculScore(){
		for (int i = 0; i < this.joueurs.size(); i++){
			for (int j = 0; j < this.joueurs.get(i).plateau.mur.length; j++){
				this.joueurs.get(i).addScore(this.joueurs.get(i).plateau.horizontalScore(j) + this.joueurs.get(i).plateau.verticalScore(j));
			}
			this.joueurs.get(i).addScore(this.joueurs.get(i).plateau.diagonalScore());
			this.joueurs.get(i).addScore(this.joueurs.get(i).plateau.plancherScore());
		}
	}

	public void gagnant(){
		int x = 0;
		int y = 0;
		for (int i = 0; i < this.joueurs.size(); i++){
			if (this.joueurs.get(i).getScore() > x){
				x = this.joueurs.get(i).getScore();
				y = i;
			}
		}
		System.out.println("Le joueur " + this.joueurs.get(y).nom + " gagne la partie avec un score de " + x + "!");
	}
}