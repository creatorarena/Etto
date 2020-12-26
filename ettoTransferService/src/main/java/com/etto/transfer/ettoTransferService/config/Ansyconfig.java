package com.etto.transfer.ettoTransferService.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration(value = "ansyConfig")
public class Ansyconfig implements AsyncConfigurer{
	private static int TASK_CORE_POOL_SIZE = 5;
	private static int TASK_MAX_POOL_SIZE = 10;
	private static int TASK_QUEUE_CAPACITY = 10;
	private static String BEAN_NAME = "CrawlerExecutor";

	@Bean(name = "executorTest")
	@Override
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
		executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
		executor.setQueueCapacity(TASK_QUEUE_CAPACITY);
		executor.setBeanName(BEAN_NAME);
		executor.initialize();

		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

		return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
	}

}
