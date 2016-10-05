/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sshtools;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.Stack;
import java.util.StringTokenizer;

import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author di3sdn
 */
public class SSHTools {

    /**
     * @param args the command line arguments
     */
    
    public static String _hostname = "pasv0427";
    public static String _username = "di3sdn";
    public static String _password =                                                                                                                                                            "abdsxx16";
    public static String _command  = "hostname";
    public static Properties p = new Properties();
    
    public static void main(String[] args) throws Exception {
        
        //do_pgr_java();
        
        //sendMail("pizzahost" , "Hab Hunger");
        
        do_check_anwendstop_start();
        
        //do_cec_sh();
        
        /*
        _command = "df";        
        String rc001 = executeSSH(_hostname, _username, _password, _command);
        System.out.println(_hostname+":"+_command);
        System.out.print(rc001);
        System.out.println("--------------------");
        */
    }
    
    public static void do_pgr_java() throws Exception {
        System.out.println("0000");
        _command = "ps -ef | grep java";
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1);
        String line;
        System.out.println("0001");
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println("0010");
            String hostname = line.trim();
            //String message = "Not Found";
            String response_full = executeSSH(hostname, _username, _password, _command);
            //if (response.toLowerCase().contains("pvhn1")) { message = response+" - Stuttgart"; }
            //if (response.toLowerCase().contains("pvhn2")) { message = response+" - KWH"; }
            StringTokenizer st = new StringTokenizer(response_full,"\n");
            while (st.hasMoreTokens()) {
                System.out.println("0020");   
                String response = st.nextToken().trim();
                //System.out.println("Response:"+response);
                String prefix = "xxxx."+response;
                String suffix = "";
                if (response.toLowerCase().contains("/usr/")) {
                    //System.out.println("---2");
                    prefix = response.substring(0,response.toLowerCase().indexOf("/usr/"));
                    suffix = response.substring(response.toLowerCase().indexOf("/usr/")).trim();
                    suffix = suffix.substring(suffix.lastIndexOf(" "));
                    System.out.println(prefix+"----"+suffix);
                }
                System.out.println("0029");   
            }
            //System.out.println(response_full);
            System.out.println("--------------------");
        }
        inputStream.close();
        bufferedReader.close();
        
    }
    
    public static void do_cec_sh() throws Exception {
        _command = "/usr/local/bin/cec.sh";
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String hostname = line.trim();
            String message = "Not Found";
            String response = executeSSH(hostname, _username, _password, _command);
            if (response.toLowerCase().contains("pvhn1")) { message = response+" - Stuttgart"; }
            if (response.toLowerCase().contains("pvhn2")) { message = response+" - KWH"; }
            System.out.println(message);
            System.out.println("--------------------");
        }
        inputStream.close();
        bufferedReader.close();
        
    }
    
    //com.ibm.ws.admin.services.WsServer
    //com.ibm.ws.admin.services.WsServerStop
    public static void do_check_anwendstop_start() throws Exception {
        boolean debug = false;
        _command = "ps -emo etime,pid,args | grep java | grep -v grep";
        long _start = 0l;
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutting down AIX Sessions");
                SSHSessions.closeSessions();
            }
        });
        File f = new File("c:\\daten\\aixupdates_"+System.currentTimeMillis()+".log");
        FileWriter fw = new FileWriter(f);
        
        
        
        while (true) {
            Stack stack = new Stack();
            Stack lpar_ok = new Stack();
            Stack lpar_nok = new Stack();
            Stack lpar_skp = new Stack();
            Stack lpar_sto = new Stack();
            Stack lpar_sta = new Stack();

            String lpars[] = getLPars();
            for (String lpar : lpars) {
                stack.push(lpar);
            }

            int lpar_cnt = 0;
            
            _start = System.currentTimeMillis();
            Enumeration e = stack.elements();
            while (e.hasMoreElements()) {
                String hostname = e.nextElement().toString();
                lpar_cnt++;
                //System.out.println("Starting Server("+lpar_cnt+"):"+hostname);
                String message = "Not Found";
                String response = executeSessionSSH(hostname, _username, _password, _command);
                if (debug) System.out.println("--Response:"+response);
                //fw.write("DEBUG:response\n");
                //fw.flush();
                if (response.contains("Shell-Status: 1")) {
                    lpar_skp.push(hostname);
                    continue;
                }
                
                StringTokenizer st_main = new StringTokenizer(response, "\n");
                int java_cnt = 0;
                boolean ok = true;
                int cnt_ok = 0;
                int cnt_no = 0;

                while (st_main.hasMoreTokens()) {
                    String aResponse = st_main.nextToken();
                    java_cnt++;
                    StringTokenizer st_resp = new StringTokenizer(aResponse.trim()," ");
                    if (debug) System.out.println("A Response:"+aResponse);
                    if (aResponse.contains("Shell-Status: 1")) { 
                        System.err.println("ERROR:"+hostname+"-"+aResponse);
                        continue; 
                    }
                    if (aResponse.contains("ERROR")) { 
                        System.err.println("ERROR:"+hostname+"-"+aResponse);
                        continue; 
                    }
                    String _str = null;
                    String _pid = null;
                    String _cmd = null;
                    try {
                        _str = st_resp.nextToken();
                        _pid = st_resp.nextToken();
                        _cmd = st_resp.nextToken("\n");
                    } catch (Exception ee) {
                        System.err.println("ERROR:Topkens Mismatch:"+hostname+"-"+aResponse);
                        continue;
                    }
                    if (debug) System.out.println("Msg("+java_cnt+"):"+aResponse);
                    if (debug) System.out.println("Msg("+java_cnt+"):  Start:"+_str+"           PID:"+_pid+"        CMD:"+_cmd);
                    //fw.write("DEBUG:  Host:"+hostname+";     PID:"+_pid+";     str:"+_str+";   CMD:"+_cmd+"\n");
                    //fw.flush();
                    if (_cmd.contains("com.ibm.ws.admin.services.WsServerStop")) {
                        String _cmd_as = _cmd.substring(_cmd.indexOf("WsServerStop"));
                        fw.write("Host:"+hostname+";     PID:"+_pid+";     str:"+_str+";   CMD:"+_cmd_as+"\n");
                        fw.flush();
                        long secs = convertStringToSeconds(_str);
                        if (secs > 120l) {
                            if (!p.contains(hostname+_pid)) {
                                sendMail(hostname, "Hostname:"+ hostname+"\nAction has been running for "+_str+" ("+secs+" Seconds)\n\nCommand:\n "+_cmd_as);
                                p.put(hostname+_pid, "lll");
                            }
                        }
                        lpar_sto.push(hostname+"  "+_str+"    secs:"+secs+"  "+_cmd_as);
                        ok=false;
                    }
                    if (_cmd.contains("com.ibm.ws.management.tools.WsServerLauncher")) {
                        String _cmd_as = _cmd.substring(_cmd.indexOf("WsServerLauncher"));
                        fw.write("Host:"+hostname+";     PID:"+_pid+";     str:"+_str+";   CMD:"+_cmd_as+"\n");
                        fw.flush();
                        long secs = convertStringToSeconds(_str);
                        if (secs > 120l) {
                            if (!p.contains(hostname+_pid)) {
                                sendMail(hostname, "Hostname:"+ hostname+"\nAction has been running for "+_str+" ("+secs+" Seconds)\n\nCommand:\n "+_cmd_as);
                                p.put(hostname+_pid, "lll");
                            }
                        }
                        lpar_sta.push(hostname+"  "+_str+"    secs:"+secs+"  "+_cmd_as);
                        ok=false;
                    }
                }
                if (ok) {
                    lpar_ok.push(hostname);
                } else {
                    lpar_nok.push(hostname);
                }
                if (debug) System.out.println("--------------");
                if (debug) System.out.println("Total MSG:"+response);
                if (debug) System.out.println("-----------------------------------------------------------------------------");
            }

            System.out.println(new Date().toString());
            System.out.println("     elapsed Time:"+(   (System.currentTimeMillis()-_start)/1)+" for "+lpar_cnt+" servers");
            System.out.println("Server Not being done:"+lpar_ok.size());
            System.out.println("Server Not being done:"+lpar_ok);
            System.out.println("Server Stops/Starts  :"+lpar_nok.size());
            System.out.println("Server Skipped  :");
            System.out.println(lpar_skp);
            System.out.println("Server Stopping:");
            while (!lpar_sto.isEmpty()) {
                System.out.println(lpar_sto.pop().toString());
            }

            System.out.println("Server Starting:");
            while (!lpar_sta.isEmpty()) {
                System.out.println(lpar_sta.pop().toString());
            }
            System.out.println("-----------------------------------------------------------------------------");
            try { Thread.sleep(1000); } catch (Exception exx) {}
        }
        
    }
    
  
    public static String executeSSH(String hostname, String username, String password, String command) {
        Session session = null;
        try {
            //System.out.println("0000");
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            config.put("PreferredAuthentications", "password");

            //System.out.println("0001");
            JSch jsch=new JSch();
            //System.out.println("0002");
            session=jsch.getSession(username, hostname, 22);
            session.setConfig(config);
            //System.out.println("0003");
            MyUserInfo ui = new MyUserInfo();
            ui.setPassword(password);
            //System.out.println("0004");
            session.setUserInfo(ui);
            //System.out.println("0005");
            session.setPassword(password);
            //System.out.println("0006");
            
            //System.out.println("0007");
            
            session.connect();
            //System.out.println("0008");
            Channel channel = session.openChannel("exec");
            //System.out.println("0009");
            InputStream in = channel.getInputStream();
            //channel.setOutputStream(System.out);
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            
            //System.out.println("0010");
            channel.connect();
            
            
            //System.out.println("0011");
            StringBuffer sb = new StringBuffer();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    } else {
                        sb.append(new String(tmp, 0, i));
                    }
                    //System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    if (channel.getExitStatus()>0) {
                        System.out.println("exit-status: " + channel.getExitStatus());
                        sb.insert(0, "Shell-Status: "+channel.getExitStatus()+"\n");
                    } else {
                    }
                    break;
                }
            }
            //System.out.println("Connected");
            return sb.toString();
        } catch (Exception E) {
            E.printStackTrace();
            return "ERROR:"+E.getMessage();
        } finally{
            try { session.disconnect(); } catch (Exception e) {}
            //System.out.println("Disconnected");
        }
    }
    
    
    public static String executeSessionSSH(String hostname, String username, String password, String command) {
        Session session = null;
        try {
            session = SSHSessions.getSession(hostname, username, password);
            //System.out.println("0008");
            Channel channel = session.openChannel("exec");
            //System.out.println("0009");
            InputStream in = channel.getInputStream();
            //channel.setOutputStream(System.out);
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            
            //System.out.println("0010");
            channel.connect();
            
            
            //System.out.println("0011");
            StringBuffer sb = new StringBuffer();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    } else {
                        sb.append(new String(tmp, 0, i));
                    }
                    //System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    if (channel.getExitStatus()>0) {
                        System.out.println("exit-status: " + channel.getExitStatus());
                        sb.insert(0, "Shell-Status: "+channel.getExitStatus()+"\n");
                    } else {
                    }
                    break;
                }
            }
            //System.out.println("Connected");
            return sb.toString();
        } catch (Exception E) {
            //E.printStackTrace();
            return "ERROR:"+E.getMessage();
        } finally{
            //try { session.disconnect(); } catch (Exception e) {}
            //System.out.println("Disconnected");
        }
    }
    
    public static class MyUserInfo implements UserInfo {
        String password = "";
        @Override
        public String getPassphrase() {
            return null;
        }
        @Override
        public String getPassword() {
            return password;
        }
        public void setPassword(String passwd) {
            password = passwd;
        }
        @Override
        public boolean promptPassphrase(String arg0) {
            return false;
        }
        @Override
        public boolean promptPassword(String arg0) {
            return false;
        }
        @Override
        public boolean promptYesNo(String arg0) {
            return false;
        }
        @Override
        public void showMessage(String arg0) {
        }

    }
    
    
    
    
    private static long convertStringToSeconds(String input) {
        try {
            if (input.length()==5) {
                String _mins = input.substring(0,2);
                String _secs = input.substring(3,5);
                long secs = Long.parseLong(_secs)+(Long.parseLong(_mins)*60);
                //System.out.println("XXX---:"+_mins+":"+_secs);
                return secs;
            }
            return 0l;
        } catch (Exception e) {
            return -1;
        }
    }
    
    private static String[] getLPars() {
        String[] lpars = {
            "pasv9051", "pasv9052", "pasv9143", "pasv9144", "pasv9193", "pasv9194"
        };
        
        String[] lpars_test = {"esbitu05","esbstu05","esbstu06"};
        return lpars;
        // "pasv9242","pasv9246","pasv0940","nasv0010",
    }
    
    private static void sendMail(String hostname, String message) {
        String to[] = {
            //"daniel.schoeman@ww-informatik.de", "modesto.vazquez@ww-informatik.de", "stephan.neumann@ww-informatik.de", "achim.schultze"
            "daniel.schoeman@ww-informatik.de"//, "modesto.vazquez@ww-informatik.de", "stephan.neumann@ww-informatik.de"
        };
        String from = "AnwendstopStartMon";
        String host = "wwsmtp01";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
        try {
            for (String to_item : to ) {
                MimeMessage emailMsg = new MimeMessage(session);
                emailMsg.setFrom(new InternetAddress(from));
                emailMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_item));
                emailMsg.setSubject("AnwendStopStart:"+hostname);
                emailMsg.setText(message);
                Transport.send(emailMsg);
            }
            //System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    
}
