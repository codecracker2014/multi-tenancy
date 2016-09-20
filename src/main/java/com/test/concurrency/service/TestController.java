package com.test.concurrency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TestController {


	@Autowired
	DummyService dummyService;
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public String testMethod(long y,ModelMap modelMap)
	{
		long result=dummyService.updateX(y);
		modelMap.addAttribute("result", result);
		return "test";
	}
	
	
	private long x;

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}
	
	public long updateX(long y)
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
