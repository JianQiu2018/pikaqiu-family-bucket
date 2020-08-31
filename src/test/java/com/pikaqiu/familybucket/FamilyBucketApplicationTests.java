package com.pikaqiu.familybucket;

import com.pikaqiu.familybucket.constants.Constants;
import com.pikaqiu.familybucket.redis.messagequeue.MessageProvider;
import com.pikaqiu.familybucket.service.abstracts.InspectionSolver;
import com.pikaqiu.familybucket.service.component.InspectionSolverChooser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilyBucketApplicationTests {

    @Autowired
    private InspectionSolverChooser chooser;

    @Test
    public void contextLoads() {

        //准备数据
//        String taskType = Constants.INSPECTION_TASK_TYPE_BATCH_CHANGE_WAREHOUSE;
        String taskType = Constants.INSPECTION_TASK_TYPE_BATCH_CHANGE_SHIPPING;
        Long orderId = 12345L;
        Long userId = 123L;
        //获取任务类型对应的solver
        InspectionSolver solver = chooser.choose(taskType);
        if (solver == null) {
            throw new RuntimeException("任务类型暂时无法处理!");
        }
        //调用不同solver的方法进行处理
        solver.solve(orderId,userId);

    }

}
