package com.example.kakashi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginatedResponse {
    private List<KakashiDto> content;
    private int pageNumber;
    private int pageSize;
    private boolean isLast;
    private long totalKakashis;
    private int totalPages;
}
