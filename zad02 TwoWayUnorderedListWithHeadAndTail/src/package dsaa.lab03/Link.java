package dsaa.lab03;

public class Link{
	public String ref;
	// in the future there will be more fields
	public Link(String ref) {
		this.ref=ref;
	}
	@Override
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

	@Override
	public String toString() {
		return ref;
	}
}