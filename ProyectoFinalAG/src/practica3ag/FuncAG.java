/*
Proyecto Final del Curso
Integrantes:
    -Pérez González Mayer Abraham
    -Urbina Villa Noe
Genetic Algorithms 3CM1
*/
package practica3ag;

import java.util.ArrayList;
import java.util.Random;

public class FuncAG {
    //FUNCION PARA OBTENER EL NUMERO DE BITS
    public static int numBits(int Rmax){
        if(Rmax == 0)
            return 1;
        return (int) Math.ceil((Math.log(Rmax)/Math.log(2))+0.000001);
    }
    //FUNCION PARA GENERAR UN NUEVO INDIVIUO ALEATORIAMENTE
    public static byte[] genIndividuo(int nB){
        byte nInd[] = new byte[nB];
        Random r = new Random();
        float aux;
        for(int i=0;i<nB;i++){
            aux = r.nextFloat();
            if(aux < 0.5)
                nInd[i] = 0;
            else
                nInd[i] = 1;
        }
        return nInd;
    }
    //FUNCION PARA OBTENER EL FENOTIPO DE UN INDIVIDUO
    public static int getFenotipo(byte g[]){
        int f = 0;
        int c=0;
        for(int i=g.length-1;i>=0;i--){
            if(g[i] == 1){
                f+=Math.pow(2, c);
            }
            c++;
        }
        return f;
    }
    //FUNCION PARA OBTENER EL GENOTIPO
    public static byte[] getGenotipo(int f,int nB){
        byte g[] = new byte[nB];
        int aux,nf = f;
        int cont = 0;
        for(int i =nB-1; i>=0;i--){
            aux = (int) Math.pow(2, i);
            if(nf >= aux){
                nf = nf - aux;
                g[cont] = 1;
            }
            else
                g[cont] = 0;
            cont ++;
        }
        return g;
    }
    //FUNCION PARA ORDENAR LOS INDIVIDUOS SEGUN SU FENOTIPO
    public static ArrayList<Individuo> ordInd(ArrayList<Individuo> gen){
        Individuo aux;
        ArrayList<Individuo> genaux = gen;
        for(int i=0;i<genaux.size();i++){
            for(int j=i;j<genaux.size();j++){
                if(genaux.get(i).getFenotipo() > genaux.get(j).getFenotipo()){
                    aux = genaux.get(i);
                    genaux.set(i, genaux.get(j));
                    genaux.set(j, aux);
                }
            }
        }
        return genaux;
    }
    //FUNCION QUE CONVIERTE ARRAGLO DE GRAY A BINARIO
    public static byte[] grayToBin(byte g[]){
        byte bg[] = new byte[g.length];
        bg[0] = g[0];
        for(int i=1;i<g.length;i++){
            bg[i]=(byte) (bg[i-1] ^ g[i]);
        }
        return bg;
    }
    
    
    //----FUNCIONES DE SELECCION-------
    public static ArrayList<Individuo> selecEstandar(ArrayList<Individuo> genIni){
        ArrayList<Individuo> aux = new ArrayList<>(); //lista para los padres
        ArrayList<Double> probCruzaInd = new ArrayList<>(); //Probabilidad de cruza individual
        ArrayList<Double> rango = new ArrayList<>(); //Rangos generados
        double suma = 0, daux = 0;
        int cont = 0;
        Random r = new Random();
        //ciclo para calcular la sumatoria de los valores fitness
        for(Individuo ind: genIni){
            suma+=ind.getVFitness();
        }
        //ciclo para determinar los rangos de probablidad de cruza
        for(Individuo ind: genIni){
            probCruzaInd.add(ind.getVFitness()/suma);
            daux += probCruzaInd.get(cont);
            rango.add(daux);
            cont++;
        }
        //ciclo para la seleccion de los padres
        for(int i=0;i<genIni.size();i++){
            daux = r.nextFloat();
            for(int j=0;j<rango.size();j++){
                if(daux <= rango.get(j)){
                    aux.add(genIni.get(j));
                    break;
                }
            }
        }
        return aux;
    }
    public static ArrayList<Individuo> selecPorRuleta(ArrayList<Individuo> genIni){
        ArrayList<Individuo> aux = new ArrayList<>(); //lista para los padres
        ArrayList<Double> probCruzaInd = new ArrayList<>(); //Probabilidad de cruza individual
        ArrayList<Double> probAcumulada = new ArrayList<>(); //Rangos generados
        double suma = 0, daux = 0;
        int cont = 0;
        Random r = new Random();
        
        //ciclo para calcular la sumatoria de los valores fitness
        for(Individuo ind: genIni){
            suma+=ind.getVFitness();
        }
        //ciclo para determinar la probabilidad de cruza individual y acumulada
        for(Individuo ind: genIni){
            probCruzaInd.add(ind.getVFitness()/suma);
            daux += probCruzaInd.get(cont);
            probAcumulada.add(daux);
            cont++;
        }
        //ciclo para la seleccion de los padres
        for(int i=0;i<genIni.size();i++){
            daux = r.nextFloat();
            for(int j=0;j<probAcumulada.size();j++){
                if(daux <= probAcumulada.get(j)){
                    aux.add(genIni.get(j));
                    break;
                }
            }
        }
        return aux;
    }
    public static ArrayList<Individuo> selecEstocasticaConRemplazo(ArrayList<Individuo> genIni,String f){
        ArrayList<Individuo> aux = new ArrayList<>();
        Generacion gaux = new Generacion(genIni,f);
        int cont=0;
        double ve;
        //ciclo para obtener el valor esperado de cada individuo
        for(Individuo ind: gaux.getGeneracion()){
            ve = ind.getVFitness()/gaux.getPromedio();
            if(ve >= 1.0){
                aux.add(ind);//agregamos individuo con entero en el ve
                cont++;
            }
        }
        //si hacen falta padres
        if(cont != genIni.size()){
            Random r = new Random();
            int flip;
            //recorremos los individuos haciendo flip
            for(int i=0;i<genIni.size();i++){
                flip = r.nextInt(2);
                if(flip == 1){
                    aux.add(genIni.get(i));
                    cont++;
                }
                if(cont == genIni.size())
                    break;
            }
        }
        return aux;
    }
    public static ArrayList<Individuo> selecEstocasticaSinRemplazo(ArrayList<Individuo> genIni,String f){
        ArrayList<Individuo> aux = new ArrayList<>();
        Generacion gaux = new Generacion(genIni,f);
        int cont=0;
        double ve;
        //ciclo para obtener el valor esperado de cada individuo
        for(Individuo ind: gaux.getGeneracion()){
            ve = ind.getVFitness()/gaux.getPromedio();
            if(ve >= 1.0){
                aux.add(ind);//agregamos individuo con entero en el ve
                cont++;
            }
        }
        //si hacen falta padres
        if(cont != genIni.size()){
            int i=0;
            //generamos la ruleta
            ArrayList<Individuo> gRul = selecPorRuleta(genIni);
            //ciclo para agregar los padres que faltan obtenidos de la ruleta
            while(cont != genIni.size()){
                aux.add(gRul.get(i));
                i++;
                cont++;
            }
        }
        return aux;
    }
    //---------------------------------
    
