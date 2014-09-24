package com.siliconarabia.okarabiacomments.ws.comment.api;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.pearlox.framework.ws.exception.ServiceException;
import com.siliconarabia.okarabiacomments.dto.CommentDTO;
import com.siliconarabia.okarabiacomments.dto.CommentHashDTO;
import com.siliconarabia.okarabiacomments.dto.SubCommentDTO;
import com.siliconarabia.okarabiacomments.dto.UserComment;

/**
 * @author Ali M & Michael Makram & Mody
 */
@WebService
@Path("/api/comment")
public interface CommentWebService {

	/**
	 * Post Comment.
	 * 
	 * @param comment
	 * @return the posted comment
	 */
	@WebMethod(operationName = "PostComment")
	@GET
	@Path("/postComment")
	@WebResult(name = "result")
	public CommentDTO postComment(
			@WebParam(name = "comment") @PathParam("comment") CommentDTO commentDTO);

	/**
	 * 
	 * @param subCommentDTO
	 *            to post
	 * @return the posted sub comment
	 */
	public SubCommentDTO postSubComment(
			@WebParam(name = "sub_comment") @PathParam("sub_comment") SubCommentDTO subCommentDTO);

	/**
	 * Get Comments on the given URL.
	 * 
	 * @param url
	 * @return list of user comment bean
	 */
	@WebMethod(operationName = "GetUserCommentsByUrl")
	@GET
	@Path("/getUserComments")
	@WebResult(name = "result")
	public List<UserComment> getUserCommentsByUrl(
			@WebParam(name = "url") @PathParam("url") String url,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset);

	/**
	 * 
	 * @param commentDTO
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "DeleteComment")
	@POST
	@Path("/deleteComment")
	@WebResult(name = "result")
	public void deleteComment(
			@WebParam(name = "userCommentId") @PathParam("userCommentId") Long userCommentId)
			throws ServiceException;

	/**
	 * 
	 * @param subCommentDTO
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "DeleteSubComment")
	@POST
	@Path("/deleteSubComment")
	@WebResult(name = "result")
	public void deleteSubComment(
			@WebParam(name = "userCommentId") @PathParam("userCommentId") Long userCommentId)
			throws ServiceException;

	/**
	 * @param commentHashCode
	 * @return
	 */
	@WebMethod(operationName = "CheckHashCode")
	@GET
	@Path("/checkHashCode")
	@WebResult(name = "result")
	public CommentHashDTO checkHashCode(
			@WebParam(name = "commentHashCodeDTO") @PathParam("commentHashCodeDTO") CommentHashDTO commentHashCodeDTO);

	/**
	 * 
	 * @param hashCode
	 * @return
	 */
	@WebMethod(operationName = "GetUserCommentByHashCode")
	@GET
	@Path("/getUserCommentByHashCode")
	@WebResult(name = "result")
	public List<UserComment> getUserCommentByHashCode(
			@WebParam(name = "hashCode") @PathParam("hashCode") String hashCode,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset);

	/**
	 * 
	 * @param hashCode
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "GetUserCommentByCategory")
	@GET
	@Path("/getUserCommentByCategory")
	@WebResult(name = "result")
	public List<UserComment> getUserCommentByCategory(
			@WebParam(name = "category") @PathParam("category") String category,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset)
			throws ServiceException;

	/**
	 * 
	 * @param leagueId
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "getUserCommentByLeagueId")
	@GET
	@Path("/getUserCommentByLeagueId")
	@WebResult(name = "result")
	public List<UserComment> getUserCommentByLeagueId(
			@WebParam(name = "leagueId") @PathParam("leagueId") long leagueId,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset)
			throws ServiceException;

	/**
	 * 
	 * @param teamId
	 * @return
	 * @throws ServiceException
	 */
	@WebMethod(operationName = "getUserCommentByTeamId")
	@GET
	@Path("/getUserCommentByTeamId")
	@WebResult(name = "result")
	public List<UserComment> getUserCommentByTeamId(
			@WebParam(name = "teamId") @PathParam("teamId") long teamId,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset)
			throws ServiceException;

	/**
	 * 
	 * @param hashCode
	 * @return
	 */
	@WebMethod(operationName = "GetNumberofComments")
	@GET
	@Path("/getNumberofComments")
	@WebResult(name = "result")
	public int getNumberofComments(
			@WebParam(name = "hashCode") @PathParam("hashCode") String hashCode,
			@WebParam(name = "timeOffset") @PathParam("timeOffset") Integer timeOffset);
}
