import java.util.*;
import java.lang.Integer;
/*
 * Harkkatyön hajautustaulu
 *
 * Santeri Virtanen Santeri.T.virtanen@tuni.fi
 *
 */


public class Hajautustaulu {
   private Linkitettylista[] taulu;
   private int koko;
   
   
   //Rakentaja joka luo hajautustaulun.
   public Hajautustaulu(int hashkoko){
      taulu = new Linkitettylista[hashkoko];
      koko = 0;
   }
   
   //pystytään tarkista
   public boolean onkoTyhja(){
      if(koko==0){
         return true;
       
      }
      return false;
   }
   
   //palautta koon
   public int koko(){
      return koko;
   }
   
   //Tekee avainmen.
   public int hashkoodi(int arvo){
   
      int avain = arvo % taulu.length;
      return avain;
   }
   
   //lisää tauluun alkion.
   public void lisaa(int alk){
      
      boolean onko = false;
      int indeksi = hashkoodi(alk);
      Linkitettylista tilapainen = new Linkitettylista(alk);
      Linkitettylista tilapainen2 = new Linkitettylista(alk);
      tilapainen= taulu[indeksi];
      
      if(taulu[indeksi] == null){
         taulu[indeksi] = tilapainen2;
         koko++;
      }else if(taulu[indeksi].alkio == alk){
         onko=true;
      }else{
         while(tilapainen.seuraava !=null){
            if( tilapainen.alkio == alk || tilapainen.seuraava.alkio== alk){
               onko = true;
            }
            tilapainen= tilapainen.seuraava;
         }
         if (onko == false){
            tilapainen.seuraava=tilapainen2;
            koko++;
         }
      }
   }

   //poistetaan alkio
   public boolean poista(int arvo){
      int indeksi = hashkoodi(arvo);
      boolean poistettu = false;
      Linkitettylista tilapainen = new Linkitettylista(arvo);
      tilapainen= taulu[indeksi];
      
      if(taulu[indeksi].alkio== arvo){
         taulu[indeksi]= taulu[indeksi].seuraava;
         tilapainen.seuraava=null;
         poistettu= true;
         koko--;
      }else{
         while(tilapainen.seuraava != null && poistettu == false){
            if (tilapainen.seuraava.alkio== arvo){
               tilapainen.seuraava= tilapainen.seuraava.seuraava;
               koko--;
               poistettu=true;
               return poistettu;
            }
            else{
               tilapainen=tilapainen.seuraava;
            }
         }
      
      }
      return poistettu;
   }
   
   //muutetaan hajautustaulu arraylistiksi käsittelyn helpottamiseksi.
   public ArrayList<Integer> muutos(){
      ArrayList<Integer> lista= new ArrayList<Integer>();
      for (int i=0; i<taulu.length; i++){
         Linkitettylista tilapainen= taulu[i];
         while (tilapainen != null){
            lista.add(tilapainen.alkio);
            tilapainen=tilapainen.seuraava;
         }
      }
      return lista;
   }
   //katsotaan et onko arvo taulussa jos on niin poistetaan.
   public boolean karsinta (int arvo){
      Linkitettylista tilapainen= new Linkitettylista(arvo);
      int ind =hashkoodi(arvo);
      tilapainen=taulu[ind];
      if(taulu[ind]==null){
         koko--;
         return false;
      }else if (taulu[ind].alkio == arvo){
         koko++;
         return true;
      }else{
         while(tilapainen.seuraava != null){
            if(tilapainen.alkio == arvo || tilapainen.seuraava.alkio==arvo){
               koko++;
               return true;
            }
            tilapainen= tilapainen.seuraava;
         }
         koko--;
         return false;
      }
   }


   


}