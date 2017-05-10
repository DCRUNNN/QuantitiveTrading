package cn.edu.nju.p.service.exhibition;


import cn.edu.nju.p.vo.MeanReversionParamVO;
import cn.edu.nju.p.vo.MeanReversionResultVO;

/**
 * Created by xihao on 17-4-15.
 */
public interface MeanReversionService {

    /**
     * 实现均线回归策略 直观返回对比
     * @param paramVO 参数集合
     * @return 显示图片需要的数据
     */
    MeanReversionResultVO getResult(MeanReversionParamVO paramVO);

}
