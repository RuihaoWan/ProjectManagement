package com.rio.proj.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskTable {

    private Long id;

    private Long stageId;

    private Long projectId;

    private Date startTime;

    private Date endTime;

}
