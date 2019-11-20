package com.example.myapplication.constant;

/**
 * 业务接口authority
 */
public class ServerUrl {

    /**
     * 腾讯：http://blog.csdn.net/cupedy/article/details/53261697
     * 获取简要信息http://qt.gtimg.cn/q=s_sz002818
     * 获取实时资金流向http://qt.gtimg.cn/q=ff_sz002818
     * 获取最新行情：http://qt.gtimg.cn/q=sz002818
     * http://qt.gtimg.cn/q=sz002818,sz002246
     */

    //    实时行情
    public static final String domain = "http://phone.xj8.live";
    public static final String login = domain+"/login";
    public static final String send_code = domain+"/send_code";
    public static String channel = domain + "/video/channel";
    public static final String moneyDetail= "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0024462,0028182&sty=CTBFTA&st=z&sr=&p=&ps=&cb=&js=var%20tab_data=({data:[(x)]})&token=70f12f2f4f091e459a279469fe49eca5";
}
