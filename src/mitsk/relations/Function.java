package mitsk.relations;

import java.util.Scanner;

public class Function {
    public static void main(String args[]) throws InterruptedException {
    polynomial(); //wielomianu stopnia 2 o zadanych współczynnikach a,b,c lub wielomianu dowolnego stopnia (jedno z dwóch)
    functionAtPoint(); //obliczenie wartości funkcji w punkcie x
    }

    // OBLICZAIE WARTOSCI FUNKCJI W PUNKCIE
    public static void functionAtPoint(){
        double a,b,c,x ;
        System.out.println("Poda współczynniki równania kwadratowego w postaci ax^2+bx+c");
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj parametr a");
        a = scan.nextDouble();
        System.out.println("Podaj parametr b");
        b = scan.nextDouble();
        System.out.println("Podaj parametr c");
        c = scan.nextDouble();

        System.out.println("Podaj punkt x dla którego chcesz obliczyć wartość funkcji ");
        x = scan.nextDouble();

        double y = a*x*x + b*x + c;
        System.out.println("Funkcja ta ma wartość y = " + y+ " dla wartości x = "+x);



    }
    // OBLICZANIE WSPOLCZYNNIKOW ROWNANIA KWADRATOWEGO
    public static void polynomial(){
        double a, b,c ;
        System.out.println("Poda współczynniki równania kwadratowego w postaci ax^2+bx+c");
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj parametr a");
        a = scan.nextDouble();
        if(a != 0) {
            System.out.println("Podaj parametr b");
            b = scan.nextDouble();

            System.out.println("Podaj parametr c");
            c = scan.nextDouble();
        } else{
            System.out.println("A musi być różne od zera!");
            System.out.println("Podaj parametr a");
            a = scan.nextDouble();
            System.out.println("Podaj parametr b");
            b = scan.nextDouble();
            System.out.println("Podaj parametr c");
            c = scan.nextDouble();
        }


        double deltA = delta(a, b, c);

        if(deltA >0){
            double x1 = (-b - Math.sqrt(deltA))/2*a;
            double x2 = (-b + Math.sqrt(deltA))/2*a;
            System.out.println("Rozwiązaniasem są liczby : "+x1 + " oraz " + x2);
        }else if (deltA == 0){
            double x = -b/(2*a);
            System.out.println("Rozwiązaniasem jest liczba : " + x);
        }else{
            System.out.println("To równanie nie ma rozwiązań!");
        }

    }
    public static double delta(double a, double b, double c){
        return b*b-(4*a*c); }
}
