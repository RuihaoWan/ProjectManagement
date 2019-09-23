package com.rio.proj.sender;

import com.rio.proj.mysql.dto.MySqlRowData;

public interface ISender {
    void sender(MySqlRowData rowData);
}
