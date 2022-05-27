import java.util.*

val scanner = Scanner(System.`in`)

fun main() {
    while (true) {
        // 자동번호 받기
        val generatedNumber = generateRandomNumbers()

        while (true) {
            // 사용자 입력 받기
            val numbers = getNumbersFromUser()

            // 비교하기 && 결과값에 따라 반복유무 체크
            if (compareNumbers(generatedNumber, numbers))
                break
        }

        // 게임진행 여부 체크
        if (!isCoinInserted())
            break
    }
}

fun generateRandomNumbers(): IntArray {
    val numbers = IntArray(3)
    for (i in 0..2) {
        numbers[i] = (1..9).random()
        print(numbers[i])
    }
    println()
    return numbers
}

fun getNumbersFromUser(): IntArray {
    print("숫자를 입력해주세요: ")
    val userNumbers = IntArray(3)
    val inputNumbers = scanner.next()
    for (i in 0..2)
        userNumbers[i] = inputNumbers[i].digitToInt()
    return userNumbers
}

fun compareNumbers(auto: IntArray, user: IntArray): Boolean {
    var strike = 0
    var ball = 0

    for (i in 0..2) {
        if (auto[i] == user[i])
            strike++
        else if (user.contains(auto[i]))
            ball++
    }
    println("strike: $strike ball: $ball")

    return strike == 3
}

fun isCoinInserted(): Boolean {
    println("숫자를 모두 맞히셨습니다! 게임종료")
    println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요")
    return scanner.nextInt() == 1
}
