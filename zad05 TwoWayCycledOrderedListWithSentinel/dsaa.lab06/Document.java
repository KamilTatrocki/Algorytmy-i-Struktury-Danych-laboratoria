package dsaa.lab06;

import java.util.ListIterator;
import java.util.Scanner;

public class Document{
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		load(scan);
	}
	public void load(Scanner scan) {
		boolean doc = true;


		while (doc) {
			String line = scan.nextLine();
			line= line.toLowerCase();
			String word[] = line.split(" ");

			if(word[0].equalsIgnoreCase("eod"))
			{
				doc=false;
				continue;
			}
			for (int i = 0; i< word.length; i++) {

				if (word[i].length() > 5) {
					if (word[i].substring(0, 5).equals("link=")) {
						Link pom = createLink(word[i].substring(5, word[i].length() ));

						if (pom!=null) {
							link.add(pom);
						}
					}
				}
			}


		}
	}
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	

	public static boolean isCorrectId(String link) {
		link= link.toLowerCase();
		if(link.charAt(0)<'a' || link.charAt(0)>'z')
			return false;
		for(int i=1; i<link.length();i++)
		{

			if(!((link.charAt(i)>='a'&& link.charAt(i)<='z')|| link.charAt(i)=='_'|| (link.charAt(i)<='9' && link.charAt(i)>='0')))
				return false;
		}
		return true;
	}
	public static boolean isNumber(String number)
	{
		for (int i=0; i<number.length();i++)
		{
			if((number.charAt(i)>'9' || number.charAt(i)<'0')) return false;
		}
		return true;
	}


	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	public static Link createLink(String link) {
		if(link.endsWith(")"))
		{
			if( isNumber(link.substring(link.indexOf("(")+1,link.length()-1)))
			{
				return new Link(link.substring(0,link.indexOf("(")), Integer.parseInt(link.substring(link.indexOf("(")+1,link.length()-1)));
			}
		}
		else
		{
			if (isCorrectId(link))
				return new Link(link, 1);
		}
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		for (int i=0; i< link.size && i<10;i++)
		{
			retStr= retStr +link.get(i).toString()+" " ;
		}
		retStr=retStr.substring(0, retStr.length()-1);

		if(link.size>=10)
		{
			retStr+="\n";
			for (int i=10; i<link.size();i++)
			{
				retStr= retStr +link.get(i).toString()+" ";
			}
			retStr=retStr.substring(0, retStr.length()-1);
		}

		return retStr;
	}

	public String toStringReverse() {
		String retStr="Document: "+name;
		ListIterator<Link> iter=link.listIterator();
		while(iter.hasNext())
			iter.next();
		int i=0;
		retStr+="\n";
		while (iter.hasPrevious()&& i<10)
		{
			retStr=retStr+iter.previous()+" ";
			i++;
		}
		if( i<10)
		{
			Link tmp = iter.previous();
			if(tmp!= null)
				retStr=retStr+tmp+" ";
		}
		if(retStr.length()>0)
			retStr=retStr.substring(0, retStr.length()-1);

		if(i==10)
		{
			retStr+="\n";

			while(iter.hasPrevious()){
				retStr=retStr+iter.previous()+" ";
			}


			retStr=retStr+iter.previous();
		}
		return retStr;
	}
	public int[] getWeights() {
		int [] tab = new int[link.size()];
		for (int i=0; i< link.size();i++ )
			tab[i]=link.get(i).weight;
		return tab;
	}
	public static void showArray(int[] arr) {
		String ref= "";
		for (int i=0; i<arr.length; i++)
		{
			ref=ref+ String.valueOf(arr[i])+" ";

		}
		if(ref.length()>0)
			ref=ref.substring(0,ref.length()-1);
		System.out.println(ref);
	}

	void bubbleSort(int[] arr) {
		showArray(arr);
		for (int i = 1; i < arr.length; ++i) {
			for (int right = arr.length-1; right >= i; --right) {
				int left = right - 1;
				if (Integer.compare(arr[left], arr[right]) > 0)
				{
					int tmp =arr[left];
					arr[left]=arr[right];
					arr[right]=tmp;
				}

			}
			showArray(arr);
		}
	}

	public void insertSort(int[] arr) {
		showArray(arr);
		for (int i = arr.length-2; i>=0; --i) {
			int value = arr[i];
			int temp;
			int j;
			for (j = i; j+1< arr.length&& Integer.compare(value, temp=arr[j + 1])> 0; ++j)
				arr[j]=temp;
			arr[j]= value;
			showArray(arr);
		}




	}
	public void selectSort(int[] arr) {
		showArray(arr);
		for (int slot = arr.length-1; slot>0; --slot) {
			int biggest = slot;
			for (int check = slot -1; check >=0; --check)
				if (Integer.compare(arr[check], arr[biggest]) > 0)
					biggest= check;
			int tmp =arr[biggest];
			arr[biggest]=arr[slot];
			arr[slot]=tmp;
			showArray(arr);
		}

	}



	private static void merge (int []t, int p1, int k1, int p2, int k2)
	{
		int pom [] = new int[k2-p1+1];
		int i=p1;
		int j=p2;
		int pos=0;
		while (i<=k1 && j<=k2)
		{
			if(t[i]<t[j])
			{
				pom[pos] =t[i];
				pos++;
				i++;
			}
			else
			{
				pom[pos]=t[j];
				pos++;
				j++;
			}
		}
		while(i<=k1)
		{
			pom[pos] =t[i];
			pos++;
			i++;
		}
		while (j<=k2)
		{
			pom[pos] =t[j];
			pos++;
			j++;
		}
		for (int it=p1;it<=k2;it++)
		{
			t[it]=pom[it-p1];
		}


	}

	public static int minimum(int x, int y) { return (x<y)? x :y; }



	public void iterativeMergeSort(int arr[])
	{
		showArray(arr);
		for (int i=1; i<= arr.length-1; i = 2*i)
		{
			for (int j=0; j<arr.length-1; j += 2*i)
			{
				int mid = minimum(j + i - 1, arr.length-1);
				int end = minimum(j + 2*i - 1, arr.length-1);
				//System.out.println(j+" "+mid+" "+(mid+1)+" "+end);
				merge(arr, j, mid,mid+1, end);
			}
			showArray(arr);
		}
	}
	public void iterativeMergeSortv2(int arr[])
	{
		showArray(arr);
		int t[] = new int[arr.length];
		for (int i=0;i< arr.length;i++)
		{
			t[i]=arr[i];
		}

		for (int i=1; i<= arr.length-1; i = 2*i)
		{
			for (int j=0; j<arr.length-1; j += 2*i)
			{
				int mid = minimum(j + i - 1, arr.length-1);
				int end = minimum(j + 2*i - 1, arr.length-1);
				//System.out.println(j+" "+mid+" "+(mid+1)+" "+end);
				mergev2(arr, t, j, mid,mid+1, end);
			}
			showArray(arr);
		}
	}
	private static void mergev2 (int []t, int []pom, int p1, int k1, int p2, int k2)
	{

		int i=p1;
		int j=p2;
		int pos=p1;
		while (i<=k1 && j<=k2)
		{
			if(t[i]<t[j])
			{
				pom[pos] =t[i];
				pos++;
				i++;
			}
			else
			{
				pom[pos]=t[j];
				pos++;
				j++;
			}
		}
		while(i<=k1)
		{
			pom[pos] =t[i];
			pos++;
			i++;
		}
		while (j<=k2)
		{
			pom[pos] =t[j];
			pos++;
			j++;
		}
		for (int it=p1;it<=k2;it++)
		{
			t[it]=pom[it];
		}


	}


	public static void countingSort(int arr[], int k, int divider) {
		k++;
		int n = arr.length;
		int []pos = new int[k];
		int []result = new int[n];
		int i, j;
		for (i = 0; i < k; i++)
			pos[i] = 0;
		for (j = 0; j < n; j++)
			pos[(arr[j]/divider)%10]++;
		pos[0]--;
		for (i = 1; i < k; i++)
			pos[i] += pos[i - 1];
		for (j = n - 1; j >= 0; j--) {
			result[pos[(arr[j]/divider)%10]] = arr[j];
			pos[(arr[j]/divider)%10]--;
		}
		for (j = 0; j < n; j++)
			arr[j] = result[j];
	}

	public void radixSort(int[] arr) {
		showArray(arr);
		int divider=1;
		for (int i=0;i<3;i++)
		{
			countingSort(arr,9,divider);
			divider=divider*10;
			showArray(arr);
		}
	}


}
