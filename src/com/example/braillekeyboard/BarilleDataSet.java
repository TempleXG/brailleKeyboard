package com.example.braillekeyboard;

import java.util.SortedMap;
import java.util.TreeMap;

public final class BarilleDataSet {
	
	public final static BarilleDataSet SINGLETON = new BarilleDataSet();
	
	public final static String basicJsonFileName = "basic.json";
	public final static String abbJsonFileName = "abb.json";
		
	private BarilleDataSet()
	{
		basic = new TreeMap<Integer, String>();
		abb = new TreeMap<Integer, String>();
	}
	
	public SortedMap<Integer, String> getBasic()
	{
		return this.basic;
	}
	
	public SortedMap<Integer, String> getAbb()
	{
		return this.abb;
	}
	
	private SortedMap<Integer, String> basic;
	private SortedMap<Integer, String> abb;
}