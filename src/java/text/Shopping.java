package text;

import java.io.Serializable;

/**
 * @author 张猪是张猪
 */
public class Shopping implements Serializable {
    //支付宝分配给开发者的应用ID
    private String app_id;
    //接口名称
    private String method;
    //请求使用的编码格式
    private String charset;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
