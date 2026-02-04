import java.util.stream.Stream

data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}

interface ProgressPrintable{
    val progressText: String
    fun printProgressBar()
}

class Quiz : ProgressPrintable{
    override val progressText: String
        get() = "${answered} of ${total} answered"
    val question1=Question<String>(
        questionText = "Речка спятила с ума-по домам пошла сама ___",
        answer = "водопровод",
        Difficulty.MEDIUM
    )
    val question2=Question<Boolean>(
        questionText = "Небо зеленое. правда или ложь",
        answer = false,
        Difficulty.EASY
    )
    val question3=Question<Int>(
        questionText = "Сколько дней между полнолуниями",
        answer = 28,
        Difficulty.HARD
    )
    companion object StudentProgress {
        val total:Int=10
        var answered: Int=3
    }

    override fun printProgressBar() {
        repeat(Quiz.answered){ print("▓")}
        repeat(Quiz.total - Quiz.answered){ print("▒")}
        println()
        println(progressText)
    }
}


fun main() {
//    println(question1.answer)
//    println(question2.answer)
//    println(question3.answer)
//    println(question1.toString())
//    println("${Quiz.answered} of ${Quiz.total} answered")
//    println(Quiz.progressText)
//    Quiz.printProgressBar()
    Quiz().printProgressBar()
}