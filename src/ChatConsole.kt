/* this class "ChatConsole" prints out to System.out all chat messages in the conversation
 */


class ChatConsole:ChatHistoryObserver{

    init{
        ChatHistory.registerObserver(this)             //registers as an observer to chat history
    }

    override fun newMessage(message: ChatMessage) {            // new message in console
            println("at ${message.date} ${message.usersend} : ${message.message}")
    }
}