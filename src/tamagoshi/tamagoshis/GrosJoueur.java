package tamagoshi.tamagoshis;


public class GrosJoueur extends Tamagoshi {

	public GrosJoueur(String name) {
		super(name);
	}
	
	public boolean consommeEnergie(){
		fun--;
		return super.consommeEnergie();
	}

}
