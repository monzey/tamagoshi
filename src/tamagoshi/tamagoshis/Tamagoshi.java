package tamagoshi.tamagoshis;

import java.util.Random;


import javax.swing.JLabel;
import tamagoshi.graphic.TamaJPanel;

/**
 * Classe représentant un Tamagoshi de façon purement logique
 * @author Maxime Bertrand
 *
 */
public class Tamagoshi {

	private String name;
	protected Random generateur;
	private int age,maxEnergy,maxFun;
	protected int fun;
	protected int energy;
	private static int lifeTime=10;
	
	private TamaJPanel tamaPanel;
	
	/**
	 * @param name le nom du tamagoshi
	 */
	public Tamagoshi(String name) {
		this.name = name;
		generateur=new Random();
		age=0;
		maxEnergy=generateur.nextInt(5)+5;
		maxFun=generateur.nextInt(5)+5;
		energy=generateur.nextInt(5)+3;
		fun=generateur.nextInt(5)+3;
		
		this.tamaPanel = new TamaJPanel();
		this.parle();
		this.tamaPanel.setTextAge(String.valueOf(this.age));
	}

	public void parle() //Exo 16
	{
		String s="";
		if (energy < 5)
			s+="je suis affamé";
		if(fun<5){
			if(! s.isEmpty())
				s+=" et ";
			s+="je m'ennuie à mourrir";
		}
		if(s.isEmpty()){
			this.tamaPanel.setTextHumeur(parler("Tout va bien !"));
		}
		else{
			this.tamaPanel.setTextHumeur(parler(s+" !"));
		}
	}

	private String parler(String phrase){
		return (phrase);
	}
	
	

	public boolean mange(){	 //Exo 4
		if (energy < maxEnergy)
		{
			energy += generateur.nextInt(3)+1;
			this.tamaPanel.setTextParle(parler("Merci !"));
			return true;
		}
		else	
		{
			this.tamaPanel.setTextParle(parler("je n'ai pas faim !!"));
			return false;
		}
	}

	public boolean vieillit(){ 	//Exo 5
		age++;
		this.tamaPanel.setTextAge(String.valueOf(this.age));
		return age==getLifeTime();
	}

	public boolean consommeEnergie(){	//Exo 6
		energy--;
		if(energy<=0){
			this.tamaPanel.setTextHumeur(parler("je suis KO: Arrrggh !"));
			return false;
		}
		return true;
	}

	public boolean consommeFun(){	//Exo 6
		fun--;
		if(fun<=0){
			this.tamaPanel.setTextHumeur(parler("snif : je fais une dépression, ciao!"));
			return false;
		}
		return true;
	}
	/**
	 * @return Retourne l'âge.
	 */
	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return Retourne la durée de vie.
	 */
	public static int getLifeTime() {
		return lifeTime;
	}

	public boolean joue(){
		if (fun < maxFun){
			fun += generateur.nextInt(3)+1;
			this.tamaPanel.setTextParle(parler("On se marre !"));
			return true;
		}
		else{
			this.tamaPanel.setTextParle(parler("laisse-moi tranquille, je bouquine !!"));
			return false;
		}
	}

	public String toString(){
		return name+" : energy="+energy+", fun="+fun;
	}

	public boolean isAlive() {
		return fun>0 && energy>0;
	}

	
	
	public TamaJPanel getPanel() {
		return this.tamaPanel;
	}

	public void resetParleLbl() {
		this.tamaPanel.setTextParle("");
	}

}
