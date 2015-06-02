package ps.pdm.projetosms;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import java.util.Objects;

/**
 * Created by inalberth on 01/06/15.
 */
public class Sms {

    public void sendMessage(Context context, String numero, String mensagem) {

        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        smsManager.sendTextMessage(numero, null, mensagem, pendingIntent, null);
    }

    public SmsMessage receberMensagem(Intent intent) {

        SmsMessage[] msg = getMessagesFromIntent(intent);

        if (msg != null)
            return msg[0]
                    ;
        return null;
    }

    public SmsMessage[] getMessagesFromIntent(Intent intent) {

        Object messages[] = (Object[])(Object[]) intent.getSerializableExtra("pdus");

        byte pduObj[][] = new byte[messages.length][];

        for (int i = 0; i < messages.length; i++) {
            pduObj[i] = (byte[])(byte[]) messages[i];
        }

        byte pdus[][] = new byte[pduObj.length][];

        if (pdus == null) {

            return null;
        }

        int pduCont = pdus.length;

        if (pduCont == 0) {

            return null;
        }

        SmsMessage msgs[] = new SmsMessage[pduCont];

        for (int i = 0; i < pduCont; i++) {

            pdus[i] = pduObj[i];
            msgs[i] = SmsMessage.createFromPdu(pdus[i]);

            String numero = msgs[0].getDisplayOriginatingAddress();
            String mensagem = msgs[0].getDisplayMessageBody();
        }

        return msgs;
    }


}
