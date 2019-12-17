package com.servicenow.test.automation.listener.test;

import org.junit.runner.JUnitCore;

import com.servicenow.test.automation.listener.CustomJunitListener;

public class ExecuteWithRunListener {

	public static void main(String[] args) {
		JUnitCore runner = new JUnitCore();
		runner.addListener(new CustomJunitListener());
		runner.run(TestFeatureAWS.class, TestFeatureAzure.class,
				TestFeatureGCP.class, TestFeatureIBM.class);
	}

}
