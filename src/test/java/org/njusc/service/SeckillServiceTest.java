package org.njusc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.njusc.dto.Exposer;
import org.njusc.dto.SeckillExecution;
import org.njusc.entity.Seckill;
import org.njusc.exception.RepeatKillException;
import org.njusc.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Echo on 17/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }


    // 集成测试代码完整逻辑,注意可以重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exprser={}", exposer);
            long phone = 13914595080L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", execution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            // 秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exprser={}", exposer);
        //{
        // exposed=true,
        // md5='cc0278983613c64c719e8a4d854cc7fa',
        // seckillId=1000,
        // now=0, start=0, end=0
        // }
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 13914595089L;
        String md5 = "cc0278983613c64c719e8a4d854cc7fa";
        SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
        logger.info("result={}", execution);
    }
}