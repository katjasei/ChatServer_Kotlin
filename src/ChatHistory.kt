/*
All messages that users write to each other (all messages history in this session)
 */


object ChatHistory : ChatHistoryObservable {                         //implement singleton pattern

    /*
     observer pattern : ChatHistory - observable - has methods registerObserver, deregisterObserver and
     notifyObservers
     */

    private val observers: MutableList<ChatHistoryObserver> = mutableListOf()

    override fun registerObserver(observer:ChatHistoryObserver){
          observers.add(observer)
    }

    override fun deregisterObserver(observer:ChatHistoryObserver){
        observers.remove(observer)
    }

    override fun notifyObservers (message:ChatMessage){
        observers.forEach { it.newMessage(message) }
    }

    /* functions for messages in ChatHistory
     */

    private val messageInHistory: MutableList<ChatMessage> = mutableListOf()

    fun insert(message: ChatMessage) {
        messageInHistory.add(message)
    }

    fun getChatHistory():List<ChatMessage>{
        return messageInHistory.toList()
    }
/*
    override fun toString(): String {

        var messages = messageInHistory.toList()
        var formatString: String = ""
        for (i in messages) {
            formatString += "\n"
        }
        return formatString

    }*/

}