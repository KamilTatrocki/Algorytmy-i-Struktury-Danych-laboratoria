package dsaa.lab02;

public class Link{
	public String ref;
	public Link(String ref) {
		this.ref=ref;
	}

	public String toString ()
	{
		return ref;
	}
	public boolean equals (Object other)
	{
		if(other instanceof Link )
		{
			return ref.equals(((Link) other).ref);
		}
		else if (other instanceof String )
		{
			return ref.equals((String) other);
		}
		return false;
	}
	// in the future there will be more fields
}
