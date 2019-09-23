package com.rio.proj.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageTable {

    private Long id;

    private Long projectId;

    private Integer stageStatus;
}
