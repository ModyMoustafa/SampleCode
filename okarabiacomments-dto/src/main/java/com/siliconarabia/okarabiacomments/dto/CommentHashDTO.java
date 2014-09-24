package com.siliconarabia.okarabiacomments.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.pearlox.framework.converter.DomainClassRef;
import com.pearlox.framework.dto.BasicDTO;

/**
 * 
 * @author Mody
 * 
 */
@SuppressWarnings("serial")
@XmlRootElement
@DomainClassRef(name = "CommentHash")
public class CommentHashDTO extends BasicDTO{

	private Long id;

	private String commentHashCode;

	private String category;

	private Long leagueId;

	private Long teamAId;

	private Long teamBId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentHashCode() {
		return commentHashCode;
	}

	public void setCommentHashCode(String commentHashCode) {
		this.commentHashCode = commentHashCode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public Long getTeamAId() {
		return teamAId;
	}

	public void setTeamAId(Long teamAId) {
		this.teamAId = teamAId;
	}

	public Long getTeamBId() {
		return teamBId;
	}

	public void setTeamBId(Long teamBId) {
		this.teamBId = teamBId;
	}
}
