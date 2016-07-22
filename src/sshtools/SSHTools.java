/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sshtools;

import com.jcraft.jsch.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *
 * @author di3sdn
 */
public class SSHTools {

    /**
     * @param args the command line arguments
     */
    
    public static String _hostname = "tasv0404";
    public static String _username = "di3sdn";
    public static String _password = "abdsxx14";
    public static String _command  = "hostname";
    
    public static void main(String[] args) throws Exception {
        
        //do_pgr_java();
        
        //do_cec_sh();
        
        _command = "df";        
        String rc001 = executeSSH(_hostname, _username, _password, _command);
        System.out.println(_hostname+":"+_command);
        System.out.print(rc001);
        System.out.println("--------------------");
        
    }
    
    public static void do_pgr_java() throws Exception {
        _command = "ps -ef | grep java";
        InputStream inputStream = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String hostname = line.trim();
            //String message = "Not Found";
            String response_full = executeSSH(hostname, _username, _password, _command);
            //if (response.toLowerCase().contains("pvhn1")) { message = response+" - Stuttgart"; }
            //if (response.toLowerCase().contains("pvhn2")) { message = response+" - KWH"; }
            StringTokenizer st = new StringTokenizer(response_full,"\n");
            while (st.hasMoreTokens()) {
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
    
}
