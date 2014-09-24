<!DOCTYPE html>
<html>
<head>
<#import "spring.ftl" as spring>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>OKArabia.com | Login</title>
<link rel="stylesheet" type="text/css" href="/css/loginStyle.css">
<script type="text/javascript" src="<@spring.url "/WEB-INF/js/commentLoader.js"/>"></script>
<#--check for language cookie, session or default-->
		 <#if lang??>
			  <#assign language=lang/> 
		 <#else>
			  <#assign language="ar">
	  	 </#if>
		<#include "WEB-INF/views/imports.ftl">

<script language="javascript" type="text/javascript">
function focusField(){
	YAHOO.util.Dom.get("j_username").focus();
}
YAHOO.util.Event.onDOMReady(focusField);
function validateEmail(){
	var emailValue = YAHOO.util.Dom.get("j_username").value;
	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	
	if(emailPattern.test(emailValue) || emailValue.length == 0)
		YAHOO.util.Dom.get("emailHint").style.display = "none";
	if(!emailPattern.test(emailValue) && emailValue.length != 0)
	{
		YAHOO.util.Dom.get("emailHint").style.display = "block";
		YAHOO.util.Dom.get("emailHint").innerHTML = "Email format you entered is wrong. Please try again <span>(user_name@example.com).</span>";
	}
 }
YAHOO.util.Event.addListener("j_username", "blur", validateEmail);
function emailHint(){
	YAHOO.util.Dom.get("emailHint").style.display = "block";
	YAHOO.util.Dom.get("emailHint").innerHTML = "<span>Email format example: (user_name@example.com).</span>";
 }
YAHOO.util.Event.addListener("j_username", "focus", emailHint);
function enableLogin(e) {
	var emailValue = YAHOO.util.Dom.get("j_username").value;
	var j_password = YAHOO.util.Dom.get("j_password").value;
	if(emailValue != "" && j_password != "")
		{
		YAHOO.util.Dom.get("loginBtn").className = "enabled";
		YAHOO.util.Dom.get("loginBtn").disabled = false;
		}
	if(emailValue == "" || j_password == "")
	{
		YAHOO.util.Dom.get("loginBtn").className = "disabled";
		YAHOO.util.Dom.get("loginBtn").disabled = true;
	}
 
}
YAHOO.util.Event.addListener("j_username", "keyup", enableLogin);
YAHOO.util.Event.addListener("j_password", "keyup", enableLogin);
</script>
</head>
<#if language = "ar">
		<#assign textAlign = "right"/>
		<#assign direction = "rtl"/>
	<#else>
		<#assign textAlign = "left"/>
		<#assign direction = "ltr"/>
	</#if>
<body class="yui-skin-sam" onload="getUserDate();">
<div class="wrap enter_content">
<div class="main" id="wrap">
<h1>Login</h1>

<div class="loginBox">
<form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
	<label accesskey="u" for="j_username" id="usernamelabel">Email:</label>
	<input type="text" name="j_username" id="j_username" value=""/>
	<div id="emailHint">Email format you entered is wrong. Please try again <span>(user_name@example.com).</span></div>
	<label accesskey="p" for="j_password" id="passwordlabel">Password:</label>
	<input type="password" id="j_password" name="j_password"/>
	<input type="submit" id="loginBtn" value="Login" disabled="disabled" class="disabled"/>
	<div class="links">
		<span>New user, </span><a id="registerBtn" href="/register">register?</a>
	</div>
</form>
<script type="text/javascript">
	var divh = document.getElementById('wrap').offsetHeight;
	divh = divh+25;
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
</div>
</div>
</div>
</body>
</html>