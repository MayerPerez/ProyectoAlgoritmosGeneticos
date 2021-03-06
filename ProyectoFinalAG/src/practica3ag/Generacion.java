/*
Proyecto Final del Curso
Integrantes:
    -Pérez González Mayer Abraham
    -Urbina Villa Noe
Genetic Algorithms 3CM1
*/
package practica3ag;

import java.util.ArrayList;

public class Generacion {
    private ArrayList<Individuo> Individuos;
    private int indMax;
    private  int indMin;
    private double prom;
    
    public Generacion(ArrayList<Individuo> g,String F){
        Individuos = g;
        prom = 0;
        //Se evaluan los individuos en la funcion fitness selecionada
        for(Individuo indAux: Individuos){
            indAux.setVFitness(InfijoAPostfijo.Evaluation(F,indAux.getFenotipo()));
            prom += indAux.getVFitness();
        }
        prom = prom/g.size();
    }
    //FUNCION PARA OBTENER EL VALOR QUE MAXIMIZA LA FUNCION
    public int getMax(){
        int index = 0;
        double vMax = Individuos.get(0).getVFitness();
        for(int i=1;i<Individuos.size();i++){
            if(Individuos.get(i).getVFitness() > vMax){
                vMax = Individuos.get(i).getVFitness();
                index = i;
            }
        }
        return index;
    }
    //FUNCION PARA OBTENER EL VALOR QUE MINIMIZA LA FUNCION
    public int getMin(){
        int index = 0;
        double vMin = Individuos.get(0).getVFitness();
        for(int i=1;i<Individuos.size();i++){
            if(Individuos.get(i).getVFitness() < vMin){
                vMin = Individuos.get(i).getVFitness();
                index = i;
            }
        }
        return index;
    }
    
    public String[][] getTable(){
        String t[][] = new String[Individuos.size()][4];
        for(int i=0;i<Individuos.size();i++){
            t[i][0] = String.valueOf(i+1);
            t[i][1] = Individuos.get(i).getGenotipoString();
            t[i][2] = String.valueOf(Individuos.get(i).getFenotipo());
            t[i][3] = String.valueOf(Individuos.get(i).getVFitness());
        }
        FuncAG.ordInd(Individuos);
        return t;
    }
    public ArrayList<Individuo> getGeneracion(){
        return Individuos;
    }
    
    public double getPromedio(){
        return prom;
    }
    
}
