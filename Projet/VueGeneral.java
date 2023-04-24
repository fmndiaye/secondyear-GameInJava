package Projet;
import Projet.AzulJoker.*;
import java.util.Scanner;
import java.util.ArrayList;
public class VueGeneral{
	protected Jeu jeu;

	public VueGeneral(ArrayList<Piece> p){
		Scanner sc = new Scanner (System.in); //afin d'intéragir avec les joueurs en version textuelle
		String str = "";
		ArrayList<Joueur> a = new ArrayList<Joueur>();
		int x = 0;
		System.out.println("Voulez-vous jouer à Azul(1) ou Azul Incolor(2) ?");
		str = sc.nextLine();
		while (!str.equals("1") && !str.equals("2")){
			System.out.println("Veuillez choisir entre 1 ou 2 ! réessayer");
			str = sc.nextLine();
		}
		if (str.equals("1")){
			System.out.println("Lancement du jeu Azul ! à combien de joueurs voulez vous jouer ? (2, 3 ou 4)");
			str = sc.nextLine();
			while (!str.equals("2") && !str.equals("3") && !str.equals("4")){
				System.out.println("Veuillez choisir entre 2, 3 ou 4 ! réessayer");
				str = sc.nextLine();
			}
			x = Integer.parseInt(str);
			for (int i = 0; i < x; i++){
				System.out.println("Nom du joueur " + (i+1) + " :");
				str = sc.nextLine();
				a.add(new Joueur(str, "incolor"));
			}
			this.jeu = new Jeu(a, p, this);
		}

		if (str.equals("2")){
			System.out.println("Lancement du jeu Azul Incolore ! à combien de joueurs voulez vous jouer ? (2, 3 ou 4)");
			str = sc.nextLine();
			while (!str.equals("2") && !str.equals("3") && !str.equals("4")){
				System.out.println("Veuillez choisir entre 2, 3 ou 4 ! réessayer");
				str = sc.nextLine();
			}
			x = Integer.parseInt(str);
			for (int i = 0; i < x; i++){
				System.out.println("Nom du joueur " + (i+1) + " :");
				str = sc.nextLine();
				a.add(new Joueur(str, "Incolor"));
			}
			this.jeu = new Jeu(a, p, this);
		}

	/* else if (str.equals("3")){ code Joker(non fonctionnel)
			System.out.println("Lancement du jeu Azul Joker ! à combien de joueurs voulez vous jouer ? (2, 3 ou 4)");
			str = sc.nextLine();
			while (!str.equals("2") && !str.equals("3") && !str.equals("4")){
				System.out.println("Veuillez choisir entre 2, 3 ou 4 ! réessayer");
				str = sc.nextLine();
			}
			x = Integer.parseInt(str);
			for (int i = 0; i < x; i++){
				System.out.println("Nom du joueur " + (i+1) + " :");
				str = sc.nextLine();
				a.add(new Joueur(str));
			}
			if (x == 2){ //on remplace une tuile de chaque couleur par une tuile joker
				p.remove(19);
				p.add(new TuileJoker("Joker"));
				p.remove(39);
				p.add(new TuileJoker("Joker"));
				p.remove(59);
				p.add(new TuileJoker("Joker"));
				p.remove(79);
				p.add(new TuileJoker("Joker"));
				p.remove(99);
			}
			this.jeu = new Jeu(a, p, this);
			*/
	}

