package com.rio.proj.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String position;

    public boolean createValidate(){
        return !StringUtils.isEmpty(username) && !StringUtils.isEmpty(position);
    }
}
