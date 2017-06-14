package cn.edu.nju.p.dao.daoutils;

import cn.edu.nju.p.po.StockPO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell- on 2017/6/9.
 */
public class GetDataFromEastMoneyUtil {

    private static final String FILE_PATH = "D://EastMoneyData//";
    private static final GetDataFromEastMoneyUtil dataHelper = new GetDataFromEastMoneyUtil();

    public static GetDataFromEastMoneyUtil getInstance() {
        return dataHelper;
    }

    public List<StockPO> getStockPOList(String code,String market) {
        List<StockPO> list = new ArrayList<>();

        return list;
    }

    private Document getDocument(String URL) {
        try{
            return Jsoup.connect(URL).timeout(8000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void getCode() {
        String url = "http://quote.eastmoney.com/stocklist.html#sz";
        Document doc = getDocument(url);
        Elements ele = doc.select("[class=sltit]");
        System.out.println(ele.get(1).select("[li]").text());
    }

    public static void main(String[] args){
        String str = "000001.SZ000002.SZ000004.SZ000005.SZ000006.SZ000007.SZ000008.SZ000009.SZ000010.SZ000011.SZ000012.SZ000014.SZ000016.SZ000017.SZ000018.SZ000019.SZ000020.SZ000021.SZ000022.SZ000023.SZ000025.SZ000026.SZ000027.SZ000028.SZ000029.SZ000030.SZ000031.SZ000032.SZ000033.SZ000034.SZ000035.SZ000036.SZ000037.SZ000038.SZ000039.SZ000040.SZ000042.SZ000043.SZ000045.SZ000046.SZ000048.SZ000049.SZ000050.SZ000055.SZ000056.SZ000058.SZ000059.SZ000060.SZ000061.SZ000062.SZ000063.SZ000065.SZ000066.SZ000068.SZ000069.SZ000070.SZ000078.SZ000088.SZ000089.SZ000090.SZ000096.SZ000099.SZ000100.SZ000150.SZ000151.SZ000153.SZ000155.SZ000156.SZ000157.SZ000158.SZ000159.SZ000166.SZ000301.SZ000333.SZ000338.SZ000400.SZ000401.SZ000402.SZ000403.SZ000404.SZ000407.SZ000408.SZ000409.SZ000410.SZ000411.SZ000413.SZ000415.SZ000416.SZ000417.SZ000418.SZ000419.SZ000420.SZ000421.SZ000422.SZ000423.SZ000425.SZ000426.SZ000428.SZ000429.SZ000430.SZ000488.SZ000498.SZ000501.SZ000502.SZ000503.SZ000504.SZ000505.SZ000506.SZ000507.SZ000509.SZ000510.SZ000511.SZ000513.SZ000514.SZ000516.SZ000517.SZ000518.SZ000519.SZ000520.SZ000521.SZ000523.SZ000524.SZ000525.SZ000526.SZ000528.SZ000529.SZ000530.SZ000531.SZ000532.SZ000533.SZ000534.SZ000536.SZ000537.SZ000538.SZ000539.SZ000540.SZ000541.SZ000543.SZ000544.SZ000545.SZ000546.SZ000547.SZ000548.SZ000550.SZ000551.SZ000552.SZ000553.SZ000554.SZ000555.SZ000557.SZ000558.SZ000559.SZ000560.SZ000561.SZ000563.SZ000564.SZ000565.SZ000566.SZ000567.SZ000568.SZ000570.SZ000571.SZ000572.SZ000573.SZ000576.SZ000581.SZ000582.SZ000584.SZ000585.SZ000586.SZ000587.SZ000589.SZ000590.SZ000591.SZ000592.SZ000593.SZ000595.SZ000596.SZ000597.SZ000598.SZ000599.SZ000600.SZ000601.SZ000603.SZ000605.SZ000606.SZ000607.SZ000608.SZ000609.SZ000610.SZ000611.SZ000612.SZ000613.SZ000615.SZ000616.SZ000617.SZ000619.SZ000620.SZ000622.SZ000623.SZ000625.SZ000626.SZ000627.SZ000628.SZ000629.SZ000630.SZ000631.SZ000632.SZ000633.SZ000635.SZ000636.SZ000637.SZ000638.SZ000639.SZ000650.SZ000651.SZ000652.SZ000655.SZ000656.SZ000657.SZ000659.SZ000661.SZ000662.SZ000663.SZ000665.SZ000666.SZ000667.SZ000668.SZ000669.SZ000670.SZ000671.SZ000672.SZ000673.SZ000676.SZ000677.SZ000678.SZ000679.SZ000680.SZ000681.SZ000682.SZ000683.SZ000685.SZ000686.SZ000687.SZ000688.SZ000690.SZ000691.SZ000692.SZ000693.SZ000695.SZ000697.SZ000698.SZ000700.SZ000701.SZ000702.SZ000703.SZ000705.SZ000707.SZ000708.SZ000709.SZ000710.SZ000711.SZ000712.SZ000713.SZ000715.SZ000716.SZ000717.SZ000718.SZ000719.SZ000720.SZ000721.SZ000722.SZ000723.SZ000725.SZ000726.SZ000727.SZ000728.SZ000729.SZ000731.SZ000732.SZ000733.SZ000735.SZ000736.SZ000737.SZ000738.SZ000739.SZ000750.SZ000751.SZ000752.SZ000753.SZ000755.SZ000756.SZ000757.SZ000758.SZ000759.SZ000760.SZ000761.SZ000762.SZ000766.SZ000767.SZ000768.SZ000776.SZ000777.SZ000778.SZ000779.SZ000780.SZ000782.SZ000783.SZ000785.SZ000786.SZ000788.SZ000789.SZ000790.SZ000791.SZ000792.SZ000793.SZ000795.SZ000796.SZ000797.SZ000798.SZ000799.SZ000800.SZ000801.SZ000802.SZ000803.SZ000806.SZ000807.SZ000809.SZ000810.SZ000811.SZ000812.SZ000813.SZ000815.SZ000816.SZ000818.SZ000819.SZ000820.SZ000821.SZ000822.SZ000823.SZ000825.SZ000826.SZ000828.SZ000829.SZ000830.SZ000831.SZ000833.SZ000835.SZ000836.SZ000837.SZ000838.SZ000839.SZ000848.SZ000850.SZ000851.SZ000852.SZ000856.SZ000858.SZ000859.SZ000860.SZ000861.SZ000862.SZ000863.SZ000868.SZ000869.SZ000875.SZ000876.SZ000877.SZ000878.SZ000880.SZ000881.SZ000882.SZ000883.SZ000885.SZ000886.SZ000887.SZ000888.SZ000889.SZ000890.SZ000892.SZ000893.SZ000895.SZ000897.SZ000898.SZ000899.SZ000900.SZ000901.SZ000902.SZ000903.SZ000905.SZ000906.SZ000908.SZ000909.SZ000910.SZ000911.SZ000912.SZ000913.SZ000915.SZ000916.SZ000917.SZ000918.SZ000919.SZ000920.SZ000921.SZ000922.SZ000923.SZ000925.SZ000926.SZ000927.SZ000928.SZ000929.SZ000930.SZ000931.SZ000932.SZ000933.SZ000935.SZ000936.SZ000937.SZ000938.SZ000939.SZ000948.SZ000949.SZ000950.SZ000951.SZ000952.SZ000953.SZ000955.SZ000957.SZ000958.SZ000959.SZ000960.SZ000961.SZ000962.SZ000963.SZ000965.SZ000966.SZ000967.SZ000968.SZ000969.SZ000970.SZ000971.SZ000972.SZ000973.SZ000975.SZ000976.SZ000977.SZ000978.SZ000979.SZ000980.SZ000981.SZ000982.SZ000983.SZ000985.SZ000987.SZ000988.SZ000989.SZ000990.SZ000993.SZ000995.SZ000996.SZ000997.SZ000998.SZ000999.SZ001696.SZ001896.SZ001979.SZ002001.SZ002002.SZ002003.SZ002004.SZ002005.SZ002006.SZ002007.SZ002008.SZ002009.SZ002010.SZ002011.SZ002012.SZ002013.SZ002014.SZ002015.SZ002016.SZ002017.SZ002018.SZ002019.SZ002020.SZ002021.SZ002022.SZ002023.SZ002024.SZ002025.SZ002026.SZ002027.SZ002028.SZ002029.SZ002030.SZ002031.SZ002032.SZ002033.SZ002034.SZ002035.SZ002036.SZ002037.SZ002038.SZ002039.SZ002040.SZ002041.SZ002042.SZ002043.SZ002044.SZ002045.SZ002046.SZ002047.SZ002048.SZ002049.SZ002050.SZ002051.SZ002052.SZ002053.SZ002054.SZ002055.SZ002056.SZ002057.SZ002058.SZ002059.SZ002060.SZ002061.SZ002062.SZ002063.SZ002064.SZ002065.SZ002066.SZ002067.SZ002068.SZ002069.SZ002070.SZ002071.SZ002072.SZ002073.SZ002074.SZ002075.SZ002076.SZ002077.SZ002078.SZ002079.SZ002080.SZ002081.SZ002082.SZ002083.SZ002084.SZ002085.SZ002086.SZ002087.SZ002088.SZ002089.SZ002090.SZ002091.SZ002092.SZ002093.SZ002094.SZ002095.SZ002096.SZ002097.SZ002098.SZ002099.SZ002100.SZ002101.SZ002102.SZ002103.SZ002104.SZ002105.SZ002106.SZ002107.SZ002108.SZ002109.SZ002110.SZ002111.SZ002112.SZ002113.SZ002114.SZ002115.SZ002116.SZ002117.SZ002118.SZ002119.SZ002120.SZ002121.SZ002122.SZ002123.SZ002124.SZ002125.SZ002126.SZ002127.SZ002128.SZ002129.SZ002130.SZ002131.SZ002132.SZ002133.SZ002134.SZ002135.SZ002136.SZ002137.SZ002138.SZ002139.SZ002140.SZ002141.SZ002142.SZ002143.SZ002144.SZ002145.SZ002146.SZ002147.SZ002148.SZ002149.SZ002150.SZ002151.SZ002152.SZ002153.SZ002154.SZ002155.SZ002156.SZ002157.SZ002158.SZ002159.SZ002160.SZ002161.SZ002162.SZ002163.SZ002164.SZ002165.SZ002166.SZ002167.SZ002168.SZ002169.SZ002170.SZ002171.SZ002172.SZ002173.SZ002174.SZ002175.SZ002176.SZ002177.SZ002178.SZ002179.SZ002180.SZ002181.SZ002182.SZ002183.SZ002184.SZ002185.SZ002186.SZ002187.SZ002188.SZ002189.SZ002190.SZ002191.SZ002192.SZ002193.SZ002194.SZ002195.SZ002196.SZ002197.SZ002198.SZ002199.SZ002200.SZ002201.SZ002202.SZ002203.SZ002204.SZ002205.SZ002206.SZ002207.SZ002208.SZ002209.SZ002210.SZ002211.SZ002212.SZ002213.SZ002214.SZ002215.SZ002216.SZ002217.SZ002218.SZ002219.SZ002220.SZ002221.SZ002222.SZ002223.SZ002224.SZ002225.SZ002226.SZ002227.SZ002228.SZ002229.SZ002230.SZ002231.SZ002232.SZ002233.SZ002234.SZ002235.SZ002236.SZ002237.SZ002238.SZ002239.SZ002240.SZ002241.SZ002242.SZ002243.SZ002244.SZ002245.SZ002246.SZ002247.SZ002248.SZ002249.SZ002250.SZ002251.SZ002252.SZ002253.SZ002254.SZ002255.SZ002256.SZ002258.SZ002259.SZ002260.SZ002261.SZ002262.SZ002263.SZ002264.SZ002265.SZ002266.SZ002267.SZ002268.SZ002269.SZ002270.SZ002271.SZ002272.SZ002273.SZ002274.SZ002275.SZ002276.SZ002277.SZ002278.SZ002279.SZ002280.SZ002281.SZ002282.SZ002283.SZ002284.SZ002285.SZ002286.SZ002287.SZ002288.SZ002289.SZ002290.SZ002291.SZ002292.SZ002293.SZ002294.SZ002295.SZ002296.SZ002297.SZ002298.SZ002299.SZ002300.SZ002301.SZ002302.SZ002303.SZ002304.SZ002305.SZ002306.SZ002307.SZ002308.SZ002309.SZ002310.SZ002311.SZ002312.SZ002313.SZ002314.SZ002315.SZ002316.SZ002317.SZ002318.SZ002319.SZ002320.SZ002321.SZ002322.SZ002323.SZ002324.SZ002325.SZ002326.SZ002327.SZ002328.SZ002329.SZ002330.SZ002331.SZ002332.SZ002333.SZ002334.SZ002335.SZ002336.SZ002337.SZ002338.SZ002339.SZ002340.SZ002341.SZ002342.SZ002343.SZ002344.SZ002345.SZ002346.SZ002347.SZ002348.SZ002349.SZ002350.SZ002351.SZ002352.SZ002353.SZ002354.SZ002355.SZ002356.SZ002357.SZ002358.SZ002359.SZ002360.SZ002361.SZ002362.SZ002363.SZ002364.SZ002365.SZ002366.SZ002367.SZ002368.SZ002369.SZ002370.SZ002371.SZ002372.SZ002373.SZ002374.SZ002375.SZ002376.SZ002377.SZ002378.SZ002379.SZ002380.SZ002381.SZ002382.SZ002383.SZ002384.SZ002385.SZ002386.SZ002387.SZ002388.SZ002389.SZ002390.SZ002391.SZ002392.SZ002393.SZ002394.SZ002395.SZ002396.SZ002397.SZ002398.SZ002399.SZ002400.SZ002401.SZ002402.SZ002403.SZ002404.SZ002405.SZ002406.SZ002407.SZ002408.SZ002409.SZ002410.SZ002411.SZ002412.SZ002413.SZ002414.SZ002415.SZ002416.SZ002417.SZ002418.SZ002419.SZ002420.SZ002421.SZ002422.SZ002423.SZ002424.SZ002425.SZ002426.SZ002427.SZ002428.SZ002429.SZ002430.SZ002431.SZ002432.SZ002433.SZ002434.SZ002435.SZ002436.SZ002437.SZ002438.SZ002439.SZ002440.SZ002441.SZ002442.SZ002443.SZ002444.SZ002445.SZ002446.SZ002447.SZ002448.SZ002449.SZ002450.SZ002451.SZ002452.SZ002453.SZ002454.SZ002455.SZ002456.SZ002457.SZ002458.SZ002459.SZ002460.SZ002461.SZ002462.SZ002463.SZ002464.SZ002465.SZ002466.SZ002467.SZ002468.SZ002469.SZ002470.SZ002471.SZ002472.SZ002473.SZ002474.SZ002475.SZ002476.SZ002477.SZ002478.SZ002479.SZ002480.SZ002481.SZ002482.SZ002483.SZ002484.SZ002485.SZ002486.SZ002487.SZ002488.SZ002489.SZ002490.SZ002491.SZ002492.SZ002493.SZ002494.SZ002495.SZ002496.SZ002497.SZ002498.SZ002499.SZ002500.SZ002501.SZ002502.SZ002503.SZ002504.SZ002505.SZ002506.SZ002507.SZ002508.SZ002509.SZ002510.SZ002511.SZ002512.SZ002513.SZ002514.SZ002515.SZ002516.SZ002517.SZ002518.SZ002519.SZ002520.SZ002521.SZ002522.SZ002523.SZ002524.SZ002526.SZ002527.SZ002528.SZ002529.SZ002530.SZ002531.SZ002532.SZ002533.SZ002534.SZ002535.SZ002536.SZ002537.SZ002538.SZ002539.SZ002540.SZ002541.SZ002542.SZ002543.SZ002544.SZ002545.SZ002546.SZ002547.SZ002548.SZ002549.SZ002550.SZ002551.SZ002552.SZ002553.SZ002554.SZ002555.SZ002556.SZ002557.SZ002558.SZ002559.SZ002560.SZ002561.SZ002562.SZ002563.SZ002564.SZ002565.SZ002566.SZ002567.SZ002568.SZ002569.SZ002570.SZ002571.SZ002572.SZ002573.SZ002574.SZ002575.SZ002576.SZ002577.SZ002578.SZ002579.SZ002580.SZ002581.SZ002582.SZ002583.SZ002584.SZ002585.SZ002586.SZ002587.SZ002588.SZ002589.SZ002590.SZ002591.SZ002592.SZ002593.SZ002594.SZ002595.SZ002596.SZ002597.SZ002598.SZ002599.SZ002600.SZ002601.SZ002602.SZ002603.SZ002604.SZ002605.SZ002606.SZ002607.SZ002608.SZ002609.SZ002610.SZ002611.SZ002612.SZ002613.SZ002614.SZ002615.SZ002616.SZ002617.SZ002618.SZ002619.SZ002620.SZ002621.SZ002622.SZ002623.SZ002624.SZ002625.SZ002626.SZ002627.SZ002628.SZ002629.SZ002630.SZ002631.SZ002632.SZ002633.SZ002634.SZ002635.SZ002636.SZ002637.SZ002638.SZ002639.SZ002640.SZ002641.SZ002642.SZ002643.SZ002644.SZ002645.SZ002646.SZ002647.SZ002648.SZ002649.SZ002650.SZ002651.SZ002652.SZ002653.SZ002654.SZ002655.SZ002656.SZ002657.SZ002658.SZ002659.SZ002660.SZ002661.SZ002662.SZ002663.SZ002664.SZ002665.SZ002666.SZ002667.SZ002668.SZ002669.SZ002670.SZ002671.SZ002672.SZ002673.SZ002674.SZ002675.SZ002676.SZ002677.SZ002678.SZ002679.SZ002680.SZ002681.SZ002682.SZ002683.SZ002684.SZ002685.SZ002686.SZ002687.SZ002688.SZ002689.SZ002690.SZ002691.SZ002692.SZ002693.SZ002694.SZ002695.SZ002696.SZ002697.SZ002698.SZ002699.SZ002700.SZ002701.SZ002702.SZ002703.SZ002705.SZ002706.SZ002707.SZ002708.SZ002709.SZ002711.SZ002712.SZ002713.SZ002714.SZ002715.SZ002716.SZ002717.SZ002718.SZ002719.SZ002721.SZ002722.SZ002723.SZ002724.SZ002725.SZ002726.SZ002727.SZ002728.SZ002729.SZ002730.SZ002731.SZ002732.SZ002733.SZ002734.SZ002735.SZ002736.SZ002737.SZ002738.SZ002739.SZ002740.SZ002741.SZ002742.SZ002743.SZ002745.SZ002746.SZ002747.SZ002748.SZ002749.SZ002750.SZ002751.SZ002752.SZ002753.SZ002755.SZ002756.SZ002757.SZ002758.SZ002759.SZ002760.SZ002761.SZ002762.SZ002763.SZ002765.SZ002766.SZ002767.SZ002768.SZ002769.SZ002770.SZ002771.SZ002772.SZ002773.SZ002774.SZ002775.SZ002776.SZ002777.SZ002778.SZ002779.SZ002780.SZ002781.SZ002782.SZ002783.SZ002785.SZ002786.SZ002787.SZ002788.SZ002789.SZ002790.SZ002791.SZ002792.SZ002793.SZ002795.SZ002796.SZ002797.SZ002798.SZ002799.SZ002800.SZ002801.SZ002802.SZ002803.SZ002805.SZ002806.SZ002807.SZ002808.SZ002809.SZ002810.SZ002811.SZ002812.SZ002813.SZ002815.SZ002816.SZ002817.SZ002818.SZ002819.SZ002820.SZ002821.SZ002822.SZ002823.SZ002824.SZ002825.SZ002826.SZ002827.SZ002828.SZ002829.SZ002830.SZ002831.SZ002832.SZ002833.SZ002835.SZ002836.SZ002837.SZ002838.SZ002839.SZ002840.SZ002841.SZ002842.SZ002843.SZ002845.SZ002846.SZ002847.SZ002848.SZ002849.SZ002850.SZ002851.SZ002852.SZ002853.SZ002855.SZ002856.SZ002857.SZ002858.SZ002859.SZ002860.SZ002861.SZ002862.SZ002863.SZ002865.SZ002866.SZ002867.SZ002868.SZ002869.SZ002870.SZ002871.SZ002872.SZ002873.SZ002875.SZ002876.SZ002877.SZ002878.SZ300001.SZ300002.SZ300003.SZ300004.SZ300005.SZ300006.SZ300007.SZ300008.SZ300009.SZ300010.SZ300011.SZ300012.SZ300013.SZ300014.SZ300015.SZ300016.SZ300017.SZ300018.SZ300019.SZ300020.SZ300021.SZ300022.SZ300023.SZ300024.SZ300025.SZ300026.SZ300027.SZ300028.SZ300029.SZ300030.SZ300031.SZ300032.SZ300033.SZ300034.SZ300035.SZ300036.SZ300037.SZ300038.SZ300039.SZ300040.SZ300041.SZ300042.SZ300043.SZ300044.SZ300045.SZ300046.SZ300047.SZ300048.SZ300049.SZ300050.SZ300051.SZ300052.SZ300053.SZ300054.SZ300055.SZ300056.SZ300057.SZ300058.SZ300059.SZ300061.SZ300062.SZ300063.SZ300064.SZ300065.SZ300066.SZ300067.SZ300068.SZ300069.SZ300070.SZ300071.SZ300072.SZ300073.SZ300074.SZ300075.SZ300076.SZ300077.SZ300078.SZ300079.SZ300080.SZ300081.SZ300082.SZ300083.SZ300084.SZ300085.SZ300086.SZ300087.SZ300088.SZ300089.SZ300090.SZ300091.SZ300092.SZ300093.SZ300094.SZ300095.SZ300096.SZ300097.SZ300098.SZ300099.SZ300100.SZ300101.SZ300102.SZ300103.SZ300104.SZ300105.SZ300106.SZ300107.SZ300108.SZ300109.SZ300110.SZ300111.SZ300112.SZ300113.SZ300114.SZ300115.SZ300116.SZ300117.SZ300118.SZ300119.SZ300120.SZ300121.SZ300122.SZ300123.SZ300124.SZ300125.SZ300126.SZ300127.SZ300128.SZ300129.SZ300130.SZ300131.SZ300132.SZ300133.SZ300134.SZ300135.SZ300136.SZ300137.SZ300138.SZ300139.SZ300140.SZ300141.SZ300142.SZ300143.SZ300144.SZ300145.SZ300146.SZ300147.SZ300148.SZ300149.SZ300150.SZ300151.SZ300152.SZ300153.SZ300154.SZ300155.SZ300156.SZ300157.SZ300158.SZ300159.SZ300160.SZ300161.SZ300162.SZ300163.SZ300164.SZ300165.SZ300166.SZ300167.SZ300168.SZ300169.SZ300170.SZ300171.SZ300172.SZ300173.SZ300174.SZ300175.SZ300176.SZ300177.SZ300178.SZ300179.SZ300180.SZ300181.SZ300182.SZ300183.SZ300184.SZ300185.SZ300187.SZ300188.SZ300189.SZ300190.SZ300191.SZ300192.SZ300193.SZ300194.SZ300195.SZ300196.SZ300197.SZ300198.SZ300199.SZ300200.SZ300201.SZ300202.SZ300203.SZ300204.SZ300205.SZ300206.SZ300207.SZ300208.SZ300209.SZ300210.SZ300211.SZ300212.SZ300213.SZ300214.SZ300215.SZ300216.SZ300217.SZ300218.SZ300219.SZ300220.SZ300221.SZ300222.SZ300223.SZ300224.SZ300225.SZ300226.SZ300227.SZ300228.SZ300229.SZ300230.SZ300231.SZ300232.SZ300233.SZ300234.SZ300235.SZ300236.SZ300237.SZ300238.SZ300239.SZ300240.SZ300241.SZ300242.SZ300243.SZ300244.SZ300245.SZ300246.SZ300247.SZ300248.SZ300249.SZ300250.SZ300251.SZ300252.SZ300253.SZ300254.SZ300255.SZ300256.SZ300257.SZ300258.SZ300259.SZ300260.SZ300261.SZ300262.SZ300263.SZ300264.SZ300265.SZ300266.SZ300267.SZ300268.SZ300269.SZ300270.SZ300271.SZ300272.SZ300273.SZ300274.SZ300275.SZ300276.SZ300277.SZ300278.SZ300279.SZ300280.SZ300281.SZ300282.SZ300283.SZ300284.SZ300285.SZ300286.SZ300287.SZ300288.SZ300289.SZ300290.SZ300291.SZ300292.SZ300293.SZ300294.SZ300295.SZ300296.SZ300297.SZ300298.SZ300299.SZ300300.SZ300301.SZ300302.SZ300303.SZ300304.SZ300305.SZ300306.SZ300307.SZ300308.SZ300309.SZ300310.SZ300311.SZ300312.SZ300313.SZ300314.SZ300315.SZ300316.SZ300317.SZ300318.SZ300319.SZ300320.SZ300321.SZ300322.SZ300323.SZ300324.SZ300325.SZ300326.SZ300327.SZ300328.SZ300329.SZ300330.SZ300331.SZ300332.SZ300333.SZ300334.SZ300335.SZ300336.SZ300337.SZ300338.SZ300339.SZ300340.SZ300341.SZ300342.SZ300343.SZ300344.SZ300345.SZ300346.SZ300347.SZ300348.SZ300349.SZ300350.SZ300351.SZ300352.SZ300353.SZ300354.SZ300355.SZ300356.SZ300357.SZ300358.SZ300359.SZ300360.SZ300362.SZ300363.SZ300364.SZ300365.SZ300366.SZ300367.SZ300368.SZ300369.SZ300370.SZ300371.SZ300372.SZ300373.SZ300374.SZ300375.SZ300376.SZ300377.SZ300378.SZ300379.SZ300380.SZ300381.SZ300382.SZ300383.SZ300384.SZ300385.SZ300386.SZ300387.SZ300388.SZ300389.SZ300390.SZ300391.SZ300392.SZ300393.SZ300394.SZ300395.SZ300396.SZ300397.SZ300398.SZ300399.SZ300400.SZ300401.SZ300402.SZ300403.SZ300404.SZ300405.SZ300406.SZ300407.SZ300408.SZ300409.SZ300410.SZ300411.SZ300412.SZ300413.SZ300414.SZ300415.SZ300416.SZ300417.SZ300418.SZ300419.SZ300420.SZ300421.SZ300422.SZ300423.SZ300424.SZ300425.SZ300426.SZ300427.SZ300428.SZ300429.SZ300430.SZ300431.SZ300432.SZ300433.SZ300434.SZ300435.SZ300436.SZ300437.SZ300438.SZ300439.SZ300440.SZ300441.SZ300442.SZ300443.SZ300444.SZ300445.SZ300446.SZ300447.SZ300448.SZ300449.SZ300450.SZ300451.SZ300452.SZ300453.SZ300455.SZ300456.SZ300457.SZ300458.SZ300459.SZ300460.SZ300461.SZ300462.SZ300463.SZ300464.SZ300465.SZ300466.SZ300467.SZ300468.SZ300469.SZ300470.SZ300471.SZ300472.SZ300473.SZ300474.SZ300475.SZ300476.SZ300477.SZ300478.SZ300479.SZ300480.SZ300481.SZ300482.SZ300483.SZ300484.SZ300485.SZ300486.SZ300487.SZ300488.SZ300489.SZ300490.SZ300491.SZ300492.SZ300493.SZ300494.SZ300495.SZ300496.SZ300497.SZ300498.SZ300499.SZ300500.SZ300501.SZ300502.SZ300503.SZ300505.SZ300506.SZ300507.SZ300508.SZ300509.SZ300510.SZ300511.SZ300512.SZ300513.SZ300514.SZ300515.SZ300516.SZ300517.SZ300518.SZ300519.SZ300520.SZ300521.SZ300522.SZ300523.SZ300525.SZ300526.SZ300527.SZ300528.SZ300529.SZ300530.SZ300531.SZ300532.SZ300533.SZ300534.SZ300535.SZ300536.SZ300537.SZ300538.SZ300539.SZ300540.SZ300541.SZ300542.SZ300543.SZ300545.SZ300546.SZ300547.SZ300548.SZ300549.SZ300550.SZ300551.SZ300552.SZ300553.SZ300554.SZ300555.SZ300556.SZ300557.SZ300558.SZ300559.SZ300560.SZ300561.SZ300562.SZ300563.SZ300565.SZ300566.SZ300567.SZ300568.SZ300569.SZ300570.SZ300571.SZ300572.SZ300573.SZ300575.SZ300576.SZ300577.SZ300578.SZ300579.SZ300580.SZ300581.SZ300582.SZ300583.SZ300584.SZ300585.SZ300586.SZ300587.SZ300588.SZ300589.SZ300590.SZ300591.SZ300592.SZ300593.SZ300595.SZ300596.SZ300597.SZ300598.SZ300599.SZ300600.SZ300601.SZ300602.SZ300603.SZ300604.SZ300605.SZ300606.SZ300607.SZ300608.SZ300609.SZ300610.SZ300611.SZ300612.SZ300613.SZ300615.SZ300616.SZ300617.SZ300618.SZ300619.SZ300620.SZ300621.SZ300622.SZ300623.SZ300625.SZ300626.SZ300627.SZ300628.SZ300629.SZ300630.SZ300631.SZ300632.SZ300633.SZ300635.SZ300636.SZ300637.SZ300638.SZ300639.SZ300640.SZ300641.SZ300642.SZ300643.SZ300645.SZ300647.SZ300648.SZ300649.SZ300650.SZ300651.SZ300652.SZ300653.SZ300655.SZ300656.SZ300657.SZ300658.SZ300659.SZ300660.SZ300661.SZ300662.SZ300663.SZ";
        String[] list = str.replaceAll(".SZ", ".sz ").split(" ");
//        System.out.println(list.length);
        String result = "";
        for(int i=0;i<list.length;i++) {
            result=result+"\""+list[i]+"\",";
        }
        System.out.println(result);
    }
//    000001,000002,000004,000005,000006,000007,000008,000009,000010,000011,000012,000014,000016,000017,000018,000019,000020,000021,000022,000023,000025,000026,000027,000028,000029,000030,000031,000032,000033,000034,000035,000036,000037,000038,000039,000040,000042,000043,000045,000046,000048,000049,000050,000055,000056,000058,000059,000060,000061,000062,000063,000065,000066,000068,000069,000070,000078,000088,000089,000090,000096,000099,000100,000150,000151,000153,000155,000156,000157,000158,000159,000166,000301,000333,000338,000400,000401,000402,000403,000404,000407,000408,000409,000410,000411,000413,000415,000416,000417,000418,000419,000420,000421,000422,000423,000425,000426,000428,000429,000430,000488,000498,000501,000502,000503,000504,000505,000506,000507,000509,000510,000511,000513,000514,000516,000517,000518,000519,000520,000521,000523,000524,000525,000526,000528,000529,000530,000531,000532,000533,000534,000536,000537,000538,000539,000540,000541,000543,000544,000545,000546,000547,000548,000550,000551,000552,000553,000554,000555,000557,000558,000559,000560,000561,000563,000564,000565,000566,000567,000568,000570,000571,000572,000573,000576,000581,000582,000584,000585,000586,000587,000589,000590,000591,000592,000593,000595,000596,000597,000598,000599,000600,000601,000603,000605,000606,000607,000608,000609,000610,000611,000612,000613,000615,000616,000617,000619,000620,000622,000623,000625,000626,000627,000628,000629,000630,000631,000632,000633,000635,000636,000637,000638,000639,000650,000651,000652,000655,000656,000657,000659,000661,000662,000663,000665,000666,000667,000668,000669,000670,000671,000672,000673,000676,000677,000678,000679,000680,000681,000682,000683,000685,000686,000687,000688,000690,000691,000692,000693,000695,000697,000698,000700,000701,000702,000703,000705,000707,000708,000709,000710,000711,000712,000713,000715,000716,000717,000718,000719,000720,000721,000722,000723,000725,000726,000727,000728,000729,000731,000732,000733,000735,000736,000737,000738,000739,000750,000751,000752,000753,000755,000756,000757,000758,000759,000760,000761,000762,000766,000767,000768,000776,000777,000778,000779,000780,000782,000783,000785,000786,000788,000789,000790,000791,000792,000793,000795,000796,000797,000798,000799,000800,000801,000802,000803,000806,000807,000809,000810,000811,000812,000813,000815,000816,000818,000819,000820,000821,000822,000823,000825,000826,000828,000829,000830,000831,000833,000835,000836,000837,000838,000839,000848,000850,000851,000852,000856,000858,000859,000860,000861,000862,000863,000868,000869,000875,000876,000877,000878,000880,000881,000882,000883,000885,000886,000887,000888,000889,000890,000892,000893,000895,000897,000898,000899,000900,000901,000902,000903,000905,000906,000908,000909,000910,000911,000912,000913,000915,000916,000917,000918,000919,000920,000921,000922,000923,000925,000926,000927,000928,000929,000930,000931,000932,000933,000935,000936,000937,000938,000939,000948,000949,000950,000951,000952,000953,000955,000957,000958,000959,000960,000961,000962,000963,000965,000966,000967,000968,000969,000970,000971,000972,000973,000975,000976,000977,000978,000979,000980,000981,000982,000983,000985,000987,000988,000989,000990,000993,000995,000996,000997,000998,000999,001696,001896,001979,002001,002002,002003,002004,002005,002006,002007,002008,002009,002010,002011,002012,002013,002014,002015,002016,002017,002018,002019,002020,002021,002022,002023,002024,002025,002026,002027,002028,002029,002030,002031,002032,002033,002034,002035,002036,002037,002038,002039,002040,002041,002042,002043,002044,002045,002046,002047,002048,002049,002050,002051,002052,002053,002054,002055,002056,002057,002058,002059,002060,002061,002062,002063,002064,002065,002066,002067,002068,002069,002070,002071,002072,002073,002074,002075,002076,002077,002078,002079,002080,002081,002082,002083,002084,002085,002086,002087,002088,002089,002090,002091,002092,002093,002094,002095,002096,002097,002098,002099,002100,002101,002102,002103,002104,002105,002106,002107,002108,002109,002110,002111,002112,002113,002114,002115,002116,002117,002118,002119,002120,002121,002122,002123,002124,002125,002126,002127,002128,002129,002130,002131,002132,002133,002134,002135,002136,002137,002138,002139,002140,002141,002142,002143,002144,002145,002146,002147,002148,002149,002150,002151,002152,002153,002154,002155,002156,002157,002158,002159,002160,002161,002162,002163,002164,002165,002166,002167,002168,002169,002170,002171,002172,002173,002174,002175,002176,002177,002178,002179,002180,002181,002182,002183,002184,002185,002186,002187,002188,002189,002190,002191,002192,002193,002194,002195,002196,002197,002198,002199,002200,002201,002202,002203,002204,002205,002206,002207,002208,002209,002210,002211,002212,002213,002214,002215,002216,002217,002218,002219,002220,002221,002222,002223,002224,002225,002226,002227,002228,002229,002230,002231,002232,002233,002234,002235,002236,002237,002238,002239,002240,002241,002242,002243,002244,002245,002246,002247,002248,002249,002250,002251,002252,002253,002254,002255,002256,002258,002259,002260,002261,002262,002263,002264,002265,002266,002267,002268,002269,002270,002271,002272,002273,002274,002275,002276,002277,002278,002279,002280,002281,002282,002283,002284,002285,002286,002287,002288,002289,002290,002291,002292,002293,002294,002295,002296,002297,002298,002299,002300,002301,002302,002303,002304,002305,002306,002307,002308,002309,002310,002311,002312,002313,002314,002315,002316,002317,002318,002319,002320,002321,002322,002323,002324,002325,002326,002327,002328,002329,002330,002331,002332,002333,002334,002335,002336,002337,002338,002339,002340,002341,002342,002343,002344,002345,002346,002347,002348,002349,002350,002351,002352,002353,002354,002355,002356,002357,002358,002359,002360,002361,002362,002363,002364,002365,002366,002367,002368,002369,002370,002371,002372,002373,002374,002375,002376,002377,002378,002379,002380,002381,002382,002383,002384,002385,002386,002387,002388,002389,002390,002391,002392,002393,002394,002395,002396,002397,002398,002399,002400,002401,002402,002403,002404,002405,002406,002407,002408,002409,002410,002411,002412,002413,002414,002415,002416,002417,002418,002419,002420,002421,002422,002423,002424,002425,002426,002427,002428,002429,002430,002431,002432,002433,002434,002435,002436,002437,002438,002439,002440,002441,002442,002443,002444,002445,002446,002447,002448,002449,002450,002451,002452,002453,002454,002455,002456,002457,002458,002459,002460,002461,002462,002463,002464,002465,002466,002467,002468,002469,002470,002471,002472,002473,002474,002475,002476,002477,002478,002479,002480,002481,002482,002483,002484,002485,002486,002487,002488,002489,002490,002491,002492,002493,002494,002495,002496,002497,002498,002499,002500,002501,002502,002503,002504,002505,002506,002507,002508,002509,002510,002511,002512,002513,002514,002515,002516,002517,002518,002519,002520,002521,002522,002523,002524,002526,002527,002528,002529,002530,002531,002532,002533,002534,002535,002536
}
//002537,002538,002539,002540,002541,002542,002543,002544,002545,002546,002547,002548,002549,002550,002551,002552,002553,002554,002555,002556,002557,002558,002559,002560,002561,002562,002563,002564,002565,002566,002567,002568,002569,002570,002571,002572,002573,002574,002575,002576,002577,002578,002579,002580,002581,002582,002583,002584,002585,002586,002587,002588,002589,002590,002591,002592,002593,002594,002595,002596,002597,002598,002599,002600,002601,002602,002603,002604,002605,002606,002607,002608,002609,002610,002611,002612,002613,002614,002615,002616,002617,002618,002619,002620,002621,002622,002623,002624,002625,002626,002627,002628,002629,002630,002631,002632,002633,002634,002635,002636,002637,002638,002639,002640,002641,002642,002643,002644,002645,002646,002647,002648,002649,002650,002651,002652,002653,002654,002655,002656,002657,002658,002659,002660,002661,002662,002663,002664,002665,002666,002667,002668,002669,002670,002671,002672,002673,002674,002675,002676,002677,002678,002679,002680,002681,002682,002683,002684,002685,002686,002687,002688,002689,002690,002691,002692,002693,002694,002695,002696,002697,002698,002699,002700,002701,002702,002703,002705,002706,002707,002708,002709,002711,002712,002713,002714,002715,002716,002717,002718,002719,002721,002722,002723,002724,002725,002726,002727,002728,002729,002730,002731,002732,002733,002734,002735,002736,002737,002738,002739,002740,002741,002742,002743,002745,002746,002747,002748,002749,002750,002751,002752,002753,002755,002756,002757,002758,002759,002760,002761,002762,002763,002765,002766,002767,002768,002769,002770,002771,002772,002773,002774,002775,002776,002777,002778,002779,002780,002781,002782,002783,002785,002786,002787,002788,002789,002790,002791,002792,002793,002795,002796,002797,002798,002799,002800,002801,002802,002803,002805,002806,002807,002808,002809,002810,002811,002812,002813,002815,002816,002817,002818,002819,002820,002821,002822,002823,002824,002825,002826,002827,002828,002829,002830,002831,002832,002833,002835,002836,002837,002838,002839,002840,002841,002842,002843,002845,002846,002847,002848,002849,002850,002851,002852,002853,002855,002856,002857,002858,002859,002860,002861,002862,002863,002865,002866,002867,002868,002869,002870,002871,002872,002873,002875,002876,002877,002878,300001,300002,300003,300004,300005,300006,300007,300008,300009,300010,300011,300012,300013,300014,300015,300016,300017,300018,300019,300020,300021,300022,300023,300024,300025,300026,300027,300028,300029,300030,300031,300032,300033,300034,300035,300036,300037,300038,300039,300040,300041,300042,300043,300044,300045,300046,300047,300048,300049,300050,300051,300052,300053,300054,300055,300056,300057,300058,300059,300061,300062,300063,300064,300065,300066,300067,300068,300069,300070,300071,300072,300073,300074,300075,300076,300077,300078,300079,300080,300081,300082,300083,300084,300085,300086,300087,300088,300089,300090,300091,300092,300093,300094,300095,300096,300097,300098,300099,300100,300101,300102,300103,300104,300105,300106,300107,300108,300109,300110,300111,300112,300113,300114,300115,300116,300117,300118,300119,300120,300121,300122,300123,300124,300125,300126,300127,300128,300129,300130,300131,300132,300133,300134,300135,300136,300137,300138,300139,300140,300141,300142,300143,300144,300145,300146,300147,300148,300149,300150,300151,300152,300153,300154,300155,300156,300157,300158,300159,300160,300161,300162,300163,300164,300165,300166,300167,300168,300169,300170,300171,300172,300173,300174,300175,300176,300177,300178,300179,300180,300181,300182,300183,300184,300185,300187,300188,300189,300190,300191,300192,300193,300194,300195,300196,300197,300198,300199,300200,300201,300202,300203,300204,300205,300206,300207,300208,300209,300210,300211,300212,300213,300214,300215,300216,300217,300218,300219,300220,300221,300222,300223,300224,300225,300226,300227,300228,300229,300230,300231,300232,300233,300234,300235,300236,300237,300238,300239,300240,300241,300242,300243,300244,300245,300246,300247,300248,300249,300250,300251,300252,300253,300254,300255,300256,300257,300258,300259,300260,300261,300262,300263,300264,300265,300266,300267,300268,300269,300270,300271,300272,300273,300274,300275,300276,300277,300278,300279,300280,300281,300282,300283,300284,300285,300286,300287,300288,300289,300290,300291,300292,300293,300294,300295,300296,300297,300298,300299,300300,300301,300302,300303,300304,300305,300306,300307,300308,300309,300310,300311,300312,300313,300314,300315,300316,300317,300318,300319,300320,300321,300322,300323,300324,300325,300326,300327,300328,300329,300330,300331,300332,300333,300334,300335,300336,300337,300338,300339,300340,300341,300342,300343,300344,300345,300346,300347,300348,300349,300350,300351,300352,300353,300354,300355,300356,300357,300358,300359,300360,300362,300363,300364,300365,300366,300367,300368,300369,300370,300371,300372,300373,300374,300375,300376,300377,300378,300379,300380,300381,300382,300383,300384,300385,300386,300387,300388,300389,300390,300391,300392,300393,300394,300395,300396,300397,300398,300399,300400,300401,300402,300403,300404,300405,300406,300407,300408,300409,300410,300411,300412,300413,300414,300415,300416,300417,300418,300419,300420,300421,300422,300423,300424,300425,300426,300427,300428,300429,300430,300431,300432,300433,300434,300435,300436,300437,300438,300439,300440,300441,300442,300443,300444,300445,300446,300447,300448,300449,300450,300451,300452,300453,300455,300456,300457,300458,300459,300460,300461,300462,300463,300464,300465,300466,300467,300468,300469,300470,300471,300472,300473,300474,300475,300476,300477,300478,300479,300480,300481,300482,300483,300484,300485,300486,300487,300488,300489,300490,300491,300492,300493,300494,300495,300496,300497,300498,300499,300500,300501,300502,300503,300505,300506,300507,300508,300509,300510,300511,300512,300513,300514,300515,300516,300517,300518,300519,300520,300521,300522,300523,300525,300526,300527,300528,300529,300530,300531,300532,300533,300534,300535,300536,300537,300538,300539,300540,300541,300542,300543,300545,300546,300547,300548,300549,300550,300551,300552,300553,300554,300555,300556,300557,300558,300559,300560,300561,300562,300563,300565,300566,300567,300568,300569,300570,300571,300572,300573,300575,300576,300577,300578,300579,300580,300581,300582,300583,300584,300585,300586,300587,300588,300589,300590,300591,300592,300593,300595,300596,300597,300598,300599,300600,300601,300602,300603,300604,300605,300606,300607,300608,300609,300610,300611,300612,300613,300615,300616,300617,300618,300619,300620,300621,300622,300623,300625,300626,300627,300628,300629,300630,300631,300632,300633,300635,300636,300637,300638,300639,300640,300641,300642,300643,300645,300647,300648,300649,300650,300651,300652,300653,300655,300656,300657,300658,300659,300660,300661,300662,300663

