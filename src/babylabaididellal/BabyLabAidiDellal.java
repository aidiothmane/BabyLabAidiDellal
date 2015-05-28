/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package babylabaididellal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Aidi Othmane
 */
public class BabyLabAidiDellal {

    /**
     * @param args the command line arguments
     */
     public String[] parserFichier(String url){
                String chaine="";
                int i = 0;
		String fichier =url;
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				chaine+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
                String[] sortie = chaine.split("\n\n");
                System.out.println(chaine);
                return sortie;
                
}
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
