/*
All potential observers need to implement the ChatHistoryObserver interface. This interface has only
one method newMessage() - called when state's change
 */

interface ChatHistoryObserver {

    fun newMessage (message: ChatMessage)

}