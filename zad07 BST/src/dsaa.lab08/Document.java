package dsaa.lab08;

import java.util.Scanner;

public class Document implements IWithName{
	public String name;
	public BST<Link> link;
	public final int MODVALUE=100000000;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
		load(scan);
	}
	public int hashCode()
	{
		int []tab = {7,11,13,17,19};
		int sum=name.charAt(0);
		for (int i=1; i<name.length();i++)
		{
			sum=(sum*tab[(i-1)%tab.length] + name.charAt(i))%100000000;

		}
		return sum;
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
	static Link createLink(String link) {
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
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}
	public String toStringInOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();
		return retStr;
	}
	


	@Override
	public String getName() {
		return name;
	}

	public boolean equals(Object other) {

		if(other instanceof Document )
		{

			return name.equals(((Document) other).name);
		}
		else if (other instanceof String )
		{
			return name.equals((String) other);
		}
		return false;
	}
}
