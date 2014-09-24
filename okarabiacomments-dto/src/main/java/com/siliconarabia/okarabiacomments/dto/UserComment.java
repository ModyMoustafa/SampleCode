package com.siliconarabia.okarabiacomments.dto;

import java.util.List;

/**
 * this been will be displayed for users in front end
 * 
 * @author Ali M
 * 
 */
public class UserComment {
	private Long id;
	private String userFullname;
	private String commentBody;
	private String commentDateSent;
	private String avatar;
	private List<UserComment> subComments;
	private String profileId;

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

	public String getCommentDateSent() {
		return commentDateSent;
	}

	public void setCommentDateSent(String commentDateSent) {
		this.commentDateSent = commentDateSent;
	}

	/**
	 * @return the userFullname
	 */
	public String getUserFullname() {
		return userFullname;
	}

	/**
	 * @param userFullname
	 *            the userFullname to set
	 */
	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserComments [userFullname=" + userFullname + ", commentBody="
				+ commentBody + ", commentDateSent=" + commentDateSent + "]";
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileId() {
		return profileId;
	}

	/**
	 * @return the subComments
	 */
	public List<UserComment> getSubComments() {
		return subComments;
	}

	/**
	 * @param subComments
	 *            the subComments to set
	 */
	public void setSubComments(List<UserComment> subComments) {
		this.subComments = subComments;
	}

}
