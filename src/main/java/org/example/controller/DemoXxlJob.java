package org.example.controller;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: Derek.huang on 2023/4/7 16:57.
 */
@Component
public class DemoXxlJob extends IJobHandler {

    private static Logger logger = LoggerFactory.getLogger(DemoXxlJob.class);

    @XxlJob(value = "DemoXxlJob")
    @Override
    public void execute() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        logger.info("当前时间:" + format);
    }
    /**
     *
     * 我对贵公司Java后端开发岗位很感兴趣，有6年Java开发经验，
     * 擅长...目前已经离职，非常期望得到您的恢复，感谢!.
     *
     */
}
