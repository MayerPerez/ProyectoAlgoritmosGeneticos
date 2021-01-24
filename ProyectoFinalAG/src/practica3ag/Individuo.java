/*
Proyecto Final del Curso
Integrantes:
    -Pérez González Mayer Abraham
    -Urbina Villa Noe
Genetic Algorithms 3CM1
*/
package practica3ag;

public class Individuo {
    private int Fenotipo;
    private byte Genotipo[];
    private double vFitness;
    private int tipoRepre;
    
    public Individuo(byte g[],int tR){
        Genotipo = g;
        tipoRepre = tR;
        if(tR == 0)
            Fenotipo = FuncAG.getFenotipo(g);
        else if(tR == 1)
            Fenotipo = FuncAG.getFenotipo(FuncAG.grayToBin(g));
    }
    
    public byte[] getGenotipo(){
        return Genotipo;
    }
    
    public int getFenotipo(){
        return Fenotipo;
    }
    public String getGenotipoString(){
        String gS = "";
        for(int i=0;i<Genotipo.length;i++){
            gS+=Genotipo[i]+" ";
        }
        return gS;
    }
    public void setVFitness(double vF){
        vFitness = vF;
    }
    public double getVFitness(){
        return vFitness;
    }
    public int getTipoRepre(){
        return tipoRepre;
    }
}
