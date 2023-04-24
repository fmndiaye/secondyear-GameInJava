package Projet;
import Projet.AzulJoker.*;
public class PlateauIncolor extends Plateau{

	public PlateauIncolor(){
		super();
		this.mur = new CaseMur[5][5];
		for (int i = 0; i < this.mur.length; i++){
			for (int j = 0; j < this.mur[i].length; j++){
				this.mur[i][j] = new CaseMur("Incolor");
			}
		}
	}

	public void addMur(int x, int y, Piece p){
		this.mur[x][y].element = p;
	}

	public boolean NotOnMur(String s, String str){
		if (!str.matches("\\d+")) 
			return false;
		for (int i = 0; i < this.mur.length; i++){
			if (this.mur[i][Integer.parseInt(str)].element != null){
				if (this.mur[i][Integer.parseInt(str)].element.couleur.equals(s))
					return false;
			}
		}
		return true;
	}

	public int diagonalScore(){
		return 0; //mur incolore donc pas de bonus placement diagonal
	}

}