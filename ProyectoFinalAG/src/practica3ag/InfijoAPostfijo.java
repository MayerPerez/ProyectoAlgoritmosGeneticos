package practica3ag;

/**
 *
 * @author Noé
 */
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.Stack;



public class InfijoAPostfijo {

    /**
     */
    //public static void main(String[] args) {
    public String InfiPostfi(){
        
    //Entrada de datos
    System.out.println("*Escribe una expresión algebraica: ");
    Scanner leer = new Scanner(System.in);

    //Depurar la expresion algebraica
    String expr = depurar(leer.nextLine());
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
    String simbols = "+-*/()";
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
  
    public double Evaluation(String expr,int valor){  
   
    double resultado;
    //Entrada (Expresión en Postfija)
    String[] post = expr.split(" ");   
   
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
    String funciones = "SCTLR";
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
    System.out.println("Expresion: " + expr);
    System.out.println("Resultado: " + P.peek());
    resultado = parseInt(P.peek());
    return resultado;
  }

  private static int evaluar(String op, String n2, String n1) {
    int num1 = Integer.parseInt(n1);
    int num2 = Integer.parseInt(n2);
    if (op.equals("+")) return (num1 + num2);
    if (op.equals("-")) return (num1 - num2);
    if (op.equals("*")) return (num1 * num2);
    if (op.equals("/")) return (num1 / num2);
    if (op.equals("%")) return (num1 % num2);
    if (op.equals("%")) return (int) (Math.pow(num1, num2));
    return 0;
  }
  
    private static int evaluarf(String op, int valor) {
    if (op.equals("S")) return (int) Math.round(Math.sin(valor));
    if (op.equals("C")) return (int) Math.round(Math.cos(valor));
    if (op.equals("T")) return (int) Math.round(Math.tan(valor));
    if (op.equals("L")) return (int) Math.round(Math.log(valor));
    if (op.equals("R")) return (int) Math.round(Math.sqrt(valor));
    return 0;
  }
  
}
  //Depurar expresión algebraica

