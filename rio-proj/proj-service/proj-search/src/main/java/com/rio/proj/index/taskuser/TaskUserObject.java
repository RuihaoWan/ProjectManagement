package com.rio.proj.index.taskuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserObject {

    private Long taskId;
    private Long userId;

}
