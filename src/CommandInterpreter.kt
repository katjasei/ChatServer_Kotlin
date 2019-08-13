import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.net.Socket
import java.time.LocalDateTime

import java.util.*
/*
Implement a command interpreter that recognizes the specified commands in method run()
 */
class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream, client:Socket): ChatHistoryObserver, Runnable
{
    private val client: Socket = client

    private val scanner: Scanner = Scanner(inputStream)
    private val printOut: PrintStream = PrintStream(outputStream)
    var username: String? = null
    var exit = false

    /*observe pattern
     */

    override fun newMessage(message: ChatMessage) {            //show new message to observers

        if(username!=message.usersend && username!=null){

        printOut.println(message.toString())

        }
    }

 override fun run() {

     ChatHistory.registerObserver(this)                     //register as an observer (ChatHistory)

     printOut.println("Welcome to 2019 chat server")
     printOut.println("Please, enter your command")

     var usercommand:String = ""

do {
    while (username == " ") {

        var usercommand = scanner.nextLine()

        if (usercommand.split(" ")[0] == ":user"|| usercommand ==" ") {
            val usercommandList = usercommand.split(" ")

            if (usercommandList.size > 1) {
                val inputName = usercommand.substringAfter(" ")

                 if (Users.addUser(inputName)) {

                     username = inputName

                     printOut.println("Username set to $username ")
                } else {
                    printOut.println("Username $inputName already exists.")
                }
            } else {
                printOut.println("User name not set: no user name specified")
            }
        } else {
            printOut.println("User name not set. Use command :user to set it ")
        }
    }
     usercommand = scanner.nextLine()
     if (usercommand=="") {printOut.println("Give a command")} else {

         val usercommand1 = usercommand.split(" ")[0]

         if (usercommand1.substring(0, 1) == ":") {

             when (usercommand1) {

                 ":users" -> printUserList(Users.getUserList())
                 ":history" -> printChatHistory(ChatHistory.getChatHistory())
                 ":exit" -> close()

                 else -> printOut.println("No such command $usercommand1")
             }
         } else {
             var message = ChatMessage(
                 username, usercommand, LocalDateTime.now()).toString()
             printOut.println(message)
             ChatHistory.insert(ChatMessage(username, usercommand, LocalDateTime.now()))
             ChatHistory.notifyObservers(ChatMessage(username, usercommand, LocalDateTime.now()))
         }
     }
} while (!exit)

 }
    private fun close(){                                               //:exit command
        printOut.println("Goodbye")
        exit = true
        client.close()
        Users.removeUser(username)
        ChatHistory.deregisterObserver(this)
        //ChatHistory.notifyObservers(ChatMessage(username,"Goodbye", LocalDateTime.now()))
    }

    private fun printUserList(userList:List<String?>){                 //:users command
     printOut.println("Users:")
     userList.forEach { printOut.println(it) }
    }

    private fun printChatHistory(chatHistory:List<ChatMessage>){     //:history command
        printOut.println("History:")

        chatHistory.forEach { printOut.println(it.toString()) }
    }

}