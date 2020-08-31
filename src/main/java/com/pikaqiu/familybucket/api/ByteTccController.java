package com.pikaqiu.familybucket.api;

import com.pikaqiu.familybucket.repository.CompanyRepository;
import com.pikaqiu.familybucket.service.ByteTccService;
import lombok.extern.slf4j.Slf4j;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Compensable(interfaceClass = ByteTccService.class,
        confirmableKey = "byteTccComfirmService",
        cancellableKey = "byteTccCancelService")
@RestController
@Slf4j
public class ByteTccController{

    @Resource(name = "byteTccService")
    private ByteTccService byteTccService;

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    @PostMapping(value = "/api/byteTcc")
    public void testByteTcc(@RequestParam Integer id, @RequestParam BigDecimal money) {
        byteTccService.testByteTcc(1, new BigDecimal("200"));

        /*companyRepository.findById(1).ifPresent(company -> {
            company.setFrozen(company.getFrozen().add(new BigDecimal("200")));
            companyRepository.save(company);
        });*/
    }

    /*@Transactional
    public void testByteTcc(Integer id, BigDecimal money) {
        byteTccService.testByteTcc(1, new BigDecimal("200"));
    }*/

}
