package org.example.spring.boot;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * How to extend Existing endpoints
 */
@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class MyEndpointWebExtension {

	@Autowired
	private InfoEndpoint delegate;
	
	@Autowired
	private Environment env;

	
	@ReadOperation
	public WebEndpointResponse<Map<?, ?>> info() {
		Map<String, Object> info = this.delegate.info();
		
		if(Arrays.asList(env.getActiveProfiles()).contains("PRD"))
			return new WebEndpointResponse<>(null, 200);
		else
			return new WebEndpointResponse<>(info, 200);
	}

}
