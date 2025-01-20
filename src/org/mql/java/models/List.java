package org.mql.java.models;

public interface List<T> {
	public void add(T element);
	public T remove(int index);
	public T get(int index);
	public void set(int index, T e);
	public int size();
}
