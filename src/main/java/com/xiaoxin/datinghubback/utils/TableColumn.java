package com.xiaoxin.datinghubback.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableColumn {
    private String columnName;
    private String dataType;
    private String columnComment;
}
