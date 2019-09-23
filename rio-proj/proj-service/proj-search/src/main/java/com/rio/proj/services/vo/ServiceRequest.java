package com.rio.proj.services.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {

    private String type;
    private Long projectId;
    private Long stageId;
    private UserInfo userInfo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class UserInfo {
        /**
         * For future extension
         */
        private String username;
    }
}
