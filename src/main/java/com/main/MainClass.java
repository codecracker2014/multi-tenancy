package com.main;

import java.util.Arrays;
public class MainClass implements Cloneable{

	/**
	 * @param args
	 */

    public static void main(String[] args) {

    	Integer integer =new Integer(22000);
    	Integer integer2=new Integer(22000);
    	Integer integer3=integer+integer2;
    	MainClass mainClass=new MainClass();
    	MainClass mainClass2=new MainClass();
    	Integer integer4=44;
    	String str="Hel"+"lo";
    	String str2="Hello";
    	int b=str.compareTo(str2);
    	int a=integer2;
    	String res=str+str2;
    	System.out.println(res);
    	Long long1=new Long("2");
    	try {
			mainClass2.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(integer+integer2);
    }
    public native MainClass MainClass();
    
}

