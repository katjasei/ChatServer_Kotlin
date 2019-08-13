import java.time.LocalDateTime
/*
class ChatMessage new message format in chat "date + usersend + message"
 */
class ChatMessage(val usersend:String?, val message: String, val date: LocalDateTime) {

   override fun toString (): String {

        return "at $date $usersend: $message"
    }

}


