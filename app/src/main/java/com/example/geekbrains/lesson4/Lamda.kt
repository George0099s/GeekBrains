package com.example.geekbrains.lesson4

import com.example.geekbrains.lesson2.AppState

val greetingFun = fun(): String {
    return "Hello"
}

val greetingFun2 =  {

    "Hello"
}

val summ = { a: Int, b: Int -> (a + b).toString()  }

val print2 = {  }

fun print(block: () -> String) {
    println(block)
}

//val inserted: Boolean = run {
//    val person: Person = getPerson()
//    val personDao: PersonDao = getPersonDao()
//    personDao.insert(person)
//}
//fun printAge(person: Person) = person.run {
//    print(age)
//}
//
//val person: Person = getPerson()
//val personDao: PersonDao = getPersonDao()
//val inserted: Boolean = personDao.insert(person)
//fun printAge(person: Person) = {
//    print(person.age)
//}

/*private fun insert(user: User) = SqlBuilder().apply {
    append("INSERT INTO user (email, name, age) VALUES ")
    append("(?", user.email)
    append(",?", user.name)
    append(",?)", user.age)
}.also {
    print("Executing SQL update: $it.")
}.run {
    jdbc.update(this) > 0
}*/

//repository.getNoteById(noteId).observeForever { t ->
//    if (t == null) return@observeForever
//
//    when (t) {
//        is AppState.Success<*> ->
//            viewStateLiveData.value = NoteViewState(note = t.data as? Note)
//        is Error ->
//            viewStateLiveData.value = NoteViewState(error = t.error)
//    }
//}