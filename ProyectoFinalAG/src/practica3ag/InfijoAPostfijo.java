/*
Proyecto Final del Curso
Integrantes:
    -Pérez González Mayer Abraham
    -Urbina Villa Noe
Genetic Algorithms 3CM1
*/
//FALTAN CORRECCIONES
package practica3ag;

import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.Stack;



public class InfijoAPostfijo {

    /**
     */
    //public static void main(String[] args) {
    public static String InfiPostfi(String exp){
        
    
        //Depurar la expresion algebraica
        String expr = depurar(exp);
        System.out.println("Expresion dep:"+expr);
        String[] arrayInfix = expr.split(" ");

        //Declaración de las pilas
        Stack < String > E = new Stack <  > (); //Pila entrada
        Stack < String > P = new Stack <  > (); //Pila temporal para operadores
        Stack < String > S = new Stack <  > (); //Pila salida

        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
          E.push(arrayInfix[i]);
        }

        //try {
          //Algoritmo Infijo a Postfijo
          while (!E.isEmpty()) {
            switch (pref(E.peek())){
              case 1:
                P.push(E.pop());
                break;
              case 3:
              case 4:
              case 5:
              case 6:
                while(pref(P.peek()) >= pref(E.peek())) {
                  S.push(P.pop());
                }
                P.push(E.pop());
                break; 
              case 2:
                while(!P.peek().equals("(")) {
                  S.push(P.pop());
                }
                P.pop();
                E.pop();
                break; 
              default:
                S.push(E.pop()); 
            } 
          }

          //Eliminacion de `impurezas´ en la expresiones algebraicas
          String infix = expr.replace(" ", "");
          String postfix = S.toString().replaceAll("[\\]\\[,]", "");

          //Mostrar resultados:
          System.out.println("Expresion Infija: " + infix);
          System.out.println("Expresion Postfija: " + postfix);


        //}catch(Exception ex){ 
          //System.out.println("Error en la expresión algebraica");
         // System.err.println(ex);
        //}

        return postfix;
 }

  private static String depurar(String s) {
    s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
    s = "(" + s + ")";
    String simbols = "+-*/()^";
    String str = "";
 
    //Deja espacios entre operadores
    for (int i = 0; i < s.length(); i++) {
      if (simbols.contains("" + s.charAt(i))) {
        str += " " + s.charAt(i) + " ";
      }else str += s.charAt(i);
    }
    return str.replaceAll("\\s+", " ").trim();
  }

  //Jerarquia de los operadores
  private static int pref(String op) {
    int prf = 99;
    if(op.equals("S") || op.equals("C") || op.equals("T") || op.equals("L")) prf = 6;
    if (op.equals("^")) prf = 5;
    if (op.equals("*") || op.equals("/")) prf = 4;
    if (op.equals("+") || op.equals("-")) prf = 3;
    if (op.equals(")")) prf = 2;
    if (op.equals("(")) prf = 1;
    return prf;
  }
  
  public String instvalor(int valor){
    String exp;
    return String.valueOf(valor);
}
  
    public static double Evaluation(String expr,int valor){  
   
    String exprP = InfiPostfi(expr);
        
    double resultado;
    //Entrada (Expresión en Postfija)
    String[] post = exprP.split(" ");   
   
    //Declaración de las pilas
    Stack < String > E = new Stack <  > (); //Pila entrada
    Stack < String > P = new Stack <  > (); //Pila de operandos

    //Añadir post (array) a la Pila de entrada (E)
    for (int i = post.length - 1; i >= 0; i--) {
      E.push(post[i]);
    }

    //Algoritmo de Evaluación Postfija
    String operadores = "+-*/%^";
    String ingcognitas = "xy";
    String funciones = "SCTL";//Error con las funciones trigonometricas
    while (!E.isEmpty()) {
      if (operadores.contains("" + E.peek())) {
        P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
      }
      else if(ingcognitas.contains("" + E.peek())){
          E.pop();
          P.push(String.valueOf(valor) + "");
      }
      else if(funciones.contains("" + E.peek())){
          P.push(String.valueOf(evaluarf(E.pop(),valor)+""));
      }
      else {
        P.push(E.pop());
      }
    }

    //Mostrar resultados:
    System.out.println("Expresion: " + exprP);
    System.out.println("Resultado: " + P.peek());
    resultado = Double.parseDouble(P.peek()); //si regresa double? o entero?
    return resultado;
  }

  private static double evaluar(String op, String n2, String n1) {
    double num1 = Double.parseDouble(n1);
    double num2 = Double.parseDouble(n2);
    if (op.equals("+")) return (num1 + num2);
    if (op.equals("-")) return (num1 - num2);
    if (op.equals("*")) return (num1 * num2);
    if (op.equals("/")) return (num1 / num2);
    if (op.equals("%")) return (num1 % num2);
    if (op.equals("^")) return (int) (Math.pow(num1, num2));
    return 0;
  }
  
    private static double evaluarf(String op, int valor) {
    if (op.equals("S")) return Math.sin(valor);
    if (op.equals("C")) return Math.cos(valor);
    if (op.equals("T")) return Math.tan(valor);
    if (op.equals("L")) return Math.log(valor);
    return 0;
  }
  
}
  //Depurar expresión algebraica

