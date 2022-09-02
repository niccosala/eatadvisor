package clienti;
import java.io.Serializable;
import condivisa.Ristorante;

/** Rappresenta un giudizio
 * @author Niccolò Sala
 * @version 1.0
*/
public class Giudizio implements Serializable {
	 
	private static final long serialVersionUID = 2585857206031794185L;
	
	private int numerico;
	private String testuale;
	private Cliente cliente;
	private Ristorante ristorante;
	
	/** Crea un giudizio.
	 * @param numerico Valore numerico (da 1 a 5) associato al giudizio.
	 * @param testuale Descrizione testuale associata ad un giudizio.
	 * @param cliente Cliente che esprime il giudizio.
	 * @param ristorante Ristorante al quale è riferito il giudizio.
	*/
	public Giudizio(int numerico, String testuale, Cliente cliente, Ristorante ristorante) {
		if (numerico >= 1 && numerico <= 5)
			this.numerico = numerico;
		else 
			throw new IllegalArgumentException("Valutazione numerica invalida");
		
		if (testuale.length() <= 256) 
			this.testuale = testuale;
		else 
			throw new IllegalArgumentException("Valutazione testuale troppo lunga");
		
		this.cliente = cliente;
		this.ristorante = ristorante;
	}
	
	/** Ritorna il valore numerico associato al giudizio.
	 * @return Un intero che rappresenta il valore numerico (da 1 a 5) associato al giudizio.
	*/
	public int getGiudizioNumerico() {
		return numerico;
	}
	
	/** Ritorna la descrizione testuale associata al giudizio.
	 * @return Una stringa che rappresenta la descrizione testuale associata al giudizio.
	*/
	public String getGiudizioTestuale() {
		return testuale;
	}
	
	/** Ritorna il cliente che esprime al giudizio.
	 * @return Un oggetto che rappresenta il cliente che esprime al giudizio.
	*/
	public Cliente getCliente() {
		return cliente;
	}
	
	/** Ritorna il ristorante a cui è riferito il giudizio.
	 * @return Un oggetto che rappresenta il ristorante a cui è riferito il giudizio.
	*/
	public Ristorante getRistorante() {
		return ristorante;
	}
	
	/** Ritorna tutti gli attributi cliente.
	 * @return Una stringa che rappresenta l'insieme degli attributi del giudizio.
	*/
	public String toString() {
		return "Ristorante: \n" + ristorante.toString() + "\n" +
				"Cliente: 	\n" + cliente.toString() + "\n" +
				"Valutazione: " + getGiudizioNumerico() + "/5 \n" +
				"Giudizio Testuale: " + getGiudizioTestuale() + "\n";
	}

}
