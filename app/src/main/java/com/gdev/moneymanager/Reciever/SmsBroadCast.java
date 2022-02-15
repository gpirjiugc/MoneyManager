package com.gdev.moneymanager.Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.gdev.moneymanager.Activity.Balance_Check;

public class  SmsBroadCast extends BroadcastReceiver {
    private static String SMS = "android.provider.Telephony.SMS_RECEIVED";
    public  static  String code = "";
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(SMS)){
            Bundle  bundle = intent.getExtras();
            Object [] objects = (Object []) bundle.get("pdus");
            SmsMessage[] smsMessage = new SmsMessage[objects.length];
            for(int i = 0;i<objects.length;i++){ smsMessage[i] = SmsMessage.createFromPdu((byte[]) objects[i]); }
            code  = smsMessage[0].getDisplayMessageBody();
            Balance_Check.Companion.show_messsage(code);

        }

    }
}
