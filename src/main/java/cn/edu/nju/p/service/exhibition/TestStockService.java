package cn.edu.nju.p.service.exhibition;


import cn.edu.nju.p.vo.TestStockVO;

/**
 * Created by dell- on 2017/6/8.
 */
public interface TestStockService {

    /**
     *
     * @param code 股票代码
     * @return 股票的诊断，以VO形式返回
     */
    public TestStockVO getTestStockVO(String code);

}
