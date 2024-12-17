package dsaa.lab08;

import java.util.NoSuchElementException;
import java.util.function.ToDoubleBiFunction;

public class BST<T extends Comparable<T>> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
		public Node(T value, Node left, Node right, Node parent) {
			super();
			this.value = value;
			this.left = left;
			this.right = right;
			this.parent = parent;
		}

	}		
	private Node root=null;

	public BST() {
	}

	public T getElement(T toFind) {
		Node pom = TreeSearch(root, toFind);
		if(pom!= null )return pom.value;
		else return null;
	}

	private Node TreeSearch (Node x, T v)
	{
		if(x==null || x.value.equals(v))
			return x;
		if(v.compareTo (x.value)<0)
			return TreeSearch(x.left,v);
		else return TreeSearch(x.right,v);
	}
	public Node successor(Node x) {
		Node y;
		if(x.right!=null)
		{
			return Treemin(x.right);
		}
		y=x.parent;
		while(y!=null && x== y.right)
		{
			x=y;
			y=y.parent;
		}
		return y;
	}
	public T successor(T elem) {
		Node x = TreeSearch(root,elem);
		if(x==null) return null;
		Node y;
		if(x.right!=null)
		{
			return Treemin(x.right).value;
		}
		y=x.parent;
		while(y!=null && x== y.right)
		{
			x=y;
			y=y.parent;
		}
		if(y==null) return null;
		return y.value;
	}



	public String toStringInOrder() {
		StringBuilder result = new StringBuilder();
		TreeInOrder(root,result);
		if(result.length()>2)
			result.delete((result.length()-2), result.length());
		return result.toString();
	}
	private void TreeInOrder(Node x, StringBuilder str)
	{
		if(x!=null)
		{
			TreeInOrder(x.left,str);
			str.append(x.value).append(", ");
			TreeInOrder(x.right,str);
		}

	}
	private void TreePreOrder(Node x, StringBuilder str)
	{
		if(x!=null)
		{
			str.append(x.value).append(", ");
			TreePreOrder(x.left,str);
			TreePreOrder(x.right,str);
		}

	}
	private void TreePostOrder(Node x, StringBuilder str)
	{
		if(x!=null)
		{
			TreePostOrder(x.left,str);
			TreePostOrder(x.right,str);
			str.append(x.value).append(", ");
		}

	}

	public String toStringPreOrder() {
		StringBuilder result = new StringBuilder();
		TreePreOrder(root,result);
		if(result.length()>2)
			result.delete((result.length()-2), result.length());
		return result.toString();
	}

	public String toStringPostOrder() {
		StringBuilder result = new StringBuilder();
		TreePostOrder(root,result);
		if(result.length()>2)
			result.delete((result.length()-2), result.length());
		return result.toString();
	}


	public boolean add(T elem) {
		Node z = new Node( elem);
		Node y=null;
		Node x=root;
		while(x!=null)
		{
			y=x;
			if(z.value.compareTo(x.value)<0)
			{
				x=x.left;
			}
			else x=x.right;
		}
		z.parent=y;
		if(y==null)
		{
			root=z;
		}
		else if(z.value.compareTo(y.value)<0)
			y.left=z;
		else y.right = z;
		return true;
	}
	private Node Treemin(Node x)
	{

		while(x.left!=null)
		{
			x=x.left;
		}
		return x;
	}
	private Node Treemax()
	{
		Node x = root;
		while(x.right!=null)
		{
			x=x.right;
		}
		return x;
	}

	public T remove(T value) {
		Node z = TreeSearch(root, value);
		Node y=null;
		Node x=null;

		if(z==null) return null;

		if(z.left== null || z.right==null) y=z;
		else y=successor(z);

		if(y.left != null)
			x=y.left;
		else
			x=y.right;

		if(x!=null)
			x.parent=y.parent;

		if(y.parent==null)
			root=x;
		else if(y==y.parent.left)
		{
			y.parent.left=x;
		}
		else y.parent.right=x;

		if(y!=z)
		{
			T pom = z.value;
			z.value=y.value;
			y.value= pom;
		}
		return y.value;
	}

	public void clear() {
		root=null;
	}

	public int size() {
		return countNodes(root);
	}
	private int countNodes(Node node)
	{
		if(node==null)
		{
			return 0;
		}
		return 1+ countNodes(node.left)+ countNodes(node.right);
	}
	public int left()
	{
		return countLeftChildNodes(root);
	}
	public int countLeftChildNodes(Node node) {
		if (node == null) {
			return 0;
		}
		int count = 0;
		if (node.left != null && node.right == null) {
			count = 1;
		}
		return count + countLeftChildNodes(node.left) + countLeftChildNodes(node.right);
	}

}
