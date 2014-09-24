package com.siliconarabia.okarabiacomments.dao;

import java.util.List;

import com.pearlox.framework.dao.BasicDao;
import com.siliconarabia.okarabiacomments.domain.CommentHash;

/**
 * 
 * @author Mody
 *
 */
public interface CommentHashCodeDao extends BasicDao<CommentHash> {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public CommentHash findCommentByHashCode(CommentHash commentHash) throws Exception;
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByCategory(CommentHash commentHash) throws Exception; 
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByLeagueId(CommentHash commentHash) throws Exception;
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByTeamAId(CommentHash commentHash) throws Exception;
	
	/**
	 * 
	 * @param commentHash
	 * @return
	 * @throws Exception
	 */
	public List<CommentHash> findCommentHashByTeamBId(CommentHash commentHash) throws Exception;
}
