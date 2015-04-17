/***********************************************
 * Filename       : CashAction.java 
 * Copyright      : Copyright (c) 2015
 * Company        : Innovaee
 * Created        : 04/10/2015
 ************************************************/

package com.innovaee.eorder.action.cash;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;

import com.innovaee.eorder.action.BaseAction;
import com.innovaee.eorder.entity.MemberShip;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.OrderNotFoundException;
import com.innovaee.eorder.exception.OrderOperationException;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.service.OrderService;
import com.innovaee.eorder.service.UserService;
import com.innovaee.eorder.utils.MenuUtil;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.utils.StringUtil;
import com.innovaee.eorder.vo.EOrderUserDetailVO;
import com.innovaee.eorder.vo.MenuLinkVO;
import com.innovaee.eorder.vo.NewOrderVO;
import com.innovaee.eorder.vo.OrderItemVO;

/**
 * @Title: CashAction
 * @Description: 收银Action
 *
 * @version V1.0
 */
public class CashAction extends BaseAction {

    /** 对象序列化ID */
    private static final long serialVersionUID = -5184755551662453454L;

    /** 数据库中对应的功能描述常量 */
    public static final String FUNCTION_DESC = "Cash";

    /** 订单明细 */
    private List<OrderItemVO> orderItemVOs;

    /** 餐桌状态 */
    private Map<Integer, Order> tableStatus;

    /** 订单 */
    private Order order;

    /** 订单ID */
    private Long orderId;

    /** 流水服务类对象 */
    @Resource
    private OrderService orderService;

    /** 用户类对象 */
    @Resource
    private UserService userService;

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String cash() {
        tableStatus = orderService.getTableStatus();

        // 刷新系统菜单
        refreshPageData();
        return SUCCESS;
    }

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String edit() {
        if (null != orderId) {
            try {
                order = orderService.getOrderById(orderId);
                // 将当前收银员修改为订单的收银员
                EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                        .getContext().getAuthentication().getPrincipal();
                if (null != userDetail) {
                    NewOrderVO newOrderVO = new NewOrderVO();
                    // 1. 设置订单ID
                    newOrderVO.setId(order.getId());
                    // 2. 设置订单状态
                    newOrderVO.setStatus(order.getOrderStatus());
                    User cashier = userDetail.getUser();
                    if (null != cashier) {
                        newOrderVO.setCashierId(cashier.getId());
                        order = orderService.updateOrder(newOrderVO);
                    }
                }
            } catch (OrderNotFoundException e) {
                setMessage(e.getMessage());
                return ERROR;
            } finally {
                // 刷新系统菜单
                refreshPageData();
            }
        }

        return SUCCESS;
    }

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String discount() {
        try {
            if (null != order.getMember().getCellphone()
                    && !"".equals(order.getMember().getCellphone().trim())) {
                if (!StringUtil.isMobileNO(order.getMember().getCellphone())) {
                    this.setMessage(MessageUtil.getMessage("cellphone_invalid"));
                    return INPUT;
                }
            } else {
                this.setMessage(MessageUtil.getMessage("cellphone_empty"));
                return INPUT;
            }

            NewOrderVO newOrderVO = new NewOrderVO();
            // 1. 设置订单ID
            newOrderVO.setId(order.getId());
            User member = userService.findUserByCellphone(order.getMember()
                    .getCellphone());
            if (null != member) {
                // 2. 设置会员信息
                newOrderVO.setMemberId(member.getId());
                if (null != member) {
                    order.setMember(member);
                    MemberShip memberShip = member.getMemberShip();
                    if (null != memberShip) {
                        order.getMember().setMemberShip(memberShip);
                        UserLevel level = memberShip.getLevel();
                        if (null != level) {
                            order.getMember().getMemberShip().setLevel(level);
                            order.setDiscountPrice(order.getTotalPrice()
                                    * level.getDiscount());
                        }
                    }
                }
            } else {
                this.setMessage(MessageUtil.getMessage(
                        "user_not_found_exception", order.getMember()
                                .getCellphone()));
                return INPUT;
            }

            // 3. 设置订单状态
            newOrderVO.setStatus(order.getOrderStatus());
            // 4. 设置收银员
            EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            User cashier = null;
            if (null != userDetail) {
                cashier = userDetail.getUser();
                if (null != cashier) {
                    newOrderVO.setCashierId(cashier.getId());
                }
            }

            orderService.updateOrder(newOrderVO);
        } catch (OrderNotFoundException e) {
            try {
                order = orderService.getOrderById(order.getId());
            } catch (OrderNotFoundException e1) {
                setMessage(e.getMessage());
            }
            setMessage(e.getMessage());
            return ERROR;
        } finally {
            try {
                order = orderService.getOrderById(order.getId());
            } catch (OrderNotFoundException e) {
                setMessage(e.getMessage());
            }

            // 刷新系统菜单
            refreshPageData();
        }

        return SUCCESS;
    }

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String save() {
        try {
            EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            User cashier = null;
            if (null != userDetail) {
                cashier = userDetail.getUser();
                if (null != cashier) {
                    orderService.payTheOrder(orderId, cashier.getId());
                    order = orderService.getOrderById(orderId);
                }
            }
        } catch (OrderNotFoundException e) {
            setMessage(e.getMessage());
            return ERROR;
        } catch (UserNotFoundException e) {
            setMessage(e.getMessage());
            return ERROR;
        } catch (OrderOperationException e) {
            setMessage(e.getMessage());
            return ERROR;
        } finally {
            tableStatus = orderService.getTableStatus();
            // 刷新系统菜单
            refreshPageData();
        }

        return SUCCESS;
    }

    /**
     * 进入流水页面
     * 
     * @return
     */
    public String print() {
        if (null != orderId) {
            try {
                // 查找当前订单
                order = orderService.getOrderById(orderId);
                // 如果非会员打印，则要先查看是否有收银员，如果没有，将当前收银员修改为订单的收银员
                User cashier = order.getCasher();
                if (null == cashier) {
                    EOrderUserDetailVO userDetail = (EOrderUserDetailVO) SecurityContextHolder
                            .getContext().getAuthentication().getPrincipal();

                    if (null != userDetail) {
                        NewOrderVO newOrderVO = new NewOrderVO();
                        // 1. 设置订单ID
                        newOrderVO.setId(order.getId());
                        // 2. 设置订单状态
                        newOrderVO.setStatus(order.getOrderStatus());
                        cashier = userDetail.getUser();
                        if (null != cashier) {
                            newOrderVO.setCashierId(cashier.getId());
                            orderService.updateOrder(newOrderVO);
                        }
                    }
                }
            } catch (OrderNotFoundException e) {
                setMessage(e.getMessage());
                return ERROR;
            }
        } else {
            setMessage(MessageUtil.getMessage("order_id", "" + orderId));
            return ERROR;
        }
        return SUCCESS;
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

    public Map<Integer, Order> getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(Map<Integer, Order> tableStatus) {
        this.tableStatus = tableStatus;
    }

    public List<OrderItemVO> getOrderItemVOs() {
        return orderItemVOs;
    }

    public void setOrderItemVOs(List<OrderItemVO> orderItemVOs) {
        this.orderItemVOs = orderItemVOs;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

}