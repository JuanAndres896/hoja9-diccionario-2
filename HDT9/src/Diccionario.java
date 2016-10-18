import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
/*
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos - Seccion 31
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Guatemala, septiembre 27 de 2016
*/
public class Diccionario {
        File archivo = null;
        FileReader fr = null;
        Factory fabrica = new Factory();
        BufferedReader br = null;
        HashMap<Association,String> raiz;
        ArrayList <String> oracion = new ArrayList<String>();
        Scanner teclado = new Scanner(System.in);       
        
    
    public Diccionario(){
        //Se realizan los metodos para obtener todas las palabras del diccionario y traducir el archivo texto.txt
        llenarDiccionario();
        System.out.println("A continuacion se muestran las palabras del diccionario:");
        imprimirArbol(raiz);
        traducirOracion();
    }
    
    public void llenarDiccionario(){
        ArrayList<String> palabras= new ArrayList<String>();
        //Se crea una lista de asociaciones (se creo una clase association aparte):
        System.out.println("Bienvenido al traductor. Ingrese la implementacion que desea usar:\n\n"
                + "1) SplayTree\n2) RedBlackTree\n 3) Mapeo por Hash");
        String op = teclado.nextLine();
        while (!op.equals("1") && !op.equals("2") && !op.equals("3")){ //programacion defensiva
                System.out.println("Error, ingrese como opcion 1, 2 o 3: ");
                op = teclado.nextLine();
            }
        int implementacion = Integer.valueOf(op);
        raiz = fabrica.setMap(implementacion);
        try {
           //Se crea un archivo tipo file que representa al diccionario
           //en la siguiente linea hay que ingresar la dirección completa del archivo de diccionario
           archivo = new File ("C:\\Users\\Rodrigo\\Documents\\2do ciclo 2016\\ADT\\HDT9\\src\\Spanish.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);//BufferedReader es un objeto que puede leer archivos de texto y guardarlos en Strings

           // Lectura del fichero
           String linea;
           //Mientras haya una linea que leer, la agrega a la lista palabras
           while((linea=br.readLine())!=null){
              palabras.add(linea);
           }
        }//excepcion
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
        //Se recorre la lista de palabras
        for(int i=0; i<palabras.size()-1;i++){
            //se obtiene el indice de la coma para separar la palabra en espanol e ingles
               int lugar=palabras.get(i).indexOf(' ');
               String ingles=palabras.get(i).substring(1,lugar);
               String espaniol=palabras.get(i).substring(lugar+1,palabras.get(i).length()-1);
               //se van agregando las acsociaciones
               raiz.insert(new Association(ingles, espaniol));
        }
        
        raiz.setValue(asociaciones.get(0));
        for (int i=1; i<asociaciones.size(); i++){
            insertarNodo(raiz, asociaciones.get(i));
            
        }
     }
    
     public void insertarNodo(SplayTree<Association<String,String>,String> padre, Association<String,String> dato)
    {
        Association<String,String> asociacion=padre.value();
        String llavePadre=asociacion.getKey();
        String llaveDato=dato.getKey();
        //se comparan las llaves del padre y del hijo que se desea insertar para ordenar el arbol
        int num=llavePadre.compareToIgnoreCase(llaveDato);
        //Si la palabra padre es mayor a la de dato, se inserta a la izquierda
        if(num>0 && padre.left()==null){
            padre.setLeft(new SplayTree<Association<String,String>>());
            padre.left().setValue(dato);
        }else if(padre.left()!=null){
            insertarNodo(padre.left(),dato);
        }
        //Si la palabra padre es menor a la de dato, se inserta a la derecha
        if(num<0 && padre.right()==null){
            padre.setRight(new SplayTree<Association<String,String>>(null, null, null,null));
            padre.right().setValue(dato);
        }else if(padre.right()!=null){
            //si ya habia un nodo a la izquierda, se inserta el nuevo a la derecha
            insertarNodo(padre.right(),dato);
        }
    }
    //Este metodo recorre el arbol in-order e imprimer todos sus nodos
    public void imprimirArbol(SplayTree<Association<String,String>> arbol){
        if(arbol.left() != null){
            imprimirArbol(arbol.left());
            System.out.println(arbol.left().value().getKey()+",");
        }
        if(arbol.right() != null){
            imprimirArbol(arbol.right());
            System.out.println(arbol.right().value().getKey()+",");
        }
        System.out.println(arbol.value().getKey()+",");
    }
    public String traducirPalabra(SplayTree<Association<String,String>> parent, String palabra){
        String palabraTraducida = "";
        Association<String,String> asociacion = parent.value();
        String parentKey = asociacion.getKey();
        int n = parentKey.compareToIgnoreCase(palabra);
        if(n==0){
            palabraTraducida = parent.value().getValue();
        }
        if(n<0){
            if(parent.right()!=null){
                palabraTraducida = traducirPalabra(parent.right(),palabra);
            }else{
                return("*"+palabra+"*");
            }
        }
        if(n>0){
            if(parent.left()!=null){
                palabraTraducida = traducirPalabra(parent.left(),palabra);
            }else{
                return("*"+palabra+"*");
            }
        }
        return palabraTraducida;
    }
    public void traducirOracion(){
        leerOracion();
        String resultado ="";
        for(int i=0; i<oracion.size();i++){
            resultado+=traducirPalabra(raiz,oracion.get(i).trim())+"";
        }
        System.out.println(resultado);
    }
    public void leerOracion(){
        String palabras="";
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine())
           archivo = new File ("C:\\Users\\Rodrigo\\Documents\\2do ciclo 2016\\ADT\\hoja7-bst\\HDT7\\src\\texto.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           int ind=0;
           while((linea=br.readLine())!=null){
              	palabras=linea;
           }
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        while(palabras.compareTo("")!=0){
	int lugar=palabras.indexOf(' ');
            if(lugar!=-1){
                    oracion.add(palabras.substring(0,lugar));
                    palabras=palabras.substring(lugar+1);
            }else{
                    oracion.add(palabras);
                    palabras="";
            }
        }
        }
    }
}
