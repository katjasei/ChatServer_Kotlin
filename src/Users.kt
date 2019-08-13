/*
Class Users for all operations with userList (list of all active users) - add user, remove and check if user in userList
 */

object Users {                                                   //implement singleton pattern

    private var usersList:HashSet<String?> = hashSetOf()

    fun addUser (username:String?): Boolean{                      //add new user in userList
        if (!existsAlready(username)){
        usersList.add(username)
        return true}
        else {return false}
    }

    private fun existsAlready(username:String?):Boolean{          // function checked if user in userList
        if (username in usersList){
            return true}
        else {
           return false
        }
    }

    fun removeUser(username: String?){                            //remove user from userList
        if (existsAlready(username)){
            usersList.remove(username)
        }

    }

    fun getUserList(): List<String?> {
        return usersList.toList()
    }

    override fun toString(): String {
        var users = usersList.toList()
        var formatString: String = ""
        for (i in users) {
            formatString += "\n"
        }
        return formatString

    }

}