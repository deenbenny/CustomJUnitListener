package com.servicenow.test.automation.listener.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.servicenow.test.automation.annotation.Report;

public class TestFeatureAzure {

	@Test
	@Report(testCaseName = "testAzureFeature", featureName = "Azure",
			testCaseDescription = "testAzureFeature Description")
	public void testAzureFeature() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAzureFeature1", featureName = "Azure",
			testCaseDescription = "testAzureFeature1 Description")
	public void testAzureFeature1() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAzureFeature2", featureName = "Azure",
			testCaseDescription = "testAzureFeature2 Description")
	public void testAzureFeature2() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAzureFeature3", featureName = "Azure",
			testCaseDescription = "testAzureFeature3 Description")
	public void testAzureFeature3() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAzureFeature4", featureName = "Azure",
			testCaseDescription = "testAzureFeature4 Description")
	public void testAzureFeature4() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testSecondFeatureIgnored", featureName = "Azure",
			testCaseDescription = "testSecondFeatureIgnored Description")
	public void testAzureFeatureIgnored() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAzureFeatureIgnored1", featureName = "Azure",
			testCaseDescription = "testAzureFeatureIgnored1 Description")
	public void testAzureFeatureIgnored1() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAzureFeatureIgnored2", featureName = "Azure",
			testCaseDescription = "testAzureFeatureIgnored2 Description")
	public void testAzureFeatureIgnored2() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAzureFeatureIgnored3", featureName = "Azure",
			testCaseDescription = "testAzureFeatureIgnored3 Description")
	public void testAzureFeatureIgnored3() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAzureFeatureIgnored4", featureName = "Azure",
			testCaseDescription = "testAzureFeatureIgnored4 Description")
	public void testAzureFeatureIgnored4() {
		Assert.assertTrue(true);
	}

	@Test
	@Report(testCaseName = "testAzureFeatureFailed", featureName = "Azure",
			testCaseDescription = "testAzureFeatureFailed Description")
	public void testAzureFeatureFailed() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAzureFeatureFailed1", featureName = "Azure",
			testCaseDescription = "testAzureFeatureFailed1 Description")
	public void testAzureFeatureFailed1() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAzureFeatureFailed2", featureName = "Azure",
			testCaseDescription = "testAzureFeatureFailed2 Description")
	public void testAzureFeatureFailed2() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAzureFeatureFailed3", featureName = "Azure",
			testCaseDescription = "testAzureFeatureFailed3 Description")
	public void testAzureFeatureFailed3() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAzureFeatureFailed4", featureName = "Azure",
			testCaseDescription = "testAzureFeatureFailed4 Description")
	public void testAzureFeatureFailed4() {
		Assert.assertTrue("Testcase is failing", false);
	}

}
