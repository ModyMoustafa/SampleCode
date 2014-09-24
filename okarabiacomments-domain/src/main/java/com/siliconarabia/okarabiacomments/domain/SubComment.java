package com.siliconarabia.okarabiacomments.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pearlox.framework.converter.DTOClassRef;
import com.pearlox.framework.converter.DTOFieldRef;
import com.pearlox.framework.domain.BasicObject;

/**
 * @author Michael Makram
 * 
 **/
@SuppressWarnings("serial")
@Entity
@DTOClassRef(name = "SubCommentDTO")
@Table(name = "sub_comment", schema = "public")
public class SubComment extends BasicObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "comment_body")
	private String commentBody;

	@Column(name = "date_sent")
	private Date dateSent;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;

	/**
	 * @return the commentBody
	 */
	public String getCommentBody() {
		return commentBody;
	}

	/**
	 * @param commentBody
	 *            the commentBody to set
	 */
	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	@DTOFieldRef(fieldName = "userId")
	public User getUser() {
		return user;
	}

	/**
	 * @param dateSent
	 *            the dateSent to set
	 */
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	/**
	 * @return the dateSent
	 */
	@DTOFieldRef(fieldName = "dateSent")
	public Date getDateSent() {
		return dateSent;
	}

	/**
	 * @return the parentComment
	 */
	@DTOFieldRef(fieldName = "commentId")
	public Comment getComment() {
		return comment;
	}

	/**
	 * @param parentComment
	 *            the parentComment to set
	 */
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
