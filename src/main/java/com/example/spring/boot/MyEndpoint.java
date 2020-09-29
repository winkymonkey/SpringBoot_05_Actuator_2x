package com.example.spring.boot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;


@Component
@Endpoint(id="custom-endpoint")
public class MyEndpoint {
	
	/**
	 * -----------------------------------------------------------
	 * URL: http://localhost:8080/actuator/custom-endpoint/
	 * METHOD: GET
	 * -----------------------------------------------------------
	 */
    @ReadOperation
    public Map<String, Object> readOperation() {
        Map<String, Object> map = new HashMap<>();
        map.put("serverDate", LocalDate.now().toString());
        map.put("serverTime", LocalTime.now().toString());
        
        BackendHealth backendHealth = new BackendHealth();
        backendHealth.setDatabaseStat("UP");
        
        map.put("backendHealth", backendHealth);
        return map;
    }
    
    /**
	 * -----------------------------------------------------------
	 * URL: http://localhost:8080/actuator/custom-endpoint/abcd
	 * METHOD: GET
	 * -----------------------------------------------------------
	 */
    @ReadOperation
    public String readOperation2(@Selector String name) {
    	return "custom-end-point selector=" + name;
    }

    /**
	 * -----------------------------------------------------------
	 * URL: http://localhost:8080/actuator/custom-endpoint/
	 * METHOD: POST
	 * -----------------------------------------------------------
	 */
    @WriteOperation
    public String writeOperation() {
        return "Write operation in custom endpoint";
    }

    /**
	 * -----------------------------------------------------------
	 * URL: http://localhost:8080/actuator/custom-endpoint/
	 * METHOD: DELETE
	 * -----------------------------------------------------------
	 */
    @DeleteOperation
    public String deleteOperation() {
    	return "Delete operation in custom endpoint";
    }
    
    
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class BackendHealth {
    	private String databaseStat;
    	private String queueStat;
    	
		public String getDatabaseStat() {
			return databaseStat;
		}
		public void setDatabaseStat(String databaseStat) {
			this.databaseStat = databaseStat;
		}
		public String getQueueStat() {
			return queueStat;
		}
		public void setQueueStat(String queueStat) {
			this.queueStat = queueStat;
		}
    }
}
