import kotlinx.coroutines.*

// Question 3
suspend fun loadUser() {
    delay(100L)
    println("loadUser")
    checkPassword()
}

suspend fun checkPassword() {
    delay(100L)
    println("checkPassword")
    openProfile()
}

suspend fun openProfile() {
    delay(100L)
    println("openProfile")
}

// Question 4
suspend fun fetchUser(): String {
    delay(100L)
    return "User"
}

suspend fun loadUserFix1() {
    println(fetchUser())
}

fun loadUserFix2() = runBlocking {
    println(fetchUser())
}

// Question 5
suspend fun getThreadName(): String {
    delay(50L)
    return Thread.currentThread().name
}

// Question 11
fun CoroutineScope.startCounter(name: String, limit: Int): Job = launch {
    for (i in 1..limit) {
        delay(100L)
        println("$name $i")
    }
}

// Question 12
fun CoroutineScope.greetAsync(name: String): Job = launch {
    delay(100L)
    println("Hello $name")
}

suspend fun greetWait(names: List<String>) = coroutineScope {
    for (name in names) {
        launch {
            delay(100L)
            println("Hello $name")
        }
    }
}

// Question 1
fun question1() = runBlocking {
    println("Question 1")
    launch {
        delay(1000L)
        println("Coffee ready")
    }
    println("Order accepted")
}

// Question 2
fun question2() = runBlocking {
    println("Question 2")
    coroutineScope {
        launch {
            println("espresso start")
            delay(300L)
            println("espresso end")
        }
        launch {
            println("tea start")
            delay(100L)
            println("tea end")
        }
        launch {
            println("latte start")
            delay(200L)
            println("latte end")
        }
    }
    coroutineScope {
        launch {
            println("espresso start")
            Thread.sleep(300L)
            println("espresso end")
        }
        launch {
            println("tea start")
            Thread.sleep(100L)
            println("tea end")
        }
        launch {
            println("latte start")
            Thread.sleep(200L)
            println("latte end")
        }
    }
}

// Question 3
suspend fun question3() {
    println("Question 3")
    loadUser()
}

// Question 4
fun question4() {
    println("Question 4")
    loadUserFix2()
    runBlocking {
        loadUserFix1()
    }
}

// Question 5
fun question5() = runBlocking {
    println("Question 5")
    println(Thread.currentThread().name)
    println(getThreadName())
}

// Question 6
fun question6() = runBlocking {
    println("Question 6")
    for (i in 1..10) {
        launch {
            println("download $i started ${Thread.currentThread().name}")
            delay(200L)
            println("download $i finished")
        }
    }
}

// Question 7
fun question7() = runBlocking {
    println("Question 7")
    println("1")
    launch {
        println("2")
        delay(100L)
        println("3")
    }
    println("4")
    delay(50L)
    println("5")
}

// Question 8
fun question8() = runBlocking {
    println("Question 8")
    val parent = launch {
        launch {
            delay(200L)
            println("child done")
        }
        println("parent body done")
    }
    parent.join()
    println("report completed")
}

// Question 9
fun question9() = runBlocking {
    println("Question 9")
    val parent = launch {
        launch {
            try {
                delay(1000L)
            } finally {
                println("cleanup 1")
            }
        }
        launch {
            try {
                delay(1000L)
            } finally {
                println("cleanup 2")
            }
        }
    }
    delay(100L)
    parent.cancelAndJoin()
    println("end")
}

// Question 10
fun question10() = runBlocking {
    println("Question 10")
    coroutineScope {
        launch {
            delay(200L)
            println("avatar")
        }
        launch {
            delay(100L)
            println("settings")
        }
    }
    println("Profile ready")
    launch {
        delay(100L)
        println("after scope")
    }
    println("end of main")
}

// Question 11
fun question11() = runBlocking {
    println("Question 11")
    coroutineScope {
        startCounter("A", 3)
        startCounter("B", 2)
        println("launched")
    }
}

// Question 12
fun question12() = runBlocking {
    println("Question 12")
    greetAsync("Bob")
    println("after first")
    greetWait(listOf("Alice", "Charlie"))
    println("after second")
}

// Question 13
fun question13() {
    println("Question 13")
    val rb = runBlocking {
        val job = launch { delay(50L) }
        println(job)
        val def = async {
            delay(50L)
            42
        }
        println(def)
        val cs = coroutineScope {
            delay(50L)
            "scope"
        }
        println(cs)
        val wc = withContext(Dispatchers.Default) {
            "context"
        }
        println(wc)
        "blocking"
    }
    println(rb)
}

// Question 14
fun question14() = runBlocking {
    println("Question 14")
    val job = launch {
        val res = 6 * 7
    }
    job.join()
    println(job)

    val deferred = async {
        6 * 7
    }
    println(deferred.await())
}

// Question 15
fun question15() = runBlocking {
    println("Question 15")
    println("Start")
    coroutineScope {
        launch {
            delay(300L)
            println("X")
        }
        launch {
            delay(100L)
            println("Y")
        }
        launch {
            delay(200L)
            println("Z")
        }
    }
    println("All done")
}

suspend fun main() {
    question1()
    question2()
    question3()
    question4()
    question5()
    question6()
    question7()
    question8()
    question9()
    question10()
    question11()
    question12()
    question13()
    question14()
    question15()
}
