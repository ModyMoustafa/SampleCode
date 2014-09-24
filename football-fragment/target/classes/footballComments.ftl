<#import "spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>OKArabia Comments</title>
		<link rel="stylesheet" type="text/css" href="<@spring.url "/css/style.css"/>"/>
		<script src="http://yui.yahooapis.com/3.4.0/build/yui/yui-min.js"></script>
		<script type="text/javascript">
			function addCmnt() {
				document.getElementById("newCmnt").style.display = 'block';
			}

			function submitform() {
				var textAreaValue = document.commentForm.textarea.value;
				var textPattern = /(\S)/;
				if (!textPattern.test(textAreaValue) || textAreaValue.length == 0 || textAreaValue.length >= 400) {
					document.getElementById("error").style.display = 'block';
					return false;
				}
				document.commentForm.submit();
			}

			function submitReplyform() {
				var textAreaValue = document.ReplyForm.textarea.value;
				var textPattern = /(\S)/;
				if (!textPattern.test(textAreaValue) || textAreaValue.length == 0 || textAreaValue.length >= 400) {
					document.getElementById("errorReply").style.display = 'block';
					return false;
				}
				document.ReplyForm.submit();
			}
			function removeComment(id){
				document.getElementById("commentRemoveLink"+id).href = "/remove?commentId=id&url="+window.location
			}
			function removeSubComment(id){
				document.getElementById("subCommentRemoveLink"+id).href="/removesub?commentId=id+url="+window.location
			}
		</script>
		
		<script type="text/javascript">
			function reply(name,id) {
				if (document.getElementById("newReplyCmnt")) {
					document.getElementById("newReplyCmnt").innerHTML = "";
					document.getElementById("newReplyCmnt").style.display = "none";
					document.getElementById("newReplyCmnt").setAttribute('id', 'hidden');
				}
				var newReplyCmntDiv = document.createElement('div');
				newReplyCmntDiv.setAttribute('id', 'newReplyCmnt');
				name.parentNode.parentNode.parentNode.parentNode
						.appendChild(newReplyCmntDiv);
				var usrName = name.parentNode.innerHTML;
				usrName= usrName.split("</a>");
				usrName= usrName[0].split(">");

                var pageRedirectUrl = document.getElementById("pageRedirectUrl").value;

				document.getElementById("newReplyCmnt").innerHTML = '<form name="ReplyForm" onsubmit="return submitReplyform();" action="/index" method="POST">'
						+ '<div  id="errorReply" style="color:red;display:none;"> Error in posting comment </div>'
						+ '<input type="hidden" value="'+id+'" name="commentId" id="commentId">'
                        + '<input type="hidden" value="' + pageRedirectUrl + '" name="pageRedirectUrl">'
						+ '<#if avatar?exists>'
						+ '<img src="${avatar}"/>'
						+ '<#else>'
						+ '<img src="<@spring.url "/images/none.png"/>"/>'
						+ '</#if>'
						+ '<textarea rows="2" cols="73" placeholder="add a comment ..." name="commentBody" id="textarea" style="width: 78%;">'
						+ usrName[2]
						+'</textarea>'
						+ '<select name="redirect" style="margin-top: 5px;">'
						+ '<option value="redirect:/facebook" selected>Facebook</option>'
						+ '<option value="redirect:/linkedin">Linkedin</option>'
						+ '<option value="redirect:/connect/twitter">Twitter</option>'
						+ '<option value="redirect:/login">OKArabia</option>'
						+ '</select>'
						+ '<input type="submit" id="reply" value="Reply" class="cmntBtn" />';
						
			}
		
		</script>
	</head>
	<body>
		<div id="wrap">
			<div id="cmntsContainer">
				<#if comments?has_content>
					<#list comments as comment>
						<div class="cmnt">
							<div id="cmnt1">
								<div class="postCmnt post">
									<#if comment.avatar?exists>
										<img src="${comment.avatar}"/>
									<#else>
										<img src="/images/none.png"/>
									</#if>
									<div class="postContent">
										<h4><a href="#">${comment.userFullname}</a></h4>
										<p>${comment.commentBody}</p>
										<a onclick="reply(this,${comment.id})" class="clickable">Reply</a>
										<#if currentUser?exists>
											<#if currentUser.profileId==comment.profileId >
												<a href="/remove?commentId=${comment.id}&url=${urlSession}">Delete</a>
											</#if>
										</#if>
										<label class="date"> ${comment.commentDateSent} </label>
									</div>
								</div>
								<#if comment.subComments?exists>
									<#list comment.subComments as subComment>
										<div class="postCmnt old">
											<#if subComment.avatar?exists>
												<img src="${subComment.avatar}"/>
											<#else>
												<img src="/images/none.png"/>
											</#if>
											<div class="postContent">
												<h4><a href="#">${subComment.userFullname}</a></h4>
												<p>${subComment.commentBody}</p>
												<a onclick="reply(this,${comment.id})" class="clickable">Reply</a>
												<#if currentUser?exists>
													<#if currentUser.profileId==subComment.profileId>
														<a href="/removesub?commentId=${subComment.id}&url=${urlSession}">Delete</a>
													</#if>
												</#if>
												<label class="date">${subComment.commentDateSent}</label>
											</div>
										</div>
									</#list>
								</#if>
							</div>
						</div>
					</#list>
				</#if>
			</div>
			<div  id="error" style="color:red;display:none;"> Error in posting comment </div>
			<form name="commentForm" onsubmit="return submitform();" action="/index" method="POST">
				<div class="addCmnt">

                    <input name="pageRedirectUrl" type="hidden" id="pageRedirectUrl">

					<div id="newCmnt">
						<#if avatar?exists>
							<img src="${avatar}"/>
						<#else>
							<img src="<@spring.url "/images/none.png"/>"/>
						</#if>
						<textarea rows="2" cols="100" placeholder="add a comment ..." name="commentBody" id="textarea"></textarea>
						<select name="redirect" style="margin-top: 5px;">
							<option value="redirect:/facebook" selected>Facebook</option>
							<option value="redirect:/linkedin">Linkedin</option>
							<option value="redirect:/connect/twitter">Twitter</option>
							<option value="redirect:/login">OKArabia</option>
						</select>
						<input id="comment" type="submit" value="comment" class="cmntBtn">
					</div>
				</div>
			</form>
		</div>
        <script type="text/javascript">
	    	var divh = document.getElementById('wrap').offsetHeight;
	    	divh = divh+10;
	    	parent.postMessage(divh, "*");
	    	parent.postMessage("getParentUrl", "*");
            function getParentUrlHandler(event) {
                document.getElementById("pageRedirectUrl").value = event.data;
            }

            if (window['addEventListener']) {
                window.addEventListener("message", getParentUrlHandler, false);
            } else {
                window.attchEvent("onmessage", getParentUrlHandler);
            }
        </script>
	</body>
</html>
