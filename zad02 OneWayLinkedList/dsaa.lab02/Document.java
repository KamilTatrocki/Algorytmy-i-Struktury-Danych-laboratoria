package dsaa.lab02;

import java.util.Scanner;

public class Document{
	public String name;
	public OneWayLinkedList<Link> links;
	public Document(String name, Scanner scan) {
			this.name = name;
			this.links = new OneWayLinkedList<>();
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

						if (correctLink(word[i].substring(5, word[i].length() ))) {
							links.add( new Link(word[i].substring(5, word[i].length() )));
						}
					}
				}
			}


		}

	}
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static boolean correctLink(String link) {
		if(link.charAt(0)<'a' || link.charAt(0)>'z')
			return false;
		for(int i=1; i<link.length();i++)
		{

			if(!((link.charAt(i)>='a'&& link.charAt(i)<='z')|| link.charAt(i)=='_'|| (link.charAt(i)<='9' && link.charAt(i)>='0')))
				return false;
		}
		return true;
	}
	
	@Override
	public String toString() {

		String tmp= "";
		tmp = "Document: "+ name+"\n";
		for (int i=0 ;i <links.size();i++)
		{
			tmp = tmp + links.get(i).ref + '\n';
		}
		if(tmp.length()>1)
		{
			return tmp.substring(0 , tmp.length()-1);
		}
		else return "";


	}

}
