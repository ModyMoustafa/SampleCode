<!DOCTYPE html>
<html>
<head>
<#import "spring.ftl" as spring>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OKarabia.com | Resgister</title>
<#include "WEB-INF/views/imports.ftl">
<#--check for language cookie, session or default-->
	<#if lang??>
		<#assign language=lang/> 
	<#else>
		 <#assign language="ar">
	</#if> 
<#--end check for language cookie, session or default-->
<link rel="stylesheet" type="text/css" href="/css/registrationStyle.css">

<script src="js/yui-min.js" type="text/javascript"></script>
<script src="http://connect.facebook.net/en_US/all.js#xfbml=1"></script>
<script type="text/javascript" src="build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="build/dragdrop/dragdrop-min.js"></script>

<script type="text/javascript" src="build/container/container-min.js"></script>
<script type="text/javascript" src="build/assets/containerariaplugin.js"></script>

<script src="http://yui.yahooapis.com/2.9.0/build/dom/dom-min.js" ></script>

<script type="text/javascript" language="javascript">
function focusField(){
	YAHOO.util.Dom.get("emailTB").focus();
}
YAHOO.util.Event.onDOMReady(focusField);
function emailHint(){
	YAHOO.util.Dom.get("emailRq").style.display = "block";
 }
YAHOO.util.Event.addListener("emailTB", "focus", emailHint);
function cnfrmPasswdHint(){
	YAHOO.util.Dom.get("cnfrmPasswdRq").className = "Rq txtVisible";
 }
YAHOO.util.Event.addListener("cnfrmPasswdTB", "focus", cnfrmPasswdHint);
function validateEmail(){
	YAHOO.util.Dom.get("emailRq").style.display = "none";
	var emailValue = YAHOO.util.Dom.get("emailTB").value;
	var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;  
	if(emailPattern.test(emailValue) || emailValue.length == 0)
		{
		YAHOO.util.Dom.get("emailHint").style.display = "none";
		}
	if(!emailPattern.test(emailValue) && emailValue.length != 0)
		{
		YAHOO.util.Dom.get("emailHint").style.display = "block";
		}
 }
YAHOO.util.Event.addListener("emailTB", "blur", validateEmail);
function showPassRq(){
	YAHOO.util.Dom.get("passwdRq").style.display = "block";	
}
YAHOO.util.Event.addListener("passwd", "focus", showPassRq);

function hidePassRq(){
	YAHOO.util.Dom.get("passwdRq").style.display = "none";	
}
YAHOO.util.Event.addListener("passwd", "blur", hidePassRq);

function passHint(){
	if(YAHOO.util.Dom.get("passwd").value.length < 4 && YAHOO.util.Dom.get("passwd").value.length != 0)
		YAHOO.util.Dom.get("passwdHint").style.display = "block";
	if(YAHOO.util.Dom.get("passwd").value.length >= 4 || YAHOO.util.Dom.get("passwd").value.length == 0)
		YAHOO.util.Dom.get("passwdHint").style.display = "none";
}
YAHOO.util.Event.addListener("passwd", "blur", passHint);
function enableRegister(e) {
	var usrName = YAHOO.util.Dom.get("emailTB").value;
	var passwd = YAHOO.util.Dom.get("passwd").value;
	var cnfrmPasswd = YAHOO.util.Dom.get("cnfrmPasswdTB").value;
	if(usrName != "" && passwd != "" && cnfrmPasswd != "")
		{
		YAHOO.util.Dom.get("registerBtn").className = "enabled";
		YAHOO.util.Dom.get("registerBtn").disabled = false;
		}
	if(usrName == "" || passwd == "" || cnfrmPasswd == "")
	{
		YAHOO.util.Dom.get("registerBtn").className = "disabled";
		YAHOO.util.Dom.get("registerBtn").disabled = true;
	}
 
}
YAHOO.util.Event.addListener("emailTB", "keyup", enableRegister);
YAHOO.util.Event.addListener("passwd", "keyup", enableRegister);
YAHOO.util.Event.addListener("cnfrmPasswdTB", "keyup", enableRegister);
function passCompare(){
	YAHOO.util.Dom.get("cnfrmPasswdRq").className = "Rq txtInvisible";
	var pass = YAHOO.util.Dom.get("passwd").value;
	var rePass = YAHOO.util.Dom.get("cnfrmPasswdTB").value;
	if( pass != rePass && rePass.length != 0)
		YAHOO.util.Dom.get("cnfrmPasswdHint").style.display = "block";
	if( pass == rePass )
		YAHOO.util.Dom.get("cnfrmPasswdHint").style.display = "none";
}
YAHOO.util.Event.addListener("cnfrmPasswdTB", "blur", passCompare);
function passStrength()
{
	YAHOO.util.Dom.get("passwdHint").style.display = "none";
	var pass = YAHOO.util.Dom.get("passwd").value;
	var lowercaseletter = /[a-z]/.test(pass);
	var uppercaseletter = /[A-Z]/.test(pass);
	var digit = /\d/.test(pass);
	
	var tooShort = pass.length < 4 && pass.length >= 1 ;
	var weak    = lowercaseletter || uppercaseletter || digit;
	var good    = (lowercaseletter && uppercaseletter) || (lowercaseletter && digit) || (digit && uppercaseletter);
	var strong   = lowercaseletter && uppercaseletter && digit;
	var vStrong = (strong && pass.length >= 8);
	if( pass.length == 0 ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Password strength";
	}
	if ( tooShort ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#ff2b2b';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Too short";
	}
	if ( weak && !tooShort ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#ffe347';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Weak";
	}
	if ( good && !tooShort ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Good";
	}
	if ( strong && !tooShort ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#fff';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Strong";
	}
	if ( vStrong && !tooShort ){
		YAHOO.util.Dom.get('weak').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('good').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('strong').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get('vStrong').style.backgroundColor = '#39c716';
		YAHOO.util.Dom.get("passStrnTxt").innerHTML = "Very strong";
	}
}
YAHOO.util.Event.addListener("passwd", "keyup", passStrength );
</script>
</head>
<#if language = "ar">
		<#assign textAlign = "right"/>
		<#assign direction = "rtl"/>
	<#else>
		<#assign textAlign = "left"/>
		<#assign direction = "ltr"/>
	</#if>
