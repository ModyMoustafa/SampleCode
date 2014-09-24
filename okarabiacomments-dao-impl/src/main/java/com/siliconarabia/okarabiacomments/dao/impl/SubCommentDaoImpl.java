package com.siliconarabia.okarabiacomments.dao.impl;

import org.springframework.stereotype.Repository;

import com.pearlox.framework.dao.impl.BasicJpaDao;
import com.siliconarabia.okarabiacomments.dao.SubCommentDao;
import com.siliconarabia.okarabiacomments.domain.SubComment;

/**
 * @author Ali M
 **/

@Repository
public class SubCommentDaoImpl extends BasicJpaDao<SubComment> implements
		SubCommentDao {

	/**
	 * Default constructor
	 */
	public SubCommentDaoImpl() {
		super(SubComment.class);
	}

}
