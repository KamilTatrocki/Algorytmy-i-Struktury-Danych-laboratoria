
package dsaa.lab02;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object=e;
		}
		E object;
		Element next=null;
	}

	Element sentinel;


	private class InnerIterator implements Iterator<E>{
		Element actElem;
		public InnerIterator() {
			actElem = sentinel.next;
		}
		@Override
		public boolean hasNext() {

			return actElem!=null;
		}

		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			E object = actElem.object;
			actElem = actElem.next;
			return object;

		}
	}

	public OneWayLinkedList() {
		sentinel = new Element(null);

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
	public boolean add(E e) {

		Element newElem=new Element(e);

		Element tail=sentinel;
		while(tail.next!=null)
			tail=tail.next;
		tail.next = newElem;
		return true;
	}

	@Override
	public void add(int index, E element) throws NoSuchElementException {

		if (index < 0) throw new NoSuchElementException();
		Element newElement = new Element(element);
		Element tmp = sentinel;
		while (index > 0 && tmp.next != null) {
			tmp = tmp.next;
			index--;
		}
		if (index > 0) throw new NoSuchElementException();
		newElement.next = tmp.next;
		tmp.next = newElement;

	}

	@Override
	public void clear() {
		sentinel.next=null;

	}

	@Override
	public boolean contains(E element) {
		return indexOf(element)>=0;
	}

	@Override
	public E get(int index) throws NoSuchElementException {
		if (index < 0) throw new NoSuchElementException();
		Element tmp = sentinel.next;
		while (index > 0 && tmp != null) {
			tmp = tmp.next;
			index--;
		}
		if (tmp == null) throw new NoSuchElementException();
		return tmp.object;
	}

	@Override
	public E set(int index, E element) throws NoSuchElementException {
		if (index < 0) throw new NoSuchElementException();
		Element tmp = sentinel.next;
		while (index > 0 && tmp != null) {
			tmp = tmp.next;
			index--;
		}
		if (tmp == null) throw new NoSuchElementException();
		E oldVal = tmp.object;
		tmp.object = element;
		return oldVal;
	}
	private Element getElement(int index){
		if(index<0) throw new IndexOutOfBoundsException();
		Element actElem=sentinel.next;
		while(index>0 && actElem!=null){
			index--;
			actElem=actElem.next;
		}
		if (actElem==null)
			throw new IndexOutOfBoundsException();
		return actElem;
	}


	@Override
	public int indexOf(E element) {
		int pos=0;
		Element act= sentinel.next;
		while(act!= null)
		{
			if(act.object.equals(element))
			{
				return pos;
			}
			act=act.next;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {

		return sentinel.next==null;
	}

	@Override
	public E remove(int index) throws NoSuchElementException {
		if (index < 0 || sentinel.next == null) throw new NoSuchElementException();
		Element tmp = sentinel;
		while (index > 0 && tmp.next != null) {
			tmp = tmp.next;
			index--;
		}
		if (tmp.next == null || index > 0) throw new NoSuchElementException();
		E toRemove = tmp.next.object;
		tmp.next = tmp.next.next;
		return toRemove;
	}

	@Override
	public boolean remove(E e) {
		Element tmp = sentinel;
		while (tmp.next!= null)
		{

			if(tmp.next.object.equals(e))
			{

				tmp.next=tmp.next.next;
				return true;
			}
			tmp=tmp.next;
		}
		return false;

	}

	@Override
	public int size() {
		int pos= 0;
		Element act = sentinel.next;
		while(act!= null)
		{
			pos++;
			act=act.next;
		}
		return pos;
	}
	public void removeO()
	{
		Element currentElement = sentinel.next;

		if(currentElement!= null)
		{
			while(currentElement.next!= null && currentElement.next.next!=null )
			{
				currentElement.next= currentElement.next.next;
				currentElement=currentElement.next;
			}
			if( currentElement.next!= null && currentElement.next.next==null)
				currentElement.next=null;
		}


	}

}

/*
Element currentElement = sentinel.next;
		while(currentElement!= null &&currentElement.next!= null && currentElement.next.next!=null )
		{
			currentElement.next= currentElement.next.next;
			currentElement=currentElement.next;
		}
		if(currentElement!= null && currentElement.next!= null && currentElement.next.next==null)
			currentElement.next=null;

	}
 */