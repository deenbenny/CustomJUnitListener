package com.servicenow.test.automation.listener.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.servicenow.test.automation.annotation.Report;

public class TestFeatureGCP {

	@Test
	@Report(testCaseName = "testGCPFeature", featureName = "GCP",
			testCaseDescription = "testGCPFeature Description")
	public void testGCPFeature() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testGCPFeature1", featureName = "GCP",
			testCaseDescription = "testGCPFeature1 Description")
	public void testGCPFeature1() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testGCPFeature2", featureName = "GCP",
			testCaseDescription = "testGCPFeature2 Description")
	public void testGCPFeature2() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testGCPFeature3", featureName = "GCP",
			testCaseDescription = "testGCPFeature3 Description")
	public void testGCPFeature3() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testGCPFeature4", featureName = "GCP",
			testCaseDescription = "testGCPFeature4 Description")
	public void testGCPFeature4() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testSecondFeatureIgnored", featureName = "GCP",
			testCaseDescription = "testSecondFeatureIgnored Description")
	public void testGCPFeatureIgnored() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testGCPFeatureIgnored1", featureName = "GCP",
			testCaseDescription = "testGCPFeatureIgnored1 Description")
	public void testGCPFeatureIgnored1() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testGCPFeatureIgnored2", featureName = "GCP",
			testCaseDescription = "testGCPFeatureIgnored2 Description")
	public void testGCPFeatureIgnored2() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testGCPFeatureIgnored3", featureName = "GCP",
			testCaseDescription = "testGCPFeatureIgnored3 Description")
	public void testGCPFeatureIgnored3() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testGCPFeatureIgnored4", featureName = "GCP",
			testCaseDescription = "testGCPFeatureIgnored4 Description")
	public void testGCPFeatureIgnored4() {
		Assert.assertTrue(true);
	}

	@Test
	@Report(testCaseName = "testGCPFeatureFailed", featureName = "GCP",
			testCaseDescription = "testGCPFeatureFailed Description")
	public void testGCPFeatureFailed() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testGCPFeatureFailed1", featureName = "GCP",
			testCaseDescription = "testGCPFeatureFailed1 Description")
	public void testGCPFeatureFailed1() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testGCPFeatureFailed2", featureName = "GCP",
			testCaseDescription = "testGCPFeatureFailed2 Description")
	public void testGCPFeatureFailed2() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testGCPFeatureFailed3", featureName = "GCP",
			testCaseDescription = "testGCPFeatureFailed3 Description")
	public void testGCPFeatureFailed3() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testGCPFeatureFailed4", featureName = "GCP",
			testCaseDescription = "testGCPFeatureFailed4 Description")
	public void testGCPFeatureFailed4() {
		Assert.assertTrue("Testcase is failing", false);
	}

}
