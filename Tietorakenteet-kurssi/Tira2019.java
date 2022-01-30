import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Integer;
/*
 * tira2019
 *
 * Santeri Virtanen Santeri.T.virtanen@tuni.fi
 *
 */

public class Tira2019{

   //lukee tiedoston
   public ArrayList<Integer> readInput(String tiedostonnimi){
      String line;
      int syote = 0;
      ArrayList<Integer> lista = new ArrayList<Integer>();
      try{
         BufferedReader br = new BufferedReader(new FileReader(tiedostonnimi));
         while ((line = br.readLine()) != null){
            syote = Integer.parseInt(line.replaceAll(" ",""));
            lista.add(syote); 
         }
         return lista;
      }
      catch(IOException e){
         System.out.println("File not found.");
         return null;
      }
   }

   //Operaatiolla luodaan tiedostot kirjoitusta varten
   private void writeOutput(String tiedostonimi){

      try {
         BufferedWriter bw = new BufferedWriter(new FileWriter(tiedostonimi)); 		
	      bw.close();
      }  
      catch (IOException e) {
         System.err.format("IOException: %s%n", e);
      }
      System.out.println("Writing file...");
   }

   private void tiedostonKirjoittaja(  String tiedostonnimi, int ensimmäinen, int toinen) throws IOException{
      BufferedWriter bw = new BufferedWriter(new FileWriter (tiedostonnimi, true));
      bw.append(ensimmäinen+" "+toinen);
      bw.newLine();
      bw.close();
   }


   // kysytään käyttäjältä mitä alkioita halutaan poistaa.
   private void poistaminen(Hajautustaulu taulu, String nimi ){
      Scanner lukija = new Scanner(System.in);
      boolean jatketaanko = true;

      while(jatketaanko==true){

         System.out.println(nimi+ " Taulussa on "+ taulu.koko()+" alkiota.");
         System.out.println( "Haluatko poistaa alkion taulusta? y/n ");

         try{
            char komento= lukija.next().charAt(0);
            if(komento== 'y'||komento== 'n'){
               if(komento=='y'){
                  System.out.println("minkä alkion haluat poistaa");
                  int alkio = lukija.nextInt();
                  boolean palaute = taulu.poista(alkio);
                  if(palaute== false){
                     System.out.println("Alkiota ei löytynyt.");
                  }
                  else{
                     System.out.println("Alkio poistettu.");
                  }
               }
               else{
                  jatketaanko=false;
               }
          
            }
            else{
               System.out.println("Virheelinen syöte");
            }
         }
         catch (Exception e){
            System.out.println("Error");
            jatketaanko=true;
         }

      }
      
   }




public static void main(String[] args){

      System.out.println("Ei mielivaltaisen suuria alkioita");
       
      Tira2019 ht=new Tira2019();
      //Tehdään arraylistit tiedostoille
      List<Integer> ALista = new ArrayList<Integer>();
      List<Integer> BLista = new ArrayList<Integer>();
      //luetaan alkiot listoihin.
      ALista = ht.readInput("setA.txt");
      BLista = ht.readInput("setB.txt");



      //unioni
      Hajautustaulu unioni = new Hajautustaulu(9999);
      for (int i = 0; i< ALista.size();i++ ){
         unioni.lisaa(ALista.get(i));
      }
      for (int i = 0; i< BLista.size();i++ ){
         unioni.lisaa(BLista.get(i));
      }  
      
      String tai ="or.txt";
      List<Integer> OR = new ArrayList<Integer>();
      ht.poistaminen(unioni, "OR");
     

      ht.writeOutput(tai);
      OR=unioni.muutos();
      Collections.sort(OR);
      int montako = 0;


      for(int i =0; i<OR.size(); i++){
         montako=0;
         for (int j = 0; j<ALista.size();j++){
            if(OR.get(i).equals(ALista.get(j))){
               montako++;
            }
         }
         for(int k = 0; k < BLista.size(); k++){
            if (OR.get(i).equals(BLista.get(k))){
               montako++;
               }
            }
   
         try{
            ht.tiedostonKirjoittaja(tai, OR.get(i), montako );
         }
         catch(Exception e){
            System.out.println("lul");
         }
      }
      // AND-muokkaus
      List<Integer> AND = new ArrayList<Integer>();
      String ja = "and.txt";
      Hajautustaulu jaTaulu = new Hajautustaulu(9999);
      boolean onkojo=false;
      Hajautustaulu ensinmainen = new Hajautustaulu(9999);
      Hajautustaulu toinen = new Hajautustaulu(9999);

      for (int i = 0; i< ALista.size();i++ ){
         ensinmainen.lisaa(ALista.get(i));
      }
      for (int i = 0; i< BLista.size();i++ ){
         toinen.lisaa(BLista.get(i));
      }

      for (int i=0; i<BLista.size(); i++){
         onkojo=ensinmainen.karsinta(BLista.get(i));
         if(onkojo==true){
            jaTaulu.lisaa(BLista.get(i));
         }
      }

      ht.poistaminen(jaTaulu, "AND");
      
      
      AND=jaTaulu.muutos();
      ht.writeOutput(ja);
      Collections.sort(AND);

      for(int i = 0;i<AND.size(); i++){

         for(int j=0;j<ALista.size(); j++){

            if (AND.get(i).equals(ALista.get(j))){

               try{
                  ht.tiedostonKirjoittaja(ja, AND.get(i), (j + 1));
               }
               catch(Exception e){
                  System.out.println("ORROR");
               }
            }
         }
      }

      //XOR
      Hajautustaulu jokotaiTaulu = new Hajautustaulu(9999); 
      List<Integer> XOR = new ArrayList<Integer>();
      String jokotai="XOR.txt";
      onkojo=true;

      for(int i=0; i<BLista.size(); i++){
         onkojo = ensinmainen.karsinta(BLista.get(i));
         if(onkojo==false){
            jokotaiTaulu.lisaa(BLista.get(i));
         }
      }
      for(int i=0; i<ALista.size(); i++){
         onkojo = toinen.karsinta(ALista.get(i));
         if(onkojo==false){
            jokotaiTaulu.lisaa(ALista.get(i));
         }
      }

      ht.poistaminen(jokotaiTaulu, "XOR");


      XOR= jokotaiTaulu.muutos();
      ht.writeOutput(jokotai);
      Collections.sort(XOR);
      int kumpi = 0;

      for (int i = 0; i < XOR.size(); i++){
         kumpi=0;
         for(int j= 0; j<ALista.size();j++){
            if(XOR.get(i).equals(ALista.get(j))){
               kumpi=1;
            }
         }
         for(int k=0; k<BLista.size(); k++){
            if(XOR.get(i).equals(BLista.get(k))){
               kumpi=2;
            }
            
         }
         try{
            ht.tiedostonKirjoittaja(jokotai, XOR.get(i), kumpi);
         }
         catch(Exception e){
            System.out.println("ORROR");
         }
      }
   }
}