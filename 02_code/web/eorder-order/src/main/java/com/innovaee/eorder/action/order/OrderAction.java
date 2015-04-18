/***********************************************
 * Filename       : OrderAction.java 
 * Copyright      : Copyright (c) 2015
 * Company        : Innovaee
 * Created        : 04/10/2015
 ************************************************/

package com.innovaee.eorder.action.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.Role;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.service.RoleService;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.KeyValue;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.innovaee.eorder.vo.NewOrderVO;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;

/**
 * @Title: OrderAction
 * @Description: 功能Action（查询和删除）
 *
 * @version V1.0
 */
@Conversion(conversions = {
        @TypeConversion(key = "order.createAtMin", converter = "com.innovaee.eorder.utils.DateConverter"),
        @TypeConversion(key = "order.createAtMax", converter = "com.innovaee.eorder.utils.DateConverter"),
        @TypeConversion(key = "order.totalPriceMin", converter = "com.innovaee.eorder.utils.FloatConverter"),
        @TypeConversion(key = "order.totalPriceMax", converter = "com.innovaee.eorder.utils.FloatConverter") })
public class OrderAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Order";

    /** 流水值对象 */
    private NewOrderVO order;

    /** 流水对象列表 */
    private List<Order> orders;

    /** 收银员列表 */
    private List<User> cashierList;

    /** 收银员id */
    private Long cashierId;

    /** 服务员列表 */
    private List<User> serventList;

    /** 服务员id */
    private Long serventId;

    /** 订单状态列表 */
    private List<KeyValue> statusList;

    /** 流水服务类对象 */
    @Resource
    private OrderService orderService;

    /** 用户服务类对象 */
    @Resource
    private RoleService roleService;

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String order() {

        getQueryList();

        order = new NewOrderVO();
        if (checkNewOrderVO()) {
            getOrderList();
        } else {
            return ERROR;
        }
        // 刷新系统菜单
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 进入菜品页面
     * 
     * @return
     */
    public String list() {
        getQueryList();
        try {
            if (checkNewOrderVO()) {
                getOrderList();
            } else {
                count = 0;
                return ERROR;
            }
        } catch (Exception e) {
            this.setMessage(e.getMessage());
            return ERROR;
        } finally {
            // 刷新系统菜单
            refreshPageData();
        }

        return SUCCESS;
    }

    /**
     * 检查一个菜品的设定是否合法
     */
    private boolean checkNewOrderVO() {
        boolean isValidVO = true;

        if (null == order) {
            order = new NewOrderVO();
            return true;
        }

        if (order.getTotalPriceMin() < 0.0f) {
            isValidVO = false;
            addFieldError("dish.price",
                    MessageUtil.getMessage("dish_price_rule"));
            setMessage(MessageUtil.getMessage("dish_price_rule"));
        }

        if (order.getTotalPriceMax() < 0.0f) {
            isValidVO = false;
            addFieldError("dish.price",
                    MessageUtil.getMessage("dish_price_rule"));
            setMessage(MessageUtil.getMessage("dish_price_rule"));
        }

        if (null != order.getCreateAtMin() && null != order.getCreateAtMax()) {
            if (order.getCreateAtMin().getTime() > order.getCreateAtMax()
                    .getTime()) {
                isValidVO = false;
                addFieldError("order.createAt",
                        MessageUtil.getMessage("order_create_at_rule"));
                setMessage(MessageUtil.getMessage("order_create_at_rule"));
            }
        }

        if (null != order.getCashierId() && 0 == order.getCashierId()) {
            order.setCashierId(null);
        }

        if (null != order.getServentId() && 0 == order.getServentId()) {
            order.setServentId(null);
        }

        return isValidVO;
    }

    /**
     * 获取查询条件的列表
     */
    private void getQueryList() {
        // 1. 收银员列表
        cashierList = new ArrayList<User>();
        Role cashierRole = roleService.findRoleByRoleName(Constants.CASHIER);
        if (null != cashierRole) {
            Set<User> cashier = cashierRole.getUsers();
            if (null != cashier && 0 < cashier.size()) {
                for (User user : cashier) {
                    cashierList.add(user);
                }
            }
        }
        // 2. 点餐员列表
        serventList = new ArrayList<User>();
        Role serventRole = roleService.findRoleByRoleName(Constants.SERVENT);
        if (null != serventRole) {
            Set<User> servent = serventRole.getUsers();
            if (null != servent && 0 < servent.size()) {
                for (User user : servent) {
                    serventList.add(user);
                }
            }
        }

        // 3. 状态列表
        statusList = new ArrayList<KeyValue>();
        statusList.add(new KeyValue(Constants.ORDER_NEW, MessageUtil
                .getMessage("order_status_new")));
        statusList.add(new KeyValue(Constants.ORDER_SUBMITTED, MessageUtil
                .getMessage("order_status_submitted")));
        statusList.add(new KeyValue(Constants.ORDER_PAID, MessageUtil
                .getMessage("order_status_paid")));
    }

    /**
     * 获取流水列表
     */
    private void getOrderList() {
        try {
            count = orderService.getOrderCount(order);
            if (0 == count) {
                this.setMessage(MessageUtil.getMessage("order_record_empty"));
                return;
            }

            int pageTotal = 1;
            if (0 == count % Constants.PAGE_SIZE) {
                pageTotal = count / Constants.PAGE_SIZE;
            } else {
                pageTotal = count / Constants.PAGE_SIZE + 1;
            }
            this.setPageTotal(pageTotal);

            // 处理用户点击【上一页】和【下一页】边界情况
            if (pageNow > pageTotal) {
                pageNow = pageTotal;
                pageInput = pageNow;
            } else if (pageNow < 1) {
                pageNow = 1;
                pageInput = 1;
            }

            if (null != pageInput) {
                if (pageInput > pageTotal) {
                    pageInput = pageTotal;
                    pageNow = pageTotal;
                } else if (pageInput < 1) {
                    pageNow = 1;
                    pageInput = 1;
                }

                orders = orderService.queryOrders(order, pageInput,
                        Constants.PAGE_SIZE);

                pageNow = pageInput;
            } else {
                orders = orderService.queryOrders(order, pageNow,
                        Constants.PAGE_SIZE);
            }
        } catch (PageIndexOutOfBoundExcpeiton e) {
            this.setMessage(e.getMessage());
        } catch (InvalidPageSizeException e) {
            this.setMessage(e.getMessage());
        }
    }

    /**
     * 刷新页面数据
     */
    private void refreshPageData() {
        // 当前用户的工具栏
        List<MenuLinkVO> toolbarList = MenuUtil.getToolbarLinkVOList();

        List<MenuLinkVO> menuLink = null;
        if (null != toolbarList && 0 < toolbarList.size()) {
            // 第一个功能对应的菜单
            menuLink = MenuUtil.getMenuLinkVOList(FUNCTION_DESC);
        }

        this.setToolbarList(toolbarList);
        this.setMenuList(menuLink);
        this.setCurrentFunctionDesc(FUNCTION_DESC);
        this.setCurrentToolbar(MenuUtil.getParentFunctionDesc(FUNCTION_DESC));

        EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        this.setLoginName(userDetail.getUser().getUsername());
    }

    public NewOrderVO getOrder() {
        return order;
    }

    public void setOrder(NewOrderVO order) {
        this.order = order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getCashierId() {
        return cashierId;
    }

    public void setCashierId(Long cashierId) {
        this.cashierId = cashierId;
    }

    public Long getServentId() {
        return serventId;
    }

    public void setServentId(Long serventId) {
        this.serventId = serventId;
    }

    public List<KeyValue> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<KeyValue> statusList) {
        this.statusList = statusList;
    }

    public List<User> getCashierList() {
        return cashierList;
    }

    public void setCashierList(List<User> cashierList) {
        this.cashierList = cashierList;
    }

    public List<User> getServentList() {
        return serventList;
    }

    public void setServentList(List<User> serventList) {
        this.serventList = serventList;
    }

}