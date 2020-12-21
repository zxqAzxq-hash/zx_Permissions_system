
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;
import text.RSACoder;
import text.Shopping;

import java.util.Map;
import java.util.TreeMap;

public class test {


    @Test
    public void code(){
        Shopping shopping = new Shopping();
        shopping.setApp_id("123456");
        shopping.setCharset("utf-8");
        shopping.setMethod("user/test");
//        添加虚拟参数
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("app_id",shopping.getApp_id());
        treeMap.put("method",shopping.getMethod());
        treeMap.put("charset",shopping.getCharset());
        String url = "";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (Map.Entry<String,Object> entry:treeMap.entrySet()){
                //url = entry.getKey()+"="+entry.getValue()+"&";
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
        }
            //String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            String s = "[?]";
            url = uriBuilder.toString().replaceAll(s,url);
            Map<String, Object> map = RSACoder.initKey();
//            获取公钥
            byte[] keys = map.get("RSAPublicKey").toString().getBytes();
            System.out.println("公钥加密");
            byte[] bytes = RSACoder.encryptByPublicKey(url.getBytes(), keys);
            System.out.println(bytes);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
