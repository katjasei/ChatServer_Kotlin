/*
ChatHistoryObservable interface, objects use this interface to register as observers and also to remove
themselves from being observers
 */

interface ChatHistoryObservable {

    fun registerObserver(observer:ChatHistoryObserver)

    fun deregisterObserver(observer:ChatHistoryObserver)

    fun notifyObservers (message:ChatMessage)

}