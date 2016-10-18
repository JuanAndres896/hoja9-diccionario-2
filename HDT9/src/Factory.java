/*
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos - Seccion 31
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Guatemala, octubre 17 de 2016
*/
import java.util.HashMap;


public class Factory <K extends Comparable<K>,V> {
    
    public HashMap<K,V> setMap(int type){
        HashMap<K,V> mapa=null;
        switch(type){
            case 1:
                mapa = new SplayTree<K,V>();
                break;
            case 2:
                  mapa = new RedBlackBST<K,V>();
                  break;
            case 3:
                mapa = new HashMap<K,V>();
                break;
        }
        return mapa;
    } 
    
}