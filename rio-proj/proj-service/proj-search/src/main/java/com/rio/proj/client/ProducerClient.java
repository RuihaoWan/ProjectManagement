package com.rio.proj.client;

import com.rio.proj.client.vo.Project;
import com.rio.proj.client.vo.ProjectRequest;
import com.rio.proj.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-proj-producer",
            fallback = ProducerClientHystrix.class)
public interface ProducerClient {
    @RequestMapping(value = "/proj-producer/get/project",
                    method = RequestMethod.POST)
    CommonResponse<List<Project>> getProjects(
            @RequestBody ProjectRequest request
            );
}
