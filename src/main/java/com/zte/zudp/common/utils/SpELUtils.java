package com.zte.zudp.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.env.Environment;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.zte.zudp.common.config.Configuration;

/**
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-03-10.
 */
public class SpELUtils {

    @Autowired
    private static Environment env;

    private static final StrSubstitutor STR_SUBSTITUTOR = new StrSubstitutor(Configuration.map());

    public static String parser(String expression) {
        return STR_SUBSTITUTOR.replace(expression);
    }

    public static Object getValue(Object model, String expression){
        return getValue(model, expression, false);
    }

    public static Object getValue(Object model, String expression, boolean throwEx){
        StandardEvaluationContext context= new StandardEvaluationContext();
        context.setRootObject(model);
        PropertyAccessor accessor1 = new MapAccessor();
        PropertyAccessor accessor2 = new ReflectivePropertyAccessor();
        List<PropertyAccessor> propertyAccessors = new ArrayList<PropertyAccessor>();
        propertyAccessors.add(accessor1);
        propertyAccessors.add(accessor2);
        context.setPropertyAccessors(propertyAccessors);
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        try{
            return exp.getValue(context);
        }catch(SpelEvaluationException e){
            if (throwEx){
                throw new RuntimeException(e.getMessage(), e);
            }else{
                return null;
            }
        }
    }

    public static void setValue(Object model, String expression, Object value){
        StandardEvaluationContext context= new StandardEvaluationContext();
        context.setRootObject(model);
        PropertyAccessor accessor1 = new MapAccessor();
        PropertyAccessor accessor2 = new ReflectivePropertyAccessor();
        List<PropertyAccessor> propertyAccessors = new ArrayList<PropertyAccessor>();
        propertyAccessors.add(accessor1);
        propertyAccessors.add(accessor2);
        context.setPropertyAccessors(propertyAccessors);
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        String[] expr = expression.split("\\.");
        Object nodeModel = model;//遍历时节点索引时的model
        for (int i=0; i<expr.length - 1; i++){
            if (expr[i].matches(".+\\[\\d+\\]$")){
                nodeModel = doListExpression(expr[i], nodeModel);
            }else{
                nodeModel = doMapExpression(expr[i], nodeModel);
            }
        }
        exp.setValue(context, value);
    }

    @SuppressWarnings("unchecked")
    private static Object doMapExpression(String expr, Object nodeModel) {
        //map
        Map map = (Map) getValue(nodeModel, expr);
        if (map == null){
            map = new HashMap();
            setValue(nodeModel, expr, map);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    private static Object doListExpression(String expr, Object nodeModel) {
        String property = expr.substring(0, expr.indexOf("["));
        int index = Integer.parseInt(expr.substring(expr.indexOf("[") + 1, expr.length() - 1));
        List list = (List)getValue(nodeModel, property);
        if (list == null){
            list = new ArrayList();
            Map item = new HashMap();
            list.add(item);
            setValue(nodeModel, property, list);

            return item;
        }else{
            Map item = null;
            if (index >= list.size()){
                item = new HashMap();
                list.add(index, item);
            }else{
                item = (Map)list.get(index);
            }

            return item;
        }
    }

}
