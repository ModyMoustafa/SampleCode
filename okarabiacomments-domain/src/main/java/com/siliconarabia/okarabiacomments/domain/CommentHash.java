package com.siliconarabia.okarabiacomments.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pearlox.framework.converter.DTOClassRef;
import com.pearlox.framework.converter.DTOFieldRef;
import com.pearlox.framework.domain.BasicObject;
/**
 * 
 * @author Mody
 *
 */
@SuppressWarnings("serial")
@Entity
@DTOClassRef(name = "CommentHashDTO")
@Table(name = "commenthash", schema = "public")
public class CommentHash extends BasicObject{

	@Column(name = "comment_hashcode")
	private String commentHashCode;

	@Column(name = "category")
	private String category;

	@Column(name = "league_id")
	private Long leagueId;

	@Column(name = "team_a_id")
	private Long teamAId;

	@Column(name = "team_b_id")
	private Long teamBId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "commentHash", cascade = CascadeType.REMOVE)
	private Set<Comment> comments = new HashSet<Comment>(0);

	@DTOFieldRef(fieldName = "commentHashCode")
	public String getCommentHashCode() {
		return commentHashCode;
	}

	public void setCommentHashCode(String commentHashCode) {
		this.commentHashCode = commentHashCode;
	}

	@DTOFieldRef(fieldName = "category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@DTOFieldRef(fieldName = "leagueId")
	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	@DTOFieldRef(fieldName = "teamAId")
	public Long getTeamAId() {
		return teamAId;
	}

	public void setTeamAId(Long teamAId) {
		this.teamAId = teamAId;
	}

	@DTOFieldRef(fieldName = "teamBId")
	public Long getTeamBId() {
		return teamBId;
	}

	public void setTeamBId(Long teamBId) {
		this.teamBId = teamBId;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Comment> getComments() {
		return comments;
	}

}
