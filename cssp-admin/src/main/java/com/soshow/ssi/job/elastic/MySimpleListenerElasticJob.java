package com.soshow.ssi.job.elastic;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MySimpleListenerElasticJob implements SimpleJob {
    
    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0: 
            	System.out.println("分片一：开始执行了");
                break;
            case 1: 
            	System.out.println("分片二：开始执行了");
                break;
            case 2: 
            	System.out.println("分片三：开始执行了");
                break;
        }
    }
}