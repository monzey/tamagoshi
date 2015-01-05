/**
 * 
 */
package tamagoshi.tamagoshis;

/**
 * @author fab
 *
 */
public class Lunatique extends Tamagoshi {

	/**
	 * @param name
	 */
	public Lunatique(String name) {
		super(name);
	}

	/**
	 * @see tamagoshi.tamagoshis.Tamagoshi#consommeEnergie()
	 */
	@Override
	public boolean consommeEnergie() {
		if(generateur.nextBoolean())
			energy--;
		return super.consommeEnergie();
	}

	/**
	 * @see tamagoshi.tamagoshis.Tamagoshi#consommeFun()
	 */
	@Override
	public boolean consommeFun() {
		if(generateur.nextBoolean())
			fun--;
		return super.consommeFun();
	}

}
