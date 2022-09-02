/** ...
 *
 * @author Niccolò Sala 
 * @author 742545, Varese
 * 
*/

package clienti;

import java.awt.EventQueue;

import condivisa.CaricaInformazioni;

/** Classe che contiene il main e avvia la GUI dell'applicazione clienti
 * @author Niccolò Sala
 * @version 1.0
*/
public class Clienti {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiAccesso finestra = new GuiAccesso();
					finestra.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		CaricaInformazioni.caricaPreset();
		
	}

}
