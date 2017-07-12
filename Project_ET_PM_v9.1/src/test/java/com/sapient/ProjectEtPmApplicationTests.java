package com.sapient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sapient.ProjectEtPmApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProjectEtPmApplication.class)
@WebAppConfiguration
public class ProjectEtPmApplicationTests {

	@Test
	public void contextLoads() {
	}

}
