package dsaa.lab07;

import java.util.LinkedList;

public class HashTable{
	LinkedList arr[]; // use pure array
	private final static int defaultInitSize=8;
	private final static double defaultMaxLoadFactor=0.7;
	private int size;	
	private final double maxLoadFactor;
	public HashTable() {
		this(defaultInitSize);
	}
	public HashTable(int size) {
		this(size,defaultMaxLoadFactor);
	}


	public HashTable(int initCapacity, double maxLF) {
		//this.arr = (LinkedList<IWithName>[]) new LinkedList[initCapacity];
		this.arr =  new LinkedList[initCapacity];
		for (int i = 0; i < initCapacity; i++) {
			this.arr[i] = new LinkedList<>();
		}
		this.size = 0;
		this.maxLoadFactor=maxLF;
	}
	public int maxlen()
	{
		int maks =0;
		for(int i=0; i< arr.length;i++)
		{
			if(arr[i].size()>maks) maks = arr[i].size();
		}
		return maks;
	}

	public boolean add(Object elem) {
		int index = (elem.hashCode()) % arr.length;
		for(int i=0; i<arr[index].size();i++)
		{
			if(arr[index].get(i).equals(elem)) return false;
		}

		arr[index].add((IWithName) elem);
		size++;

		if (getLoadFactor() > maxLoadFactor) {
			doubleArray();
		}

		return true;
	}


	
	private void doubleArray() {
		LinkedList[] newArr =  new LinkedList[arr.length * 2];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = new LinkedList<>();
		}

		for (LinkedList<IWithName> list : arr) {
			for (IWithName elem : list) {
				int newIndex = (elem.hashCode()) % newArr.length;
				newArr[newIndex].add(elem);
			}
		}

		this.arr = newArr;
	}

	private double getLoadFactor() {
		return (double) size / arr.length;
	}


	@Override
//	public String toString() {
//
//		String str = "";
//		int i=0;
//		boolean empty =true;
//		for (LinkedList<IWithName> list : arr)
//		{
//			empty=true;
//			str=str+i+":";
//
//			for (IWithName elem : list)
//			{
//				empty=false;
//				str= str+" "+elem.getName()+",";
//			}
//			if(!empty) str=str.substring(0,str.length()-1);
//			str=str+"\n";
//			i++;
//		}
//		return str;
//
//
//	}
	public String toString() {
		StringBuilder str= new StringBuilder();
		IWithName x;
		for(int i=0; i<arr.length; i++)
		{
			str.append(i).append(":");
			for(Object obj : arr[i])
			{
				x = (IWithName) obj;
				str.append(" ").append(x.getName()).append(",");
			}
			if(!arr[i].isEmpty()) str.deleteCharAt(str.length()-1);
			str.append("\n"); //wronganswer
		}
		return str.toString();
	}

	public Object get(Object toFind) {
		int index = (  ( toFind  ).hashCode()) % arr.length;
		for (Object elem : arr[index]) {
			if (elem.equals(toFind)) {
				return elem;
			}
		}

		return null;
	}
	
}

