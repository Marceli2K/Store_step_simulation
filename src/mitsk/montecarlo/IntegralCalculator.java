package mitsk.montecarlo;

import mitsk.generators.Uniform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntegralCalculator {
    private double maxInterval;
    private double minInterval;
    double aIntegral,bIntegral,cIntegral;
    private List<Double> minMAx;

    IntegralCalculator(Uniform xDistribution){
        maxInterval = Uniform.max;
        minInterval = Uniform.min;
    }

    public double integralCalculator(int trials, double a, double b, double c ) {
        double yp = 0;
        double yk = 0;
        aIntegral = a;
        bIntegral = b;
        cIntegral = c;
        minMAx =localMinMAx();
        double yp1 = minMAx.get(0);
        double yk1 = minMAx.get(1);

        if(yk1 < yp1){
            yk = yp1;
            yp = yk1;
        }else if (yk1 > yp1 && yp1 >0){
            yp = 0;
            yk = yk1;
        }else if (yk1 > yp1 && yp1<=0){
            yp = yp1;
            yk = yk1;
        }else if(yk1==yp1){
            yp = 0;
            yk = yk1;
        }

        double pointsIn = 0;
        for (int i=0; i<trials; i++) {
            double random_y = new Uniform(yp, yk).getNext();
            double random_x = new Uniform(maxInterval, minInterval).getNext();
            pointsIn += funcIn(random_x, random_y );
        }
        double rectangleArea = (maxInterval-minInterval) * (yk-yp);
        double integral = (pointsIn / (double)trials) * rectangleArea;

        return integral;
    }

    //Funkcja wyliczająca wartość funkcji w danym punkcie
    private double func(double x) {
        return (x*x*aIntegral + x*bIntegral + (cIntegral));
    }

    // Funkcja obliczająca minima oraz maxima lokalne, oraz wracająca jest w postaci listy, gdzie list[0] to min, a list[1] to max
    private List<Double> localMinMAx(){
        List<Double> lista = new ArrayList<Double>();
        List<Double> minMax = new ArrayList<Double>();
        for(double i = 0; i <= (maxInterval- minInterval) ; i++){
            double result = i*i*aIntegral + i*bIntegral + (cIntegral);
            //System.out.println("wartosc y dla danej funkcji"+result);
            lista.add(result);
        }
        minMax.add(Collections.min(lista));
        minMax.add(Collections.max(lista));
        return minMax;
    }


    //  1 jeżeli punkt leży nad osią OY i jednocześnie pod wykresem funkcji całkowanej
    //  -1 jeżeli punkt leży pod osią OY i jednocześnie nad wykresem funkcji całkowanej
    //  0 w przeciwnym razie
    private double funcIn(double x, double y) {
        //System.out.println("y : " +y + " " + " func "+ func(x) );
        if (( y > 0) && (y <= func(x))){
            //System.out.println("return 1");
            return 1;
        }else if (( y <= 0) && (y >= func(x))){
            //System.out.println("return -1");
            return -1;
        }
        //System.out.println("return 0");
        return 0;
    }

}
