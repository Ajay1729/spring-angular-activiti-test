package com.business.proposal.workflowengineservice;

import com.business.proposal.workflow.engine.WorkflowEngineServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {WorkflowEngineServiceApplication.class})
@WebAppConfiguration
@IntegrationTest
public class WorkflowEngineServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
