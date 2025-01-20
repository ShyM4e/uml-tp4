package org.mql.java.models;

import java.util.Iterator;

public class LinkedList <T> implements List<T>, Iterable <T>{
	
	private T value;
	private LinkedList<T> next;
	
	
	public LinkedList() {
		value=null;
		next=null;
	}
	public LinkedList(T value) {
		this.value=value;
		next=null;
	}
	
	@Override
	public void add(T element) {
		if(value==null && next==null) value= element;
		else if (next==null) next =new LinkedList<T>(element);
		else next.add(element);
		
	}

	@Override
	public T remove(int index) {
		if(index ==1) {
			T removed= next.value;
			next=next.next;
			return removed;
		}
		if (index == 0) {
            T removedValue = value;
            if (next != null) {
                value = next.value;
                next = next.next;
            } else {
                value = null;
                next = null;
            }
            return removedValue;
        } 
		if (next == null) return null;
		return next.remove(index - 1);
		
	}

	@Override
	public T get(int index) {
		if(value==null && next==null) return null;
		if (index==0) return value;
		if(next!=null) return next.get(index-1);
		return null;
	}

	@Override
	public void set(int index, T e) {
		if(index>size()) System.out.println("Out of bounds");
		if (index==0) value=e;
		next.set(index-1, e);
		
	}

	@Override
	public int size() {
		if(value==null && next==null) return 0;
		else if (next==null) return 1;
		else return 1+next.size();
	}
	

	
	
	
	
	
	//Partie Iterator
	@Override
	public Iterator<T> iterator() {
		return new Iter(this);
	}
	class Iter implements Iterator<T>{
		private LinkedList<T> pointer;
		
		public Iter(LinkedList<T> pointer) {
			this.pointer=pointer;
		}

		@Override
		public boolean hasNext() {
			System.out.println(">> hasNext() invoked");
			return (pointer!=null);
		}

		@Override
		public T next() {
			System.out.println(">> Next() invoked");
			T element= pointer.value;
			pointer=pointer.next;
			return element;
		}
		
	}
	
}
