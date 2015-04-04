/***********************************************
 * Filename        : FloatConverter.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 04/04/2015
 ************************************************/

package com.innovaee.eorder.utils;

import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;


/**
 * @Title: FloatConverter
 * @Description: 表单浮点字段转换器
 *
 * @version V1.0
 */
public class FloatConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toType) {
        Float result = 0.0f;
        
        if(Float.class == toType) {
            String strVal = values[0];
           
            try{
                result = Float.parseFloat(strVal);
            }catch(Exception exception) {
                result = 0.0f;
            }
        }
        
        return result;
    }

    @Override
    public String convertToString(Map context, Object val) {
        return "" + val;
    }

}
