package com.pikaqiu.familybucket.service.component;

import com.pikaqiu.familybucket.service.abstracts.InspectionSolver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author PikaQiu
 * @date 2019/8/23 21:41
 */
@Component
public class InspectionSolverChooser implements ApplicationContextAware {


    private Map<String, InspectionSolver> chooseMap = new HashMap<>();

    public InspectionSolver choose(String type) {
        return chooseMap.get(type);
    }

    @PostConstruct
    public void register() {
        Map<String, InspectionSolver> solverMap = context.getBeansOfType(InspectionSolver.class);
        for (InspectionSolver solver : solverMap.values()) {
            for (String support : solver.supports()) {
                chooseMap.put(support,solver);
            }
        }
    }

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}