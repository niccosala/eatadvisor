package condivisa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/** Classe che mette a disposizione metodi per l'interazione con un file
 * @author Niccolò Sala
 * @version 1.0
*/
public class AzioneFile {
	
	private File f;
	private String nome;
	
	/** Classe che offre i metodi base per interagire con un file.
	 * @param nome Nome del file sul quale applicare i metodi.
	*/
	public AzioneFile(String nome) {
		File file;
		this.nome = nome;
		
		if(!nome.contains(".dati"))
			nome += ".dati"; 
		
		String path = System.getProperty("user.home") + File.separator + "EatAdvisor" + File.separator + nome;
		
		try {
			file = new File(path);
			file.getParentFile().mkdirs(); 
			file.createNewFile();
			this.f = file;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** Ritorna e se richieso stampa il contenuto del file.
	 * @param to_print Stampa il contenuto del file se vero, non stampare altrimenti.
	 * @return Lista di oggetti contenente gli oggetti contenuti nel file.
	*/
	public ArrayList<Object> leggi(boolean to_print) {
		if(!f.exists() || f.length()<1) {
			System.out.println("Il file non esiste o è vuoto");
			return null;
		}
		System.out.println("Il file esiste > Lettura");
		
		ArrayList <Object> list = new ArrayList<Object>();
		
		try {
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) 
				list.add(oi.readObject());

			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(to_print)
			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i).toString());
		
		return list;
	}

	/** Sovrascrive il contenuto del file.
	 * @param obj Lista di oggetti da inserire nel file.
	*/
	public void sovrascrivi(ArrayList<Object> obj) {
		if(!f.exists()) {
			System.out.println("Il file non esiste");
			return;
		}
		System.out.println("Il file esiste > Sovrascritturta");
		
		try {
			FileOutputStream fs = new FileOutputStream(f);
			ObjectOutputStream os = new ObjectOutputStream(fs);

			for (int i=0; i<obj.size(); i++)
				os.writeObject(obj.get(i));

			os.close();
			fs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Scrive nel file mantenendo il suo vecchio contenuto.
	 * @param obj Lista di oggetti da inserire nel file.
	 * @param repeat Vero se lo stesso oggetto può essere scritto più volte nel file, falso alrtimenti.
	 * @return Vero se la scrittura va a buon fine, falso altrimenti.
	*/
	public boolean scrivi(Object obj, boolean repeat) {
		if(!f.exists()) {
			System.out.println("Il file non esiste");
			return false;
		}
		System.out.println("Il file esiste > Scritturta");
		
		ArrayList<Object> keep = new ArrayList<Object>();
		if(f.length()>0) {
			keep = leggi(false);
			if(!repeat) 
				if(trova(keep, obj)) 
					return false;
		}
		keep.add(obj);
		
		sovrascrivi(keep);
		return true;
	}
	
	/** Trova un oggetto nel file.
	 * @param obj Oggetto da cercare nel file.
	 * @return Vero se l'oggetto viene trovato, falso altrimenti.
	*/
	public boolean trova(Object obj) {
		if(!f.exists()) {
			System.out.println("Il file non esiste");
			return false;
		}
		System.out.println("Il file esiste > Ricerca");
		
		try {
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) {
				Object current = oi.readObject();
				if(current.toString().toLowerCase().equals(obj.toString().toLowerCase())) {
					oi.close();
					fi.close();
					return true;
				}
			}

			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/** Trova un oggetto nella lista.
	 * @param list Lista di oggetti in cui effettuare la ricerca.
	 * @param obj Oggetto da cercare nella lista.
	 * @return Vero se l'oggetto viene trovato, falso altrimenti. 
	*/
	public boolean trova(ArrayList<Object> list, Object obj) {
		for(int i=0; i<list.size(); i++) 
			if(list.get(i).toString().equals(obj.toString()))
				return true;
		return false;
	}
	
	/** Canecella un oggetto dal file.
	 * @param obj Oggetto da cancellare dal file.
	 * @return Vero se l'oggetto viene trovato e cancellato, falso altrimenti.
	*/
	public boolean cancella(Object obj) {

		boolean flag = false;
		
		if(!f.exists()) {
			System.out.println("Il file non esiste");
			return flag;
		}
		System.out.println("Il file esiste > Ricerca");
		
		ArrayList <Object> list = new ArrayList<Object>();
		
		try {
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) {
				Object current = oi.readObject();
				if(!current.toString().toLowerCase().equals(obj.toString().toLowerCase())) 
					list.add(current);
				else
					flag = true;
			}

			for(int i=0; i<list.size(); i++)
				System.out.println(list.get(i).toString());
			
			if(flag)
				sovrascrivi(list);
			else
				list.clear();
			
			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}

	/** Filtra il contenuto del file secondo una specifica classe.
	 * @param cls Classe per filtrare il file.
	 * @return Lista di oggetti contenente gli oggetti filtrati dal file.
	*/
	public ArrayList<Object> filtraPerTipo(Class<?> cls) {
		if(!f.exists() || f.length()<1) {
			System.out.println("Il file non esiste o è vuoto");
			return null;
		}
		System.out.println("Il file esiste > Lettura");
		
		ArrayList <Object> list = new ArrayList<Object>();
		
		try {
			FileInputStream fi = new FileInputStream(f);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			while(fi.available() > 0) {
				Object current = oi.readObject();
				if(current.getClass().equals(cls))
					list.add(current);
			}

			oi.close();
			fi.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/** Ritorna il file sul quale si stanno svolgendo le operazioni
	 * @return Un oggetto di tipo file che rappresenta il file in questione
	*/
	public File getFile() {
		return f;
	}
	
	/** Ritorna il nome del file sul quale si stanno svolgendo le operazioni
	 * @return Una stringa che rappresenta il nome del file in questione
	*/
	public String getNomeFile() {
		return nome;
	}

}
