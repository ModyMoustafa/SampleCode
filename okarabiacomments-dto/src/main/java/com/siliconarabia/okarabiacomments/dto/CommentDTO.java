package com.siliconarabia.okarabiacomments.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.pearlox.framework.converter.DomainClassRef;
import com.pearlox.framework.converter.DomainEntityRef;
import com.pearlox.framework.converter.DomainFieldRef;
import com.pearlox.framework.dto.BasicDTO;

/**
 * 
 * @author Ali
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@DomainClassRef(name = "Comment")
public class CommentDTO extends BasicDTO {
	private Long id;
	private Long userId;
	private String commentBody;
	private Date dateSent;
	private Long commentHashId ;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	@DomainFieldRef(fieldName = "user")
	@DomainEntityRef(className = "User")
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
	 * @param dateSent
	 *            the dateSent to set
	 */
	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	/**
	 * @return the dateSent
	 */
	public Date getDateSent() {
		return dateSent;
	}

	public void setCommentHashId(Long commentHashId) {
		this.commentHashId = commentHashId;
	}

	@DomainFieldRef(fieldName = "commentHash")
	@DomainEntityRef(className = "CommentHash")
	public Long getCommentHashId() {
		return commentHashId;
	}

}
