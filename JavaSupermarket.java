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

        ArrayList <Prodotto> Catalogo = new ArrayList<>();
        ArrayList <Prodotto> Carrello = new ArrayList<>();




        System.out.println("\nIl tuo Carrello è vuoto.");

        System.out.println("\nVuoi comprare qualcosa?");



        Catalogo.add(new Prodotto("Pane a Fette", 2.50, 0));
        Catalogo.add(new Prodotto("Marmellata", 4.00, 0));
        Catalogo.add(new Prodotto("Nutella", 5.20, 0));



        System.out.println("\nSugli scaffali c'è: ");

        for(int i = 0; i < Catalogo.size(); i++){

            System.out.println(Catalogo.get(i).nome);

        }




        System.out.println("\n");

    }


    public static int AdToCart(Prodotto Prodotto){



        return 0;
    }


}