package com.jian.kass.oksockettest;

import com.xuhao.didi.core.iocore.interfaces.ISendable;

import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

//类A:
//...定义将要发送的数据结构体...
public class TestSendData implements ISendable {
    private String str = "";

    public TestSendData(String msg) {
       this.str = msg;
    }

    @Override
    public byte[] parse() {
        //根据服务器的解析规则,构建byte数组
        byte[] body = str.getBytes(Charset.defaultCharset());
//        ByteBuffer bb = ByteBuffer.allocate(4 + body.length);
//        bb.order(ByteOrder.BIG_ENDIAN);
//        bb.putInt(body.length);
//        bb.put(body);
//        return bb.array();
        return body;
    }
}