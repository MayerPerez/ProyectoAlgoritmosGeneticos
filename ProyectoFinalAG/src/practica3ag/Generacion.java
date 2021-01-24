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
    
    public Generacion(ArrayList<Individuo> g,int nF){
        Individuos = g;
        prom = 0;
        //Se evaluan los individuos en la funcion fitness selecionada
        for(Individuo indAux: Individuos){
            switch (nF) {
                case 0:
                    indAux.setVFitness((double)f1(indAux.getFenotipo()));
                    break;
                case 1:
                    indAux.setVFitness(f2(indAux.getFenotipo()));
                    break;
                case 2:
                    indAux.setVFitness(f3(indAux.getFenotipo()));
                    break;
                default:
                    break;
            }
            prom += indAux.getVFitness();
        }
        prom = prom/g.size();
    }
    
    //-----------FUNCIONES DE APTITUP-------------
    private int f1(int fen){
        return (int) Math.pow(fen, 2);
    }
    
    private double f2(int fen){
        return Math.abs((fen-5)/(2+Math.sin(fen)));
    }
    
    private double f3(int fen){
        if(fen == 0)
            return 0;
        return fen*Math.log(fen)*Math.cos(fen);
    }
    //-----------------------------------------
    
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
