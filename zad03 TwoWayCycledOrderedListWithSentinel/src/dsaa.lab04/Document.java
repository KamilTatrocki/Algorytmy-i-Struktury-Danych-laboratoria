package dsaa.lab04;

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
	private static Link createLink(String link) {
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
}