<body class="yui-skin-sam">
	<script type="text/javascript">
		function clearForm(oForm) {
	
			 var elements = oForm.elements;
	
			 oForm.reset();
			 elements["search_query"].value='';
	
			   var clrelem = document.getElementById("clear");
			   clrelem.style.visibility="hidden";
			}
	
	
			function formTyping(oForm)
			{
			   //alert("typing");
			   var clrelem = document.getElementById("clear");
			   if (oForm.elements["search_query"].value==''){
			       //alert("empty string!");
			        clrelem.style.visibility="hidden";
			   }else{
	
			       clrelem.style.visibility="visible";
			   }
			}
	
	</script>
	<#import "WEB-INF/views/header.ftl" as header>
	<@header.header "results" "home"/>
	<div class="wrap resl_cont" <#--style="text-align:${textAlign}; direction:${direction};"--> >
		<div class="main">
			<h1>Registration</h1>
			<div class="registration">
				<a href="/login">Already registered? login</a>
				<form id="registrationForm" name="registrationForm" action="register" method="post">
					<label id="emailLb">Email:</label>
					<input id="emailTB" type="text" class="TB" name="email"/>
					<div id="emailHint">
						Email format you entered is wrong. Please try again <span>(user_name@example.com).</span>
					</div>
					<div id="emailRq" class="Rq">
						Email format example: (user_name@example.com).
					</div>
					<label id="passwdLb">Password:</label>
					<input id="passwd" type="password" maxlength="32" name="password"/>
					<div class="passwdStrength">
						<span id="passStrnTxt">Password strength</span>
						<div id="weak" class="dash"></div>
						<div id="good" class="dash"></div>
						<div id="strong" class="dash"></div>
						<div id="vStrong" class="dash"></div>
					</div>
					<div id="passwdHint">
						Your password must be 4 to 32 characters.
					</div>
					<div id="passwdRq" class="Rq">
						Your password must be 4 to 32 characters. Capitalisation matters.
					</div>
					<label id="cnfrmPasswdLb">Repeat password:</label>
					<input id="cnfrmPasswdTB" type="password" class="TB"/>
					<div id="cnfrmPasswdHint">
						The passwords you entered don't match, please try again.
					</div>
					<div id="cnfrmPasswdRq" class="Rq txtInvisible">
						Repeat the password you just entered above for confirmation.
					</div>
					<input type="submit" id="registerBtn" value="Register" class="disabled"/>
					<#if user??>
						<label style="color:red;"> User Already Exists</label>
					</#if>
				</form>
			</div>
		</div>
	</div>
	<#import "WEB-INF/views/footer.ftl" as footer>
	<@footer.footer/>
</body>
</html>
