import java.io.File
import java.util.concurrent.TimeUnit

fun main(args: Array<String>){
    println("Hello kotlin")
}

class Main {
    fun test1() {
        println("This is a test")
    }
}

fun test2() {
    println("This is a test 2")
}


fun String.runCommand(workingDir: File = File("."), timeoutAmount: Long = 60, timeoutUnit: TimeUnit = TimeUnit.SECONDS): String? = runCatching {
    ProcessBuilder("\\s".toRegex().split(this))
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start().also { it.waitFor(timeoutAmount, timeoutUnit) }
        .inputStream.bufferedReader().readText()
}.onFailure { it.printStackTrace() }.getOrNull()