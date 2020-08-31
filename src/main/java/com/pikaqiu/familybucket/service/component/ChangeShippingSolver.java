package com.pikaqiu.familybucket.service.component;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.service.abstracts.InspectionSolver;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 21:45
 */
@Component
public class ChangeShippingSolver extends InspectionSolver {

    @Override
    public void solve(Long orderId, Long userId) {
        System.out.println("订单"+orderId+"开始进行转快递了。。");
    }

    @Override
    public String[] supports() {
        return new String[] {Constants.INSPECTION_TASK_TYPE_BATCH_CHANGE_SHIPPING};
    }
}