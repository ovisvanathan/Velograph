package com.horus.velograph.coll;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.horus.velograph.model.Vertex;

public class BlockingQueue<V extends Vertex> implements Queue<V>, List<V> {

    private Node front;
    private Node rear;
    private int length;

	private static final int DEFAULT_SIZE = 25;

	Object [] items = new Object[DEFAULT_SIZE];

	int currIndex;
	
	int CURRENT_SIZE = DEFAULT_SIZE;
	
	int SIZE_INCREMENT = 100;

	ArrayIterator arrIterator;

    public BlockingQueue(){
    }

    public synchronized void put(V data) throws InterruptedException {
        System.out.println("put method called...");
        if(this.length > 9){
            System.out.println("Maximum capacity reached. Hence waiting for take() operation");
            wait();
        }
        Node node = new Node(data);
        if(isEmpty()){
            front = node;
        }else {
            rear.nextNode = node;
        }
        rear = node;
        
		if(currIndex < CURRENT_SIZE) {
			items[currIndex++] = rear;
		}
		
		length++;
        System.out.println("Data added in the queue. Going to notify the observers.");
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        System.out.println("take() method called");
        V data;
        if(isEmpty()){
            System.out.println("No data found in the queue. take()() method execution paused.");
            wait();
        }
        System.out.println("Data became available. take() method resumed.");
        data = front.data;
        front = front.nextNode;
        length--;
       
		V item = null;
		int x = currIndex - 1;
		if(x > 0 && x < length)
			item = ((Node)items[x]).data;

	//	if(data != item)
	//		throw new Exception("array and list out of sync");

	   return data;
    }

    public boolean isEmpty(){
        return length == 0;
    }

	class Node {	
	  public V data;
	  public Node nextNode;
	
		public Node(V data) {
			this.data = data;		
		}
	
	}
	


	// For Queue interface
	public boolean	add(V e) {
		try {
			put(e);
		} catch(Exception e2) {}
		return true;
	}
	
	public V	element() {
		return null;
	}

	public boolean	offer(V e) {
		try {	
			put(e);
		} catch(Exception e2) {}

		return true;
	}


	public V	peek() {
		return null;
	}

	public V	poll() {
		return null;
	}
	
	public V	remove() {	
			V x = null;
			try {
					x = take();
				} catch(Exception e) {}

		return x;
	}
	
	
	// For List interface
	public void	add(int index, V element) {
		if(index > 0 && index < length)
			items[index] = new Node(element);	
	}

	public boolean	addAll(Collection<? extends V> c) {	

		try {
				for(V v : c)
					put(v);	
		
			} catch(Exception e) {}
	
		return true;
	}
	
	public boolean	addAll(int index, Collection<? extends V> c) {
		return false;
	}


	public void	clear() {
	
	}
	
	public boolean	contains(Object o) {
		for(int x=0;x<currIndex;x++)
			return ((Node)items[x]).data == o;
	
		return true;
	}
	

	public boolean	containsAll(Collection<?> c) {
		return false;	
	}


	public boolean	equals(Object o) {
		if(!(o instanceof List))
			return false;
	
		return false;
	}

	public V	get(int index) {
		if(index > 0 && index < length)
			return ((Node)items[index]).data;	
	
		return null;
	}

	public int	hashCode() {
		return (BlockingQueue.class.hashCode()<<0xff)*37 + 29;
	}
	
	public int	indexOf(Object o) {
		for(int x=0;x<currIndex;x++)
			if (((Node)items[x]).data == o)
				return x;
	
		return -1;
	}
	
	class ArrayIterator	implements Iterator {
		
		int currptr = 0;	
		
			public boolean hasNext() {
				synchronized(this) {				
					if(length > 0 && currptr < length)
						return true;
				}				
				
				return false;
			}
			
			public V next() {
				synchronized(this) {				
					return ((Node)items[currptr++]).data;
				}
				
			}		
		
	}		

	public Iterator<V>	iterator() {
		arrIterator = new ArrayIterator();
		return arrIterator;
	}
	

	public int	lastIndexOf(Object o) {
		int k = -1;
		for(int x=0;x<currIndex;x++)
			if (((Node)items[x]).data == o)
				k=x;
	
		return k;
	}


	public ListIterator<V>	listIterator() {
		return null;
	}


	public ListIterator<V>	listIterator(int index) {
		return null;
	}


	public V	remove(int index) {
		return null;
	}

	public boolean	remove(Object o) {
		return false;
	}

	public boolean	removeAll(Collection<?> c) {
		return false;	
	}


	public boolean	retainAll(Collection<?> c) {
		return false;		
	}


	public V	set(int index, V element) {
		if(index > 0 && index < length)
			((Node)items[index]).data = element;
		return element;
	}


	public int	size() {
		return this.length;
	}

	public List<V>	subList(int fromIndex, int toIndex) {
		return null;		
	}


	public Object[]	toArray() {
		return items;
	}


	public <V> V[]	toArray(V[] a) {
		if (a.length < length)
        // Make a new array of a's runtime type, but my contents:
			return (V[]) Arrays.copyOf(items, length, a.getClass());
			System.arraycopy(items, 0, a, 0, length);
		return a;
	}


	/*
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue queue = new BlockingQueue();
        
    }
	*/
}
