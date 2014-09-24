<#--
<#--/***
 *
 *
 * @(#) {dashboardWidget.ftl}
 *
 * Copyright (c) 2011 Pearlox Corp. All rights reserved.
 *
 * Pearlox PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */-->
<#macro menu cat page>
	<#if ! (cat?exists)>
		<#assign cat="all"/>
	</#if>
	<#if !(countryActive?exists)>
		<#assign countryActive="Everywhere"/>
	</#if>
	<#if cat = "all" || cat = "news" ||cat = "sports">
		<ul class="top_1 top_ex">
			<#--let everywhere be always the first element-->
			<#if countryActive = "Everywhere">
				<li class="curr"><span class="mic mic_Everywhere"></span><@spring.message"result.Everywhere"/></li>
			<#else>
				<li><a href="/${page}?search_query=${keyword}&page=0&cat=${cat}&place=Everywhere&lang=${lang}&wd=C<#if id?exists>&id=${id}</#if>" ><span class="mic mic_Everywhere"></span><@spring.message"result.Everywhere"/></a></li>
			</#if>
			
			<#--list other countries-->
			<#list countries as countryName>
				<#if countryName.countryName != "Everywhere">
					<#if countryActive?exists>
						<#if countryActive = countryName.countryName>
							<li class="curr"><span class="mic mic_${countryName.countryName}"></span><@spring.message"result.${countryName.countryName}"/></li>
						<#else>
							<li><a href="/${page}?search_query=${keyword}&page=0&cat=${cat}&place=${countryName.countryName}&lang=${lang}&wd=C<#if id?exists>&id=${id}</#if>" ><span class="mic mic_${countryName.countryName}"></span><@spring.message"result.${countryName.countryName}"/></a></li>
						</#if>
					</#if> 
				</#if>
			</#list>
		</ul>
	</#if>
</#macro>