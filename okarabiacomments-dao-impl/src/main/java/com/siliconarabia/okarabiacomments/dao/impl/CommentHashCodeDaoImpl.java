package com.siliconarabia.okarabiacomments.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.pearlox.framework.dao.impl.BasicJpaDao;
import com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao;
import com.siliconarabia.okarabiacomments.domain.CommentHash;

@Repository
public class CommentHashCodeDaoImpl extends BasicJpaDao<CommentHash> implements
		CommentHashCodeDao {

	private static final Logger log = LoggerFactory
			.getLogger(CommentHashCodeDaoImpl.class);

	public CommentHashCodeDaoImpl() {
		super(CommentHash.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao#
	 * findCommentByHashCode
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@Override
	public CommentHash findCommentByHashCode(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from CommentHash where commentHashCode=:commentHashCode";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("commentHashCode",
					commentHash.getCommentHashCode());
			return (CommentHash) query.getSingleResult();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao#
	 * findCommentHashByCategory
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentHash> findCommentHashByCategory(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from CommentHash where category=:category";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("category", commentHash.getCategory());
			log.info("Cat DAO SIZE"+ query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao#
	 * findCommentHashByLeagueId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentHash> findCommentHashByLeagueId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from CommentHash where leagueId=:leagueId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("leagueId", commentHash.getLeagueId());
			log.info("League DAO SIZE"+ query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao#
	 * findCommentHashByTeamAId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentHash> findCommentHashByTeamAId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from CommentHash where teamAId=:teamAId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("teamAId", commentHash.getTeamAId());
			log.info("TeamA DAO SIZE"+ query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.siliconarabia.okarabiacomments.dao.CommentHashCodeDao#
	 * findCommentHashByTeamBId
	 * (com.siliconarabia.okarabiacomments.domain.CommentHash)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CommentHash> findCommentHashByTeamBId(CommentHash commentHash)
			throws Exception {
		try {
			String hql = "from CommentHash where teamBId=:teamBId";
			Query query = getEntityManager().createQuery(hql);
			query.setParameter("teamBId", commentHash.getTeamBId());
			log.info("TeamB DAO SIZE"+ query.getResultList().size());
			return query.getResultList();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
