package com.siliconarabia.okarabiacomments.ws.comment.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pearlox.framework.converter.AnnotationConverter;
import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.comment.service.api.CommentService;
import com.siliconarabia.okarabiacomments.converter.CommentConverter;
import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.CommentHash;
import com.siliconarabia.okarabiacomments.domain.SubComment;
import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserComment;
import com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService;

/**
 * @author Ali M & Michael Makram & Mody
 */

public class CommentWebServiceImpl implements CommentWebService {
	@Autowired
	private AnnotationConverter annotationConverter;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentConverter commentConverter;

	private static final Logger log = LoggerFactory
			.getLogger(CommentWebServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * postComment(com.siliconarabia.okarabiacomment.dto.CommentDTO)
	 */
	@Override
	public CommentDTO postComment(CommentDTO commentDTO)
			throws ServiceException {
		log.info("commentDTO>>>>>>>>>>>>" + commentDTO);
		try {
			Comment comment = annotationConverter.getDomainObject(commentDTO);
			log.info("comment>>>>>>>>>>>>" + comment);
			comment = commentService.postComment(comment);
			log.info("commentposted>>>>>>>>>>>>" + comment);
			CommentDTO addedCommentDTO = annotationConverter.getDTO(comment);
			log.info("addedCommentDTO>>>>>>>>>>>>" + addedCommentDTO);
			return addedCommentDTO;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * 
	 */
	@Override
	public List<UserComment> getUserCommentsByUrl(String url, Integer timeOffset)
			throws ServiceException {
		try {
			List<Comment> comments = commentService.getCommentsByUrl(url);
			List<UserComment> userComments = commentConverter.getUserComments(
					comments, timeOffset);
			return userComments;
		} catch (Exception e) {
			log.info("Exception in postComment WS IMPL " + e.getStackTrace());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * postSubComment(com.siliconarabia.okarabiacomments.dto.SubCommentDTO)
	 */
	@Override
	public SubCommentDTO postSubComment(SubCommentDTO subCommentDTO)
			throws ServiceException {
		try {
			log.info("Comment id = " + subCommentDTO.getCommentId());
			log.info("User id = " + subCommentDTO.getUserId());
			SubComment subComment = annotationConverter
					.getDomainObject(subCommentDTO);
			log.info("Comment = " + subComment.getComment());
			log.info("User = " + subComment.getUser());
			subComment = commentService.postSubComment(subComment);
			SubCommentDTO addedSubCommentDTO = annotationConverter
					.getDTO(subComment);
			return addedSubCommentDTO;
		} catch (Exception e) {
			log.info("Exception in postSubComment WS IMPL " + e.getStackTrace());
			return null;
		}
	}

	@Override
	public void deleteComment(Long userCommentId) throws ServiceException {
		try {
			commentService.deleteComment(userCommentId);
		} catch (Exception e) {
			log.info("ERROR in Delete comment " + e.getStackTrace());
		}
	}

	@Override
	public void deleteSubComment(Long userCommentId) throws ServiceException {
		try {
			commentService.deleteSubComment(userCommentId);
		} catch (Exception e) {
			log.info("ERROR in Delete subcomment " + e.getStackTrace());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * checkHashCode(com.siliconarabia.okarabiacomments.dto.CommentHashDTO)
	 */
	@Override
	public CommentHashDTO checkHashCode(CommentHashDTO commentHashCodeDTO) {

		try {

			log.info("Check commentHashDTO : : "
					+ commentHashCodeDTO.getCommentHashCode());

			CommentHash commentHash = annotationConverter
					.getDomainObject(commentHashCodeDTO);
			log.info("Check  commentHash : : "
					+ commentHash.getCommentHashCode());
			commentHash = commentService.checkCommentHash(commentHash);
			commentHashCodeDTO = annotationConverter.getDTO(commentHash);
			return commentHashCodeDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * getUserCommentByHashCode(java.lang.String)
	 */
	@Override
	public List<UserComment> getUserCommentByHashCode(String hashCode,
			Integer timeOffset) {

		try {
			CommentHashDTO commentHashDTO = new CommentHashDTO();
			commentHashDTO.setCommentHashCode(hashCode);
			commentHashDTO.setLeagueId(0l);
			commentHashDTO.setTeamAId(0l);
			commentHashDTO.setTeamBId(0l);
			log.info("Get commentHashDTO" + commentHashDTO.getCommentHashCode());
			CommentHash commentHash = annotationConverter
					.getDomainObject(commentHashDTO);
			log.info("Get Comment Has : : " + commentHash.getCommentHashCode());
			commentHash = commentService.checkCommentHash(commentHash);
			List<Comment> comments = commentService
					.getCommentsByHashCode(commentHash);
			List<UserComment> userComments = commentConverter.getUserComments(
					comments, timeOffset);
			return userComments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * getUserCommentByCategory(java.lang.String)
	 */
	@Override
	public List<UserComment> getUserCommentByCategory(String category,
			Integer timeOffset) throws ServiceException {
		try {

			CommentHashDTO commentHashDTO = new CommentHashDTO();
			commentHashDTO.setCategory(category);
			commentHashDTO.setLeagueId(0l);
			commentHashDTO.setTeamAId(0l);
			commentHashDTO.setTeamBId(0l);
			log.info("Cat commentHashDTO" + commentHashDTO.getCategory());
			CommentHash commentHash = annotationConverter
					.getDomainObject(commentHashDTO);
			log.info("Cat Comment Has : : " + commentHash.getCommentHashCode());
			List<Comment> comments = commentService
					.getCommentByCategory(commentHash);
			List<UserComment> userComments = commentConverter.getUserComments(
					comments, timeOffset);
			return userComments;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * getUserCommentByLeagueId(long)
	 */
	@Override
	public List<UserComment> getUserCommentByLeagueId(long leagueId,
			Integer timeOffset) throws ServiceException {
		try {
			CommentHashDTO commentHashDTO = new CommentHashDTO();
			commentHashDTO.setLeagueId(leagueId);
			commentHashDTO.setTeamAId(0l);
			commentHashDTO.setTeamBId(0l);
			log.info("League commentHashDTO" + commentHashDTO.getCategory());
			CommentHash commentHash = annotationConverter
					.getDomainObject(commentHashDTO);
			log.info("League Comment Has : : "
					+ commentHash.getCommentHashCode());
			List<Comment> comments = commentService
					.getCommentByLeagueId(commentHash);
			List<UserComment> userComments = commentConverter.getUserComments(
					comments, timeOffset);
			return userComments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * getUserCommentByTeamId(long)
	 */
	@Override
	public List<UserComment> getUserCommentByTeamId(long teamId,
			Integer timeOffset) throws ServiceException {
		try {
			CommentHashDTO commentHashDTO = new CommentHashDTO();
			commentHashDTO.setLeagueId(0l);
			commentHashDTO.setTeamAId(teamId);
			commentHashDTO.setTeamBId(teamId);
			log.info("Team commentHashDTO" + commentHashDTO.getCategory());
			CommentHash commentHash = annotationConverter
					.getDomainObject(commentHashDTO);
			log.info("Team Comment Has : : " + commentHash.getCommentHashCode());
			List<Comment> comments = commentService
					.getCommentByTeamId(commentHash);
			List<UserComment> userComments = commentConverter.getUserComments(
					comments, timeOffset);
			return userComments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.ws.comment.api.CommentWebService#
	 * getNumberofComments(java.lang.String)
	 */
	@Override
	public int getNumberofComments(String hashCode, Integer timeOffset) {
		List<UserComment> userComments = getUserCommentByHashCode(hashCode,
				timeOffset);

		if (userComments == null || userComments.size() == 0) {
			return 0;
		} else {
			return userComments.size();
		}

	}

}
