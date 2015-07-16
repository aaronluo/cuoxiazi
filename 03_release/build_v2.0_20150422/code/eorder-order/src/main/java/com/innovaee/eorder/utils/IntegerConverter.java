/***********************************************
 * Filename        : IntegerConverter.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/04/2015
 ************************************************/

package com.innovaee.eorder.utils;

import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;


/**
 * @Title: FloatConverter
 * @Description: 表单整形字段转换器
 *
 * @version V1.0
 */
public class IntegerConverter extends StrutsTypeConverter {

    @SuppressWarnings("rawtypes")
    @Override
    public Object convertFromString(Map context, String[] values, Class toType) {
        Integer result = 0;
        
        if(Integer.class == toType || int.class == toType) {
            String strVal = values[0];
           
            try{
                result = Integer.parseInt(strVal);
            }catch(Exception exception) {
               result = 0;
            }
        }
        
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String convertToString(Map context, Object val) {
        return ""+val;
    }

}
