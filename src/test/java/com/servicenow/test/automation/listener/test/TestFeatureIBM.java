package com.servicenow.test.automation.listener.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.servicenow.test.automation.annotation.Report;

public class TestFeatureIBM {

	@Test
	@Report(testCaseName = "testIBMFeature", featureName = "IBM",
			testCaseDescription = "testIBMFeature Description")
	public void testIBMFeature() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testIBMFeature1", featureName = "IBM",
			testCaseDescription = "testIBMFeature1 Description")
	public void testIBMFeature1() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testIBMFeature2", featureName = "IBM",
			testCaseDescription = "testIBMFeature2 Description")
	public void testIBMFeature2() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testIBMFeature3", featureName = "IBM",
			testCaseDescription = "testIBMFeature3 Description")
	public void testIBMFeature3() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testIBMFeature4", featureName = "IBM",
			testCaseDescription = "testIBMFeature4 Description")
	public void testIBMFeature4() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testSecondFeatureIgnored", featureName = "IBM",
			testCaseDescription = "testSecondFeatureIgnored Description")
	public void testIBMFeatureIgnored() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testIBMFeatureIgnored1", featureName = "IBM",
			testCaseDescription = "testIBMFeatureIgnored1 Description")
	public void testIBMFeatureIgnored1() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testIBMFeatureIgnored2", featureName = "IBM",
			testCaseDescription = "testIBMFeatureIgnored2 Description")
	public void testIBMFeatureIgnored2() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testIBMFeatureIgnored3", featureName = "IBM",
			testCaseDescription = "testIBMFeatureIgnored3 Description")
	public void testIBMFeatureIgnored3() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testIBMFeatureIgnored4", featureName = "IBM",
			testCaseDescription = "testIBMFeatureIgnored4 Description")
	public void testIBMFeatureIgnored4() {
		Assert.assertTrue(true);
	}

	@Test
	@Report(testCaseName = "testIBMFeatureFailed", featureName = "IBM",
			testCaseDescription = "testIBMFeatureFailed Description")
	public void testIBMFeatureFailed() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testIBMFeatureFailed1", featureName = "IBM",
			testCaseDescription = "testIBMFeatureFailed1 Description")
	public void testIBMFeatureFailed1() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testIBMFeatureFailed2", featureName = "IBM",
			testCaseDescription = "testIBMFeatureFailed2 Description")
	public void testIBMFeatureFailed2() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testIBMFeatureFailed3", featureName = "IBM",
			testCaseDescription = "testIBMFeatureFailed3 Description")
	public void testIBMFeatureFailed3() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testIBMFeatureFailed4", featureName = "IBM",
			testCaseDescription = "testIBMFeatureFailed4 Description")
	public void testIBMFeatureFailed4() {
		Assert.assertTrue("Testcase is failing", false);
	}

}
