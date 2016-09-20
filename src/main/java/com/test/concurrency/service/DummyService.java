package com.test.concurrency.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

	public static long x;

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}
	@Transactional
	public  long updateX(long y)
	{
		long k=y*100000000;
		System.out.println("Thread id "+Thread.currentThread().getId() +" x= "+x);
		for(long i=0;i<k;i++)
		{
			x=x+1;
		}
		System.out.println("Thread id "+Thread.currentThread().getId() +" x= "+x);
		return x;
	}
	
}
