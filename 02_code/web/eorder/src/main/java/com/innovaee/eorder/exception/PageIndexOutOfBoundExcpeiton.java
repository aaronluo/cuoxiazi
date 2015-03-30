package com.innovaee.eorder.exception;

import com.innovaee.eorder.utils.MessageUtil;

public class PageIndexOutOfBoundExcpeiton extends BaseException {

    static {
        exceptionKey = "page_out_of_bound_exception";
    }

    private static final long serialVersionUID = 1L;

    /** 总页数 */
    private int totalPage;

    /** 当前页 */
    private int curPage;

    /**
     * 页数越界异常构造函数
     * 
     * @param totalPage
     *            总页数
     * @param curPage
     *            当前页
     */
    public PageIndexOutOfBoundExcpeiton(int totalPage, int curPage) {
        super();
        this.totalPage = totalPage;
        this.curPage = curPage;
    }

    public String getMessage() {
        return MessageUtil.getMessage(exceptionKey, "" + totalPage, ""
                + curPage);
    }
}