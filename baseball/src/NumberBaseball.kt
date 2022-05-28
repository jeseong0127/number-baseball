import java.util.*

private val scanner = Scanner(System.`in`)

private const val ARRAY_SIZE = 3
private const val RESTART_CODE = 1
private const val END_CODE = 2

fun main() {
    while (true) {
        // 자동번호 받기
        val generatedNumbers = generateRandomNumbers()
        for (number in generatedNumbers) {
            print(number)
        }
        println()

        while (true) {
            // 사용자 입력 받기
            val numbers = getNumbersFromUser()

            // 비교하기
            val strikeAndBall = countStrikeAndBall(generatedNumbers, numbers)
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
        val number = (0..9).random()
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
    while (true) {
        print("숫자를 입력해주세요: ")
        val inputNumbers = scanner.nextLine() // 공백입력도 길이에 들어가도록
            // 길이 3인지 체크
            // 숫자인지 아닌지 체크
            // 중복값이 있는지 없는지 체크

            // 입력한 길이가 3이 아닌 경우 || 숫자인지 아닌지
            if (!(checkInputDepth(inputNumbers,ARRAY_SIZE)) || !(isNumbers(inputNumbers))) {
                println("3자리 숫자를 입력해야 합니다.")
                println()
                continue
            }
            // 중복체크
            if(checkOverlap(inputNumbers)){
                println("중복없이 숫자를 입력해야 합니다.")
                println()
                continue
            }
            return convertStringToIntArray(inputNumbers)
    }
}

fun checkOverlap(inputNumbers: String): Boolean {
    var checkOverlap = false
    val userNumbers = convertStringToIntArray(inputNumbers)
    // 만들어진 숫자배열에 중복값을 체크
    repeat(ARRAY_SIZE) { index ->
        // 중복되서 inser 불가능
        if(!(isInsertable(userNumbers, index, userNumbers[index])))
            checkOverlap = true
    }
    return checkOverlap
}

fun checkInputDepth(inputNumbers: String, inputSize: Int): Boolean {
    return inputNumbers.length == inputSize
}

fun isNumbers(inputNumbers: String): Boolean {
    var isNumbers = false
    try {
        val numbers = inputNumbers.toInt()
        if(inputNumbers[0].isDigit() || numbers in 0..999){
            isNumbers = true
        }
    }catch (e:NumberFormatException){
        isNumbers = false
    }
    return isNumbers
}

fun convertStringToIntArray(inputNumbers: String): IntArray {
    val userNumbers = IntArray(ARRAY_SIZE)
    repeat(ARRAY_SIZE) { index ->
        userNumbers[index] = inputNumbers[index].digitToInt()
    }
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
    var isCoin = true
    println("숫자를 모두 맞히셨습니다! 게임종료")
    while (true){
        println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요")
        val inputNumber = scanner.nextLine()
        if (!(checkInputDepth(inputNumber, 1)) || !(isNumbers(inputNumber))){
            println("1 또는 2를 입력해야 합니다.")
            continue
        }
        if (Integer.parseInt(inputNumber) == RESTART_CODE){
            break
        }else if(Integer.parseInt(inputNumber) == END_CODE){
            isCoin = false
            break
        }else{
            println("1 또는 2를 입력해야 합니다.")
        }
    }
    return isCoin
}
