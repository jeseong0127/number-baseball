import java.util.*

private val scanner = Scanner(System.`in`)

fun main() {
    while (true){
        print("숫자를 입력해주세요: ")
        val inputNumbers = scanner.next()
        if (inputNumbers.length < 3){
            break
        }
    }
}