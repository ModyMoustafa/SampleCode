package com.siliconarabia.okarabiacomments.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pearlox.framework.dao.impl.BasicJpaDao;
import com.siliconarabia.okarabiacomments.dao.CommentDao;
import com.siliconarabia.okarabiacomments.domain.Comment;
import com.siliconarabia.okarabiacomments.domain.CommentHash;

/**
 * @author Ali M
 */
@Repository
public class CommentDaoImpl extends BasicJpaDao<Comment> implements CommentDao {
	private static final Logger log = LoggerFactory
			.getLogger(CommentDaoImpl.class);

	/**
	 * Default constructor
	 */
	public CommentDaoImpl() {
		super(Comment.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentDao#postComment(com.
	 * siliconarabia.okarabiacomments.domain.Comment)
	 */
	@Override
	@Transactional
	public Comment postComment(Comment comment) throws Exception {
		log.info("comment>>>>>>>>>>>>" + comment);
		try {
			log.debug("comment body: " + comment.getCommentBody());
			Comment postedComment = saveOrUpdate(comment);
			log.info("postedComment>>>>>>>>>>>>" + postedComment);
			if (postedComment != null)
				log.debug("comment body: " + postedComment.getCommentBody());
			return postedComment;
		} catch (Exception e) {
			log.error("Exception in postCommentDaoImpl postedComment= "
					+ e.getMessage());
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentsByUrl(java
	 * .lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Comment> getCommentsByUrl(String url) throws Exception {

		Query query = getEntityManager().createQuery(
				"from Comment where url = :url");
		query.setParameter("url", url);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentByHashCode
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByHashCode(CommentHash commentHash)
			throws Exception {
		try {
			log.info("Comment Hash hashcode : : " + commentHash.getId());

			String hql = "from Comment c where c.commentHash.id=:commentHashId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("commentHashId", commentHash.getId());
			log.info("Size from DAO" + query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentByCategory
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByCategory(CommentHash commentHash)
			throws Exception {
		try {
			log.info("CommentHash Dao" + commentHash.getCategory());
			String hql = "from Comment c left join c.commentHash ch where ch.category=:category";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("category", commentHash.getCategory());
			log.info("Size from DAO" + query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentByLeagueId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByLeagueId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from Comment c left join c.commentHash ch where ch.leagueId=:leagueId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("leagueId", commentHash.getLeagueId());
			log.info("Size from DAO" + query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentByTeamId(
	 * com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByTeamAId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from Comment c left join c.commentHash ch where ch.teamAId=:teamAId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("teamAId", commentHash.getTeamAId());
			log.info("Size from DAO" + query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.siliconarabia.okarabiacomments.dao.CommentDao#getCommentByTeamBId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentByTeamBId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from Comment c left join c.commentHash ch where ch.teamBId=:teamBId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("teamBId", commentHash.getTeamBId());
			log.info("Size from DAO" + query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