    //----FUNCIONES DE CRUZA-----------
    public static ArrayList<Individuo> cruzaDe1Punto(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = new ArrayList<>();
        Random r = new Random();
        int pc; 
        byte h1[];
        byte h2[];
        byte baux;
        
        for(int i=0;i<genAnt.size();i+=2){
            pc = r.nextInt(nB-1)+1; //punto de cruza
            h1 = genAnt.get(i).getGenotipo();
            h2 = genAnt.get(i+1).getGenotipo();
            for(int j=0;j<nB;j++){
                if(j>=pc){
                    baux = h1[j];
                    h1[j] = h2[j];
                    h2[j] = baux;
                }
            }
            aux.add(new Individuo(h1,genAnt.get(i).getTipoRepre()));
            aux.add(new Individuo(h2,genAnt.get(i).getTipoRepre()));
        }
        
        return aux;
    }
    public static ArrayList<Individuo> cruzaDe2Puntos(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = new ArrayList<>();
        Random r = new Random();
        int pc1,pc2,i=0; 
        byte h1[];
        byte h2[];
        byte baux;
        
        while(i<genAnt.size()){
            
            pc1 = r.nextInt(nB-1)+1; //punto de cruza 1
            pc2 = r.nextInt(nB-1)+1; //punto de cruza 2
            
            if(pc1 < pc2){
                h1 = genAnt.get(i).getGenotipo();
                h2 = genAnt.get(i+1).getGenotipo();
                for(int j=0;j<nB;j++){
                    if(j>=pc1 && j<=pc2){
                        baux = h1[j];
                        h1[j] = h2[j];
                        h2[j] = baux;
                    }
                }
                aux.add(new Individuo(h1,genAnt.get(i).getTipoRepre()));
                aux.add(new Individuo(h2,genAnt.get(i).getTipoRepre()));
                i+=2;
            }
        }
        
        return aux;
    }
    
