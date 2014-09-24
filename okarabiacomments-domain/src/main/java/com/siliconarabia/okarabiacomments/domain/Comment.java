package com.siliconarabia.okarabiacomments.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pearlox.framework.converter.DTOClassRef;
import com.pearlox.framework.converter.DTOFieldRef;
import com.pearlox.framework.domain.BasicObject;

/**
 * @author Ali M & Michael Makram
 **/
@SuppressWarnings("serial")
@Entity
@DTOClassRef(name = "CommentDTO")
@Table(name = "comment", schema = "public")
public class Comment extends BasicObject {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "comment_body")
	private String commentBody;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comment_hashcode_id", nullable = false)
	private CommentHash commentHash ;
	
	@Column(name = "date_sent")
	private Date dateSent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment", cascade = CascadeType.REMOVE)
	private Set<SubComment> subComments = new HashSet<SubComment>(0);

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
	 * @return the subComments
	 */
	public Set<SubComment> getSubComments() {
		return subComments;
	}

	/**
	 * @param subComments
	 *            the subComments to set
	 */
	public void setSubComments(Set<SubComment> subComments) {
		this.subComments = subComments;
	}

	public void setCommentHash(CommentHash commentHash) {
		this.commentHash = commentHash;
	}

	/**
	 * 
	 * @return
	 */
	@DTOFieldRef(fieldName = "commentHashId")
	public CommentHash getCommentHash() {
		return commentHash;
	}

}
