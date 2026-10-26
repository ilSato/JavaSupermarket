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
    boolean kg;


    public Prodotto(String nomeIn, double prezzoIn, boolean kgIn) {

        this.nome = nomeIn;
        this.prezzo = prezzoIn;
        this.kg = kgIn;

    }

}


class DatiUtente {

    double money;

}


public class JavaSupermarket {


    public static void main(String[] args) {

        //ArrayList

        ArrayList <Prodotto> Catalogo = new ArrayList<>();
        ArrayList <Prodotto> Carrello = new ArrayList<>();
    

        //Catalogo

        Catalogo.add(new Prodotto("Pane a Fette", 2.50, false));
        Catalogo.add(new Prodotto("Marmellata", 4.00, false));
        Catalogo.add(new Prodotto("Nutella", 5.20, false));
        Catalogo.add(new Prodotto("Pesce Fresco", 0.113, true));
        Catalogo.add(new Prodotto("Latte Fresco", 1.50, false));
        Catalogo.add(new Prodotto("Pasta di Semola", 0.90, false));
        Catalogo.add(new Prodotto("Passata di Pomodoro", 1.20, false));
        Catalogo.add(new Prodotto("Olio Extra Vergine", 6.50, false));
        Catalogo.add(new Prodotto("Farina Tipo 00", 0.95, false));
        Catalogo.add(new Prodotto("Zucchero Semolato", 1.10, false));
        Catalogo.add(new Prodotto("Caffè Macinato", 3.80, false));
        Catalogo.add(new Prodotto("Biscotti ai Cereali", 2.20, false));
        Catalogo.add(new Prodotto("Acqua Minerale", 0.40, false));
        Catalogo.add(new Prodotto("Tonno in Olio d'Oliva", 2.80, false));
        Catalogo.add(new Prodotto("Mozzarella di Bufala", 2.30, false));
        Catalogo.add(new Prodotto("Prosciutto Cotto", 0.31, true));
        Catalogo.add(new Prodotto("Parmigiano Reggiano", 5.50, false));
        Catalogo.add(new Prodotto("Uova Fresche", 1.80, false));
        Catalogo.add(new Prodotto("Mele Golden", 0.02, true));
        Catalogo.add(new Prodotto("Banane", 0.019, true));
        Catalogo.add(new Prodotto("Yogurt Greco", 1.15, false));
        Catalogo.add(new Prodotto("Cioccolato Fondente", 1.60, false));
        Catalogo.add(new Prodotto("Detersivo Piatti", 1.75, false));
        Catalogo.add(new Prodotto("Carta Igienica", 2.50, false));
        Catalogo.add(new Prodotto("Detersivo Lavatrice", 4.90, false));


        //Scanner
        
        Scanner scanner = new Scanner(System.in);
        

        //Variabili

        double totale = 0;
        double money = 0;
        int quantitaPack = 0;
        double quantitaKg = 0;
        double quantita = 0;
        boolean notFound = false;
        boolean kg = false;


        //Code

        Carrello = PullCart();

        money = PullBalance();
        
        System.out.println("\nVuoi comprare qualcosa?");

        ShowCatalogue(Catalogo);


        while(true){

            totale = CalcTotal(totale, Carrello);

            System.out.println("\nScrivi 'Carrello' per vedere il tuo Carrello, 'Catalogo' per vedere il Catalogo, 'Clear' per Svuotare il Carrello, 'Remove' per rimuovere un singolo tipo di prodotto nella Quantità desiderata, 'Money' per vedere i soldi rimanenti, 'Pay' per acquistare i Prodotti nel Carrello oppure ciò che vuoi comprare per aggiungerlo nel formato [Prodotto + Invio + Quantità].");

            String visualizza = scanner.nextLine();

            switch (visualizza.toLowerCase()) {
                case "carrello":
                    ShowCart(totale, Carrello, kg);

                    break;

                case "catalogo":

                    ShowCatalogue(Catalogo);
                
                    break;

                case "clear":

                    Carrello.clear();

                    SaveCart(Carrello);

                    System.out.println("\nCarrello Svuotato");
                
                    break;

                case "remove":

                    visualizza = scanner.nextLine();

                    notFound = CheckExistence(Catalogo, visualizza, notFound);

                    kg = CheckKg(Catalogo, visualizza, kg);

                    boolean again = true;

                    if(!notFound){

                        while(again){

                            if(kg){

                                quantitaKg = scanner.nextDouble();

                                try{

                                    String acquisto = visualizza;

                                    RemoveFromCart(Carrello, Catalogo, visualizza, quantitaKg, quantitaPack, kg);

                                    totale = CalcTotal(totale, Carrello);

                                    again= false;

                                    scanner.nextLine();
                        
                                }catch (Exception e) {

                                    System.out.println("Inserisci una Quantità valida");

                                    again = true;

                                    scanner.nextLine();

                                }

                            }
                            else{

                                quantitaPack = scanner.nextInt();

                                try{

                                    String acquisto = visualizza;

                                    RemoveFromCart(Carrello, Catalogo, visualizza, quantitaKg, quantitaPack, kg);

                                    totale = CalcTotal(totale, Carrello);

                                    again= false;

                                    scanner.nextLine();
                            
                                }catch (Exception e) {

                                    System.out.println("Inserisci una Quantità valida");

                                    again = true;

                                    scanner.nextLine();

                                }

                            }

                        }

                    }
                
                    break;

                case "money":

                    System.out.println("\nHai " + money + " Euro");
                
                    break;

                case "pay":

                    money = Pay(money, totale);

                    Carrello.clear();

                    SaveCart(Carrello);

                    SaveBalance(money);

                    totale = 0;
                
                    break;
            
                default:

                    notFound = CheckExistence(Catalogo, visualizza, notFound);
                    kg = CheckKg(Catalogo, visualizza, kg);

                    again = true;

                    if(!notFound){

                        while(again){

                            if(kg){

                                quantitaKg = scanner.nextDouble();

                                try{

                                    String acquisto = visualizza;

                                    AdToCart(Carrello, Catalogo, acquisto, quantitaKg, quantitaPack, kg);

                                    totale = CalcTotal(totale, Carrello);

                                    again= false;

                                    scanner.nextLine();
                        
                                }catch (Exception e) {

                                    System.out.println("Inserisci una Quantità valida");

                                    again = true;

                                    scanner.nextLine();

                                }

                            }
                            else{

                                quantitaPack = scanner.nextInt();

                                try{

                                    String acquisto = visualizza;

                                    AdToCart(Carrello, Catalogo, acquisto, quantitaKg, quantitaPack, kg);

                                    totale = CalcTotal(totale, Carrello);

                                    again= false;

                                    scanner.nextLine();
                            
                                }catch (Exception e) {

                                    System.out.println("Inserisci una Quantità valida");

                                    again = true;

                                    scanner.nextLine();

                                }

                            }

                        }

                    }

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

            if(Catalogo.get(i).kg){

                System.out.println(Catalogo.get(i).nome + ", " + Catalogo.get(i).prezzo + " Euro/kg");
            }
            else{
                
                System.out.println(Catalogo.get(i).nome + ", " + Catalogo.get(i).prezzo + " Euro");
            }
            
        }

    }


    public static boolean CheckExistence(ArrayList<Prodotto> Catalogo, String acquisto, boolean notFound){

        notFound = false;

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){

                notFound = false;

                break;
            }
            else{

                notFound = true;
            }

        }

