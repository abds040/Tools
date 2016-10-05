/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abds040.tools;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import static com.ibm.mq.MQEnvironment.properties;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import java.io.IOException;

/**
 *
 * @author di3sdn
 */
public class MQBrowse {

    public static void hexa(int num) {
        int m = 0;
        if( (m = num >>> 4) != 0 ) {
            hexa( m );
        }
        System.out.print((char)((m=num & 0x0F)+(m<10 ? 48 : 55)));
    }
    
    public static void main(String args[]) {
        try {
            
            hexa(191026962);
            /*
            String qManager = "QMIMIQ31";
            String queueName = "TARGET.QUEUE";
            String hostname = "imiq31b";
            int port = 1414;
            String channel = "CL.QMIMIQ31.TEST";

            MQEnvironment.hostname = hostname;
            MQEnvironment.port = port;
            // defaults to 1414 for WebSphereMQ Client connections.
            MQEnvironment.channel = channel;
            // on the queue manager
            
          
            MQQueueManager QMgr = new MQQueueManager(qManager); 

            int openOptions = MQC.MQOO_FAIL_IF_QUIESCING | MQC.MQOO_INPUT_SHARED | MQC.MQOO_BROWSE;

            MQQueue queue = QMgr.accessQueue(queueName, openOptions);

            MQMessage theMessage = new MQMessage();
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_FIRST |MQC.MQGMO_VERSION_3;
            
            gmo.matchOptions = MQC.MQMO_NONE;
            gmo.waitInterval = 5000;

            int cnt = 0;
            
            boolean thereAreMessages = true;
            while (thereAreMessages) {
                try {
                    System.out.println("------------------------------------------------------------------");
                    cnt++;
                    System.out.println("msg "+cnt);
                    //read the message          
                    queue.get(theMessage, gmo);
                    //print the text            
                    String msgText = theMessage.readString(theMessage.getMessageLength());
                    String tmpText = "";
                    int start = 0;
                    int len;
                    while (start < msgText.length()) {
                        tmpText = msgText.substring(start);
                        if (tmpText.length()> 5000) {
                            start=start+5000;
                            tmpText=tmpText.substring(0,5000);
                        } else {
                            start=start+tmpText.length();
                            //tmpText;
                        }
                        System.out.println((start+10000000)+":"+tmpText);
                    }
                    
                    // <--- Solution code Here
                    //move cursor to the next message               
                    gmo.options = MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_NEXT;

                } catch (MQException e) {

                    if (e.reasonCode == e.MQRC_NO_MSG_AVAILABLE) {
                        System.out.println("no more message available or retrived");
                    }

                    thereAreMessages = false;
                } catch (IOException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
