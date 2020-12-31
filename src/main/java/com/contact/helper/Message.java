package com.contact.helper;

public class Message {
	
	 String content;
	 String type;
	
		
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Message [content=" + content + ", type=" + type + "]";
	}
	
	

}
