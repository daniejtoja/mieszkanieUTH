package mieszkanie;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


    Map <Integer, Mieszkanie> blokWMain = new HashMap<Integer, Mieszkanie>();
    Blok blokPierwszy = new Blok(wstawMieszkaniaDoBloku(ileWygenerowac(), blokWMain));
    blokPierwszy.getWszystkieMieszkania();


    }

    public static int ileWygenerowac(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Proszę podaj ile mieszkań chcesz wygenerować: ");
        int ileMieszkan = scanner.nextInt();
        return ileMieszkan;
    }
    /*
    Powyższa funkcja pyta nas ile chcemy wygenerować mieszkań do naszego bloku:-)
    */


    public static Map wstawMieszkaniaDoBloku(int ileWstawic, Map tuWstaw){
        Random r = new Random();
        String []typKuchenki = {"Gazowa", "Elektryczna", "Kaflowa"}; //tu dopiero definuję jakie typy kuchenek mamy, bo chyba są jeszcze jakieś inne? Więc w tym przypadku mamy 3 do wyboru
        for(int i = 0; i<ileWstawic; ++i) {
            Mieszkanie testoweMieszkanie = new Mieszkanie(new Kuchnia(powierzchniaK(r), jakaKuchenka(r, typKuchenki)), new Pokoj(powierzchniaMP(r)), new Pokoj(powierzchniaDP(r)));
            tuWstaw.put(i, testoweMieszkanie);
        }

        return tuWstaw;
    }
    /*
    Powyższa funkcja ma za zadanie wstawić losowo wygenerowane mieszkania w ilości podanej w "ileWstawic", owe ileWstawić, to wcześniej napisana
    funkcja "ileWygenerować", która pyta nas właśnie o tę liczbę
     */

    public static int powierzchniaMP(Random r){
        return 5 + r.nextInt(6);
    }
    /*
    Na potrzeby zadania z javawkoronie.blogi.pl mamy podany przedział metrażu małego pokoju - 5-10 m^2. Jako liczba ograniczająca mamy 6, ponieważ w nawiasie podajemy zakres
    tzw. non inclusive. Z racji, że losujemy liczby typu Integer, to największą liczbą owego przedziału jest 5.
     */
    public static int powierzchniaDP(Random r){
        return 15 + r.nextInt(11);
    }
    /*
    Na potrzeby zadania z javawkoronie.blogi.pl mamy podany przedział metrażu dużego pokoju - 15-25 m^2. Jako liczba ograniczająca mamy 6, ponieważ w nawiasie podajemy zakres
    tzw. non inclusive. Z racji, że losujemy liczby typu Integer, to największą liczbą owego przedziału jest 10.
    */
    public static int powierzchniaK(Random r){
        return 8 + r.nextInt(5);
    }
    /*
    Na potrzeby zadania z javawkoronie.blogi.pl mamy podany przedział metrażu kuchni - 8-12 m^2. Jako liczba ograniczająca mamy 6, ponieważ w nawiasie podajemy zakres
    tzw. non inclusive. Z racji, że losujemy liczby typu Integer, to największą liczbą owego przedziału jest 4.
     */

    public static String jakaKuchenka(Random r, String []typKuchenki){
        return typKuchenki[r.nextInt(typKuchenki.length)];
    }
    /*
    Funkcja losuje nam losowy String z tablicy. W tym przypadku chcieliśmy wylosować typ kuchenki, ale równie dobrze można użyć tej funkcji do losowania jakiegokolwiek stringa chcemy
     */

    static class Blok{
        private Map<Integer, Mieszkanie> blokMieszkalny;

        public Blok(Map<Integer, Mieszkanie> blokMieszkalny) {
            this.blokMieszkalny = blokMieszkalny;
        }

        public String getMieszkanie(int numerMieszkania){
            String s = blokMieszkalny.get(numerMieszkania).toString();
            return s;
        }

        public void getWszystkieMieszkania(){
            System.out.println("Blok z adresem: " + blokMieszkalny.hashCode());
            for (int i = 0; i < blokMieszkalny.size(); ++i){
                System.out.println("Mieszkanie numer: " + (i+1) + ". Dane: " + getMieszkanie(i));
            }
        }

        public int ileMamyMieszkan(){
            return blokMieszkalny.size();
        }


    }
    /*
    Blok zawiera mapę, która pozwala pod nr. mieszkania(key) przypisać konkretny obiekt(mieszkanie, dokładna nazwa tego pola, to "value").
    Funkcja getMieszkanie zwraca String z funkcją toString danego mieszkania, które znajduje się pod kluczem "numerMieszkania"
    Funkcja getWszystkieMieszkania wypisuje wszystkie mieszkania od pierwszego korzystając z funkcji getMieszkanie
     */


    static class Mieszkanie{
        Kuchnia kuchnia;
        Pokoj malyPokoj;
        Pokoj duzyPokoj;


        public Mieszkanie(Kuchnia kuchnia, Pokoj malyPokoj, Pokoj duzyPokoj) {
            this.kuchnia = kuchnia;
            this.malyPokoj = malyPokoj;
            this.duzyPokoj = duzyPokoj;
        }

        public double obliczPowierzchnie(){
            return (kuchnia.getPowierzchniaKuchni() + malyPokoj.getPowierzchniaPokoju() + duzyPokoj.getPowierzchniaPokoju());
        }

        @Override
        public String toString() {
            return "Mieszkanie o powierzchni: " + obliczPowierzchnie()  + "m2. Typ kuchenki: " + kuchnia.getTypKuchenki() +
            ". Metraż kuchni: " + kuchnia.getPowierzchniaKuchni() + "m2. " + "Metraż małego pokoju: " + malyPokoj.getPowierzchniaPokoju() + "m2. " +
                    "Metraż dużego pokoju: " + duzyPokoj.getPowierzchniaPokoju() + "m2.";
        }
    }
    /*
    Delikatnie zmodyfikowałem funkcję, toString(), aby pokazywała bardziej estetycznie, przejrzyście dane mieszkania.
     */

    static class Pokoj{
        private double powierzchniaPokoju;

        public Pokoj(double powierzchniaPokoju) {
            this.powierzchniaPokoju = powierzchniaPokoju;
        }

        public double getPowierzchniaPokoju() {
            return powierzchniaPokoju;
        }

        public void setPowierzchniaPokoju(double powierzchniaPokoju) {
            this.powierzchniaPokoju = powierzchniaPokoju;
        }


    }
    /*
    Skorzystałem tu z get i set:-)
     */

    static class Kuchnia{
        private double powierzchniaKuchni;
        private String typKuchenki;

        public Kuchnia(double powierzchniaKuchni, String typKuchenki) {
            this.powierzchniaKuchni = powierzchniaKuchni;
            this.typKuchenki = typKuchenki;
        }

        public double getPowierzchniaKuchni() {
            return powierzchniaKuchni;
        }

        public void setPowierzchniaKuchni(double powierzchniaKuchni) {
            this.powierzchniaKuchni = powierzchniaKuchni;
        }

        public String getTypKuchenki() {
            return typKuchenki;
        }

        public void setTypKuchenki(String typKuchenki) {
            this.typKuchenki = typKuchenki;
        }
    }
    /*
    Tu również get i set!
     */

}
