package com.kuake.fix_client.msg;

import quickfix.FieldNotFound;
import quickfix.Message;
import quickfix.field.MsgType;

/**
 * @author liuzihao.
 * 2024/11/26 11:10
 **/
public class CustomMessage extends Message {

    public static final String MSGTYPE = "C1";
    private static final long serialVersionUID = 1L;

    public CustomMessage() {
        getHeader().setField(new MsgType(MSGTYPE));
    }

    public void setName(String name) throws FieldNotFound {
        setString(199, name);
    }

    public String getName() throws FieldNotFound {
        return getString(199);
    }
}
