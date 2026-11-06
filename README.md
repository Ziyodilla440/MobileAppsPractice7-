5 Nov 2026
Practice work
Coroutines introduction: practical questions.
Solve 15 questions in IntelliJ IDEA (Kotlin/JVM, kotlinx-coroutines-core 1.10.2), then the extra ones if time left. Predict the output first, then run it. Usage of AI is not allowed.
Question 1. First coroutine
Context: A cafe app must confirm the order at once, while the coffee itself needs one second to prepare. The confirmation must not wait for the coffee.
Task: Build console app with runBlocking where a launched coroutine waits one second with delay and prints "Coffee ready", while the main body prints "Order accepted" right after the launch. Show the order of lines in the readme and explain why "Order accepted" comes first.
Question 2. Three baristas
Context: Three baristas make drinks at the same time on one thread: espresso needs 300 ms, tea 100 ms and latte 200 ms. A teammate says it is the same if you use a sleep instead of a suspension.
Task: Launch three coroutines, each prints "start", waits with delay and prints "end". Write the output. Then replace delay with Thread.sleep and write the new output. Explain in one sentence what the thread does in each case.
Question 3. Chain of suspend functions
Context: A login flow has three steps: load the user, check the password and open the profile. Each step takes some time and must run strictly after the previous one.
Task: Write three suspend functions where each one calls the next, with a delay and a printed step name in each. Call the first one from a suspend main. Show that suspending does not reorder your lines.
Question 4. Calling suspend from normal code
Context: A teammate wrote a normal function that calls a suspend function to load a user, and the project does not compile.
Task: Reproduce the compile error and write its message in the readme. Fix it in two different ways. Say which fix you never use on the Android UI thread and why.
Question 5. Suspend is not a thread
Context: A junior developer believes that marking a function suspend moves its work to a background thread.
Task: Write a suspend function that returns the name of the current thread. Call it from runBlocking and print the thread name before the call and the returned one. Prove or disprove the belief with your output. Name the hidden parameter that the compiler adds to every suspend function.
Question 6. Ten downloads, one thread
Context: A download manager has ten files, but the app is allowed to use only the main thread. All downloads must make progress without waiting for each other.
Task: Launch ten coroutines under runBlocking. Each prints "download i started" and the thread name, waits and prints "download i finished". Show that all ten start before any finishes. In the readme decide if this is concurrency or parallelism and why.
Question 7. Order of launch
Context: A screen prints a loading message, starts a background step and then continues with its own work. The team lead is surprised that the background step does not run at the moment it is started.
Task: Under runBlocking print "1", launch a coroutine that prints "2", waits 100 ms and prints "3", then print "4", wait 50 ms and print "5". Predict the output before you run it. Explain why "2" does not come right after "1".
Question 8. Parent waits for child
Context: A report task starts a sub task that compresses attachments for 200 ms. The report must be marked completed only after the sub task is finished, even if the report body itself finishes earlier.
Task: Launch a parent coroutine that launches a child coroutine and prints "parent body done". The child waits and prints "child done". In main wait for the parent with join and print "report completed". Explain which rule of structured concurrency gives this order.
Question 9. Screen closed, requests cancelled
Context: A user closes a screen while two network requests are still running. Both requests must stop at once and must print a cleanup message.
Task: Launch a parent coroutine with two child coroutines. Each child waits one second inside a try block and prints a cleanup message in finally. Cancel the parent after 100 ms with cancelAndJoin and print "end". Explain why the children stop although you cancelled only the parent.
Question 10. Profile ready
Context: A profile screen loads avatar (200 ms) and settings (100 ms) at the same time. The message "Profile ready" must appear only after both are loaded.
Task: Use coroutineScope with two launched coroutines. Print "Profile ready" right after the scope. Then launch one more coroutine after the scope and show that "end of main" is printed before it. Explain the difference between the two cases.
Question 11. Reusable counter
Context: Several screens start the same kind of ticking counter, and the team wants one reusable function instead of copying the launch code. The function must return at once and the new coroutine must belong to the caller scope.
Task: Write an extension function on CoroutineScope that starts a counter coroutine, prints name and number every 100 ms up to a given number and returns its Job. Start two counters with different limits inside coroutineScope and print "launched" before the first number. Explain why the receiver is needed.
Question 12. Returns at once or waits
Context: The team needs two helpers for greetings: one that starts a greeter in the background and returns immediately, and another that greets several people and returns only when all greetings are printed.
Task: Implement the first helper as an extension on CoroutineScope and the second as a suspend function with coroutineScope. In main call both and print a line after each call. Show with the output which one returns at once and which one waits.
Question 13. Five builders
Context: A new student mixes up the builders and does not know what each one returns or whether it blocks the thread.
Task: Write a small program that uses launch, async, runBlocking and coroutineScope once each and prints what you get back from each one. In the readme write the cheat sheet for all five builders from the lecture, including withContext: what it returns, does it block the thread, does it wait for children.
Question 14. Job or Deferred
Context: A developer needs the result of a calculation (six times seven), started it with launch and cannot get the value out of it.
Task: Write the launch version and show why it cannot give a result. Then write the async version that returns a Deferred and print 42 with await. In the readme explain the difference between Job and Deferred.
Question 15. Three exports in a scope
Context: A report exports three files at the same time. Export X takes 300 ms, Y takes 100 ms and Z takes 200 ms. The message "All done" must come after all three exports and nothing may be joined by hand.
Task: Under runBlocking print "Start", then use one coroutineScope with three launched coroutines that print their name when finished, then print "All done". The output must be Start, Y, Z, X, All done. No join and no delay in the main body.
