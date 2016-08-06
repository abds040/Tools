/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sshtools;


import com.jcraft.jsch.*;
import java.io.InputStream;
import java.util.HashMap;
/**
 *
 * @author di3sdn
 */
public class SSHSessions {
    
    public static HashMap<String,Session> sessions = new HashMap<String,Session>();
    
    public static Session getSession(String hostname, String username, String password) {
        Session session = null;
        session = sessions.get(hostname);
        if (session!= null &&session.isConnected()) { return session; }
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
            SSHTools.MyUserInfo ui = new SSHTools.MyUserInfo();
            ui.setPassword(password);
            //System.out.println("0004");
            session.setUserInfo(ui);
            //System.out.println("0005");
            session.setPassword(password);
            //System.out.println("0006");
            
            //System.out.println("0007");
            
            session.connect(2000);
            sessions.put(hostname, session);
            return session;
        } catch (Exception e) {
            return null;
        } finally {
            
        }
    }
    public static void closeSessions() {
        for (Session session : sessions.values()) {
            try { 
                System.out.println("Shutting down AIX Session:"+session.getHost());
                session.disconnect();
            } catch (Exception e) {}
        }
    }
    
}
