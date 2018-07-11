package benbi.util;

public class HttpRequesterTest {
    public static void testPut(String msg) {
        //百度天气的api
        //String url1 = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH";
        long startTime = System.currentTimeMillis();
        msg = HttpRequester.msgFormalize(msg);
        String url1 = "http://localhost:10009/api/example/create-iou?iouValue=1&iouMsg="+msg+"&partyName=O=PartyB,L=New%20York,C=US";
        String result1 = HttpRequester.sendPut(url1);
        long alphaGenTime = System.currentTimeMillis() - startTime;
//            System.out.println(result1);
        System.out.println("time: " + alphaGenTime +"ms");
        //输出{"param":"你好世界"}
    }
    public static void testGet() {
        //百度天气的api
        //String url1 = "http://api.map.baidu.com/telematics/v3/weather?location=%E5%8C%97%E4%BA%AC&output=json&ak=W69oaDTCfuGwzNwmtVvgWfGH";
        long startTime = System.currentTimeMillis();
        String url1 = "http://localhost:10009/api/example/my-ious";
        String result1 = HttpRequester.sendGet(url1);
        long alphaGenTime = System.currentTimeMillis() - startTime;
        System.out.println(result1);
        System.out.println("time: " + alphaGenTime);
        //输出{"param":"你好世界"}
    }
   /* public static void main(String[] args){
        testPut();
        testGet();
    }*/
}