    public static ArrayList<Individuo> cruzaDeNPuntos(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = new ArrayList<>();
        ArrayList<Integer> pE = new ArrayList<>();
        byte h1[];
        byte h2[];
        byte baux;
        Random r = new Random();
        int cont,p,n;
        //ciclo para cruzar todos los padres
        for(int i=0;i<genAnt.size();i+=2){
            //generamos numero de pociones aleatorias a seleccionar
            n = r.nextInt(nB+1);
            cont=0;
            //ciclo para generar diferentes posiciones aleatorias
            while(cont != n){
                p = r.nextInt(nB);
                if(!pE.contains(p)){
                    pE.add(p);
                    cont++;
                }
            }
            //cruza uniforme
            h1 = genAnt.get(i).getGenotipo();
            h2 = genAnt.get(i+1).getGenotipo();
            
            for(Integer index: pE){
                baux = h1[index];
                h1[index] = h2[index];
                h2[index] = baux;
            }
            
            aux.add(new Individuo(h1,genAnt.get(i).getTipoRepre()));
            aux.add(new Individuo(h2,genAnt.get(i).getTipoRepre()));
            pE.clear();
        }
        
        return aux;
    }
    //---------------------------------
    
    //------FUNCIONES DE MUTACION------
    public static ArrayList<Individuo> mutaAleatoria(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = genAnt;
        int nM = (int) (genAnt.size()*0.2); //determina cuantos individuos van a ser mutados
        int iS,aS;
        byte gaux[];
        Random r = new Random();
        for(int i=0;i<nM;i++){
            iS = r.nextInt(genAnt.size());//selecciona un individuo aleatoriamente
            aS = r.nextInt(nB);//selecciona un alelo del inidivuo aleatoriamente
            gaux = aux.get(iS).getGenotipo();//obtenemos genotipo de ind seleccionado
            //intercambio de alelo
            if(gaux[aS] == 1){
                gaux[aS] = 0;
            }
            else{
                gaux[aS] = 1;
            }
            aux.remove(iS);//quitamos elemento
            aux.add(iS, new Individuo(gaux,genAnt.get(0).getTipoRepre()));//añadimos elemento mutado
        }
        return aux;
    }
    public static ArrayList<Individuo> mutaPorInsercion(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = genAnt;
        int nM = (int) (genAnt.size()*0.2); //determina cuantos individuos van a ser mutados
        int iS,poS,pdS,i=0;
        byte gaux[];
        Random r = new Random();
        while(i<nM){
            poS = r.nextInt(nB);//selecciona la posicion origen aleatoriamente
            pdS = r.nextInt(nB);//selecciona la posicion destino aleatoriamente
            if(poS > pdS){
                iS = r.nextInt(genAnt.size());//selecciona un individuo aleatoriamente
                gaux = aux.get(iS).getGenotipo();//obtenemos genotipo de ind seleccionado
                desp(gaux,poS,pdS); //mutacion
                aux.remove(iS);//quitamos elemento
                aux.add(iS, new Individuo(gaux,genAnt.get(0).getTipoRepre()));//añadimos elemento mutado
                i++;
            }
            
        }
        return aux;
    }
    public static ArrayList<Individuo> mutaPorDesplazamiento(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = genAnt;
        int nM = (int) (genAnt.size()*0.2); //determina cuantos individuos van a ser mutados
        int iS,poS,pdS,i=0,nD,j;
        byte gaux[];
        Random r = new Random();
        while(i<nM){
            nD = r.nextInt(nB/2);//generamos numero de desplazamientos
            j=0;
            iS = r.nextInt(genAnt.size());//selecciona un individuo aleatoriamente
            gaux = aux.get(iS).getGenotipo();//obtenemos genotipo de ind seleccionado
            while(j<nD){
                poS = r.nextInt(nB);//selecciona la posicion origen aleatoriamente
                pdS = r.nextInt(nB);//selecciona la posicion destino aleatoriamente
                if(poS > pdS){
                    desp(gaux,poS,pdS); //mutacion
                    aux.remove(iS);//quitamos elemento
                    aux.add(iS, new Individuo(gaux,genAnt.get(0).getTipoRepre()));//añadimos elemento mutado
                    j++;
                }
            }
            i++;   
        }
        return aux;
    }
    public static ArrayList<Individuo> mutaPorIntercambioReciproco(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = genAnt;
        int nM = (int) (genAnt.size()*0.2); //determina cuantos individuos van a ser mutados
        int iS,p1S,p2S,i=0;
        byte gaux[],baux;
        Random r = new Random();
        while(i<nM){
            p1S = r.nextInt(nB);//selecciona la posicion origen aleatoriamente
            p2S = r.nextInt(nB);//selecciona la posicion destino aleatoriamente
            iS = r.nextInt(genAnt.size());//selecciona un individuo aleatoriamente
            gaux = aux.get(iS).getGenotipo();//obtenemos genotipo de ind seleccionado
            //intercambio
            baux = gaux[p1S];
            gaux[p1S] = gaux[p2S];
            gaux[p2S] = baux;
            
            aux.remove(iS);//quitamos elemento
            aux.add(iS, new Individuo(gaux,genAnt.get(0).getTipoRepre()));//añadimos elemento mutado
            i++;
        }
        return aux;
    }
    //FALTA TERMINAR METODO DE MUTACION
    public static ArrayList<Individuo> mutaHeuristica(ArrayList<Individuo> genAnt,int nB){
        ArrayList<Individuo> aux = genAnt;
        ArrayList<Integer> genesS = new ArrayList<>();
        int nM = (int) (genAnt.size()*0.2); //determina cuantos individuos van a ser mutados
        int iS,gS,i=0,n,c;
        byte gaux[],baux;
        Random r = new Random();
        while(i<nM){
            gS = r.nextInt(nB+1);//selecciona el numero de genes aleatoriamente
            iS = r.nextInt(genAnt.size());//selecciona un individuo aleatoriamente
            gaux = aux.get(iS).getGenotipo();//obtenemos genotipo de ind seleccionado
            c=0;
            System.out.println(gS);
            //seleccionamos genes a ser permutados
            while(c != gS){
                n = r.nextInt(nB);
                if(!genesS.contains(n)){
                    genesS.add(n);
                    System.out.print(genesS.get(c)+" ");
                    c++;
                }
            }
            System.out.println("");
            //aux.remove(iS);//quitamos elemento
            //aux.add(iS, new Individuo(gaux,genAnt.get(0).getTipoRepre()));//añadimos elemento mutado
            i++;
        }
        return aux;
    }
    //---------------------------------
    private static void desp(byte or[],int po,int pd){
        ArrayList<Byte> b = new ArrayList<>();
        byte aux;
        for(int i=0;i<or.length;i++){
            b.add(or[i]);
        }
        aux = b.remove(po);
        b.add(pd, aux);
        for(int i=0;i<or.length;i++){
            or[i]=b.get(i);
        } 
    }
    public static Individuo mejorVecino(byte ind[],ArrayList<Integer> genes){
        ArrayList<Individuo> vecinos = new ArrayList<>();
        byte baux[] = new byte[ind.length];
        byte bg[] = new byte[genes.size()];
        //copiamos genotipo
        for(int c=0;c<ind.length;c++){
            baux[c] = ind[c];
            System.out.print(ind[c]+" ");
        }
        System.out.println("");
        for(int i=0;i<genes.size();i++){
            bg[i] = baux[genes.get(i)];
        }
        
        return null;
    }
}
