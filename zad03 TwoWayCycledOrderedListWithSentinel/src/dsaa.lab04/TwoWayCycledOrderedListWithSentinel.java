package dsaa.lab04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<E>> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object = e;
		}
		public Element(E e, Element next, Element prev) {
			this.object =e;
			this.next = next;
			this.prev = prev;
		}
		// add element e after this
		public void addAfter(Element elem) {
			Element temp = this.next;
			this.next= elem;
			elem.prev = this;
			elem.next =temp;
			temp.prev = elem;

		}
		// assert it is NOT a sentinel
		public void remove() {

				this.prev.next= this.next;
				this.next.prev = this.prev;


		}
		E object;
		Element next=null;
		Element prev=null;
	}


	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E>{
		Element pos;
		public InnerIterator() {
			this.pos = sentinel.next;
		}
		@Override
		public boolean hasNext() {
			return pos.next!=sentinel;
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
		public InnerListIterator() {
			this.p = sentinel.next;
		}
		@Override
		public boolean hasNext() {
			return p.next!=sentinel;

		}

		@Override
		public E next() {
			E oldValue = p.object;
			p=p.next;
			return oldValue;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		public Element getP() {
			return p;
		}
		@Override
		public boolean hasPrevious() {
			return p.prev!=sentinel;
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
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		this.sentinel= new Element(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;

		this.size=0;

	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		Element newElement = new Element(e);
		//System.out.println("dodaje element:" + e);
		if(size==0)
		{
			sentinel.addAfter(newElement);
		}
		else
		{
			Element current = sentinel.next;
			while(current!=sentinel && current.object.compareTo(e)<=0)
			{

				current=current.next;
			}

			current.prev.addAfter(newElement);
		}
		size++;
		return true;

	}

	private Element getElement(int index) {
		if(index>=size) throw new NoSuchElementException();
		Element tmp = sentinel.next;
		for(int i =0; i<index; i++)
		{
			tmp = tmp.next;
		}
		return tmp;
	}

	private Element getElement(E obj) {
		Element tmp = sentinel.next;
		while(tmp!=sentinel)
		 {
			if(tmp.object.equals(obj));
			return tmp;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void clear() {
		sentinel.next=sentinel;
		sentinel.prev=sentinel;
		size=0;
	}

	@Override
	public boolean contains(E element) {

		return indexOf(element)>=0;
	}

	@Override
	public E get(int index) {
		if(index>=size) throw new NoSuchElementException();
		Element tmp = sentinel.next;
		for(int i =0; i<index; i++)
		{
			tmp = tmp.next;
		}
		return tmp.object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		Element tmp = sentinel.next;
		int index=0;
		while(tmp!=sentinel)
		{
			if(tmp.object.equals(element))
			return index;
			index++;
		}

		return -1;
	}

	@Override
	public boolean isEmpty() {

		return sentinel.next==sentinel;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		if(index>=size || index<0) throw new NoSuchElementException();
		Element tmp = sentinel.next;
		for(int i =0; i<index; i++)
		{
			tmp = tmp.next;
		}
		tmp.prev.next=tmp.next;
		tmp.next.prev=tmp.prev;
		size--;
		return tmp.object;
	}

	@Override
	public boolean remove(E e) {
		Element tmp = sentinel.next;
		while(tmp!=sentinel)
		{
			if(tmp.object.equals(e))
			{
				tmp.prev.next=tmp.next;
				tmp.next.prev=tmp.prev;
				size--;
				return true;
			}
			tmp=tmp.next;
		}
		return false;
	}

	@Override
	public int size() {

		return size;
	}

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {


		if(this==other||other.isEmpty())
			return;

		Element CurrentElement= sentinel;
		Element OtherElement = other.sentinel.next;

		while(CurrentElement.next!=sentinel && OtherElement!=other.sentinel)
		{
			if((OtherElement.object).compareTo((CurrentElement.next.object))<0)
			{
				OtherElement=OtherElement.next;
				CurrentElement.addAfter(OtherElement.prev);
			}
			CurrentElement=CurrentElement.next;
		}

		if(CurrentElement.next==sentinel)
		{
			while(OtherElement!=other.sentinel)
			{
				OtherElement=OtherElement.next;
				CurrentElement.addAfter(OtherElement.prev);
				CurrentElement=CurrentElement.next;
			}
		}
		size=size+ other.size;
		other.clear();

		/*
		Element current = other.sentinel.next;
		while(current!= other.sentinel)
		{

			this.add(current.object);
			other.remove(current.object);

			current=other.sentinel.next;

		}
		*/
	}
	
	//@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {

			Element tmp = sentinel.next;
			while(tmp!=sentinel)
			{
				if(tmp.object.equals(e))
				{
					tmp.prev.next=tmp.next;
					tmp.next.prev=tmp.prev;
					size--;

				}
				tmp=tmp.next;
			}


	}

}

