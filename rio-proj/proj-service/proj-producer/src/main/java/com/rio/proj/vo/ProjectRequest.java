package com.rio.proj.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {

    private Long creatorId;
    private String projectName;
    private String startDate;
    private String endDate;


    public boolean createValidate(){
        return creatorId != null && !StringUtils.isEmpty(projectName)
                && !StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate);
    }
}