        if(notFound){
            System.out.println("\nNessuna Corrispondenza trovata");
        }

        return notFound;
    }


    public static boolean CheckKg(ArrayList<Prodotto> Catalogo, String acquisto, boolean kg){

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){

                if(Catalogo.get(j).kg){
                    kg = true;

                }
                else{
                    kg = false;

                }

                break;
            }

        }

        return kg;
    }


    public static void AdToCart(ArrayList<Prodotto> Carrello, ArrayList<Prodotto> Catalogo, String acquisto, double quantitaKg, int quantitaPack, boolean kg){

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){

                if(kg){

                    double quantitaTemp = Math.round(quantitaKg*100);

                    for(double x = 0; x < quantitaTemp/100; x += 0.01){

                        Carrello.add(Catalogo.get(j));

                    }

                    System.out.println(quantitaTemp/100 + " Kg di " + Catalogo.get(j).nome + " aggiunto al Carrello.");

                }
                else{

                    for(int x = 0; x < quantitaPack; x++){

                        Carrello.add(Catalogo.get(j));

                    }

                    System.out.println(quantitaPack + " " + Catalogo.get(j).nome + " aggiunto al Carrello.");
                }

            }

        }

        SaveCart(Carrello);

    }


    public static void ShowCart(double totale, ArrayList<Prodotto> Carrello, boolean kg){

        Boolean addOne = false;
        Boolean hasMoreItems = false;

        CalcTotal(totale, Carrello);


        Carrello.sort(Comparator.comparing(p -> p.nome));
 

        System.out.println("\nIl Carrello contiene: ");

        double num = 1;

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

                if(Carrello.get(y).kg){

                    System.out.println(Carrello.get(y).nome + " x" + num/100 + "Kg, " + Carrello.get(y).prezzo*num + " Euro");

                }
                else{

                    System.out.println(Carrello.get(y).nome + " x" + num + ", " + Carrello.get(y).prezzo*num + " Euro");

                }

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


    public static void RemoveFromCart(ArrayList<Prodotto> Carrello, ArrayList<Prodotto> Catalogo, String acquisto, double quantitaKg, int quantitaPack, boolean kg){

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){

                if(kg){

                    for(double x = 0; x < quantitaKg; x += 0.01){

                        Carrello.remove(Catalogo.get(j));

                    }
                    



                    /*
                    for(int j = 0; j < quantita; j++){

                        for(Prodotto p : Carrello){

                            if(nome.equalsIgnoreCase(p.nome)){

                                Carrello.remove(p);

                                break;
                            }
                        }
                    }
                    */



                    System.out.println(quantitaKg + " Kg di " + Catalogo.get(j).nome + " rimosso dal Carrello.");

                }
                else{

                    for(int x = 0; x < quantitaPack; x++){

                        Carrello.remove(Catalogo.get(j));

                    }

                    System.out.println(quantitaPack + " " + Catalogo.get(j).nome + " rimosso dal Carrello.");
                }

            }

        }

        SaveCart(Carrello);

    }


    //Qui giu nei meandri del codice nemmeno io so come fa a funzionare. Chi sarà abbastanza coraggioso, o stolto, da modificarlo subirà l'ira del codice scritto a cazzo.


    public static void SaveBalance(double money){

        Gson gson = new Gson();

        DatiUtente dati = new DatiUtente();

        dati.money = money;

        String jsonString = gson.toJson(dati);

        try{
            FileWriter writer = new FileWriter("DatiUtente.json");
            writer.write(jsonString);
            writer.close();
            System.out.println("\n     *Il Bilancio è stato salvato*");

        } catch(IOException a){
            a.printStackTrace();
        }
    }


    public static Double PullBalance(){

        Gson gson = new Gson();

        try (FileReader fileReader = new FileReader("DatiUtente.json")){

            DatiUtente dati = gson.fromJson(fileReader, DatiUtente.class);

            System.out.println("\n     *Saldo precedente recuperato*");

            if(dati == null){
                System.out.println("\n     *Nessun File trovato*");
                return 50.0;
            }

            return dati.money;

        } catch (IOException e) {
            System.out.println("\n     *Nessun Dato Utente trovato*");
            return 50.0;
        }
    }

}


