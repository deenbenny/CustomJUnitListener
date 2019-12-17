package com.servicenow.test.automation.listener.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.servicenow.test.automation.annotation.Report;

public class TestFeatureAWS {

	@Test
	@Report(testCaseName = "testAWSFeature", featureName = "AWS",
			testCaseDescription = "testAWSFeature Description")
	public void testAWSFeature() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAWSFeature1", featureName = "AWS",
			testCaseDescription = "testAWSFeature1 Description")
	public void testAWSFeature1() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAWSFeature2", featureName = "AWS",
			testCaseDescription = "testAWSFeature2 Description")
	public void testAWSFeature2() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAWSFeature3", featureName = "AWS",
			testCaseDescription = "testAWSFeature3 Description")
	public void testAWSFeature3() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Report(testCaseName = "testAWSFeature4", featureName = "AWS",
			testCaseDescription = "testAWSFeature4 Description")
	public void testAWSFeature4() {
		Assert.assertTrue("Working fine", true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testSecondFeatureIgnored", featureName = "AWS",
			testCaseDescription = "testSecondFeatureIgnored Description")
	public void testAWSFeatureIgnored() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAWSFeatureIgnored1", featureName = "AWS",
			testCaseDescription = "testAWSFeatureIgnored1 Description")
	public void testAWSFeatureIgnored1() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAWSFeatureIgnored2", featureName = "AWS",
			testCaseDescription = "testAWSFeatureIgnored2 Description")
	public void testAWSFeatureIgnored2() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAWSFeatureIgnored3", featureName = "AWS",
			testCaseDescription = "testAWSFeatureIgnored3 Description")
	public void testAWSFeatureIgnored3() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	@Report(testCaseName = "testAWSFeatureIgnored4", featureName = "AWS",
			testCaseDescription = "testAWSFeatureIgnored4 Description")
	public void testAWSFeatureIgnored4() {
		Assert.assertTrue(true);
	}

	@Test
	@Report(testCaseName = "testAWSFeatureFailed", featureName = "AWS",
			testCaseDescription = "testAWSFeatureFailed Description")
	public void testAWSFeatureFailed() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAWSFeatureFailed1", featureName = "AWS",
			testCaseDescription = "testAWSFeatureFailed1 Description")
	public void testAWSFeatureFailed1() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAWSFeatureFailed2", featureName = "AWS",
			testCaseDescription = "testAWSFeatureFailed2 Description")
	public void testAWSFeatureFailed2() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAWSFeatureFailed3", featureName = "AWS",
			testCaseDescription = "testAWSFeatureFailed3 Description")
	public void testAWSFeatureFailed3() {
		Assert.assertTrue("Testcase is failing", false);
	}

	@Test
	@Report(testCaseName = "testAWSFeatureFailed4", featureName = "AWS",
			testCaseDescription = "testAWSFeatureFailed4 Description")
	public void testAWSFeatureFailed4() {
		Assert.assertTrue("Testcase is failing", false);
	}

}
