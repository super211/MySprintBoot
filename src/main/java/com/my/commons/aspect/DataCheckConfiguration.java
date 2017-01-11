package com.my.commons.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class DataCheckConfiguration {
	
	@Bean
	public DataCheckAspect nofifyAspect(){
		return new DataCheckAspect();
	}
	
	@Bean
	public EntitlementAspect nofifyAspects(){
		return new EntitlementAspect();
	}
}
