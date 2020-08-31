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
package com.pikaqiu.familybucket.patterns;

import lombok.extern.slf4j.Slf4j;

/**
 * Description: 模板模式
 *
 * @author qiujian
 * @date 2020/3/20 10:32
 */
@Slf4j
public abstract class TemplatePattern {


    public void getUp(){
      log.info("起床~~");
    }
    public abstract void breakfast();
    public abstract void goToWork();
    public abstract void working();
    public abstract void lunch();
    public abstract void goHome();
    public abstract void supper();

    public void sleep(){
        log.info("sleep~");
    }

    /**
     * 模板方法
     */
    public final void process(){
        getUp();
        breakfast();
        goToWork();
        working();
        lunch();
        working();
        goHome();
        supper();
        sleep();
    }

}
