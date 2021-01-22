/*
Practica 3:Diseño de un AG simnple
Pérez González Mayer Abraham
Genetic Algorithms 3CM1
*/
package practica3ag;

import java.util.ArrayList;


public class Optimizacion {
    private ArrayList<Individuo> gen = new ArrayList<>();
    private ArrayList<Generacion> generaciones = new ArrayList<>();
    private int limMax;
    private  int limMin;
    private int numBits;
    private int numFunction;
    private int tipoRepre;
    private int numInd;
    
    public Optimizacion(int nF, int lMin, int lMax,int tR, int nI){
        limMin = lMin;
        limMax = lMax;
        numBits = FuncAG.numBits(lMax);
        numFunction = nF;
        tipoRepre = tR;
        numInd = nI;
        //Se generan individuos aleatorios sin repeticion
        for(int i=0;i<nI;i++){
            Individuo ind = new Individuo(FuncAG.genIndividuo(numBits),tR);
            boolean flagE = false;
            for (Individuo indAux: gen){
                if(ind.getFenotipo() == indAux.getFenotipo())
                    flagE = true;
            }
            if(flagE || ind.getFenotipo()< lMin || ind.getFenotipo()>lMax){
                i--;
            }
            else{
                gen.add(ind);
            }
        }
        generaciones.add(new Generacion(gen,nF));
        
    }
    
    public void iteracion(int tS,int tC,int tM){
        ArrayList<Individuo> aux;
        aux = seleccion(tS);
        aux = cruza(tC,aux);
        aux = mutacion(tM,aux);
        generaciones.add(new Generacion(aux,numFunction));
    }
    
    public ArrayList<Individuo> seleccion(int tS){
        int g = generaciones.size()-1;
        ArrayList<Individuo> aux;
        switch(tS){
            case 0:
                aux = FuncAG.selecEstandar(generaciones.get(g).getGeneracion());
            break;
            case 1:
                aux = FuncAG.selecPorRuleta(generaciones.get(g).getGeneracion());
            break;
            case 2:
                aux = FuncAG.selecEstocasticaConRemplazo(generaciones.get(g).getGeneracion(),numFunction);
            break;
            case 3:
                aux = FuncAG.selecEstocasticaSinRemplazo(generaciones.get(g).getGeneracion(),numFunction);
            break;
            default:
                aux = null;
            break;
        }
        return aux;
    }
    
    public ArrayList<Individuo> cruza(int tC,ArrayList<Individuo> sel){
        ArrayList<Individuo> aux;
        switch(tC){
            case 0:
                aux = FuncAG.cruzaDe1Punto(sel, numBits);
            break;
            case 1:
                aux = FuncAG.cruzaDe2Puntos(sel, numBits);
            break;
            case 2:
                aux = FuncAG.cruzaDeNPuntos(sel, numBits);
            break;
            default:
                aux = null;
            break;
        }
        return aux;
    }
    
    public ArrayList<Individuo> mutacion(int tM,ArrayList<Individuo> hijos){
        ArrayList<Individuo> aux;
        switch(tM){
            case 0:
                aux = FuncAG.mutaAleatoria(hijos, numBits);
            break;
            case 1:
                aux = FuncAG.mutaPorInsercion(hijos, numBits);
            break;
            case 2:
                aux = FuncAG.mutaPorDesplazamiento(hijos, numBits);
            break;
            case 3:
                aux = FuncAG.mutaPorIntercambioReciproco(hijos, numBits);
            break;
            case 4:
                aux = FuncAG.mutaHeuristica(hijos, numBits);
            break;
            default:
                aux = null;
            break;
        }
        return aux;
    }
    
    public ArrayList<Individuo> getGenInicial(){
        return gen;
    }
    
    public int getNumeroIndi(){
        return numInd;
    }
    public int getNumBits(){
        return numBits;
    }
    
    public ArrayList<Generacion> getGeneraciones(){
        return generaciones;
    }
}
