package cn.edu.nju.p.service;

import cn.edu.nju.p.vo.MomentumResultVO;
import cn.edu.nju.p.vo.MomentumVO;

/**
 * 动量策略的接口
 */
public interface MomentumService {

    /**
     * 获取基本的数据
     * @param momentumVO 数据输入
     * @return 返回显示结果的vo
     */
    MomentumResultVO getResult(MomentumVO momentumVO);

}
