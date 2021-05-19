package mitsk.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    private static Uniform uniforomObject;
    private static RoundRobin roundRobinObject;
    private static Weighted weightedObject;

    public static void main(String args[]) throws InterruptedException {
        System.out.println("wybierz program");
        System.out.println("1.Uniform -zwraca liczbę z przedziału min –max (zdefiniowane przez parametry konstruktora) z rozkładem równomiernym (0,25pkt) \n\n2.RoundRobin –zwraca kolejno liczby (zdefiniowane przez parametry konstruktora). Po zwróceniu ostatniej liczby, generator zwraca liczby od początku. (0,25pkt) \n\n3.Weighted –zwraca liczbę zgodnie z rozkładem o zadanej gęstości (zdefiniowanej przez parametr konstruktora). Definiowanie gęstości ma zostać zrealizowane przez listę obiektów posiadających parametry from, to i weight. (0,25pkt)");

        Scanner scan = new Scanner(System.in);
        int expression = scan.nextInt();

        switch(expression) {
            case 1:
                System.out.println("Podaj zakres liczb do wylosowania");
                double randFirst = scan.nextInt();
                double randSecond = scan.nextInt();
                uniforomObject = new Uniform(randFirst, randSecond);
                System.out.println(uniforomObject.getNext());
                break;

            case 2:
                roundRobinObject = new RoundRobin(new int[]{8, 7, 5});
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                System.out.println(roundRobinObject.getNext());
                break;
            case 3:
                double xd = 0;
                for(int i=0; i<10000; i++){
                    weightedObject = new Weighted(new Weighted.Obiekty(2,3,2), new Weighted.Obiekty(5,7,2), new Weighted.Obiekty( 9,11,4));
                    xd = xd + weightedObject.getNext();
                }
                System.out.println(xd/10000);
                break;
            default:
        }

    }
}
