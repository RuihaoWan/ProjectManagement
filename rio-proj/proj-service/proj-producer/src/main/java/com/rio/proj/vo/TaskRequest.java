package com.rio.proj.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    private String name;
    private String desc;
    private String startTime;
    private String endTime;
    private String url;
    private Long projectId;
    private Long stageId;
    private List<Long> ids; // user ids
    private List<String> types; //types

    public boolean createValidate(){
        return !StringUtils.isEmpty(name) && !StringUtils.isEmpty(desc)
                && !StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)
                && !StringUtils.isEmpty(url) && projectId!=null && stageId!=null;
    }
}
