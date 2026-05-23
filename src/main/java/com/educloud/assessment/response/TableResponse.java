package com.educloud.assessment.response;



import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TableResponse<T> {
    private List<Map<String, Object>> columns;
    private List<T> data;
    private int page;
    private int size;


    // optional (for future DataTables server-side)
    private long totalElements;
    private int totalPages;
    private boolean last;
}