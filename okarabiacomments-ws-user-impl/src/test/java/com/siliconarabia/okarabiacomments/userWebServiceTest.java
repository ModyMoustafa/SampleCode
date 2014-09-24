package com.siliconarabia.okarabiacomments;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;

/**
 * @author Ali M Unit Test for Comments WS
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml",
		"classpath:/META-INF/spring/dao-config.xml",
		"/META-INF/spring/bundle-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class userWebServiceTest {

	@Autowired
	private UserWebService userWebService;

	private static final Logger log = LoggerFactory
			.getLogger(userWebServiceTest.class);

	/**
	 * Test post comments method
	 */
	@Test
	@Rollback
	public void postCommentTest() {
		try {
			UserDTO userDTO = userWebService.findUserByProfileId("10000287");
			log.info("=============== getAllUsers " + userDTO.toString());
			Assert.assertNotNull(userDTO);
		} catch (Exception e) {
			log.info("=============== Exception in getAllUsers "
					+ e.getMessage());
		}
	}
}
