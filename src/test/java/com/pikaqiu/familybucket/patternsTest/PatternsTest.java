/**
 * Copyright(c) Runsdata Technology Co.,Ltd.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of Runsdata
 * Technology Co.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with Runsdata Technology Co.,Ltd.
 * For more information about Runsdata, welcome to http://www.runsdata.com
 * <p>
 * Revision History:
 * Date     Version  Name     Description
 * 2020/3/20  1.0     qiujian   CreationFile
 */
package com.pikaqiu.familybucket.patternsTest;

import com.pikaqiu.familybucket.patterns.TemplatePattern;
import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author qiujian
 * @date 2020/3/20 10:38
 */
@Slf4j
public class PatternsTest extends TemplatePattern {

    @Override
    public void breakfast() {
        log.info("breakfast~");
    }

    @Override
    public void goToWork() {
        log.info("goToWork~");
    }

    @Override
    public void working() {
        log.info("working~");
    }

    @Override
    public void lunch() {
        log.info("lunch~");
    }

    @Override
    public void goHome() {
        log.info("goHome~");
    }

    @Override
    public void supper() {
        log.info("supper~");
    }

    public static void main(String[] args) {
       TemplatePattern templatePattern = new PatternsTest();
       templatePattern.process();
    }

}
