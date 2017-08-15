package org.njusc.dao;

import org.apache.ibatis.annotations.Param;
import org.njusc.entity.SuccessKilled;

/**
 * Created by Echo on 17/8/15.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细, 可过滤重复(联合主键)
     * @param seckillId
     * @param userPhone
     * @return 插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据id查询SuccessKilled并携带秒杀产品对象实体
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
