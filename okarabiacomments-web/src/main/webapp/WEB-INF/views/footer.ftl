<#macro footer>
<footer id="footercontainer">
		<#if keyword??>
			<#assign keyword = keyword/>
		<#elseif RequestParameters.search_query??>
			<#assign keyword = RequestParameters.search_query/>
		<#else>
			<#assign keyword = "Arab"/>
		</#if>
		
		<#if keyword = "">
			<#assign keyword = "Arab"/>
		</#if>
		
		<#if countryActive??>	
			<#assign countryActive = countryActive/>
		<#else>
			<#if RequestParameters.place??>
				<#assign countryActive = RequestParameters.place/>
			<#else>
		 		<#assign countryActive = "Everywhere"/>
			</#if>	
		</#if>
		
		<#if lang??>
	  		<#assign language=lang/> 
	 	<#else>
	  		<#assign language="ar"/>
	 	</#if> 
		<#if language = "ar">
			<#assign textAlign = "right"/>
		<#else>
			<#assign textAlign = "left"/>
		</#if>
<#-- New footer... Start -->
<div class="main" Style="text-align:${textAlign};">
<ul>
	<li class="ttl">okarabia</li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/results?search_query=${keyword}&dataDir=3&cat=all&place=${countryActive}&lang=${language}&wd=C"><@spring.message"result.search"/></a></li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/news?search_query=<#if keyword == "">Arab<#else>${keyword}</#if>&dataDir=3&cat=news&place=${countryActive}&lang=${language}&wd=C"><@spring.message"result.news"/></a></li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/music?search_query=<#if keyword == "">Amr%20Diab<#else>${keyword}</#if>&amp;cat=music&amp;lang=${language}&amp;wd=C"><@spring.message"music"/></a></li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/football?search_query=&amp;cat=football&amp;dataDir=3&amp;place=${countryActive}&amp;lang=${language}&amp;wd=C"><@spring.message"football.title"/></a></li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/OKArabiaJobs?search_query=<#if keyword == "">Cairo<#else>${keyword}</#if>&amp;dataDir=3&amp;lang=${language}&amp;wd=C"><@spring.message"jobs"/></a></li>
	<li><a Style="float:${textAlign};" href="http://www.okarabia.com/realTimeSearch?search_query=<#if keyword == "">Arab<#else>${keyword}</#if>&amp;dataDir=3&amp;place=${countryActive}&amp;lang=${language}&amp;wd=C"><@spring.message"realtime.title"/></a></li>
</ul>
<ul>
	<li class="ttl"><@spring.message"related"/></li>
	<li><a Style="float:${textAlign};" href="http://www.pashajobs.com/">PashaJobs</a></li>
	<li><a Style="float:${textAlign};" href="http://www.catcombinator.com/">CatCombinator</a></li>
	<li><a Style="float:${textAlign};" href="http://www.oklist.com/">OKList</a></li>
</ul>
<ul>
	<li class="ttl">silicon arabia</li>
	<#--<li><a href="#">Web site</a></li>
	<li><a href="#">Blog</a></li>-->

	

	<li><a Style="float:${textAlign};" href="<@spring.message"jobs.link"/>"><@spring.message"jobs"/></a></li>

</ul>
<#--<ul>
	<li class="ttl">miscellaneous</li>
	<li><a href="#">Terms of Service</a></li>
	<li><a href="#">Confidentiality Policy</a></li>
</ul>-->
</div>
<div class="wrap footer_wrap">
<p>Copyright &copy;2011 Silicon Arabia Corp. All rights reserved.</p>
</div>

<script type="text/javascript">
	var _sf_async_config={uid:7144,domain:"okarabia.com"};
	(function(){
		function loadChartbeat() {
	    	window._sf_endpt=(new Date()).getTime();
	    	var e = document.createElement('script');
	    	e.setAttribute('language', 'javascript');
	    	e.setAttribute('type', 'text/javascript');
	    	e.setAttribute('src',
	       		(("https:" == document.location.protocol) ? "https://a248.e.akamai.net/chartbeat.download.akamai.com/102508/" : "http://static.chartbeat.com/") +
	       		"js/chartbeat.js");
	    	document.body.appendChild(e);
	  	}
	  	var oldonload = window.onload;
	  	window.onload = (typeof window.onload != 'function') ?
	    loadChartbeat : function() { oldonload(); loadChartbeat(); };
	})();
</script>
<#-- New footer... End -->
</footer>
</#macro>
