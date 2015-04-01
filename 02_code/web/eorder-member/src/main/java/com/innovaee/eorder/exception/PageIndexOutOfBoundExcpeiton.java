package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

public class PageIndexOutOfBoundExcpeiton extends BaseException {

    static {
        exceptionKey = "page_out_of_bound_exception";
    }
    
    private static final long serialVersionUID = 1L;

    private int totalPage;
    
    private int curPage;

    public PageIndexOutOfBoundExcpeiton(int totalPage, int curPage) {
        super();
        this.totalPage = totalPage;
        this.curPage = curPage;
    }
    
    @Override 
    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, ""+totalPage, ""+curPage);
    }
}
