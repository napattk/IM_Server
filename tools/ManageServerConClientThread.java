package tools;

import java.util.*;
public class ManageServerConClientThread {

	public static HashMap hm=new HashMap<String, ServerConClientThread>();
	
	//add
	public static  void addClientThread(String uid,ServerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static ServerConClientThread getClientThread(String uid)
	{
		return (ServerConClientThread)hm.get(uid);
	}
	
	//get all online user id
	public static String getAllOnLineUserid()
	{
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}