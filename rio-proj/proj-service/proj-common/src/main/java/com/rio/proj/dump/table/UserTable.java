package com.rio.proj.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTable {
    private Long id;

    private String username;

    private String token;

    private Integer position;
}
