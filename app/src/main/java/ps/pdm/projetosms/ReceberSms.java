package ps.pdm.projetosms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by inalberth on 01/06/15.
 */
public class ReceberSms extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {

        Sms sms = new Sms();

        SmsMessage smsMessage = sms.receberMensagem(intent);

        String numero = smsMessage.getDisplayOriginatingAddress();
        String mensagem = smsMessage.getDisplayMessageBody();

        Toast.makeText(context, "Recebido de: " + numero + " a mensagem: \n" + mensagem, Toast.LENGTH_SHORT).show();

    }
}
