package com.rio.proj.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageRequest {

    private Long projectId;
    private String stageName;

    public boolean createValidate(){
        return projectId != null && !StringUtils.isEmpty(stageName);
    }
}
