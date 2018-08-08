/**
 * this is a thread between Server and Client
 */
package tools;

import java.util.*;

import org.json.simple.JSONObject;

import model.*;

import java.net.*;
import java.io.*;

public class ServerConClientThread extends Thread {

	Socket s;

	public ServerConClientThread(Socket s) {
		this.s = s;
	}

	public void notifyOther(String iam) {
		// get all online user threads
		HashMap hm = ManageServerConClientThread.hm;
		@SuppressWarnings("rawtypes")
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			JSONObject m = new JSONObject();
			m.put("connection", iam);
			m.put("mesType", MessageType.message_ret_onLineFriend);
			
			
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageServerConClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.put("getter", onLineUserId);
				
				System.out.println("ServerConClientThread" +m.toString());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		/*
		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);

			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageServerConClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		*/
	}

	public void run() {

		while (true) {

			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				JSONObject m = (JSONObject) ois.readObject();
				
				if (m.get("mesType").equals(MessageType.message_comm_mes)) {
					// get thread from getter
					ServerConClientThread sc = ManageServerConClientThread.getClientThread(m.get("getter").toString());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if (m.get("mesType").equals(MessageType.message_ret_onLineFriend)) {
					String res = ManageServerConClientThread.getAllOnLineUserid();
					
					JSONObject m2 = new JSONObject();
					
					m2.put("mesType", MessageType.message_comm_mes);
					m2.put("connection", res);
					m2.put("getter", m.get("sender"));
				
					
					
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}

				/*
				 
				 Message m = (Message) ois.readObject();
				// check the message from a client, then make response
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// get thread from getter
					ServerConClientThread sc = ManageServerConClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesType().equals(MessageType.message_get_onLineFriend)) {
					String res = ManageServerConClientThread.getAllOnLineUserid();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				*/

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}