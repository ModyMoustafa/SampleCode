package com.siliconarabia.okarabiacomments.comment.service.api;

import java.util.List;

import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.CommentHash;
import com.siliconarabia.okarabiacomments.domain.SubComment;

/**
 * 
 * @author Ali M
 * 
 */
public interface CommentService {

	/**
	 * Add the comment to the data base
	 * 
	 * @param comment
	 *            to post
	 * @return the posted comment
	 */
	public Comment postComment(Comment comment) throws Exception;

	/**
	 * 
	 * @param url
	 *            to get the comments on it
	 * @return list of comments on this url
	 */
	public List<Comment> getCommentsByUrl(String url);

	/**
	 * Add the sub comment to the data base
	 * 
	 * @param subComment
	 *            to post
	 * @return the posted sub comment
	 * @throws ServiceException
	 */
	public SubComment postSubComment(SubComment subComment);

	/**
	 * 
	 * @param userCommentId
	 * @throws Exception
	 */
	public void deleteComment(Long userCommentId) throws Exception;

	/**
	 * 
	 * @param userCommentId
	 * @throws Exception
	 */
	public void deleteSubComment(Long userCommentId) throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 */
	public List<Comment> getCommentsByHashCode(CommentHash commentHash);

	/**
	 * 
	 * @param commentHash
	 * @return
	 */
	public CommentHash checkCommentHash(CommentHash commentHash);

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByCategory(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByLeagueId(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByTeamId(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByCategory(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByLeagueId(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByTeamAId(CommentHash commentHash)
			throws Exception;

	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByTeamBId(CommentHash commentHash)
			throws Exception;
}
