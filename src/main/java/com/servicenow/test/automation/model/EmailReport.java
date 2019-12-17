package com.servicenow.test.automation.model;

public class EmailReport {

	private String featureName;
	private int passed;
	private int failed;
	private int skipped;
	private int total;

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public int getPassed() {
		return passed;
	}

	public void setPassed(int passed) {
		this.passed = passed;
	}

	public int getFailed() {
		return failed;
	}

	public void setFailed(int failed) {
		this.failed = failed;
	}

	public int getSkipped() {
		return skipped;
	}

	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "EmailReport{" +
				"featureName='" + featureName + '\'' +
				", passed=" + passed +
				", failed=" + failed +
				", skipped=" + skipped +
				", total=" + total +
				'}';
	}
}
