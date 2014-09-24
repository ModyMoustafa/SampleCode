<#macro header redirectTo from>
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
		   if(clrelem != null){
		   	if (oForm.elements["search_query"].value==''){
		       	//alert("empty string!");
		        	clrelem.style.visibility="hidden";
		   	}else{

		       		clrelem.style.visibility="visible";
		   	}
		   }
		}

</script>
<#--YUI Sync request to get User time offset--->
<script type="text/javascript">
function getUserDate(){
			var d = new Date();
			var gmtHours = -d.getTimezoneOffset()/60;
			makeRequest(gmtHours);
	} 
	
		<#--handle success of makeRequest-->
	var handleSuccess = function(o){
		<#--alert("success");-->	
	}
		<#--handle failure of makeRequest-->
	var handleFailure = function(o){
		<#--alert("failure");-->
	}
	
	var callback =
	{
	  success:handleSuccess,
	  failure:handleFailure,
	};
	
		<#--YUI Sync request-->
	function makeRequest(timeOffset){
		var sUrl = "timezone?timezoneOffset="+ timeOffset;
		var request = YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
	}
</script>
<header>

	<#-- New header... start -->
		<#if keyword??>
			<#assign keyword = keyword/>
		<#elseif RequestParameters.search_query??>
			<#assign keyword = RequestParameters.search_query/>
		<#else>
			<#assign keyword = ""/>
		</#if> 
		<#assign kewo = keyword?replace('%', '%25')>
		<#assign kewo = kewo?replace('&', '%26')>
		<#assign kewo = kewo?replace('#', '%23')>
		<#assign kewo = kewo?replace('$', '%24')>
		<#assign kewo = kewo?replace('"', '%22')>
	
		<#if countryActive??>	
			<#assign countryActive = countryActive/>
		<#else>
			<#if RequestParameters.place??>
				<#assign countryActive = RequestParameters.place/>
			<#else>
		 		<#assign countryActive = "Everywhere"/>
			</#if>	
		</#if>
	
		<#if category??>	
			<#assign category = category/>
		<#elseif RequestParameters.cat??>
			<#assign category = RequestParameters.cat/>
		<#elseif from?? && from=="home">
			<#assign category = "all"/>
		<#else>
			<#assign category = "rts"/>	
		</#if>
		<#if lang??>
	  		<#assign language=lang/> 
	 	<#else>
	  		<#assign language="ar"/>
	 	</#if> 
	<div class="wrap top_nav">
		<ul class="top_lft">
			<li class="current">OKArabia</li>
			<li><a href="http://www.catcombinator.com/">CatCombinator</a></li>
			<li><a href="http://www.pashajobs.com/">PashaJobs</a></li>
			<li><a href="http://www.oklist.com/">OKList</a></li>
		</ul>
		<ul class="top_rght">
			<#--<li><a href="#" class="st_lnk"><img src="images/st_img.jpg" alt=""></a></li>-->
			<#--<li><a href="#">française</a>|<a href="#">عربية</a></li>-->
			<#if language="ar">
					<li <#--style="width:60px"-->><a href="#" onclick="switchLocale('en'); return true;">English</a>
					<#--<li class="sepratorLi"><div class="seprator"></div></li>-->|
					<a href="#" onclick="switchLocale('fr'); return true;">Français</a></li>
			<#elseif language="en">
					<li <#--style="width:60px"-->><a href="#" onclick="switchLocale('fr'); return true;">Français</a>
					<#--<li class="sepratorLi"><div class="seprator"></div></li>-->|
					<a href="#" onclick="switchLocale('ar'); return true;">العربية</a></li>
			<#else>
					<li <#--style="width:60px"-->><a href="#" onclick="switchLocale('en'); return true;">English</a>
					<#--<li class="sepratorLi"><div class="seprator"></div></li>-->|
					<a href="#" onclick="switchLocale('ar'); return true;">العربية</a></li>
			</#if>
		</ul>
	</div>
	<div class="wrap top_srch">
		<div class="main"><img src="http://www.okarabia.com/images/sheep.gif" alt="" class="sheet_feast"><a class="logo" href="./"><img src="http://www.okarabia.com/images/logo.png" alt=""></a>
			<form action="${redirectTo}" method="get" id="formSearch">
				<div>
				<#assign keyword = keyword?replace('"', '&#34;')>
					<input type="text" class="srch_box search_query" name="search_query">
					<#if from != "home">
						<span id="clear">
							<input type="button" name="clear" value="X" onclick="clearForm(this.form);"></input>
						</span>
					</#if>
					<input type="submit" value="<@spring.message"result.search"/>" class="srch_btn" <#if language="fr">style="font-size:10px;" </#if>>
					<div class="fixUl" id="feedsContainer"></div> 
				</div>
				<input type="hidden" name="place" value="${countryActive}"/> 
				<input type="hidden" name="cat" value=<#if category == "football">"all"<#else>"${category}"</#if>/>
				<input type="hidden" name="myHidden" id="myHidden"/>
			</form>
		</div>
		<ul class="nav_bar">
			<#if from != "home">
				<#assign tab =".tab">
			<#else>
				<#assign tab ="">
			</#if>
			<li <#if category == "all">class="current"</#if>><#if category != "all"><a href="http://www.okarabia.com/results?search_query=${kewo}&dataDir=3&cat=all&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"result.search${tab}"/><#if category != "all"></a></#if></li>
			<#if from != "home" && category != "chat" && kewo != "">
				<li <#if category == "news">class="current"</#if>><#if category != "news"><a href="http://www.okarabia.com/news?search_query=${kewo}&dataDir=3&cat=news&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"result.news"/><#if category != "news"></a></#if></li>
				<li <#if category == "music">class="current"</#if>><#if category != "music"><a href="http://www.okarabia.com/music?search_query=${kewo}&cat=music&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"music"/><#if category != "music"></a></#if></li>
				<li <#if category == "rts">class="current"</#if>><#if category != "rts"><a href="http://www.okarabia.com/realTimeSearch?search_query=${kewo}&dataDir=3&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"realtime.title"/><#if category != "rts"></a></#if></li>
				<li <#if category == "jobs">class="current"</#if>><#if category != "jobs"><a href="http://www.okarabia.com/OKArabiaJobs?search_query=${kewo}&dataDir=3&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"result.jobs"/><#if category != "jobs"></a></#if></li>
			</#if>
			<li <#if category == "football">class="current"</#if>><#if category != "football"><a href="http://www.okarabia.com/football?search_query=football&cat=football&dataDir=3&place=${countryActive}&lang=${language}&wd=C"></#if><@spring.message"football.title"/><#if category != "football"></a></#if></li>
		</ul>
	</div>
	<#-- New header... end -->
	<#-- auto complete -->
	<script type="text/javascript"> 
		YAHOO.example.BasicRemote = function() {
			YAHOO.util.Dom.get("autocomplete").focus();
		    // Use an XHRDataSource
		    var oDS = new YAHOO.util.XHRDataSource("/autoComplete");
		    // Set the responseType
		    oDS.responseType = YAHOO.util.XHRDataSource.TYPE_TEXT;
		    // Define the schema of the delimited results
		    oDS.responseSchema = {
		        recordDelim: "\n",
		        fieldDelim: "\t"
		    };
		    // Enable caching
		    oDS.maxCacheEntries = 5;
		 
		    //  the AutoComplete
		    var oAC = new YAHOO.widget.AutoComplete("autocomplete", "feedsContainer", oDS);
		    oAC.queryDelay = .1;
		    // The webservice needs additional parameters
		    oAC.generateRequest = function(sQuery) {
		        return "?input=" + sQuery ;
		        alert(sQuery);
		    };
		    oAC.resultTypeList = false; 
		    
		    // Define an event handler to populate a hidden form field 
		    // when an item gets selected 
		    var myHiddenField = YAHOO.util.Dom.get("myHidden"); 
		    var myHandler = function(sType, aArgs) { 
		        var myAC = aArgs[0]; // reference back to the AC instance 
		        // var elLI = aArgs[1]; // reference to the selected LI element 
		        var oData = aArgs[2]; // object literal of selected item's result data 
		 	    
		        // update hidden form field with the selected item's ID 
		     	myHiddenField.value = oData[0];
		      	var form = YAHOO.util.Dom.get("formSearch");
		      	form.submit();
		    }; 
		    if(oAC.itemSelectEvent != null){
		    	oAC.itemSelectEvent.subscribe(myHandler); 
		   }
		    return {
		        oDS: oDS,
		        oAC: oAC
		    };
		}();
	</script> 
	<#-- end auto complete -->
</header>
</#macro>
