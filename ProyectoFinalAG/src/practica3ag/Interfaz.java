/*
Practica 3:Diseño de un AG simnple
Pérez González Mayer Abraham
Genetic Algorithms 3CM1
*/
package practica3ag;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Interfaz {
    private JFrame jfMain;
    private JFrame jfGraf;
    private JPanel pnlMain;
    private JPanel pnlGraf;
    private JLabel lblSelecF;
    private JRadioButton rbtnf1;
    private JRadioButton rbtnf2;
    private JRadioButton rbtnf3;
    private ButtonGroup grp;
    private JLabel lblf1;
    private JLabel lblf2;
    private JLabel lblf3;
    private JLabel lblSelecTAG;
    private JRadioButton rbtnModGen;
    private JRadioButton rbtnModElit;
    private ButtonGroup grp2;
    private JLabel lblSelecNumGen;
    private JTextField txfNumGen;
    private JLabel lblSelecR;
    private JTextField txfRmin;
    private JTextField txfRmax;
    private JLabel lblSelecModR;
    private JRadioButton rbtnBin;
    private JRadioButton rbtnGray;
    private ButtonGroup grp3;
    private JLabel lblSelecNumInd;
    private JTextField txfNumInd;
    //-------------------------
    private JLabel lblSelecTSelec;
    private JRadioButton rbtnSelEstandar;
    private JRadioButton rbtnSelPorRuleta;
    private JRadioButton rbtnSelEstocasticaCR;
    private JRadioButton rbtnSelEstocasticaSR;
    private ButtonGroup grp4;
    private JLabel lblSelecTCruza;
    private JRadioButton rbtnCruza1Punto;
    private JRadioButton rbtnCruza2Punto;
    private JRadioButton rbtnCruzaNPunto;
    private ButtonGroup grp5;
    private JLabel lblSelectTMutacion;
    private JRadioButton rbtnMutAleatoria;
    private JRadioButton rbtnMutInsercion;
    private JRadioButton rbtnMutDesplazamiento;
    private JRadioButton rbtnMutInterReciproco;
    private JRadioButton rbtnMutHeuristica;
    private ButtonGroup grp6;
    //--------------------------
    private JButton btnAcept;
    private JLabel lblFunction;
    private JLabel lblIndMax;//Falta
    private JLabel lblIndMin;//Falta
    private JButton btnNextGen;
    private JButton btnPrevGen;
    private JTable t;
    private JScrollPane jspt;
    
    private Optimizacion opt;
    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private ChartPanel panelG;
    private int cont;
    private String titles[] = {"No. Individuo","Genotipo","Fenotipo","f(x)"};
    
    public Interfaz(){
        initMainComponents();
    }
    
    private void initMainComponents(){
        int w = 500;
        int h = 530;
        jfMain = new JFrame();
        jfMain.setSize(w, h);
        jfMain.setLocationRelativeTo(null);
        jfMain.setResizable(false);
        jfMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfMain.setLayout(null);
        
        pnlMain = new JPanel(null);
        lblSelecF = new JLabel("Selecciona la funcion a optimizar:");
        rbtnf1 = new JRadioButton();
        rbtnf2 = new JRadioButton();
        rbtnf3 = new JRadioButton();
        grp = new ButtonGroup();
        lblf1 = new JLabel();
        lblf2 = new JLabel();
        lblf3 = new JLabel();
        lblSelecTAG = new JLabel("Selecciona el tipo de algoritmo genetico:");
        rbtnModGen = new JRadioButton("Modelo Generacional");
        rbtnModElit = new JRadioButton("Modelo Elitista");
        grp2 = new ButtonGroup();
        lblSelecNumGen = new JLabel("Introduce el numero de generaciones:");
        txfNumGen = new JTextField();
        lblSelecR = new JLabel("Introduce el rango de la poblacion:");
        txfRmin = new JTextField();
        JLabel a = new JLabel("-");
        txfRmax = new JTextField();
        lblSelecModR = new JLabel("Selecciona el tipo de representacion:");
        rbtnBin = new JRadioButton("Representacion binaria");
        rbtnGray = new JRadioButton("Representacion tipo gray");
        grp3 = new ButtonGroup();
        lblSelecNumInd = new JLabel("Introduce el numero de individuos para la generación inical:");
        txfNumInd = new JTextField();
        lblSelecTSelec = new JLabel("Elige el tipo de seleccion:");
        rbtnSelEstandar = new JRadioButton("Seleccion Estandar");
        rbtnSelPorRuleta = new JRadioButton("Seleccion Por Ruleta");
        rbtnSelEstocasticaCR = new JRadioButton("Seleccion Estoacastica con Remplazo");
        rbtnSelEstocasticaSR = new JRadioButton("Seleccion Estoacastica sin Remplazo");
        grp4 = new ButtonGroup();
        lblSelecTCruza = new JLabel("Selecciona el tipo de cruza:");
        rbtnCruza1Punto = new JRadioButton("Cruza de 1 Punto");
        rbtnCruza2Punto = new JRadioButton("Cruza de 2 Puntos");
        rbtnCruzaNPunto = new JRadioButton("Cruza de N Puntos");
        grp5 = new ButtonGroup();
        lblSelectTMutacion = new JLabel("Selecciona el tipo de mutacion:");
        rbtnMutAleatoria = new JRadioButton("Mutacion Aleatoria");
        rbtnMutInsercion = new JRadioButton("Mutacion Por Insercion");
        rbtnMutDesplazamiento = new JRadioButton("Mutacion Por Desplazamiento");
        rbtnMutInterReciproco = new JRadioButton("Mutacion Por Intercambio Reciproco");
        rbtnMutHeuristica = new JRadioButton("Mutacion Heuristica");
        grp6 = new ButtonGroup();
        btnAcept = new JButton("Aceptar");
        
        lblSelecF.setForeground(Color.red);
        rbtnf1.setSelected(true);
        grp.add(rbtnf1);
        grp.add(rbtnf2);
        grp.add(rbtnf3);
        lblf1.setIcon(new ImageIcon("f1.PNG"));
        lblf2.setIcon(new ImageIcon(new ImageIcon("f2.PNG")
                .getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
        lblf3.setIcon(new ImageIcon("f3.PNG"));
        lblSelecTAG.setForeground(Color.red);
        grp2.add(rbtnModGen);
        grp2.add(rbtnModElit);
        rbtnModGen.setSelected(true);
        lblSelecNumGen.setForeground(Color.red);
        lblSelecR.setForeground(Color.red);
        lblSelecModR.setForeground(Color.red);
        rbtnBin.setSelected(true);
        grp3.add(rbtnBin);
        grp3.add(rbtnGray);
        lblSelecNumInd.setForeground(Color.red);
        lblSelecTSelec.setForeground(Color.red);
        rbtnSelEstandar.setSelected(true);
        grp4.add(rbtnSelEstandar);
        grp4.add(rbtnSelPorRuleta);
        grp4.add(rbtnSelEstocasticaCR);
        grp4.add(rbtnSelEstocasticaSR);
        lblSelecTCruza.setForeground(Color.red);
        rbtnCruza1Punto.setSelected(true);
        grp5.add(rbtnCruza1Punto);
        grp5.add(rbtnCruza2Punto);
        grp5.add(rbtnCruzaNPunto);
        lblSelectTMutacion.setForeground(Color.red);
        rbtnMutAleatoria.setSelected(true);
        grp6.add(rbtnMutAleatoria);
        grp6.add(rbtnMutInsercion);
        grp6.add(rbtnMutDesplazamiento);
        grp6.add(rbtnMutInterReciproco);
        grp6.add(rbtnMutHeuristica);
        
        pnlMain.setBounds(0, 0, w, h);
        lblSelecF.setBounds(3, 0, 200, 20);
        rbtnf1.setBounds(0, 20, 20, 30);
        rbtnf2.setBounds(0, 50, 20, 30);
        rbtnf3.setBounds(0, 80, 20, 30);
        lblf1.setBounds(20, 20, 100, 30);
        lblf2.setBounds(20, 50, 150, 30);
        lblf3.setBounds(20, 80, 160, 30);
        lblSelecTAG.setBounds(240, 0, 250, 20);//3, 130, 250, 20
        rbtnModGen.setBounds(240, 20, 150, 20);
        rbtnModElit.setBounds(240, 50, 150, 20);
        lblSelecNumGen.setBounds(3, 130, 250, 20);//3, 210, 250, 20
        txfNumGen.setBounds(10, 160, 40, 20);
        lblSelecR.setBounds(240, 130, 200, 20);//3, 270, 200, 20
        txfRmin.setBounds(247, 160, 40, 20);
        a.setBounds(297, 160, 10, 20);
        txfRmax.setBounds(317, 160, 40, 20);
        lblSelecModR.setBounds(3, 200, 250, 20);//3, 330, 250, 20
        rbtnBin.setBounds(0, 220, 200, 20);
        rbtnGray.setBounds(0, 240, 200, 20);
        lblSelecTSelec.setBounds(240, 200, 200, 20);
        rbtnSelEstandar.setBounds(240, 220, 200, 20);
        rbtnSelPorRuleta.setBounds(240, 240, 200, 20);
        rbtnSelEstocasticaCR.setBounds(240, 260, 250, 20);
        rbtnSelEstocasticaSR.setBounds(240, 280, 250, 20);
        lblSelecTCruza.setBounds(0, 300, 200, 20);
        rbtnCruza1Punto.setBounds(0, 320, 200, 20);
        rbtnCruza2Punto.setBounds(0, 340, 200, 20);
        rbtnCruzaNPunto.setBounds(0, 360, 200, 20);
        lblSelectTMutacion.setBounds(240, 300, 200, 20);
        rbtnMutAleatoria.setBounds(240, 320, 200, 20);
        rbtnMutInsercion.setBounds(240, 340, 200, 20);
        rbtnMutDesplazamiento.setBounds(240, 360, 200, 20);
        rbtnMutInterReciproco.setBounds(240, 380, 250, 20);
        rbtnMutHeuristica.setBounds(240, 400, 200, 20);
        lblSelecNumInd.setBounds(3, 420, 350, 20);
        txfNumInd.setBounds(10, 440, 40, 20);
        btnAcept.setBounds(180, 470, 100, 20);
        
        pnlMain.add(lblSelecF);
        pnlMain.add(rbtnf1);
        pnlMain.add(rbtnf2);
        pnlMain.add(rbtnf3);
        pnlMain.add(lblf1);
        pnlMain.add(lblf2);
        pnlMain.add(lblf3);
        pnlMain.add(lblSelecTAG);
        pnlMain.add(rbtnModGen);
        pnlMain.add(rbtnModElit);
        pnlMain.add(lblSelecNumGen);
        pnlMain.add(txfNumGen);
        pnlMain.add(lblSelecR);
        pnlMain.add(txfRmin);
        pnlMain.add(a);
        pnlMain.add(txfRmax);
        pnlMain.add(lblSelecModR);
        pnlMain.add(rbtnBin);
        pnlMain.add(rbtnGray);
        pnlMain.add(lblSelecTSelec);
        pnlMain.add(rbtnSelEstandar);
        pnlMain.add(rbtnSelPorRuleta);
        pnlMain.add(rbtnSelEstocasticaCR);
        pnlMain.add(rbtnSelEstocasticaSR);
        pnlMain.add(lblSelecTCruza);
        pnlMain.add(rbtnCruza1Punto);
        pnlMain.add(rbtnCruza2Punto);
        pnlMain.add(rbtnCruzaNPunto);
        pnlMain.add(lblSelectTMutacion);
        pnlMain.add(rbtnMutAleatoria);
        pnlMain.add(rbtnMutInsercion);
        pnlMain.add(rbtnMutDesplazamiento);
        pnlMain.add(rbtnMutInterReciproco);
        //pnlMain.add(rbtnMutHeuristica);       //Falta terminar metodo
        
        pnlMain.add(lblSelecNumInd);
        pnlMain.add(txfNumInd);
        pnlMain.add(btnAcept);
        
        initMainEvents();
        
        jfMain.add(pnlMain);
        jfMain.setVisible(true);
    }
    private void initMainEvents(){
        btnAcept.addActionListener((ActionEvent event) -> {
            try{
                int nF,lMin,lMax,tR,nI,dif,tS,tC,tM,nG;
                lblFunction = new JLabel();
                
                if(rbtnf1.isSelected()){
                    nF = 0;
                    lblFunction.setIcon(new ImageIcon("f1.PNG")); ;
                }
                else if(rbtnf2.isSelected()){
                    nF = 1;
                    lblFunction.setIcon(new ImageIcon(new ImageIcon("f2.PNG")
                    .getImage().getScaledInstance(150, 30, Image.SCALE_SMOOTH)));
                }
                else{
                    nF = 2;
                    lblFunction.setIcon(new ImageIcon("f3.PNG")); ;
                }
                nG = Integer.parseInt(txfNumGen.getText());
                if(nG <= 0){
                    throw new LanzarException(3);
                }
                lMin = Integer.parseInt(txfRmin.getText());
                lMax = Integer.parseInt(txfRmax.getText());
                if(lMin == lMax || lMin>lMax)
                    throw new LanzarException(0);
                dif = lMax-lMin;
                if(rbtnBin.isSelected()){
                    tR = 0;
                }
                else
                    tR = 1;
                nI = Integer.parseInt(txfNumInd.getText());
                if(nI%2 != 0)
                    throw new LanzarException(1);
                if(nI>dif)
                    throw new LanzarException(2);
                if(rbtnSelEstandar.isSelected()){
                    tS = 0;
                }
                else if(rbtnSelPorRuleta.isSelected())
                    tS = 1;
                else if(rbtnSelEstocasticaCR.isSelected())
                    tS = 2;
                else
                    tS = 3;
                if(rbtnCruza1Punto.isSelected()){
                    tC = 0;
                }
                else if(rbtnCruza2Punto.isSelected())
                    tC = 1;
                else
                    tC = 2;
                if(rbtnMutAleatoria.isSelected()){
                    tM = 0;
                }
                else if(rbtnMutInsercion.isSelected()){
                    tM = 1;
                }
                else if(rbtnMutDesplazamiento.isSelected())
                    tM = 2;
                else if(rbtnMutInterReciproco.isSelected())
                    tM = 3;
                else
                    tM = 4;
                cont = 0;
                opt = new Optimizacion(nF,lMin,lMax,tR,nI);
                for(int i=0;i<nG;i++){
                    opt.iteracion(tS, tC, tM);
                }
                //lblIndMax.setText("Mejor individuo: #"+(opt.getMax()+1));
                //lblIndMin.setText("Peor individuo: #"+(opt.getMin()+1));
                showData();
            }
            catch(LanzarException e){
                //e.printStackTrace();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(pnlMain,"Datos introducidos incorrectos.","Error",
                        JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        });
    }
    private void showData(){
        int w = 700;
        int h = 660;
        
        jfGraf = new JFrame();
        jfGraf.setSize(w, h);
        jfGraf.setTitle("Generacion Inicial");
        jfGraf.setLocation(300, 40);
        jfGraf.setResizable(false);
        jfGraf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfGraf.setLayout(null);
        
        pnlGraf = new JPanel(null);
        JLabel lblWordF = new JLabel("Funcion: ");
        t = new JTable();
        jspt = new JScrollPane(t);
        btnNextGen = new JButton("SIG");
        btnPrevGen = new JButton("ANT");
        lblIndMax = new JLabel();
        lblIndMin = new JLabel();
        lblIndMax.setText("Mejor individuo: #"+(opt.getGeneraciones().get(0).getMax()+1));
        lblIndMin.setText("Peor individuo: #"+(opt.getGeneraciones().get(0).getMin()+1));
        
        t.setModel(new DefaultTableModel(opt.getGeneraciones().get(0).getTable(),titles){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        
        btnPrevGen.setVisible(false);
        
        ArrayList<Individuo> aux = FuncAG.ordInd(opt.getGeneraciones().get(0).getGeneracion());
        
        dataset = new DefaultCategoryDataset();
        for(int i=0;i<opt.getNumeroIndi();i++){
            dataset.setValue(aux.get(i).getVFitness(),"Valor Fitness",
                    String.valueOf(aux.get(i).getFenotipo() ));
        }
        
        chart = ChartFactory.createLineChart("Histograma", "x", "f(x)", dataset,
                PlotOrientation.VERTICAL,true,true,false);
        
        panelG = new ChartPanel(chart);
        
        
        pnlGraf.setBounds(0, 0, w, h);
        btnPrevGen.setBounds(5, 5, 70, 30);
        lblWordF.setBounds(100, 5, 70, 30);
        lblFunction.setBounds(170, 5, 160, 30);
        lblIndMax.setBounds(300, 5, 150, 30);
        lblIndMin.setBounds(480, 5, 150, 30);
        btnNextGen.setBounds(620, 5, 70, 30);
        jspt.setBounds(0, 40, w, 180);
        panelG.setBounds(0, 220, w, 410);
        
        pnlGraf.add(btnPrevGen);
        pnlGraf.add(lblWordF);
        pnlGraf.add(lblFunction);
        pnlGraf.add(lblIndMax);
        pnlGraf.add(lblIndMin);
        pnlGraf.add(btnNextGen);
        pnlGraf.add(jspt);
        pnlGraf.add(panelG);
        
        jfGraf.add(pnlGraf);
        
        initEventShowData();
        
        jfGraf.setVisible(true);
    }
    
    private void initEventShowData(){
        btnNextGen.addActionListener((ActionEvent event) -> {
            btnPrevGen.setVisible(true);
            cont+=1;
            
            jfGraf.setTitle("Generacion "+cont);
            lblIndMax.setText("Mejor individuo: #"+(opt.getGeneraciones().get(cont).getMax()+1));
            lblIndMin.setText("Peor individuo: #"+(opt.getGeneraciones().get(cont).getMin()+1));
            
            t.setModel(new DefaultTableModel(opt.getGeneraciones().get(cont).getTable(),titles){
                @Override
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            });
            /*PROBLEMAS AL ACTUALIZAR LA GRAFICA Y AL ORDENAR LOS DATOS SEGUN SU FENOTIPO
            */
            ArrayList<Individuo> aux = FuncAG.ordInd(opt.getGeneraciones().get(cont).getGeneracion());
            dataset.clear();
            for(int i=0;i<aux.size();i++){
                dataset.setValue(aux.get(i).getVFitness(),"Valor Fitness",
                        String.valueOf(aux.get(i).getFenotipo() ));
            }
            /*chart = ChartFactory.createLineChart("Histograma", "x", "f(x)", dataset,
                PlotOrientation.VERTICAL,true,true,false);
            panelG = new ChartPanel(chart);*/
            //System.out.println(opt.getGeneraciones().size()+" "+cont);
            if(cont == opt.getGeneraciones().size()-1){
                btnNextGen.setVisible(false);
            }
        });
        btnPrevGen.addActionListener((ActionEvent event) -> {
            btnNextGen.setVisible(true);
            cont-=1;
            
            jfGraf.setTitle("Generacion "+cont);
            lblIndMax.setText("Mejor individuo: #"+(opt.getGeneraciones().get(cont).getMax()+1));
            lblIndMin.setText("Peor individuo: #"+(opt.getGeneraciones().get(cont).getMin()+1));
            
            t.setModel(new DefaultTableModel(opt.getGeneraciones().get(cont).getTable(),titles){
                @Override
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            });
            ArrayList<Individuo> aux = FuncAG.ordInd(opt.getGeneraciones().get(cont).getGeneracion());
            dataset.clear();
            for(int i=0;i<aux.size();i++){
                dataset.setValue(aux.get(i).getVFitness(),"Valor Fitness",
                        String.valueOf(aux.get(i).getFenotipo() ));
            }
            /*chart = ChartFactory.createLineChart("Histograma", "x", "f(x)", dataset,
                PlotOrientation.VERTICAL,true,true,false);
            panelG = new ChartPanel(chart);*/
            if(cont == 0){
                jfGraf.setTitle("Generacion Inicial");
                btnPrevGen.setVisible(false);
            }
            
        }); 
    }
    
    public class LanzarException extends Exception{
        
        public LanzarException(int n){
            if(n == 0)
                JOptionPane.showMessageDialog(pnlMain,"El rango introducido es incorrecto.","Rango erroneo", JOptionPane.WARNING_MESSAGE);
            else if(n == 1)
                JOptionPane.showMessageDialog(pnlMain, "El numero de individuos de la generacion\ninicial debe ser par.",
                    "Numero impar",JOptionPane.WARNING_MESSAGE);
            else if(n == 2)
                JOptionPane.showMessageDialog(pnlMain,"El numero de individuos es mayor a la longitud rango.",
                        "Numero supera rango", JOptionPane.WARNING_MESSAGE);
            else if(n == 3)
                JOptionPane.showMessageDialog(pnlMain, "El numero de generaciones debe ser\nmayor o igual a 1.",
                    "Pocas generaciones",JOptionPane.WARNING_MESSAGE);
        }
    }
    
}
