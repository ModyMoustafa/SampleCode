<#import "spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Just an example of comments page injection into remote client web sites</title>
	</head>
	<body>
		 <#if lang??>
			  <#assign language=lang/> 
		 <#else>
			  <#assign language="ar">
	  	 </#if>
	    <h3>Just an example of comments page injection into remote client web sites</h3>
		<div>above iframe</div>
		<div id="test"></div>
		<div>below iframe</div>
		<script type="text/javascript" src="/okarabia-comment-loader.js?containerid=test&w=490&h=800&url=http://minikoora.com/&orgcom=true"></script>
	</body>
</html>
