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
            val strikeAndBall = countStrikeAndBall(generatedNumber, numbers)
            println("strike:" + strikeAndBall[BaseBall.STRIKE.ordinal] + " ball: " + strikeAndBall[BaseBall.BALL.ordinal])

            // 결과값에 따라 반복유무 체크
            if (strikeAndBall[BaseBall.STRIKE.ordinal] == ARRAY_SIZE)
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

fun countStrikeAndBall(generatedNumbers: IntArray, numbers: IntArray): IntArray {
    val strikeAndBall = IntArray(2)

    repeat(ARRAY_SIZE) { index ->
        if (generatedNumbers[index] == numbers[index])
            strikeAndBall[BaseBall.STRIKE.ordinal]++
        else if (numbers.contains(generatedNumbers[index]))
            strikeAndBall[BaseBall.BALL.ordinal]++
    }

    return strikeAndBall
}

fun isCoinInserted(): Boolean {
    println("숫자를 모두 맞히셨습니다! 게임종료")
    println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요")
    return scanner.nextInt() == 1
}
