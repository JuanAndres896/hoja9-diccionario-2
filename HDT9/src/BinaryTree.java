/*
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos - Seccion 31
* Juan Andres Garcia - 15046
* Rodrigo Barrios - 15009
* Guatemala, septiembre 27 de 2016
*/
public class BinaryTree<E,T>
{
    protected E key; // value associated with node
    protected T val;
    protected BinaryTree<E,T> parent; // parent of node
    protected BinaryTree<E,T> left, right; // children of node
    
    public BinaryTree(E llave, T value, BinaryTree<E,T> left, BinaryTree<E,T> right, BinaryTree<E,T> parent){
        key = llave;
        val=value;
        this.left=left;
        this.right=right;
        this.parent=parent;
    }
    public BinaryTree<E,T> left()
    // post: returns reference to (possibly empty) left subtree
    // post: returns reference to (possibly empty) left subtree
    {
            return left;
    }

    public BinaryTree<E,T> right()
    // post: returns reference to (possibly empty) left subtree
    // post: returns reference to (possibly empty) left subtree
    {
            return right;
    }

    public BinaryTree<E,T> parent()
    // post: returns reference to parent node, or null
    {
        return parent;
    }
    
    public void setLeft(BinaryTree<E,T> newLeft){
        this.left=newLeft;
    }
    
    public void setRight(BinaryTree<E,T> newRight){
        this.right=newRight;
    }
    
    public void setParent(BinaryTree<E,T> newParent){
        this.left=newParent;
    }
   
     public E key()
	// post: returns value associated with this node
	{
		return key;
	}
    
    public T value()
	// post: returns value associated with this node
	{
		return val;
	}
	
    
    public void setKey(E llave)
	// post: sets the value associated with this node
	{
		key = llave;
	}
	public void setValue(T value)
	// post: sets the value associated with this node
	{
		val = value;
	}
    
}