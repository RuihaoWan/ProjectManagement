package com.rio.proj.dump.table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTable {

    private Long id;

    private Long creatorId;

    private Integer projectStatus;

    private Date startDate;

    private Date endDate;
}
