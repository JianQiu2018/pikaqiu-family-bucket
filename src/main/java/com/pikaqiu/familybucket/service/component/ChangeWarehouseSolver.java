package com.pikaqiu.familybucket.service.component;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.service.abstracts.InspectionSolver;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 21:42
 */
@Component
public class ChangeWarehouseSolver extends InspectionSolver {

    @Override
    public void solve(Long orderId, Long userId) {
        System.out.println("订单"+orderId+"开始进行批量转仓了。。");
    }

    @Override
    public String[] supports() {
        return new String[] {Constants.INSPECTION_TASK_TYPE_BATCH_CHANGE_WAREHOUSE};
    }
}