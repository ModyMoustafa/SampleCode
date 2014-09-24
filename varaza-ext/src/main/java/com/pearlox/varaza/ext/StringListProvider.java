/***
 * 
 * 
 * @(#) StringListProvider.java 4:20:22 PM Mar 11, 2010
 * 
 * Copyright (c) 2010 Omlulu Corp. All rights reserved.
 * 
 * Omlulu PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.pearlox.varaza.ext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Dmitry
 */

@Provider
@Produces("application/json")
public class StringListProvider implements MessageBodyWriter<List<String>> {

	public long getSize(List<String> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return t.size();
	}

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return List.class.isAssignableFrom(type)
				&& mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
	}

	public void writeTo(List<String> t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				entityStream));
		String ts = "[";
		Iterator<String> iter = t.iterator();
		while (iter.hasNext()) {
			String val = iter.next();
			ts += "[" + val + "]";
			if (iter.hasNext()) {
				ts += ",";
			}
		}
		ts+="]";
		bw.write(ts);
		bw.flush();
	}
}