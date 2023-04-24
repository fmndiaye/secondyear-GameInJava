package Projet;
import Projet.AzulJoker.*;
public class Plateau{
	protected Case[][] lignes;
	protected CaseMur[][] mur;
	protected Case[] plancher;

	public Plateau(){
		this.lignes = new Case[5][];
		for (int i = 0; i < 5; i++){
			this.lignes[i] = new Case[i + 1];
		}

		for(int i = 0; i < this.lignes.length; i++){
			for (int j = 0; j < this.lignes[i].length; j++){
				this.lignes[i][j] = new Case();
			}
		}

		int x = 0;
		String[] a = {"Bleu", "Jaune", "Rouge", "Noir", "Blanc"};
		this.mur = new CaseMur[5][5];
		for (int i = 0; i < this.mur.length; i++){ //boucle pour remplir le mur comme indiqué sur l'image du sujet
			for (int j = 0; j < this.mur[i].length; j++){
				if (x == 5)
					x = 0;
				if (j == 0){
					if (i > 0){
						if (this.mur[i-1][4] != null)
							this.mur[i][j] = new CaseMur(this.mur[i-1][4].couleur);
					} else {
						this.mur[i][j] = new CaseMur(a[x]);
					    x++;	
					}
				} else {
					this.mur[i][j] = new CaseMur(a[x]);
					x++;
				}
			}
		}
		this.plancher = new Case[7];	
		for (int i = 0; i < this.plancher.length; i++){
			this.plancher[i] = new Case();
		}			
	}

	public void addLignes(int x, int y, Piece p){
		this.lignes[x][y].element = p;
	}

	public void addMur(int x, int y, Piece p){
		if (this.mur[x][y].couleur == p.couleur)
			this.mur[x][y].element = p;
	}

	public void addPlancher(Piece p){
		for (int i = 0; i < this.plancher.length; i++){
			if (this.plancher[i].element == null){
				this.plancher[i].element = p;	
				break;
			}
		}	
	}

	public void afficheLignes(){
		for (int i = 0; i < this.lignes.length; i++){
			System.out.print("Ligne " + i + ": ");
			for (int j = 0; j < this.lignes[i].length; j++){
					System.out.print(this.lignes[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void afficheMur(){
		for (int i = 0; i < this.mur.length; i++){
			System.out.print("Ligne du mur " + i + ": ");
			for (int j = 0; j < this.mur[i].length; j++){
				System.out.print(this.mur[i][j] + " ");
			}
			System.out.println();
		}
	}

	public boolean Bounds(String s){
		for (int i = 0; i < this.lignes.length; i++){
			if (s.equals(Integer.toString(i)))
				return true;
		}
		return false;
	}

	public boolean GoodColor(String s, String str){
		if (!str.matches("\\d+"))
			return false;
		for (int i = 0; i < this.lignes[Integer.parseInt(str)].length; i++){
			if (this.lignes[Integer.parseInt(str)][i].element != null){
				if (!this.lignes[Integer.parseInt(str)][i].element.couleur.equals(s))
					return false;
			}
		}
		return true;
	}

	public boolean NotOnMur(String s, String str){
		if (!str.matches("\\d+")) 
			return false;
		for (int i = 0; i < this.mur[Integer.parseInt(str)].length; i++){
			if (this.mur[Integer.parseInt(str)][i].element != null){
				if (this.mur[Integer.parseInt(str)][i].element.couleur.equals(s))
					return false;
			}
		}
		return true;
	}

	public boolean PlancherPlein(){
		for (int i = 0; i < this.plancher.length; i++){
			if (this.plancher[i].element == null)
				return false;
		}
		return true;
	}

	public void showPlancher(){
		for (int i = 0; i < this.plancher.length; i++){
			System.out.print(this.plancher[i] + " ");
		}
		System.out.println();
	}

	public boolean ligneComplete(int x){
		for (int i = 0; i < this.lignes[x].length; i++){
			if (this.lignes[x][i].element == null)
				return false;
		}
		return true;
	}
	
	public boolean ligneMurComplete(int x){
		for (int i = 0; i < this.mur[x].length; i++){
			if (this.mur[x][i].element == null)
				return false;
		}
		return true;
	}

	public int horizontalScore(int x){
		int y = 0;
		for (int i = 0; i < this.mur[x].length - 1; i++){
			if (this.mur[x][i].element == null)
				y += 1;
		}
		if (y == 0) 
			return 2; //une ligne complete vaut 2 points
		return 0;

	}

	public int verticalScore(int x){
		int y = 0;
		for (int i = 0; i < this.mur.length - 1; i++){
			if (this.mur[i][x].element == null || this.mur[i][x].element.couleur.equals("Joker"))
				y += 1;
		}
		if (y == 0)
			return 7; //une colonne complete vaut 2 points
		return 0;
	}

	public int diagonalScoreColor(String s){
		boolean b = true;
		outerloop:
		for (int i = 0; i < this.mur.length; i++){
			for (int j = 0; j < this.mur[i].length; j++){
				if (this.mur[i][j].couleur.equals(s) && this.mur[i][j].element == null /*|| this.mur[i][j].element.couleur.equals("Joker") code Joker (non fonctionnel)*/){
					b = false;
					break outerloop;
				}
			}
		}
		if (b == true)
			return 10; //une diagonal complete d'une couleur vaut 10 points
		return 0;
	}

	public int diagonalScore(){
		return diagonalScoreColor("Bleu") + diagonalScoreColor("Jaune") + diagonalScoreColor("Rouge") + diagonalScoreColor("Noir") + diagonalScoreColor("Blanc");
	}

	public int plancherScore(){
		int x = 0;
		for (int i = 0; i < this.plancher.length; i++){
			if (this.plancher[i].element != null){
				if (i == 0 || i == 1)
					x -= 1;
				if (i == 2 || i == 3 || i == 4)
					x -= 2;
				if (i == 5 || i == 6)
					x -= 3;
			}
		}
		return x;
	}
}