package com.actwolab.sweefy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/wschat")
public class SampleServlet
{
	private static ArrayList<Session> sessionList = new ArrayList<Session>();

	@OnOpen
	public void onOpen(Session session)
	{
		try
		{
			sessionList.add(session);
			session.getBasicRemote().sendText("Hello!");
		}
		catch (IOException e)
		{
		}
	}

	@OnError
	public void onError(Session session, Throwable e)
	{
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session session)
	{
		sessionList.remove(session);
	}

	@OnMessage
	public void onMessage(Session session, String message)
	{
		try
		{
			for (Session sess : sessionList)
			{
				sess.getBasicRemote().sendText(message);
			}
		}
		catch (IOException e)
		{
		}
	}
}
