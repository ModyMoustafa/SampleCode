package com.siliconarabia.okarabiacomments.dao;

import java.util.List;

import com.pearlox.framework.dao.BasicDao;
import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.CommentHash;

/**
 * @author Ali M
 */

public interface CommentDao extends BasicDao<Comment> {

	/**
	 * Posts comment
	 * 
	 * @return null
	 * @throws Exception
	 */
	public Comment postComment(Comment comment) throws Exception;

	/**
	 * Gets Comments of URL
	 * 
	 * @return List<Comment>
	 * @throws Exception
	 */
	public List<Comment> getCommentsByUrl(String url) throws Exception;
	
	/**
	 * get comments by Hashcode
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByHashCode(CommentHash commentHash) throws Exception; 
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByCategory(CommentHash commentHash) throws Exception;
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByLeagueId(CommentHash commentHash) throws Exception;
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByTeamAId(CommentHash commentHash) throws Exception;
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<Comment> getCommentByTeamBId(CommentHash commentHash) throws Exception;
}
