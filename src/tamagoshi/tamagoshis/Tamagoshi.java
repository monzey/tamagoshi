package tamagoshi.tamagoshis;

import java.util.Random;

public class Tamagoshi {

	private String name;
	protected Random generateur;
	private int age,maxEnergy,maxFun;
	protected int fun;
	protected int energy;
	private static int lifeTime=10;
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
			parler("Tout va bien !");
		}
		else{
			parler(s+" !");
		}
	}

	private void parler(String phrase){
		System.out.println("\n\t"+name+" : \""+phrase+"\"");
	}

	public boolean mange(){	 //Exo 4
		if (energy < maxEnergy)
		{
			energy += generateur.nextInt(3)+1;
			parler("Merci !");
			return true;
		}
		else	
		{
			parler("je n'ai pas faim !!");
			return false;
		}
	}

	public boolean vieillit(){ 	//Exo 5
		age++;
		return age==getLifeTime();
	}

	public boolean consommeEnergie(){	//Exo 6
		energy--;
		if(energy<=0){
			parler("je suis KO: Arrrggh !");
			return false;
		}
		return true;
	}

	public boolean consommeFun(){	//Exo 6
		fun--;
		if(fun<=0){
			parler("snif : je fais une dépression, ciao!");
			return false;
		}
		return true;
	}
	/**
	 * @return Returns the age.
	 */
	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return Returns the lifeTime.
	 */
	public static int getLifeTime() {
		return lifeTime;
	}

	public boolean joue(){
		if (fun < maxFun){
			fun += generateur.nextInt(3)+1;
			parler("On se marre !");
			return true;
		}
		else{
			parler("laisse-moi tranquille, je bouquine !!");
			return false;
		}
	}

	public String toString(){
		return name+" : energy="+energy+", fun="+fun;
	}

	public boolean isAlive() {
		return fun>0 && energy>0;
	}

}
