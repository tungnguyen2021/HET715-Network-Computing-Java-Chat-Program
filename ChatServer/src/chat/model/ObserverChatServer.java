package chat.model;

public interface ObserverChatServer {
	public void updateChatUserList_add(ChatUser chatUser);
	public void updateChatUserList_remove(ChatUser chatUser);
	public void updateReceiveMessage(String message, ChatUser theChatUser);
}