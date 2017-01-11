package com.my.commons.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

@Aspect
public class EntitlementAspect {

	final static Logger log = LoggerFactory.getLogger(EntitlementAspect.class);

	// When autowiring, the class should be interface,
	// and at least should have 1 implementation of this interface
	// like EntitlementServiceBean. Otherwise:No qualifying bean of type
	@Autowired
	private EntitlementService entitlementService;

	@Pointcut("@annotation(com.my.commons.aspect.EntitlementCheck)")
	public void checkPointcut() {
	}

	@Around("checkPointcut()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {

		System.err.println("Call from EntitlementCheckASPECT.");

		Assert.notNull(entitlementService);

		/*
		 * log.info("---"); log.info(
		 * "Performing 'Around' operation inside HystrixAspect"); log.info(
		 * "Current thread ID: " + Thread.currentThread().getId());
		 */

		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		EntitlementCheck annotation = method.getAnnotation(EntitlementCheck.class);
		if (annotation == null) {
			// Can occur when not using CCGLIB-style 'subclass' proxies,
			// when using an interface that has a concrete class
			// that has the annotation.
			throw new RuntimeException();
		}

		String userId = annotation.userId();
		String commandGroupKey = annotation.commandGroupKey();
		String resourceName = annotation.resourceKey();

		entitlementService.setUserId(userId);
		entitlementService.setResource(commandGroupKey);
		entitlementService.setResource(resourceName);

		boolean executeCommand = true;

		// Continue execution in a new Hystrix thread
		HystrixCommand<Object> command = new HystrixCommand<Object>(
				HystrixCommandGroupKey.Factory.asKey(commandGroupKey)) {

			@Override
			protected Object run() throws Exception {
				try {
					return entitlementService.checkForEntitlement();
				} catch (Exception e) {
					throw e;
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}
			}
		};

		executeCommand = (Boolean) command.execute();

		System.out.println("resourceName: " + resourceName);

		if (executeCommand) {
			log.info("--- Entitlement Success - Call the service-------");
			System.out.println("--- Entitlement Success - Call the service-------");
			return joinPoint.proceed();
		} else {
			log.info("--- Entitlement Failure - Throw Exception-------");
			throw new RuntimeException("Security Exception");
		}

	}
}
