package com.qa.realerp.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersTestNG implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start Method " +result.getName() + " Started !!!");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test Success Method " +result.getName() + " Started !!!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure Method " +result.getName() + " Started !!!");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test Skipped Method " +result.getName() + " Started !!!");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("On Test Failed Within Success Percentage Method " +result.getName() + " Started !!!");
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("On Class Start Method Started !!!");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On Class Finish Method Started !!!");
		
	}

}
