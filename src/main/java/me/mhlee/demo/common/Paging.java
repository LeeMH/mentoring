package me.mhlee.demo.common;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.PageRequest;

public class Paging {
    @Schema(title = "조회 페이지", example = "0", description = "첫 페이지 0부터 시작", required = true)
    private final int page;

    @Schema(title = "페이지당 목록수", example = "10", description = "", required = true)
    private final int size;

    public Paging() {
        this(0, 10);
    }

    public Paging(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    @Schema(hidden = true)
    public long getOffset() {
        return (long) page * size;
    }

    public long getSizeLong() {
        return (long) size;
    }

    @Schema(hidden = true)
    public PageRequest getPageRequest() {
        return PageRequest.of(page, size);
    }
}