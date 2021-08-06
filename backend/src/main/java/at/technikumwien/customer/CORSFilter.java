package at.technikumwien.customer;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
		var requestHeaders = request.getHeaders();
		var responseHeaders = response.getHeaders();
		
		responseHeaders.add("Access-Control-Allow-Origin", getRequestOrigin(requestHeaders));
        responseHeaders.add("Access-Control-Allow-Methods", "GET, DELETE, OPTIONS, POST, PUT");
        responseHeaders.add("Access-Control-Allow-Headers", getRequestHeaders(requestHeaders));
		
		var cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
		responseHeaders.add("Cache-Control", cacheControl);
	}
	
	private String getRequestOrigin(MultivaluedMap<String, String> requestHeaders) {
		var origin = requestHeaders.getFirst("Origin");
		return (origin != null ? origin : "null");
	}
	
	private String getRequestHeaders(MultivaluedMap<String, String> requestHeaders) {
		var headers = requestHeaders.get("Access-Control-Request-Headers");
		return (headers != null ? String.join(", ", headers) : "");
	}
}