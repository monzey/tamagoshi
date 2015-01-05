package tamagoshi.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

/**
 * un petit jeu avec des tamagoshis
 */
public class TamaGame {

	private ArrayList<Tamagoshi> listeTotale, alive;

	/**constructeur d'une instance du jeu*/
	public TamaGame() {
		listeTotale = new ArrayList<Tamagoshi>();
		alive = new ArrayList<Tamagoshi>();
		initialisation();
	}

	@SuppressWarnings("unchecked")
	private void initialisation(){
		System.out.println("Entrez le nombre de tamagoshis désiré !");
		int n = 0;
		while(n<1){
			System.out.println("Saisisez un nombre > 0 :");
			try {
				n = Integer.parseInt(Utilisateur.saisieClavier());
			} catch (NumberFormatException e) {
				System.out.println("Il faut saisir un nombre !");
			}
		}
		for(int i=0;i<n;i++){
			System.out.println("Entrez le nom du tamagoshi numéro "+i+" : ");
			if(Math.random()<.5)
				listeTotale.add(new GrosJoueur(Utilisateur.saisieClavier()));
			else
				listeTotale.add(new GrosMangeur(Utilisateur.saisieClavier()));

		}
		alive = (ArrayList<Tamagoshi>) listeTotale.clone();
		//ou encore pour le même résultat
		//		alive = new ArrayList<Tamagoshi>(listeTotale);
	}

	private double score(){
		int score=0;
		for(Tamagoshi t : listeTotale)
			score += t.getAge();
		return score*100/(Tamagoshi.getLifeTime()*listeTotale.size());
	}

	private void resultat(){
		System.out.println("-------------bilan------------");
		for(Tamagoshi t : listeTotale){
			String classe = t.getClass().getSimpleName();
			System.out.println(t.getName()+" qui était un "+classe+" "+(t.getAge()==Tamagoshi.getLifeTime()?" a survécu et vous remercie :)":" n'est pas arrivé au bout et ne vous félicite pas :("));
		}
		System.out.println("\nniveau de difficulté : "+listeTotale.size()+", score obtenu :"+score()+"%");
	}


	/**
	 * Cette méthode démarre le jeu sur une instance
	 */
	public void play(){
		int cycle=1;
		while(! alive.isEmpty())
		{
			System.out.println("\n------------Cycle n°"+(cycle++)+"-------------");
			for(Tamagoshi t : alive)
				t.parle();
			nourrirTamagoshi();
			jouerTamagoshi();
			for(Iterator<Tamagoshi> i=alive.iterator();i.hasNext();){
				Tamagoshi t = i.next();
				if(! t.consommeEnergie() || ! t.consommeFun() || t.vieillit())
					i.remove();
			}
		}
		System.out.println("\n\t--------- fin de partie !! ----------------\n\n");
		resultat();
	}

	private void nourrirTamagoshi(){
		alive.get(tamaSelection("\n\tNourrir quel tamagoshi ?")).mange();
	}

	private void jouerTamagoshi(){
		alive.get(tamaSelection("\n\tJouer avec quel tamagoshi ?")).joue();
	}

	/** renvoie l'index du tamagoshi sélectionné étant donnée une question
	 * @param question la question liée é la sélection
	 * @return le numéro du tamagoshi sélectionné
	 */
	private int tamaSelection(String question){
		System.out.println(question);
		int index=0;
		for (ListIterator<Tamagoshi> tamaIterator = alive.listIterator(); tamaIterator.hasNext();) {
			System.out.print("\t("+(tamaIterator.nextIndex())+") "+tamaIterator.next().getName()+"  ");
		}
		System.out.print("\n\tEntrez un choix : ");
		try {
			index = Integer.parseInt(Utilisateur.saisieClavier());
		} catch (NumberFormatException e) {
			System.out.println("Il faut saisir un nombre !");
			return tamaSelection(question);
		}
		if(index < 0 || index >= alive.size()){
			System.out.println("il n'y a pas de tamagoshi portant le numéro "+index);
			return tamaSelection(question);
		}
		return index;
	}

	/**Exécute le programme du jeu*/
	public static void main(String[] args) {
		TamaGame jeu = new TamaGame();
		jeu.play();
	}

}
