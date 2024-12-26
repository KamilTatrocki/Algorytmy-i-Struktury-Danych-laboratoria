package dsaa.lab09;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
		public Element(int representant) {
			this.representant = representant;
			this.next = NULL;
			this.length = 1;
			this.last = representant;
		}
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
	}
	
	@Override
	public void makeSet(int item) {
		arr[item] = new Element(item);
	}

	@Override
	public int findSet(int item) {
		if (arr[item] == null)
			return NULL;
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int rootA = findSet(itemA);
		int rootB = findSet(itemB);

		if (rootA == NULL || rootB == NULL || rootA == rootB) {
			return false;
		}


		if (arr[rootA].length < arr[rootB].length) {
			int temp = rootA;
			rootA = rootB;
			rootB = temp;
		}


		int lastInA = arr[rootA].last;
		arr[lastInA].next = rootB;
		int current = rootB;
		arr[rootA].last=arr[rootB].last;
//		while(true) {
//			arr[current].representant = rootA;
//			if (arr[current].next == NULL) {
//				//arr[rootA].last = arr[current].last;
//				break;
//			}
//			current = arr[current].next;
//		}

		while(current!=NULL) {
			arr[current].representant = rootA;
			current = arr[current].next;
		}
		//arr[current].representant = rootA;

		arr[rootA].length += arr[rootB].length;

		return true;
	}


	
	@Override

	public String toString() {
		StringBuilder sb = new StringBuilder("Disjoint sets as linked list:\n");
		boolean[] visited = new boolean[arr.length];

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null && arr[i].representant == i && !visited[i]) {
				StringBuilder listBuilder = new StringBuilder();
				int start = i;
				int current = start;

				do {
					if (listBuilder.length() > 0) listBuilder.append(", ");
					listBuilder.append(current);
					visited[current] = true;
					current = arr[current].next;
				} while (current != NULL && current != start);

				sb.append(listBuilder.toString()).append("\n");
			}
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

}


