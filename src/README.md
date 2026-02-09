# Лабораторная работа №13-14
Коллекции, обобщения и функциональный стиль в Kotlin
## Описание
Данная лабораторная работа посвящена изучению продвинутых возможностей языкаKotlin,которые активно используются при разработке Android-приложений.
В рамках работы рассматриваются:
- обобщённые типы (Generics);
- коллекции Kotlin;
- функции высшего порядка;
- extension-функции;
  Все примеры ориентированы на практическое применение и подготовку к разработкемобильных приложений.
## Как запустить проект
1. Клонируйте репозиторий:
```bash
git clone <URL_репозитория>
```
## Автор
Черкина д.А
## Лицензия
Проект создан в учебных целях.

# Generics (обобщённые типы) 
написание универсальногокода,работающего с разными типами данных;

**Generics** позволяют:
* описать класс один раз;
* подставлять нужный тип данных при создании объекта;
* избежать дублирования кода;
* повысить читаемость и безопасность типов.
```
class Question<T>(
  val questionText: String,
  val answer: T,
  val difficulty: Difficulty
)
```


# Enum class
**enum class** применяется, когда:
* существует ограниченный набор возможных значений;
* использование любых других значений недопустимо;
* требуется строгий контроль на уровне компилятора.
```
enum class Difficulty {
    EASY,
    MEDIUM,
    HARD
}
```

# Data class
**data class** — это класс, предназначенный для хранения данных

Если класс объявлен как data class, компилятор Kotlin автоматическигенерируетдля него следующие методы:
* equals()
* hashCode() (важен при работе с коллекциями)
* toString()
* componentN() (для деструктуризации)
* copy()

```
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)
```
# Singleton
**Singleton** — это класс, у которого может существовать только одинэкземпляр.
В Kotlin для этого используется специальная конструкция object.
```
object StudentProgress{
  var total: Int=10
  var answered: Int=3
}
```
# Companion object
В Kotlin объекты можно объявлять внутри классов. Если такой объект помечен как **companion**, его свойства доступнынапрямуючерез имя класса.
```
class Quiz {
    val question1 = Question<String>(
        questionText = "Речка спятила с ума – По домам пошла сама. ___",
        answer = "водопровод",
        Difficulty.MEDIUM
    )

    val question2 = Question<Boolean>(
        questionText = "Небо зелёное. Правда или ложь",
        answer = false,
        Difficulty.EASY
    )

    val question3 = Question<Int>(
        questionText = "Сколько дней между полнолуниями?",
        answer = 28,
        Difficulty.HARD
    )

    fun main() {
        println("${StudentProgress.answered} of ${StudentProgress.total} answered.")
    }
}
```
# Extensions
**Extensions** позволяют:
* добавлять новые свойства и функции к уже существующимтипам;
* использовать их через точечную нотацию (.), как будто они быличастьюкласса;
* не изменять исходный код класса.

