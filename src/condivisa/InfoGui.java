package condivisa;

import java.awt.Dimension;
import java.awt.Toolkit;

/** Classe che mette a disposizione funzioni per la gestione della GUI rispetto alle dimensioni dello schermo
 * @author Niccolò Sala
 * @version 1.0
*/
public class InfoGui {
	
	private static Dimension schermo = Toolkit.getDefaultToolkit().getScreenSize();
	private static int altezza = (int)schermo.getHeight(); 
	private static int larghezza = (int)schermo.getWidth();
	
	/** Ritorna l'altezza dello schermo.
	 * @return Un intero che rappresenta l'altezza dello schermo.
	*/
	public static int getAltezza() {
		return altezza;
	}
	
	/** Ritorna la larghezza dello schermo.
	 * @return Un intero che rappresentala larghezza dello schermo.
	*/
	public static int getLarghezza() {
		return larghezza;
	}
	
	/** Ritorna un punto percentuale dell'altezza dello schermo.
	 * @return Un intero che rappresenta un punto percentuale dell'altezza dello schermo.
	*/
	public static int getUnitaAltezza() {
		return altezza / 100;
	}
	
	/** Ritorna un punto percentuale della larghezza dello schermo.
	 * @return Un intero che rappresenta un punto percentuale della larghezza dello schermo.
	*/
	public static int getUnitaLarghezza() {
		return larghezza / 100;
	}
	
	/** Ritorna lo spazio ai lati (sinistra e destra) tra schermo e finestra.
	 * @return Un intero che rappresenta lo spazio ai lati tra schermo e finestra.
	*/
	public static int getSpazioEO(int unita) {
		return (larghezza - (unita * getUnitaLarghezza())) / 2;
	}
	
	/** Ritorna lo spazio ai lati (sopra e sotto) tra schermo e finestra.
	 * @return Un intero che rappresenta lo spazio ai lati tra schermo e finestra.
	*/
	public static int getSpazioNS(int unita) {
		return (altezza - (unita * getUnitaAltezza())) / 2;
	}

}
