package com.ccsw.tutorial.prestamo.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

public class PrestamoSearchDto {

    /**
     * @prestamo ccsw
     *
     */
    private PageableRequest pageable;

    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }
}