# Scope-функции
**Scope**v-функции позволяют работать со свойствами и методамиобъекта,неповторяя имя переменной, если это избыточно. 
Это особенно часто используетсявAndroid-разработке и Jetpack Compose.
**Scope-функции** — это функции высшего порядка, которые:
* принимают лямбда-выражение;
* временно меняют контекст (scope) внутри этого блока;
* позволяют обращаться к объекту через it или this.
```
fun printQuiz(){
        question1.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()

        question2.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()

        question3.let {
            println(it.questionText)
            println(it.answer)
            println(it.difficulty)
        }
        println()
    }
```
# Массив
**Массив (Array)** — это простейший способ хранить несколько значенийодноготипа данных. 
* массив состоит из элементов (items);
* элементы упорядочены;
* доступ к элементам осуществляется по индексу.
```
val rockPlanets= arrayOf<String>("Mercury","Venus", "Earth","Mars")
val gasPlanets= arrayOf("Jupiter","Saturn","Uranus","Neptune")
```
# Lists
**List** — это упорядоченная коллекция с изменяемым размером. Чащевсегоонареализована поверх массива, который автоматически увеличиваетсяпринеобходимости.
# List и MutableList
**List** — интерфейс для только чтения упорядоченной коллекции.
**MutableList** — расширяет List и добавляет методы для изменениясписка(add,remove и т.д.).
```
val solarSystem=listOf(
  "Mercury","Venus", "Earth","Mars","Jupiter","Saturn","Uranus","Neptune"
   )
```
Чтобы изменять список, нужно использовать **mutableListOf()**
```
val solarSystem=mutableListOf(
  "Mercury","Venus", "Earth","Mars","Jupiter","Saturn","Uranus","Neptune"
 )
```
#  Sets
**Set** — это коллекция без фиксированного порядка, которая недопускаетдубликатов. 
* элементы уникальны;
* порядок элементов не гарантирован;
* нет индексов.
```
val solarSystem=mutableSetOf(
  "Mercury","Venus", "Earth","Mars","Jupiter","Saturn","Uranus","Neptune"
)
```
# Map collection
**Map** — это коллекция, состоящая из ключей и значений. 
Она называется «картой», потому что каждому уникальномуключусопоставляется значение.
* Ключи уникальны
* Значения могут повторяться
```
val solarSystem= mutableMapOf(
        "Mercury" to  0,
        "Venus" to  0,
        "Earth" to  1,
        "Mars" to  2,
        "Jupiter" to  79,
        "Saturn" to  82,
        "Uranus" to  27,
        "Neptune" to  14
    )
```
# Higher-order functions
**Функции высокого порядка (high order function)** - это функции, которыелибопринимают функцию в качестве параметра, либо возвращают функцию, либоито,идругое.

Функция **forEach()** выполняет переданную в неё функциюдля каждогоэлементаколлекции. По смыслу она похожа на for-цикл или repeat(). Сигнатура метода: forEach(action: (T) -> Unit)
* T — тип элементов коллекции
* лямбда принимает один параметр
* если параметр один, можно использовать стандартное имя it

```
fun main(){
  cookies.forEach {
  println("Пункт меню: ${it.name}")
}
```
Функция **map()** позволяет трансформировать элементыколлекциивновуюколлекцию того же размера.
```
 val fullMenu=cookies.map {
  "${it.name} - $${it.price}"
  }
  println("Полное меню")
  fullMenu.forEach {
    println(it)
  }
```
Функция **filter()** создаёт подмножество коллекции, оставляя толькоэлементы,которые соответствуют условию.
```
val softBakedMenu=cookies.filter {
  it.softBaked
}
  println("Мягко печенье")
  softBakedMenu.forEach {
    println("${it.name} - $${it.price}")
  }
```
Функция **groupBy()** позволяет превратить список в Map, где:
* ключ — результат функции, переданной в groupBy
* значение — список элементов, для которых функция вернула этотключ
```
val groupedMenu=cookies.groupBy { it.softBaked }
   val softBakedMenu=groupedMenu[true]?:emptyList()
   val crunchyMenu=groupedMenu[false]?: emptyList()
   println("Soft cookies:")
    softBakedMenu.forEach {
        println("${it.name} -$${it.price}")
    }
    println("Crunchy cookies:")
    crunchyMenu.forEach {
        println("${it.name} -$${it.price}")
    }
```
Функция **fold()** аккумулирует значения коллекции в одно значение. Полезнодлясуммирования, подсчёта общего количества и т.п.
```
val totalPrice=cookies.fold(0.0){
        total, cookie -> total+cookie.price
    }
    println("Total price: $${totalPrice}")
```
Функция **sortedBy()** сортирует коллекцию по значениюсвойства, указанноговлямбде.
```
val alphabeticalMenu=cookies.sortedBy { it.name }
    println("Менюв алфавитном порядке")
    alphabeticalMenu.forEach {
        println(it.name)
    }
```