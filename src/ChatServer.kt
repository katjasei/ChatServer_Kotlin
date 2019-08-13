import java.lang.Exception
import java.net.ServerSocket
/*
Listens to incoming connection requests using accept() method
Starts a new CommandInterpreter thread for each connection
*/

class ChatServer {

    fun serve() {

        ChatConsole()                                                // prints all chat messages in console
        TopChatter                                                   //prints user + number of message, that user send
   try {

       val ss = ServerSocket(51851)                                  //in this case port is 51851
       println("We have port" + ss.localPort)

       while (true) {
           println("Step 1")
           val s = ss.accept()
           println("Step 2")
           println("new connection " + ss.inetAddress.hostAddress + " " + s.port)
           Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream(), client = s)).start()
       }

   } catch (error :Exception){ println ("Error  $error")  }

    }

}