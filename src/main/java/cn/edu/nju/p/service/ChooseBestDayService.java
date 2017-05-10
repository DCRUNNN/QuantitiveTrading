package cn.edu.nju.p.service;

import cn.edu.nju.p.vo.BestDayResultVO;
import cn.edu.nju.p.vo.MeanReversionParamForBestDayVO;
import cn.edu.nju.p.vo.MomentumParamVO;

/**
 * Created by xihao on 17-4-3.
 */
public interface ChooseBestDayService {

    /**
     * 获得动量策略的超额收益率和策略收益率和形成期（持有期）的关系
     * @param momentumParamVO 动量策略的一些参数
     * @return 画图需要的惨素
     */
    BestDayResultVO getResultReturnsOfMomentum(MomentumParamVO momentumParamVO);

    /**
     * 获得均值回归的超额收益率和策略胜率和持有期的关系
     * @param vo 输入的必要的参数
     * @return 返回画图需要的参数（超额收益和策略胜率和持有期的关系）
     */
    BestDayResultVO getResultReturnsOfMeanReversion(MeanReversionParamForBestDayVO vo);
}
