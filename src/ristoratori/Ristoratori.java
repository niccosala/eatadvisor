/** ...
 *
 * @author Niccolò Sala 
 * @author 742545, Varese
 * 
*/

package ristoratori;

import java.awt.EventQueue;

import condivisa.CaricaInformazioni;

/** Classe che contiene il main e avvia la GUI dell'applicazione ristoratori
 * @author Niccolò Sala
 * @version 1.0
*/
public class Ristoratori {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiRistoratori finestra = new GuiRistoratori();
					finestra.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		CaricaInformazioni.caricaPreset();
		
	}

}
