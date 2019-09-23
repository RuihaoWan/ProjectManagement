package com.rio.proj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGetRequest {
    private Long creatorId;
    private List<Long> ids;
    public boolean validate(){
        return creatorId != null && !CollectionUtils.isEmpty(ids);
    }
}
