package com.rio.proj.client;

import com.rio.proj.client.vo.Project;
import com.rio.proj.client.vo.ProjectRequest;
import com.rio.proj.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerClientHystrix implements ProducerClient{

    @Override
    public CommonResponse<List<Project>> getProjects(ProjectRequest request) {
        return new CommonResponse<>(-1,"eureka-client-proj-producer error");
    }
}
