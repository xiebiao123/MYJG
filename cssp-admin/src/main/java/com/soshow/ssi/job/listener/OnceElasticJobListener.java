package com.soshow.ssi.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

/**
 * 若作业处理数据库数据，处理完成后只需一个节点完成数据清理任务即可。
 * 此类型任务处理复杂，需同步分布式环境下作业的状态同步，提供了超时设置来避免作业不同步导致的死锁，请谨慎使用。
 * @author xieb
 *
 */
public class OnceElasticJobListener extends AbstractDistributeOnceElasticJobListener {
    
    public OnceElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
		super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
	}

    @Override
    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
    	System.out.println("分布式,执行一次的Listener-job执行之前");
    }
    
    @Override
    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
    	System.out.println("分布式,执行一次的Listener-job执行之后");
    }
}