	public void offre(){
		this.jeu.preparation();
		Scanner sc = new Scanner (System.in); //afin d'intéragir avec les joueurs en version textuelle
		String str = "";
		int x = 0;
		int y = 0;
		int z = 0;
		int d = 0;
		String s = "";
		Joueur a = new Joueur("");
		while (!this.jeu.centre.isEmpty() || !this.jeu.AllFabEstVide()){
			a = this.jeu.joueurs.get(0);
			this.jeu.joueurs.set(0, this.jeu.joueurs.get(this.jeu.premier));
			this.jeu.joueurs.set(this.jeu.premier, a);
			for (int i = 0; i < this.jeu.joueurs.size(); i++){	
				if (this.jeu.centre.isEmpty() && this.jeu.AllFabEstVide()){//On vérifie si les fabriques et le centre sont tous vides, si c'est le cas on termine la manche
					System.out.println("Le centre et toutes les fabriques sont vide !");
					break;
				}
				System.out.println();
				if (i == 0){
					System.out.println(this.jeu.joueurs.get(i).nom + " est le premier joueur !");
					y = 0;
				}
				System.out.println();
				System.out.println("Tour du Joueur " + this.jeu.joueurs.get(i).nom);
				System.out.println();	
				System.out.println("Pièces du centre de la table:");
				this.jeu.AfficheCentre();
				System.out.println();
				System.out.println("Pièces des fabriques:");
				this.jeu.AfficheAllFabrique();
				System.out.println();
				System.out.println("Voulez vous piocher dans le centre ou dans l'une des fabriques ? taper 1 pour centre, 2 pour fabrique");
				str = sc.nextLine();
				if (!str.equals("1") && !str.equals("2")){
					while (!str.equals("1") && !str.equals("2")){
						System.out.println("Mauvais numéro ! Veuillez réessayer:");
						str = sc.nextLine();
					}
				}
				x = Integer.parseInt(str);
				if (x == 1 && this.jeu.centre.isEmpty()){ //si le centre est vide on pioche dans les fabriques
					System.out.println("Le centre est vide !");
					x = 2;
				}

				if (x == 2 && this.jeu.AllFabEstVide()){ //si toutes les fabrique sont vide on pioche au centre
					System.out.println("Toutes les fabriques sont vides !");
					x = 1;
				}

				if (x == 1){
					if (i != 0)
						y += 1;
					System.out.println("Centre:");
					this.jeu.AfficheCentre();
					System.out.println("Veuillez choisir une couleur au centre:");
					str = sc.nextLine();
					for (int j = 0; j < this.jeu.centre.size(); j++){
						if (this.jeu.centre.get(j).couleur.equals(str))
							z += 1;
						}

					if (z == 0){
						while(z < 1){
							System.out.println("Cette couleur n'est pas disponible au centre ! veuillez réessayer");
							str = sc.nextLine();
							for (int j = 0; j < this.jeu.centre.size(); j++){
								if (this.jeu.centre.get(j).couleur.equals(str))
									z += 1;
							}
						}
					}
				
					this.jeu.offreEtape1a(str, i);
					z = 0;
					
					/*for (int j = 0; j < this.jeu.centre.size(); j++){ //code ajouter pour Joker (non fonctionnel)
						if (this.jeu.centre.get(j).couleur.equals("Joker"))
							z += 1;
					}

					if (z != 0){
						System.out.println("Voulez-vous récupérer les pièces Joker disponible au centre ?");
						str = sc.nextLine();
						if (!str.equals("oui") && !str.equals("non")){
							while (!str.equals("oui") && !str.equals("non")){
								System.out.println("Veuillez répondre par oui ou par non ! réessayer:");
							str = sc.nextLine();
							}
						}
					}
					z = 0;
					if (str.equals("oui")){
						this.jeu.getJokerCentre(i);
					}
					*/

					System.out.println("Pioche du joueur:");
					this.jeu.joueurs.get(i).showPioche();
					System.out.println();
					if (y > 0 && this.jeu.premier != i){
						System.out.println("Vous êtes le premier joueur à avoir piocher au centre pour ce tour,");
						System.out.println("Vous serez donc le premier joueur du tour suivant");
						y = 0;
						this.jeu.newPremier(i); 
					}
				}

				if (x == 2){
					this.jeu.AfficheAllFabrique();
					System.out.println("Veuillez choisir une fabrique (donnez en le numéro):");
					str = sc.nextLine();
					if (!str.matches("\\d+") || !this.jeu.bonneTaille(str) || !this.jeu.FabEstVide(str)){ // (\\d+) test si str est un nombre
						while (!str.matches("\\d+") || !this.jeu.bonneTaille(str) || !this.jeu.FabEstVide(str)){
							System.out.println("Veuillez entrez un nombre compris entre 0 et " + (this.jeu.fabrique.length - 1) + " ou choisir une fabrique qui n'est pas vide !");
							str = sc.nextLine();
						}
					}
					d = Integer.parseInt(str);
					this.jeu.AfficheFabrique(d);
					System.out.println("Veuillez choisir une couleur:");
					str = sc.nextLine();
					for (int j = 0; j < this.jeu.fabrique[d].length; j++){
						if (this.jeu.fabrique[d][j] != null){
							if (this.jeu.fabrique[d][j].couleur.equals(str))
								z += 1;
						}
					}
					if (z == 0){
						while(z < 1){
							System.out.println("Cette couleur n'est pas disponible sur la fabrique ! veuillez réessayer");
							str = sc.nextLine();
							for (int j = 0; j < this.jeu.fabrique[d].length; j++){
								if (this.jeu.fabrique[d][j] != null){
									if (this.jeu.fabrique[d][j].couleur.equals(str))
										z += 1;
								}
							}
						}
					}
					this.jeu.offreEtape1b(str, i, d);
					z = 0;
					/* for (int j = 0; j < this.jeu.fabrique[d].length; j++){ //code ajouter pour Joker(non fonctionnel)
						if (this.jeu.fabrique[d][j].couleur.equals("Joker"))
							z += 1;
					}

					if (z != 0){
						System.out.println("Voulez-vous récupérer les pièces Joker disponible sur la fabrique ?");
						str = sc.nextLine();
						if (!str.equals("oui") && !str.equals("non")){
							while (!str.equals("oui") && !str.equals("non")){
								System.out.println("Veuillez répondre par oui ou par non ! réessayer:");
							str = sc.nextLine();
							}
						}
					}
					z = 0;
					if (str.equals("oui")){
						this.jeu.getJokerFab(i, d);
					}
					*/ 
					System.out.println("les pièces restante sont mit au centre de la table");
					this.jeu.FabAuCentre(d);
				}
				s = str;

				System.out.println("Vos pièces:");
				this.jeu.joueurs.get(i).showPioche();
				System.out.println();
				System.out.println("Vos lignes");
				this.jeu.joueurs.get(i).plateau.afficheLignes();
				System.out.println();
				System.out.println("Votre mur:");
				this.jeu.joueurs.get(i).plateau.afficheMur();
				System.out.println();
				System.out.println("Votre plancher");
				this.jeu.joueurs.get(i).plateau.showPlancher();
				System.out.println();
				System.out.println("Voulez-vous directement déposer toutes vos pièces sur votre plancher ? Répondez par 'oui' ou par 'non'");
				str = sc.nextLine();
				if (!str.equals("oui") && !str.equals("non")){
					while (!str.equals("oui") && !str.equals("non")){
						System.out.println("Veuillez répondre par oui ou par non ! réessayer:");
						str = sc.nextLine();
					}
				}
				if (str.equals("non")){
					System.out.println("Vos pièces:");
					this.jeu.joueurs.get(i).showPioche();
					System.out.println();
					System.out.println("Votre mur:");
					this.jeu.joueurs.get(i).plateau.afficheMur();
					System.out.println();
					System.out.println("Vos lignes:");
					this.jeu.joueurs.get(i).plateau.afficheLignes();
					System.out.println();
					System.out.println("Votre plancher");
					this.jeu.joueurs.get(i).plateau.showPlancher();
					System.out.println();
					System.out.println("Veuillez choisir une ligne où placer les pièces de cette couleur");
					str = sc.nextLine();
					while (!str.matches("\\d+") || !this.jeu.joueurs.get(i).plateau.Bounds(str) || !this.jeu.joueurs.get(i).plateau.GoodColor(s, str) || !this.jeu.joueurs.get(i).plateau.NotOnMur(s, str)){	
						if (str.equals("retour")){
							break;
						}
						System.out.println("Veuillez choisir une nombre allant de 0 à " + (this.jeu.joueurs.get(i).plateau.lignes.length - 1) + ", une ligne n'ayant pas de couleur différente ou n'ayant pas la même couleur sur votre mur!" );
						System.out.println("Taper 'retour' si vous avez changez d'avis et souhaitez mettre vos pièces sur votre plancher");
						str = sc.nextLine();
					}

					if (str.equals("retour")){
						str = "oui";
					} else {
						d = Integer.parseInt(str);
						this.jeu.offreEtape2(s, d, i);
						System.out.println("Vos lignes");
						this.jeu.joueurs.get(i).plateau.afficheLignes();
						System.out.println();
						System.out.println("Votre plancher:");
						this.jeu.joueurs.get(i).plateau.showPlancher();
						System.out.println("---");
					}
				}

				if (str.equals("oui")){
					this.jeu.remplirPlancher(i);
					System.out.println("Votre plancher:");
					this.jeu.joueurs.get(i).plateau.showPlancher();
				}
			}
		}
		System.out.println();
		System.out.println("Le centre et toutes les fabriques sont vide, fin manche !");
		System.out.println();
		this.decoration();
	}

