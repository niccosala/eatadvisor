package condivisa;

import clienti.Cliente;
import clienti.Giudizio;
import condivisa.Indirizzo.Qualificatore;
import condivisa.Ristorante.Tipologia;

/** Classe che carica i dati di default nei file
 * @author Niccolò Sala
 * @version 1.0
*/
public class CaricaInformazioni {
	
	private static AzioneFile f_ut = new AzioneFile("Utenti");
	private static AzioneFile f_dat = new AzioneFile("EatAdvisor");
	
	/** Carica i dati di default nei file
	*/
	public static void caricaPreset() {
		
		Cliente c1 = new Cliente("Mario", "Rossi", "mario.rossi@gmail.com", "marro", "pw", "Milano", "MI");
		Cliente c2 = new Cliente("Stefano", "Milani", "stemila@gmail.com", "ste", "ilmila", "Bollate", "MI");
		Cliente c3 = new Cliente("Niccolò", "Sala", "nikline9@gmail.com", "nikline", "a", "Arese", "MI");
		Cliente c4 = new Cliente("Alessandro", "Marini", "marini@libero.it", "alemar", "ale", "Monza", "MB");
		Cliente c5 = new Cliente("Simone", "Cagliari", "caglia@yahoo.it", "simoo97", "pw", "Varese", "VA");
		Cliente c6 = new Cliente("Andrea", "Alona", "alona@libero.it", "a", "a", "Monza", "MB");
		Cliente c7 = new Cliente("Alessandra", "Marini", "marini@libero.it", "alemar", "ale", "Monza", "MB");
		Cliente c8 = new Cliente("Martina", "Galletta", "marty.galle01@gmail.com", "lamarty", "pw", "Torino", "TO");
		Cliente c9 = new Cliente("Luisa", "Serena", "lui.sere@yahoo.it", "luisaaa", "pw", "Viserbella", "RM");
		Cliente c10 = new Cliente("Giula", "Collina", "giulia2000@gmail.com", "giulia_collina", "giu", "Genova", "GE");
		
		Indirizzo i1 = new Indirizzo(Qualificatore.Via, "Gramsci", 4, "Lainate", "MI", 20020);
		Indirizzo i2 = new Indirizzo(Qualificatore.Viale, "Dei Giardini", 15, "Lainate", "MI", 20020);
		Indirizzo i3 = new Indirizzo(Qualificatore.Viale, "Dei Visconti", 49, "Bologna", "BO", 40121);
		Indirizzo i4 = new Indirizzo(Qualificatore.Via, "Dante", 8, "Lainate", "MI", 20020);
		Indirizzo i5 = new Indirizzo(Qualificatore.Via, "Roma", 51, "Rho", "MI", 20017);
		Indirizzo i6 = new Indirizzo(Qualificatore.Via, "Giulio Cesare", 10, "Rho", "MI", 20020);
		Indirizzo i7 = new Indirizzo(Qualificatore.Corso, "Garibaldi", 113, "Varese", "VA", 21100);
		Indirizzo i8 = new Indirizzo(Qualificatore.Piazza, "Italia", 21, "Varese", "VA", 21100);
		Indirizzo i9 = new Indirizzo(Qualificatore.Piazzale, "Loreto", 210, "Milano", "MI", 20019);
		Indirizzo i10 = new Indirizzo(Qualificatore.Corso, "San Martino", 14, "Genova", "GE", 16121);
		
		Ristorante r1 = new Ristorante("Burger 2 Trip", "burger2trip.it", "0299011234", i1, Tipologia.Italiano);
		Ristorante r2 = new Ristorante("Pizzeria Stella", "pizzastella.it", "0234453211", i2, Tipologia.Italiano);
		Ristorante r3 = new Ristorante("Jolly", "jolly.com", "0511896565", i3, Tipologia.Fusion);
		Ristorante r4 = new Ristorante("Penglai", "penglai.altervista.org", "029353016", i4, Tipologia.Etnico);
		Ristorante r5 = new Ristorante("Locanda Stefania", "locandaste.it", "0299543321", i5, Tipologia.Etnico);
		Ristorante r6 = new Ristorante("Sosta e Gusta", "sostaegusta.it", "0218081821", i6, Tipologia.Fusion);
		Ristorante r7 = new Ristorante("Sushi and Go", "sushingo.it", "0332561877", i7, Tipologia.Etnico);
		Ristorante r8 = new Ristorante("Sapori di casa", "saporidicasa.it", "0332119800", i8, Tipologia.Italiano);
		Ristorante r9 = new Ristorante("La mangiatoia", "lamangiatoia.com", "0288661093", i9, Tipologia.Fusion);
		Ristorante r10 = new Ristorante("Perbacco", "perbacco.altervista.org", "0101056890", i10, Tipologia.Italiano);
		
		f_ut.scrivi(c1, false);
		f_ut.scrivi(c2, false);
		f_ut.scrivi(c3, false);
		f_ut.scrivi(c4, false);
		f_ut.scrivi(c5, false);
		f_ut.scrivi(c6, false);
		f_ut.scrivi(c7, false);
		f_ut.scrivi(c8, false);
		f_ut.scrivi(c9, false);
		f_ut.scrivi(c10, false);

		Giudizio g1 = new Giudizio(5, "Ottimi panini, sfiziosi. Buon prezzo e personale educato. Lo consiglio!!", c1, r1);
		Giudizio g2 = new Giudizio(3, "Servizio mediocre, ma il cibo è buono.", c2, r2);
		Giudizio g3 = new Giudizio(5, "Uno dei migliori ristoranti della zona, con prezzi moderati, ottimo cibo e camerieri cortesi. Assolutamente consigliato", c3, r3);
		Giudizio g4 = new Giudizio(4, "Ottimo cibo e ottimo servizio, un po' bui forse...", c4, r4);
		Giudizio g5 = new Giudizio(2, "Cibo buono, ma personale davvero scortese e maleducato. Da non tornarci assolutamente", c5, r5);
		Giudizio g6 = new Giudizio(1, "Sconsigliatissimo... ho trovato un capello nel piatto e ho chiesto di farlo cambiare, ho aggiunto appositamente del sale al piatto e... indovinate? Quando me l'hanno riportato secondo loro appena fatto... il piatto era salatissimo!!", c6, r6);
		Giudizio g7 = new Giudizio(2, "Pesce di bassa qualità", c7, r7);
		Giudizio g8 = new Giudizio(3, "Il cibo non sa di nulla... non mi spiego il motivo del nome del ristorante... almeno i camerieri sono educati", c8, r8);
		Giudizio g9 = new Giudizio(4, "Ristorante rustico ma dai sapori intensi. Lo consiglio", c9, r9);
		Giudizio g10 = new Giudizio(3, "Calda accoglienza, cibo nella media", c10, r10);
		Giudizio g11 = new Giudizio(3, "Ottimi hamburger, ma le patatine un po' meno", c9, r1);
		Giudizio g12 = new Giudizio(4, "Panini molto buoni e posto piccolo ma carino :)", c8, r1);
		Giudizio g13 = new Giudizio(4, "Ottima pizzeria per andare in compagnia e ottimi prezzi!!", c6, r2);
		Giudizio g14 = new Giudizio(3, "Camerieri molto simpatici, ma un po' lenti nel servizio. Cibo buono", c5, r4);
		Giudizio g15 = new Giudizio(4, "Dopo il cambio di gestione decisamente meglio. Lo consiglio", c4, r6);
		Giudizio g16 = new Giudizio(3, "Nella media", c3, r6);
		Giudizio g17 = new Giudizio(1, "Qualità molto scarsa, gestione disorganizzata e conto salato. Mai più", c3, r7);
		Giudizio g18 = new Giudizio(3, "Me l'avevano consigliato ma pensavo meglio. Mmmhh...", c3, r9);
		Giudizio g19 = new Giudizio(4, "Locale carino e confortevole, buon cibo", c1, r9);
		Giudizio g20 = new Giudizio(3, "Bella la posizione sul mare, cibo nella media.", c1, r10);

		f_dat.scrivi(r1, false);
		f_dat.scrivi(r2, false);
		f_dat.scrivi(r3, false);
		f_dat.scrivi(r4, false);
		f_dat.scrivi(r5, false);
		f_dat.scrivi(r6, false);
		f_dat.scrivi(r7, false);
		f_dat.scrivi(r8, false);
		f_dat.scrivi(r9, false);
		f_dat.scrivi(r10, false);
		
		f_dat.scrivi(g1, false);
		f_dat.scrivi(g2, false);
		f_dat.scrivi(g3, false);
		f_dat.scrivi(g4, false);
		f_dat.scrivi(g5, false);
		f_dat.scrivi(g6, false);
		f_dat.scrivi(g7, false);
		f_dat.scrivi(g8, false);
		f_dat.scrivi(g9, false);
		f_dat.scrivi(g10, false);
		f_dat.scrivi(g11, false);
		f_dat.scrivi(g12, false);
		f_dat.scrivi(g13, false);
		f_dat.scrivi(g14, false);
		f_dat.scrivi(g15, false);
		f_dat.scrivi(g16, false);
		f_dat.scrivi(g17, false);
		f_dat.scrivi(g18, false);
		f_dat.scrivi(g19, false);
		f_dat.scrivi(g20, false);
		
	}

}
