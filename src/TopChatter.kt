/*
Writes to console list of active users including the number of messages sent every time the list changes
 */

object TopChatter:ChatHistoryObserver{

    init {
        ChatHistory.registerObserver(this)              //registers as an observer to ChatHistory
    }

    private val map = mutableMapOf<String?, Int>()

    override fun newMessage(message: ChatMessage) {

    if (map.containsKey(message.usersend)){
        map.compute(message.usersend){_,v -> v!!+1}
    }
        else{map[message.usersend]=1}

       println(map)
    }
}

/*
        var activeUsers = Users.getUserList()
         var numberOfMessages = 0

        for(user in activeUsers){

                if (user == message.usersend)
                    numberOfMessages = +1

            map[user] = numberOfMessages
        }



        println(map)

*/
