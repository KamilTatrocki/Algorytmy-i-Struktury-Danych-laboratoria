package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
	
	private class Element{

		public Element(E e) {
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			this.object = e;
			this.next = next;
			this.prev = prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}
	
	Element head;
	Element tail;
	// can be realization with the field size or without
	int size=0;
	
	private class InnerIterator implements Iterator<E>{
		Element pos;
		// TODO maybe more fields....
		
		public InnerIterator() {
		//TODO
			this.pos=head;
		}
		@Override
		public boolean hasNext() {
			//TODO
			return pos!=tail;
		}
		
		@Override
		public E next() {
			E oldValue = pos.object;
			pos=pos.next;
			return oldValue;
		}
	}
	
	private class InnerListIterator implements ListIterator<E>{
		Element p;
		// TODO maybe more fields....

		public InnerListIterator()
		{
			this.p= head;
		}

		public Element getP() {
			return p;
		}

		@Override
		public void add(E e) {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public boolean hasNext() {
			return p!=tail;
		}

		@Override
		public boolean hasPrevious() {

			return p!=head;
		}

		@Override
		public E next() {
			E oldValue = p.object;
			p=p.next;
			return oldValue;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {

			E oldValue = p.object;
			p=p.prev;
			return oldValue;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(E e) {
			p.object = e;
			
		}
	}
	
	public TwoWayUnorderedListWithHeadAndTail() {
		// make a head and a tail	
		head=null;
		tail=null;

	}
	
	@Override
	public boolean add(E e) {

		Element newElement = new Element(e);
		if(head == null){
			head = newElement;
			tail = newElement;
			size++;
			return true;
		}
		tail.next = newElement;
		newElement.prev = tail;
		tail = newElement;
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {

		if(index<0 || index>size) throw new NoSuchElementException();
		if(index == size) {
			add(element);
			return;
		}
		if(index ==0 )
		{
			Element newElement = new Element(element, head, null);
			if (head != null) {
				head.prev = newElement;
			}

			head=newElement;
			size++;
			return;
		}
		Element current = head;
		for(int i=0; i<index; i++)
		{
			current=current.next;
		}
		Element newElement = new Element(element, current, current.prev);
		current.prev.next = newElement;
		current.prev = newElement;
		size++;


	}

	@Override
	public void clear() {

		head = null;
		tail = null;
		size = 0;

	}

	@Override
	public boolean contains(E element) {

		return indexOf(element)>=0;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size) throw new NoSuchElementException();
		Element current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.object;
	}

	@Override
	public E set(int index, E element) {
		if(index<0 || index>=size) throw new NoSuchElementException();
		Element current = head;
		for(int i=0; i<index;i++)
		{
			current=current.next;
		}
		E oldValue = current.object;
		current.object = element;
		return oldValue;
	}

	@Override
	public int indexOf(E element) {
		int index = 0;
		Element current = head;
		while(current != null) {
			if(current.object.equals(element)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return size==0;


	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int index) {
		if(index < 0 || index >= size) throw new NoSuchElementException();
		Element current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		if(current.prev != null) {
			current.prev.next = current.next;
		} else {
			head = current.next;
		}
		if(current.next != null) {
			current.next.prev = current.prev;
		} else {
			tail = current.prev;
		}
		size--;
		return current.object;
	}

	@Override
	public boolean remove(E e) {
		Element current = head;
		while(current != null) {
			if(current.object.equals(e)) {
				if(current.prev != null) {
					current.prev.next = current.next;
				} else {
					head = current.next;
				}
				if(current.next != null) {
					current.next.prev = current.prev;
				} else {
					tail = current.prev;
				}
				size--;
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public int size() {

		return size;
	}
	public String toStringReverse() {
		ListIterator<E> iter=new InnerListIterator();
		while(iter.hasNext())
			iter.next();
		String retStr="";

		if(((InnerListIterator) iter).getP()!=null ){
			while(iter.hasPrevious())
			{
				retStr+="\n";
				retStr+= iter.previous();

			}

			retStr+="\n";
			retStr+= iter.previous();
		}

		return retStr;
	}

	public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
		if (this == other) {
			return;
		}
		if (this.size == 0) {
			this.head = other.head;
			this.tail = other.tail;
		} else if (other.size != 0) {
			other.head.prev = this.tail;
			this.tail.next = other.head;
			this.tail = other.tail;
		}


		this.size += other.size;
		other.head = null;
		other.tail = null;
		other.size = 0;


		
	}
	public void removeO()
	{
		Element currentElement = head;

		if(currentElement!= null)
		{
			while(currentElement.next!= null && currentElement.next.next!=null )
			{
				currentElement.next= currentElement.next.next;
				currentElement.next.prev=currentElement;
				currentElement=currentElement.next;

			}
			if( currentElement.next!= null && currentElement.next.next==null)
				currentElement.next=null;
		}
		tail=currentElement;
		if(size%2==0)
		{
			size=size/2;
		}
		else
		{
			size=(size/2)+1;
		}

	}
}

