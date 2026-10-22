import java.util.Scanner;
import java.util.ArrayList;



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
        
        Scanner Scanner = new Scanner(System.in);
        

        //Variabili

        double totale = 0;

        


        //Code
        
        System.out.println("\nVuoi comprare qualcosa?");


        System.out.println("\nSugli scaffali c'è: ");

        for(int i = 0; i < Catalogo.size(); i++){

            System.out.println(Catalogo.get(i).nome);

        }



        while(true){

            System.out.println("Vuoi visualizzare il Carrello o Fare Acquisti? (1 or 2)");

            String visualizza = Scanner.nextLine();

            switch (visualizza) {
                case "1":
                    
                    ShowCart(totale, Carrello);

                    break;

                case "2":

                    System.out.println("Dimmi cosa vuoi comprare");

                    String acquisto = Scanner.nextLine();

                    for(int j = 0; j < Catalogo.size(); j++){

                        if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){
                            System.out.println("\nCorrispondenza trovata");

                            AdToCart(Catalogo.get(j), Carrello);
                            break;
                        }
                    }
                    
                    break;
            
                default:

                    System.out.println("No Corrispondenza");

                    break;
            }

            System.out.println("\n");

        }

    }


    public static int AdToCart(Prodotto Prodotto, ArrayList<Prodotto> Carrello){

        System.out.println(Prodotto.nome + " aggiunto al Carrello.");

        Carrello.add(Prodotto);


        return 0;
    }

    public static double ShowCart(double totale, ArrayList<Prodotto> Carrello){

        for(Prodotto p : Carrello){
            totale += p.prezzo;
        } 

        System.out.println("\nIl Carrello contiene: ");

        for(Prodotto p : Carrello){
            System.out.println(p.nome + ", " +p.prezzo + " Euro");
        }

        System.out.println("Totale: " + totale);
        

        return totale;
    }


}