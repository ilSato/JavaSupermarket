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

        boolean visCar = false;

        


        //Code
        
        System.out.println("\nVuoi comprare qualcosa?");


        System.out.println("\nSugli scaffali c'è: ");

        for(int i = 0; i < Catalogo.size(); i++){

            System.out.println(Catalogo.get(i).nome);

        }

        System.out.println("Dimmi cosa vuoi comprare");

        String acquisto = Scanner.nextLine();

        for(int j = 0; j < Catalogo.size(); j++){

            if(Catalogo.get(j).nome.equalsIgnoreCase(acquisto)){
                System.out.println("\nCorrispondenza trovata");

                AdToCart(Catalogo.get(j), Carrello);
                break;
            }
        }



        System.out.println("Vuoi visualizzare il Carrello?");

        String visualizza = Scanner.nextLine();

        if(visualizza.equalsIgnoreCase("si")){

            ShowCart(Carrello);

        }



        System.out.println("\n");

    }


    public static int AdToCart(Prodotto Prodotto, ArrayList<Prodotto> Carrello){

        System.out.println(Prodotto.nome + " aggiunto al Carrello.");

        Carrello.add(Prodotto);


        return 0;
    }

    public static int ShowCart(ArrayList<Prodotto> Carrello){

        System.out.println("\nIl Carrello contiene: ");

        for(Prodotto p : Carrello){
            System.out.println(p.nome);
        }
        


        return 0;
    }


}