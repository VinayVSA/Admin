package com.emp;


import io.gatling.javaapi.http.*;
import io.gatling.javaapi.core.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class Gatj extends Simulation{
	
HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8081").acceptHeader("application/json");
	
	ScenarioBuilder scn =  scenario("Basic Load Test")
		    .exec(
		    	      http("Get All Using RestTemplete")
		    	        .get("/client/employees/resttemplate")
		    	        .check(status().is(200))
		    	    )
		    	    .pause(5);
	{
	    setUp(
	      scn.injectOpen(
	        atOnceUsers(1), // Start with 1 users at once
	        rampUsers(5).during(10) // Gradually add 5 users over 10 seconds
	      ).protocols(httpProtocol)
	    );
	  }


}
