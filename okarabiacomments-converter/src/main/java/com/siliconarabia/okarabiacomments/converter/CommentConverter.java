package com.siliconarabia.okarabiacomments.converter;

/***
 * 
 * 
 * @(#) IssueSearchResultConverter.java 3:09:28 PM Apr 13, 2011
 * 
 * Copyright (c) 2011 Copyright Silicon Nile Arabia Corp. All rights reserved.
 * 
 * Silicon Nile Arabia PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.SubComment;
import com.siliconarabia.okarabiacomments.domain.User;
import com.siliconarabia.okarabiacomments.dto.UserComment;

/**
 * 
 * @author Ali M & Michael Makram
 * 
 */
@Component
public class CommentConverter {
	private static final Logger log = LoggerFactory
			.getLogger(CommentConverter.class);

	/**
	 * Convert list comments domain to list of usercomment bean
	 * 
	 * @param comments
	 *            (list of domains)
	 * @return List of User comment bean
	 */
	public List<UserComment> getUserComments(List<Comment> comments,
			Integer timeOffset) {
		List<UserComment> userComments = new ArrayList<UserComment>(
				comments.size());
		for (Comment comment : comments) {
			userComments.add(getUserComment(comment, timeOffset));
		}
		return userComments;
	}

	/**
	 * Convert comment domain to usercomment bean
	 * 
	 * @param comment
	 * @return usercomment
	 */
	private UserComment getUserComment(Comment comment, Integer timeOffset) {
		UserComment userComment = new UserComment();
		// get User Object by userId from commentDTO
		User user = comment.getUser();
		// setting all needed properties in userCommentObject
		userComment.setId(comment.getId());
		userComment.setAvatar(user.getAvatar());
		userComment.setCommentBody(comment.getCommentBody());
		/************ Set Time Offset in Date ************/
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(comment.getDateSent());
		int HOUR_OF_DAY = calendar.get(calendar.HOUR_OF_DAY);
		calendar.set(calendar.HOUR_OF_DAY, HOUR_OF_DAY + timeOffset);
		Date date = calendar.getTime();
		log.info("<<<<<<<<<<<<<<Date>>>>>>>>>>>>>>>>>>>>" + date);
		/*************************************************/
		if (date != null) {
			String dateString = date + "";
			log.debug("dateString: " + dateString);
			userComment.setCommentDateSent(dateString.substring(0, 16) + " "
					+ dateString.substring(24));
		} else {
			userComment.setCommentDateSent(date + "");
		}
		userComment.setUserFullname(user.getFullName());
		userComment.setProfileId(user.getProfileId());
		log.debug("userComment: " + userComment);
		Set<SubComment> subComments = comment.getSubComments();
		List<UserComment> userSubComments = new ArrayList<UserComment>();
		for (SubComment subComment : subComments) {
			userSubComments.add(getSubComment(subComment, timeOffset));
		}
		userComment.setSubComments(userSubComments);
		return userComment;
	}

	/**
	 * Convert sub comment domain to usercomment bean
	 * 
	 * @param subComment
	 * @return usercomment
	 */
	private UserComment getSubComment(SubComment subComment, Integer timeOffset) {
		UserComment userComment = new UserComment();
		// get User Object by userId from commentDTO
		User user = subComment.getUser();
		// setting all needed properties in userCommentObject
		userComment.setId(subComment.getId());
		userComment.setAvatar(user.getAvatar());
		userComment.setCommentBody(subComment.getCommentBody());
		/************ Set Time Offset in Date ************/
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(subComment.getDateSent());
		int HOUR_OF_DAY = calendar.get(calendar.HOUR_OF_DAY);
		calendar.set(calendar.HOUR_OF_DAY, HOUR_OF_DAY + timeOffset);
		Date date = calendar.getTime();
		/*************************************************/
		if (date != null) {
			String dateString = date + "";
			log.debug("dateString: " + dateString);
			userComment.setCommentDateSent(dateString.substring(0, 16) + " "
					+ dateString.substring(24));
		} else {
			userComment.setCommentDateSent(date + "");
		}
		userComment.setUserFullname(user.getFullName());
		userComment.setProfileId(user.getProfileId());
		log.debug("userComment: " + userComment);
		return userComment;

	}
}