	public void decoration(){
		System.out.println("Phase de décoration");
		for (int i = 0; i < this.jeu.joueurs.size(); i++){
			System.out.println("Joueur " + this.jeu.joueurs.get(i).nom);
			System.out.println("Vos lignes:");
			this.jeu.joueurs.get(i).plateau.afficheLignes();
			System.out.println("Votre mur:");
			this.jeu.joueurs.get(i).plateau.afficheMur();
			System.out.println();
			System.out.println("Décoration en cours...");
			this.jeu.deco(i);
			System.out.println();
			System.out.println("Vos lignes après décoration:");
			this.jeu.joueurs.get(i).plateau.afficheLignes();
			System.out.println("Votre mur après décoration:");
			this.jeu.joueurs.get(i).plateau.afficheMur();
			System.out.println("Votre plancher:");
			this.jeu.joueurs.get(i).plateau.showPlancher();
			System.out.println();
			System.out.println("Les pièces restante de même couleur que vos piece placé sur le mur sont envoyer dans la défausse");
			System.out.println("Score du joueur " + this.jeu.joueurs.get(i).nom + ": " + this.jeu.joueurs.get(i).getScore());
			System.out.println();
			System.out.println();
			if (this.jeu.isOver()){
				System.out.println("Un joueur a compléter une ligne de son mur ! fin de la partie !");
				System.out.println("Score finaux:");
				this.jeu.calculScore();
				for (int j = 0; j < this.jeu.joueurs.size(); j++){
					System.out.println("Score du joueur " + this.jeu.joueurs.get(j).nom + ": " + this.jeu.joueurs.get(j).getScore());
				}
				this.jeu.gagnant();
				break;
			}
		}
		System.out.println("Fin de la phase de décoration");
		if (!this.jeu.isOver()){
			System.out.println("-------------------------------------------------");
			System.out.println();
			System.out.println("Phase de préparation en cours...");

			this.offre();
		}
	}

	public void jouer(){
		while (!this.jeu.isOver()){
			this.offre();
		}
	}
}