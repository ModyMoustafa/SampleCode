package com.siliconarabia.okarabiacomments.web.index.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.siliconarabia.okarabiacomments.web.util.CommentsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author johny Sep 28, 2011
 */
@Controller
public class CommentLoaderController {

    private static Integer DEFAULT_IFRAME_WIDTH = 400;
    private static Integer DEFAULT_IFRAME_HEIGHT = 500;

    @RequestMapping(value = "/okarabia-comment-loader.js", method = RequestMethod.GET)
    public void initPage(
            @RequestParam(value = "w", required = false) Integer width,
            @RequestParam(value = "h", required = false) Integer height,
            @RequestParam(value = "containerid") String containerId,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "timezoneOffset", required = false) String timezoneOffset,
            // "noc" refers to number of comments to display
            //			@RequestParam(value = "noc", required = false) Integer noc,
            // "orgcom" stands for "organize comments"
            //			@RequestParam(value = "orgcom", required = false) boolean orgcom,
            HttpServletRequest request, 
            HttpServletResponse response)
            throws IOException {
    	//	if (request.getParameter("orgcom") == null)	orgcom=false;
    	//	if (request.getParameter("noc") == null)	noc=3;
    	
    	// Check the timezone if Exist
		if (timezoneOffset != null && !timezoneOffset.isEmpty()) {
			request.getSession().setAttribute("timezoneUserOffset", timezoneOffset);
		}
		
    	int noc = 3;
        StringBuffer result = new StringBuffer();
        result.append("IFRAME_CONTAINER_ID = \"").append(containerId).append("\";\n");
        result.append("UNIFORMRESOURCELOCATOR = \"").append(url).append("\";\n");
        result.append("IFRAME_WIDTH = ").append(width != null ? width : DEFAULT_IFRAME_WIDTH).append(";\n");
        result.append("IFRAME_HEIGHT = ").append(height != null ? height : DEFAULT_IFRAME_HEIGHT).append(";\n");

        StringBuffer requestUrl = request.getRequestURL();
        String domainPart = requestUrl.substring(0, requestUrl.indexOf(request.getRequestURI()));
        request.getSession().setAttribute(CommentsConstants.ORIGIN_URL_SESSION_ATTRIBUTE, url);
        result.append("IFRAME_SRC = \"").append(domainPart).append("/index?noc="+noc+"&w="+width+"&h="+height+"&containerid="+containerId+"&url="+url+"\";\n");
        result.append("\n");

        InputStream stream = getClass().getClassLoader().getResourceAsStream("WEB-INF/js/commentLoader.js");
        byte[] buff = new byte[1024];
        int read = 0;

        while ((read = stream.read(buff, 0, 1024)) >= 0) {
            String s = new String(buff, 0, read);
            result.append(s);
        }

        stream.close();

        response.setContentType("text/javascript");
        response.getOutputStream().write(result.toString().getBytes());
        response.flushBuffer();
    }
}
