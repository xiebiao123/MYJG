package com.soshow.ssi.job.elastic;

import java.util.List;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.soshow.ssi.cssp.student.dto.StudentDTO;

public class MyDataflowElasticJob implements DataflowJob<StudentDTO> {

	@Override
	public List<StudentDTO> fetchData(ShardingContext shardingContext) {
		System.out.println("开始取数据");
		return null;
	}

	@Override
	public void processData(ShardingContext shardingContext, List<StudentDTO> data) {
		System.out.println("开始处理数据");
	}
   
}