@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.thirdDigit
import kotlin.math.*


/**
 * Пример главной функции
 */
fun main() {
    val a = triangleKind(4.0, 3.0, 2.0)
    println("Оценкyyyyа: $a")
}

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var result: String = ""
    var k1: Int = 0
    var k2: Int = 0
    if (age < 10) k1 = age
    else if (age < 100) {
        k1 = age % 10
        k2 = age
    } else {
        k1 = age % 10
        k2 = age % 100
    }

    if (k1 == 1 && k2 != 11) result = "$age год"

    if (k1 >= 2 && k1 <= 4 && (k2 < 12 || k2 > 14)) result = "$age года"

    if (k1 >= 5 && k1 <= 9 || k1 == 0) result = "$age лет"

    if (k2 >= 11 && k2 <= 14 || k1 == 0) result = "$age лет"


    return result
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = (s1 + s2 + s3) / 2.0
    val t: Double
    if (s <= s1)
        t = s / v1
    else if (s <= s1 + s2)
        t = t1 + (s - s1) / v2
    else
        t = t1 + t2 + (s - s1 - s2) / v3
    return t
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var rook1: Boolean = false
    var rook2: Boolean = false
    val result: Int

    if (kingX == rookX1 || kingY == rookY1)

        rook1 = true

    if (kingX == rookX2 || kingY == rookY2)

        rook2 = true

    if (rook1 && rook2)
        result = 3
    else if (!rook1 && !rook2)
        result = 0
    else if (rook1)
        result = 1
    else result = 2


    return result
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var rook: Boolean = false
    var bishop: Boolean = false
    val result: Int

    if (kingX == rookX || kingY == rookY)

        rook = true

    if (abs(kingX - bishopX) == abs(kingY - bishopY))

        bishop = true

    if (rook && bishop)
        result = 3
    else if (!rook && !bishop)
        result = 0
    else if (rook)
        result = 1
    else result = 2


    return result
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {

    var result: Int = -1
    var q: Double = max(max(a, b), c)

    if (q == c) {
        if (c > (a + b))
            result = -1
        else if (c * c == a * a + b * b)
            result = 1
        else if (c * c > a * a + b * b)
            result = 2
        else if (c * c < a * a + b * b)
            result = 0
    } else if (q == a) {
        if (a > (c + b))
            result = -1
        else if (a * a == c * c + b * b)
            result = 1
        else if (a * a > c * c + b * b)
            result = 2
        else if (a * a < c * c + b * b)
            result = 0

    } else {

        if (b > (a + c))
            result = -1
        else if (b * b == a * a + c * c)
            result = 1
        else if (b * b > a * a + c * c)
            result = 2
        else if (b * b < a * a + c * c)
            result = 0
    }
    return result
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    var result: Int
    if (a < c) {
        if (b < c)
            result = -1
        else if (b <= d)
            result = b - c
        else result = d - c
    } else if (a == c) {
        if (b <= d)
            result = b - c
        else result = d - c
    } else {
        if (d < a)
            result = -1
        else if (b <= d)
            result = b - a
        else result = d - a
    }
    return result
}
