package dsaa.lab09;

public class DisjointSetForest implements DisjointSetDataStructure {
	
	private class Element{
		int rank;
		int parent;
		Element(int parent) {
			this.rank = 0;
			this.parent = parent;
		}
	}

	Element []arr;
	
	public DisjointSetForest(int size) {
		arr = new Element[size];

	}
	
	@Override
	public void makeSet(int item) {
		arr[item] = new Element(item);
	}

	@Override
	public int findSet(int item) {
		if (arr[item].parent != item) {
			arr[item].parent = findSet(arr[item].parent);
		}
		return arr[item].parent;
	}

	@Override
	public boolean union(int itemA, int itemB) {

		if(arr[itemA].parent==arr[itemB].parent) return false;
		Link(findSet(itemA), findSet(itemB));
		return true;
	}
	public void Link (int x, int y)
	{
		if(arr[x].rank>arr[y].rank)
		{
			arr[y].parent=x;
		}
		else {
			arr[x].parent=y;
			if(arr[y].rank==arr[x].rank)
			{
				arr[y].rank= arr[y].rank+1;
			}
		}
	}

	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Disjoint sets as forest:\n");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				sb.append(i).append(" -> ").append(arr[i].parent).append("\n");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

}
