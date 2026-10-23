import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import java.util.Comparator;



class Prodotto {

    String nome;
    double prezzo;
    int sconto;


    public Prodotto(String nomeIn, double prezzoIn, int scontoIn) {

        this.nome = nomeIn;
        this.prezzo = prezzoIn;
        this.sconto = scontoIn;

    }

}


public class JavaSupermarket {


    public static void main(String[] args) {

        //ArrayList

        ArrayList <Prodotto> Catalogo = new ArrayList<>();
        ArrayList <Prodotto> Carrello = new ArrayList<>();
    

        //Catalogo

        Catalogo.add(new Prodotto("Pane a Fette", 2.50, 0));
        Catalogo.add(new Prodotto("Marmellata", 4.00, 0));
        Catalogo.add(new Prodotto("Nutella", 5.20, 0));


        //Scanner
        
        Scanner scanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        

        //Variabili

        double totale = 0;

        


        //Code
        
        System.out.println("\nVuoi comprare qualcosa?");


        System.out.println("\nSugli scaffali c'è: ");

        for(int i = 0; i < Catalogo.size(); i++){

            System.out.println(Catalogo.get(i).nome);

        }



        while(true){

            System.out.println("Scrivi 'Carrello' per vedere il tuo Carrello oppure ciò che vuoi comprare per aggiungerlo.");

            String visualizza = scanner.nextLine();

            if (visualizza.equalsIgnoreCase("carrello")) {

                ShowCart(totale, Carrello);

            }
            else{

                String acquisto = visualizza;

                AdToCart(Carrello, Catalogo, acquisto);

            }

            System.out.println("\n");

        }

    }


    public static int AdToCart(ArrayList<Prodotto> Carrello, ArrayList<Prodotto> Catalogo, String acquisto){

        //Scanner

        Scanner intScanner = new Scanner(System.in);


        //Code

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){
                System.out.println("\nCorrispondenza trovata. \nChe Quantita ne vuoi Acquistare?");

                int quantita = intScanner.nextInt();

                for(int x = 0; x < quantita; x++){

                    Carrello.add(Catalogo.get(j));

                }

                System.out.println(quantita + " " + Catalogo.get(j).nome + " aggiunto al Carrello.");

                break;
            }
        }

        return 0;
    }

    public static double ShowCart(double totale, ArrayList<Prodotto> Carrello){

        Carrello.sort(Comparator.comparing(p -> p.nome));


        for(Prodotto p : Carrello){
            totale += p.prezzo;
        } 

        System.out.println("\nIl Carrello contiene: ");

        for(Prodotto p : Carrello){

            System.out.println(p.nome + ", " +p.prezzo + " Euro");
        }

        System.out.println("Totale: " + totale + " Euro");
        

        return totale;
    }


}