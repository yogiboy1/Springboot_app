package com.yohan.myfirstapp;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "privet")
public class PrivetProperties {
private String loadTestData = "not dev";

public String getLoadTestData() {
	return loadTestData;
}

public void setLoadTestData(String loadTestData) {
	this.loadTestData = loadTestData;
}


}
