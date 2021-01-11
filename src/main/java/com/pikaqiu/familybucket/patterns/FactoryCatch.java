package com.pikaqiu.familybucket.patterns;

import com.pikaqiu.familybucket.patterns.decorator.ComponentCatch;
import com.pikaqiu.familybucket.patterns.decorator.FirstCatch;
import com.pikaqiu.familybucket.patterns.decorator.SecondCatch;
import com.pikaqiu.familybucket.patterns.decorator.ThiredCatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 装饰模式和代理模式区别？
 *
 * 代理模式：在方法之前和之后实现处理，在方法上实现增强，隐藏真实方法的真实性，保证安全。
 *
 * 装饰模式：不改变原有的功能，实现增强，不断新增很多装饰。
 *
 */
public class FactoryCatch {

    public static ComponentCatch getComponentCatch() {
        ThiredCatch thiredCatch = new ThiredCatch(new SecondCatch(new FirstCatch()));
        return thiredCatch;
    }

//    public static final String escapeHtml4(String str) {
//        return StringEscapeUtils.escapeHtml4(str);
//    }

    public static void main(String[] args) {
        /*ComponentCatch getComponentCatch = getComponentCatch();
        Object value1 = getComponentCatch.getCatch("1");
        System.out.println("value1:" + value1);
        System.out.println("###########################");
        Object value2 = getComponentCatch.getCatch("1");
        System.out.println("value2:" + value2);*/

        /*String str = "thi is a test <dsd,dsdd>这是[fdfd]一>个)&^测12232试18.21dsdd";
        System.out.println("用escapeJava方法转义之后的字符串为:"
                + StringEscapeUtils.escapeHtml3(str));

        String files = "dsdss.xlsx";
        System.out.println(files.substring(files.lastIndexOf(".")));*/



        /*authUserDTO = JSONObject.parseObject(filterStr, AuthUserDTO.class);
        System.out.println("---: " + authUserDTO.toString())*/;

        /*AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setPassword("税务受理,缴款中,增量/房业务,买卖,业务号:23301012787-20201111101420,坐落:未来科技城150号");
        authUserDTO.setUserName("18296158343<script>'al'ert(1)</script>");
        authUserDTO.setPhoneNumber("123,12323");

        String str = JSONObject.toJSONString(authUserDTO);
        System.out.println("Str: " + str);

        String str2 = escapeHtml4(str);
        System.out.println(str2);

        String filterStr = StringFilter(str2);
        System.out.println(filterStr);*/


    }

    /**
     * 过滤特殊字符
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringFilter(String str) {
//        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        String regEx="[\\\\`~!@#$%^&*()+=|';'<>?~！@#￥%……&*（）——+|【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


}
