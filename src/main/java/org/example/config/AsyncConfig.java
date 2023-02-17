package org.example.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 异步线程池
 * 将线程执行报错记录到表中
 * 方法名称、方法参数、具体错误
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);


	@Override
	@Bean("AsyncConfigTaskExecutor")
	public Executor getAsyncExecutor() {
		SubjectAwareTaskExecutor executor = new SubjectAwareTaskExecutor();
		executor.setCorePoolSize(2 * PROCESSORS);
		executor.setMaxPoolSize(4 * PROCESSORS);
		executor.setQueueCapacity(66);
		executor.setKeepAliveSeconds(120);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		//设置在关闭线程池时是否等待任务完成
		executor.setWaitForTasksToCompleteOnShutdown(true);
		//设置等待终止的秒数
		executor.setAwaitTerminationSeconds(120);
		executor.setThreadNamePrefix("AsyncConfigTaskExecutor Thread-");
		executor.initialize();
		//使用 TTL 提供的 TtlExecutors
		return TtlExecutors.getTtlExecutor(executor);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

	class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
		@Override
		public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
			try {
				//异步线程执行日志
				String objectsString = JSON.toJSONString(objects);
				String errorMsg = "async uncaught exception handler" + throwable.getMessage() + " " + method.getName() + " " + objectsString;
				logger.error(errorMsg);
				//TODO 日志记录

				//正常只要记录异步线程执行 后面都是针对业务进行状态位控制
				//异步导出 线程异常 状态位更新
				for (Object object : objects) {
//					if (object instanceof FileReceiveNote) {
//						FileReceiveNote fileReceiveNote = (FileReceiveNote) object;
//						fileReceiveNote.setStatus(ReceiveNoteStatus.FAIL.name());
//						fileReceiveNote.setUpdateBy(ShiroUtils.getUserName());
//						fileReceiveNote.setUpdateDt(new Date());
//						fileReceiveNote.setRemark(throwable.getMessage());
//						fileReceiveNoteMapper.updateByPrimaryKeySelective(fileReceiveNote);
//						break;
//					}
				}
			} catch (Exception exception) {
				String errorMsg = "async uncaught exception handler db" + exception.getMessage();
				logger.error(errorMsg);
			}
		}
	}

	public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
}