package com.my;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.my.dao.MyJobTwo;

@Configuration
@ComponentScan("com.db.glue.wm")
public class QuartzConfiguration {

	@Value("${rate}")
	private String rate;

	@Bean
	public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean() {
		MethodInvokingJobDetailFactoryBean obj = new MethodInvokingJobDetailFactoryBean();
		obj.setTargetBeanName("jobone");
		obj.setTargetMethod("myTask");
		return obj;
	}

	@Bean
	public SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
		SimpleTriggerFactoryBean stFactory = new SimpleTriggerFactoryBean();
		stFactory.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
		//stFactory.setStartDelay(Long.parseLong(rate));//延迟trigger
		stFactory.setRepeatInterval(Long.parseLong(rate));//设置时间间隔
		stFactory.setRepeatCount(3);//设置重复次数
		return stFactory;
	}

	//for job2
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(MyJobTwo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "RAM");
		map.put(MyJobTwo.COUNT, 1);
		factory.setJobDataAsMap(map);
		factory.setGroup("mygroup");
		factory.setName("myjob");
		return factory;
	}

	//for job2
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean() {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setStartDelay(3000);
		stFactory.setName("mytrigger");
		stFactory.setGroup("mygroup");
		stFactory.setCronExpression("0 0/1 * 1/1 * ? *");
		return stFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(simpleTriggerFactoryBean().getObject(), cronTriggerFactoryBean().getObject());
		// scheduler.setTriggers(simpleTriggerFactoryBean().getObject());
		return scheduler;
	}
}
