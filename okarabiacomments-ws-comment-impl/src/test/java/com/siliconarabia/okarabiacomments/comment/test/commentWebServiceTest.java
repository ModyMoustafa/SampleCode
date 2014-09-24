package com.siliconarabia.okarabiacomments.comment.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserComment;
import com.siliconarabia.okarabiacomments.dto.UserDTO;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;
import com.siliconarabia.okarabiacomments.ws.user.api.UserWebService;

/**
 * Unit Test for Comment WS
 * 
 * @author Ali M
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-beans.xml",
		"classpath:/META-INF/spring/dao-config.xml",
		"/META-INF/spring/bundle-context.xml" })
public class commentWebServiceTest {
	@Autowired
	private CommentWebService commentWebService;
	@Autowired
	private UserWebService userWebService;
	private static final Logger log = LoggerFactory
			.getLogger(commentWebServiceTest.class);

	@Test
	@Rollback
	public void postCommentTest() throws Exception {
		UserDTO userDTO = userWebService.findUserByProfileId("10000287");
		CommentDTO comment = new CommentDTO();
		comment.setCommentBody("hello world again and again");
		comment.setCommentHashId(1l);
		comment.setUserId(userDTO.getId());

		try {
			CommentDTO postedComment = commentWebService.postComment(comment);
			Assert.assertNotNull(postedComment);
		} catch (Exception e) {
			log.error("Exception in postCommentUT: " + e.getMessage());
		}
	}

	@Test
	@Rollback
	public void getUserCommentsTest() throws ServiceException {
		try {
			List<UserComment> userComments = commentWebService
					.getUserCommentsByUrl("www.test.com", 2);
			for (UserComment userComment : userComments) {
				log.debug("userComment: " + userComment.toString());
			}
			Assert.assertNotNull(userComments);
		} catch (Exception e) {
			log.error("Exception in getAllCommentsUT " + e.getMessage());
		}
	}

	@Test
	@Rollback
	public void postSubCommentTest() throws Exception {
		log.info("Start testing post subcomment");
		UserDTO userDTO = userWebService.findUserByProfileId("10000287");
		SubCommentDTO comment = new SubCommentDTO();
		comment.setCommentBody("This is sub comment");
		comment.setCommentId(1L);
		comment.setUserId(userDTO.getId());

		try {
			SubCommentDTO postedComment = commentWebService
					.postSubComment(comment);
			Assert.assertNotNull(postedComment);
		} catch (Exception e) {
			log.error("Exception in postSubCommentUT: " + e.getMessage());
		}
	}

}
