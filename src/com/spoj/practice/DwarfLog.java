package com.spoj.practice;

import java.util.*;
import java.lang.*;

public class DwarfLog {
	public static void main (String[] args)
	{
//		BidiMap<Integer, Integer> map = new BidiMap();
//		map.put(1, 11);
//		map.put(2, 22);
//		map.put(3, 33);
//		map.put(4, 44);
//		map.put(5, 55);
//		map.put(6, 66);
//		
//		System.out.println(map.getKey(11));
//		System.out.println(map.getKey(1));
//		System.out.println(map.getValue(1));
//		System.out.println(map.getValue(11));
//		return;
		Scanner in = new Scanner(System.in);
		int noOfDwarves = in.nextInt();
		Dwarf dwarf = new Dwarf(noOfDwarves);
		int noOfRequests = in.nextInt();
		for(int iterator=1; iterator<=noOfRequests; iterator++)
		{
			int requestType = in.nextInt();
			int firstNumber = in.nextInt();
			int secondNumber = in.nextInt();
			if(requestType == 1)
			{
				dwarf.operationOne(firstNumber,secondNumber);
			}
			else
			{
				
			}
			
		}
		in.close();
	}
}
class Dwarf
{
	private int totalDwarves;
	private BidiMap<Integer, Integer> arrangementMap;
	private TreeSet<Integer> misplacedPositions;
	public Dwarf(int totalDwarves)
	{
		this.totalDwarves = totalDwarves;
		arrangementMap = new BidiMap<Integer, Integer>();
		for(int it=0; it<=totalDwarves; it++)
		{
			arrangementMap.put(it,it);
		}
		misplacedPositions = new TreeSet<Integer>();
	}
		
	public void operationOne(int dwarf1, int dwarf2)
	{
		int position1 = arrangementMap.getKey(dwarf1);
		int position2 = arrangementMap.getKey(dwarf2);
		swapPositions(dwarf1, dwarf2);
		updateMisplacementSet(position1,dwarf2);
		updateMisplacementSet(position2,dwarf1);		
	}
	
	public void operationTwo(int dwarf1, int dwarf2) {
		
	}
	
	void swapPositions(int dawrf1, int dawrf2) {
		int position1 = arrangementMap.getKey(dawrf1);
		int position2 = arrangementMap.getKey(dawrf2);
		arrangementMap.put(position1,dawrf2);
		arrangementMap.put(position2,dawrf1);
	}
	
	void updateMisplacementSet(int position, int dwarf) {
		if(position != dwarf && !misplacedPositions.contains(Integer.valueOf(position))) {
			misplacedPositions.add(Integer.valueOf(position));
		}
	}
	
}
class BidiMap<K, V> implements Map<K, V>
{
	Map<K, V> map1;
	Map<V, K> map2;
	
	public BidiMap()
	{
		map1 = new HashMap<K,V>();
		map2 = new HashMap<V,K>();
	}
	
	public BidiMap(K k, V v)
	{
		map1 = new HashMap<K,V>();
		map2 = new HashMap<V,K>();
	}
	
	@Override
	public int size() {
		return map1.size();
	}

	@Override
	public boolean isEmpty() {
		return map1.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map1.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map1.containsValue(value);
	}

	@Override
	public V get(Object key) {
		return map1.get(key);
	}

	@Override
	public V put(K key, V value) {
		map2.put(value, key);
		return map1.put(key, value);
	}

	@Override
	public V remove(Object key) {
		map2.remove(map1.get(key));
		return map1.remove(key);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		map1.putAll(m);
	}

	@Override
	public void clear() {
		map2.clear();
		map1.clear();		
	}

	@Override
	public Set<K> keySet() {
		return map1.keySet();
	}
	
	public Set<V> valueSet() {
		return map2.keySet();
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public V getValue(Object key)
	{
		return this.get(key);
	}

	public K getKey(Object value) {
		return map2.get(value);
	}
}