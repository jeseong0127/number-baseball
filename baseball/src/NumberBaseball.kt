import java.util.*

private val scanner = Scanner(System.`in`)

private const val ARRAY_SIZE = 3

fun main() {
    while (true) {
        // 자동번호 받기
        val generatedNumber = generateRandomNumbers()

        while (true) {
            // 사용자 입력 받기
            val numbers = getNumbersFromUser()

            // 비교하기
            val resultNumber = compareNumbers(generatedNumber, numbers)

            // 결과값에 따라 반복유무 체크
            if (countStrikeAndBall(resultNumber))
                break
        }

        // 게임진행 여부 체크
        if (!isCoinInserted())
            break
    }
}

fun generateRandomNumbers(): IntArray {
    val numbers = IntArray(ARRAY_SIZE)
    repeat(ARRAY_SIZE) { index ->
        setNumbers(numbers, index)
    }
    return numbers
}

private fun setNumbers(numbers: IntArray, index: Int) {
    while (true) {
        val number = (1..9).random()
        if (isInsertable(numbers, index, number)) {
            numbers[index] = number
            return
        }
    }
}


private fun isInsertable(numbers: IntArray, index: Int, number: Int): Boolean {
    var isInsertable = true
    for (i in 0 until index) {
        if (numbers[i] == number) {
            isInsertable = false
            break
        }
    }
    return isInsertable
}

fun getNumbersFromUser(): IntArray {
    print("숫자를 입력해주세요: ")
    val userNumbers = IntArray(3)
    val inputNumbers = scanner.next()
    for (i in 0..2)
        userNumbers[i] = inputNumbers[i].digitToInt()
    return userNumbers
}

fun compareNumbers(auto: IntArray, user: IntArray): IntArray {
    val result = IntArray(2)
    for (i in 0..2) {
        if (auto[i] == user[i])
            result[0]++
        else if (user.contains(auto[i]))
            result[1]++
    }
    return result
}

fun countStrikeAndBall(resultBall: IntArray): Boolean {
    var result = false
    println("strike:" + resultBall[0] + " ball: " + resultBall[1])

    if (resultBall[0] == 3)
        result = true

    return result
}

fun isCoinInserted(): Boolean {
    println("숫자를 모두 맞히셨습니다! 게임종료")
    println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요")
    return scanner.nextInt() == 1
}
