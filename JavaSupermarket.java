import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import com.google.gson.Gson;
import java.io.FileWriter;



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
        Catalogo.add(new Prodotto("Pesce Fresco", 11.30, 0));


        //Scanner
        
        Scanner scanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        

        //Variabili

        double totale = 0;
        int quantita = 0;


        //Code
        
        System.out.println("\nVuoi comprare qualcosa?");

        ShowCatalogue(Catalogo);


        while(true){

            System.out.println("\nScrivi 'Carrello' per vedere il tuo Carrello, 'Catalogo' per vedere il Catalogo, 'Clear' per Svuotare il Carrello oppure ciò che vuoi comprare per aggiungerlo nel formato [Prodotto + Invio + Quantità].");

            String visualizza = scanner.nextLine();

            switch (visualizza.toLowerCase()) {
                case "carrello":
                    ShowCart(totale, Carrello);

                    break;

                case "catalogo":

                    ShowCatalogue(Catalogo);
                
                    break;

                case "clear":

                    Carrello.clear();

                    System.out.println("\nCarrello Svuotato");
                
                    break;
            
                default:
                    quantita = intScanner.nextInt();

                    String acquisto = visualizza;

                    AdToCart(Carrello, Catalogo, acquisto, quantita);

                    break;
            }

            quantita = 0;

        }

    }


    public static void ShowCatalogue(ArrayList<Prodotto> Catalogo){

        System.out.println("\nSugli scaffali c'è: ");

        for(int i = 0; i < Catalogo.size(); i++){

            System.out.println(Catalogo.get(i).nome);

        }
    }


    public static void AdToCart(ArrayList<Prodotto> Carrello, ArrayList<Prodotto> Catalogo, String acquisto, int quantita){

        int notFound = 0;

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){
                System.out.println("\nCorrispondenza trovata.");

                for(int x = 0; x < quantita; x++){

                    Carrello.add(Catalogo.get(j));

                }

                System.out.println(quantita + " " + Catalogo.get(j).nome + " aggiunto al Carrello.");

                notFound = j;

                break;
            }
        }


        if(Catalogo.get(notFound).nome.equalsIgnoreCase(acquisto) == false){
            System.out.println("\nNessuna Corrispondenza trovata.");

        }

    }


    public static double ShowCart(double totale, ArrayList<Prodotto> Carrello){

        Boolean addOne = false;
        Boolean hasMoreItems = false;


        Carrello.sort(Comparator.comparing(p -> p.nome));


        for(Prodotto p : Carrello){
            totale += p.prezzo;
        } 

        System.out.println("\nIl Carrello contiene: ");

        int num = 1;

        String nome = "Via via. Vieni via con me";


        for(int y = 0; y < Carrello.size(); y++){

            if(y + 1 < Carrello.size()){
                nome = Carrello.get(y+1).nome;
            }
            else{
                nome = "Via via vieni via con me";

                if(hasMoreItems){
                    addOne = true;

                }
            }

            if(Carrello.get(y).nome.equals(nome)){
                num ++;
            }
            else{

                hasMoreItems = true;

                if(addOne){
                    num ++;
                }

                System.out.println(Carrello.get(y).nome + " x" + num + ", " + Carrello.get(y).prezzo*num + " Euro");

                num = 0;
                addOne = false;
            }

        }

        hasMoreItems = false;

        System.out.println("Totale: " + totale + " Euro");
        

        return totale;
    }

}