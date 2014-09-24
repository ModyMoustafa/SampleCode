package com.siliconarabia.okarabiacomments.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siliconarabia.okarabiacomments.comment.service.api.CommentService;
import com.siliconarabia.okarabiacomments.dao.CommentDao;
import com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao;
import com.siliconarabia.okarabiacomments.dao.SubCommentDao;
import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.CommentHash;
import com.siliconarabia.okarabiacomments.domain.SubComment;

/**
 * 
 * @author Ali M
 * 
 */
@Service
public class CommentServiceImpl implements CommentService {
	private final Logger log = LoggerFactory
			.getLogger(CommentServiceImpl.class);
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private SubCommentDao subCommentDao;

	@Autowired
	private CommentHashCodeDao commentHashCodeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #postComment(com.siliconarabia.okarabiacomments.domain.Comment)
	 */
	@Override
	public Comment postComment(Comment comment) {
		try {

			Comment addedComment = commentDao.postComment(comment);
			return addedComment;
		} catch (Exception e) {
			String msg = "Error in postComment Service";
			log.error(msg);
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #getCommentsByUrl(java.lang.String)
	 */
	@Override
	public List<Comment> getCommentsByUrl(String url) {
		try {

			List<Comment> comments = commentDao.getCommentsByUrl(url);
			return comments;
		} catch (Exception e) {
			String msg = "Error in getCommentsByUrl Service";
			log.error(msg);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #postSubComment(com.siliconarabia.okarabiacomments.domain.SubComment)
	 */
	@Override
	public SubComment postSubComment(SubComment subComment) {
		try {

			subComment = subCommentDao.saveOrUpdate(subComment);
			return subComment;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteComment(Long userCommentId) throws Exception {
		Comment comment = commentDao.findById(userCommentId);
		commentDao.delete(comment);

	}

	@Override
	public void deleteSubComment(Long userCommentId) throws Exception {
		SubComment subComment = subCommentDao.findById(userCommentId);
		subCommentDao.delete(subComment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #getCommentsByHashCode
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<Comment> getCommentsByHashCode(CommentHash commentHash) {

		try {
			log.info("SIZE service : : "
					+ commentDao.getCommentByHashCode(commentHash).size());
			return commentDao.getCommentByHashCode(commentHash);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #checkCommentHash(com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public CommentHash checkCommentHash(CommentHash commentHash) {
		try {
			CommentHash tempCommentHash = commentHashCodeDao
					.findCommentByHashCode(commentHash);
			if (tempCommentHash != null) {
				return tempCommentHash;
			} else
				return commentHashCodeDao.saveOrUpdate(commentHash);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #getCommentByCategory
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<Comment> getCommentByCategory(CommentHash commentHash)
			throws Exception {
		try {
			// return commentDao.getCommentByCategory(commentHash);
			List<CommentHash> commentHashList = findCommentHashByCategory(commentHash);
			List<Comment> mainCommentList = new ArrayList<Comment>();
			for (int i = 0; i < commentHashList.size(); i++) {
				List<Comment> internalCommentList = commentDao
						.getCommentByHashCode(commentHashList.get(i));
				for (int j = 0; j < internalCommentList.size(); j++) {
					if (!mainCommentList.contains(internalCommentList.get(j))) {
						mainCommentList.add(internalCommentList.get(j));
					}
				}
			}
			return mainCommentList;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #getCommentByLeagueId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<Comment> getCommentByLeagueId(CommentHash commentHash)
			throws Exception {
		try {
			// return commentDao.getCommentByLeagueId(commentHash);
			List<CommentHash> commentHashList = findCommentHashByLeagueId(commentHash);
			List<Comment> mainCommentList = new ArrayList<Comment>();
			for (int i = 0; i < commentHashList.size(); i++) {
				List<Comment> internalCommentList = commentDao
						.getCommentByHashCode(commentHashList.get(i));
				for (int j = 0; j < internalCommentList.size(); j++) {
					if (!mainCommentList.contains(internalCommentList.get(j))) {
						mainCommentList.add(internalCommentList.get(j));
					}
				}
			}
			return mainCommentList;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #getCommentByTeamAId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<Comment> getCommentByTeamId(CommentHash commentHash)
			throws Exception {
		try {
			// List<Comment> commentTeamAList = commentDao
			// .getCommentByTeamAId(commentHash);
			// List<Comment> commentTeamBList = commentDao
			// .getCommentByTeamBId(commentHash);
			// for (int i = 0; i < commentTeamBList.size(); i++) {
			// commentTeamAList.add(commentTeamBList.get(i));
			// }
			// return commentTeamAList;
			List<CommentHash> commentHashForTeamAList = findCommentHashByTeamAId(commentHash);
			List<Comment> mainCommentList = new ArrayList<Comment>();
			for (int i = 0; i < commentHashForTeamAList.size(); i++) {
				List<Comment> internalCommentList = commentDao
						.getCommentByHashCode(commentHashForTeamAList.get(i));
				for (int j = 0; j < internalCommentList.size(); j++) {
					if (!mainCommentList.contains(internalCommentList.get(j))) {
						mainCommentList.add(internalCommentList.get(j));
					}
				}
			}
			List<CommentHash> commentHashForTeamBList = findCommentHashByTeamBId(commentHash);
			for (int i = 0; i < commentHashForTeamBList.size(); i++) {
				List<Comment> internalCommentList = commentDao
						.getCommentByHashCode(commentHashForTeamAList.get(i));
				for (int j = 0; j < internalCommentList.size(); j++) {
					if (!mainCommentList.contains(internalCommentList.get(j))) {
						mainCommentList.add(internalCommentList.get(j));
					}
				}
			}
			return mainCommentList;
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #findCommentHashByCategory
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<CommentHash> findCommentHashByCategory(CommentHash commentHash)
			throws Exception {
		try {
			return commentHashCodeDao.findCommentHashByCategory(commentHash);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #findCommentHashByLeagueId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<CommentHash> findCommentHashByLeagueId(CommentHash commentHash)
			throws Exception {
		try {
			return commentHashCodeDao.findCommentHashByLeagueId(commentHash);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #findCommentHashByTeamAId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<CommentHash> findCommentHashByTeamAId(CommentHash commentHash)
			throws Exception {
		try {
			return commentHashCodeDao.findCommentHashByTeamAId(commentHash);
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.comment.service.api.CommentService
	 * #findCommentHashByTeamBId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public List<CommentHash> findCommentHashByTeamBId(CommentHash commentHash)
			throws Exception {
		try {
			return commentHashCodeDao.findCommentHashByTeamBId(commentHash);
		} catch (Exception e) {
			return null;
		}
	}

}
