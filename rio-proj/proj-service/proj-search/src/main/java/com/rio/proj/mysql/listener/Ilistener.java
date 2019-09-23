package com.rio.proj.mysql.listener;

import com.rio.proj.mysql.dto.BinlogRowData;

public interface Ilistener {

    void register();

    void onEvent(BinlogRowData eventData);
}
