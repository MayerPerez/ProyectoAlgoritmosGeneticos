/*
Proyecto Final del Curso
Integrantes:
    -Pérez González Mayer Abraham
    -Urbina Villa Noe
Genetic Algorithms 3CM1
*/
/*
Clase principal
*/
package practica3ag;

import java.util.ArrayList;

public class ProyectoFinalAG {

    public static void main(String[] args) {
        new Interfaz();
        //S(x+3)/(x+2)
        /*String f = "S(x+3)/(x+2)";
        String fp = InfijoAPostfijo.InfiPostfi(f);
        System.out.println("Main:  "+ fp);
        int x = 5;
        double fx = InfijoAPostfijo.Evaluation(f, x);
        System.out.println(fx);
        
        /*
        Funcion x^2
        De 0 a 31
        Representacion binaria
        Generaciones de 6 individuos
        */
        /*Optimizacion opt = new Optimizacion("x^2",0,31,0,6);
        for(Individuo ind: opt.getGenInicial()){
            System.out.println(ind.getVFitness());
        }
        System.out.println("");
        ArrayList<Individuo> aux = FuncAG.selecPorTorneo(opt.getGenInicial(), "x^2");
        /*System.out.println("\n\nResultado");
        for(int i=0;i<aux.size();i++){
            System.out.println(aux.get(i).getGenotipoString());
        }//*/
        /*byte b[] = {0,1,1,0,0,1};
        ArrayList<Integer> g = new ArrayList<>();
        g.add(1);
        g.add(2);
        g.add(3);
        Individuo ind = FuncAG.mejorVecino(b, g);*/
    }
    
}
