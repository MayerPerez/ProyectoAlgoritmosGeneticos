/*
Practica 3:Diseño de un AG simnple
Pérez González Mayer Abraham
Genetic Algorithms 3CM1
*/
package practica3ag;

public class Practica3AG {

    public static void main(String[] args) {
        new Interfaz();
        
        /*
        Funcion x^2
        De 0 a 31
        Representacion binaria
        Generaciones de 6 individuos
        */
        /*Optimizacion opt = new Optimizacion(0,0,31,0,6);
        for(Individuo ind: opt.getGenInicial()){
            System.out.println(ind.getGenotipoString());
        }
        System.out.println("");
        ArrayList<Individuo> aux = FuncAG.mutaHeuristica(opt.getGenInicial(),opt.getNumBits());
        System.out.println("\n\nResultado");
        for(int i=0;i<aux.size();i++){
            System.out.println(aux.get(i).getGenotipoString());
        }*/
        /*byte b[] = {0,1,1,0,0,1};
        ArrayList<Integer> g = new ArrayList<>();
        g.add(1);
        g.add(2);
        g.add(3);
        Individuo ind = FuncAG.mejorVecino(b, g);*/
    }
    
}