import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;



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
        double money = 50.00;
        int quantita = 0;


        //Code

        Carrello = PullCart();
        
        System.out.println("\nVuoi comprare qualcosa?");

        ShowCatalogue(Catalogo);


        while(true){

            System.out.println("\nScrivi 'Carrello' per vedere il tuo Carrello, 'Catalogo' per vedere il Catalogo, 'Clear' per Svuotare il Carrello, 'Money' per vedere i soldi rimanenti, 'Pay' per acquistare i Prodotti nel Carrello oppure ciò che vuoi comprare per aggiungerlo nel formato [Prodotto + Invio + Quantità].");

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

                case "money":

                    System.out.println("\nHai " + money + " Euro");
                
                    break;

                case "pay":

                    money = Pay(money, totale);

                    Carrello.clear();

                    totale = 0;
                
                    break;
            
                default:
                    quantita = intScanner.nextInt();

                    String acquisto = visualizza;

                    AdToCart(Carrello, Catalogo, acquisto, quantita);

                    totale = CalcTotal(totale, Carrello);

                    break;
            }

            quantita = 0;

        }

    }


    public static void SaveCart(ArrayList<Prodotto> Carrello){

        Gson gson = new Gson();

        String jsonString = gson.toJson(Carrello);

        try{
            FileWriter writer = new FileWriter("Carrello.json");
            writer.write(jsonString);
            writer.close();
            System.out.println("\n     *Il Carrello è stato salvato*");

        } catch(IOException a){
            a.printStackTrace();
        }

    }


    public static ArrayList<Prodotto> PullCart(){

        Gson gson = new Gson();

        try{

            FileReader fileReader = new FileReader("Carrello.json");

            Type tipoLista = new TypeToken<ArrayList<Prodotto>>(){}.getType();

            ArrayList<Prodotto> OldCarrello = gson.fromJson(fileReader, tipoLista);

            fileReader.close();

            System.out.println("\n     *Carrello Precedente Recuperato*");

            return OldCarrello;

        }catch(IOException e){
            System.out.println("\n     *Nessun Carrello Trovato*");

            return new ArrayList<>();

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


        SaveCart(Carrello);

    }


    public static void ShowCart(double totale, ArrayList<Prodotto> Carrello){

        Boolean addOne = false;
        Boolean hasMoreItems = false;

        CalcTotal(totale, Carrello);


        Carrello.sort(Comparator.comparing(p -> p.nome));
 

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
    }


    public static double Pay(double money, double totale){

        if(money >= totale){
            
            money -= totale;

            System.out.println("\nDevi Pagare " + totale + " Euro");
            System.out.println("Hai Comprato i Prodotti nel Carrello");
            System.out.println("Hai " + money + " Euro");

        }
        else{
            System.out.println("Non hai abbastanza soldi");
        }

        return money;
        
    }


    public static double CalcTotal(double totale, ArrayList<Prodotto> Carrello){

        totale = 0;

        for(Prodotto p : Carrello){
            totale += p.prezzo;
        }

        return totale;
    }

}


