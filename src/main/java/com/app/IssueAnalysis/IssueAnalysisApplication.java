package com.app.IssueAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IssueAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueAnalysisApplication.class, args);
	}

}
