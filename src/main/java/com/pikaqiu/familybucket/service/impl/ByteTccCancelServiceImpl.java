package com.pikaqiu.familybucket.service.impl;

import com.pikaqiu.familybucket.repository.CompanyRepository;
import com.pikaqiu.familybucket.service.ByteTccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * description:
 * tcc try逻辑
 * 1.@Service("companyServiceImpl")这里必须指定此bean名称，tcc过程依靠此名称区分执行tcc中哪个逻辑
 * 2.参与tcc的方法必须添加@Transactional注解
 * 3.建议tcc每个步骤，方法执行后添加日志，方便问题排查
 *
 * @author qiujian
 * @date 2020/8/14 15:06
 */
@Service("byteTccCancelService")
public class ByteTccCancelServiceImpl implements ByteTccService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void testByteTcc(Integer id, BigDecimal money) {
        /*companyRepository.findById(id).ifPresent(company -> {
            company.setFrozen(company.getFrozen().subtract(money));
            companyRepository.save(company);
        });*/
        System.out.println("---------cancel ---------------");
        this.jdbcTemplate.update("update company set frozen = frozen - ? where id = ?", money, id);
    }

}
