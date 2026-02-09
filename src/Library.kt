class Library <T>{
    val item=mutableListOf<T>()
}
data class Book(
    val title: String,
    val author: String,
    val year: Int,
    val isbn: String
)
data class Magazine(
    val title: String,
    val issue: Int,
    val month: String
)
data class DVD(
    val title: String,
    val director: String,
    val duration: Int
)
sealed class LibraryItem{
    data class BookItem(val book: Book):LibraryItem()
    data class MagazineItem(val magazine: Magazine):LibraryItem()
    data class DVDItem(val dvd: DVD):LibraryItem()

}
fun filterByYear(library: Library<LibraryItem>, year: Int): List<Book>{
    return library.item
        .filter { it is LibraryItem.BookItem }
        .map { (it as LibraryItem.BookItem).book }
        .filter { it.year==year }
}
fun sortByTitle(library: Library<LibraryItem>): List<LibraryItem>{
    return library.item.sortedBy { item ->
        when (item) {
            is LibraryItem.BookItem -> item.book.title
            is LibraryItem.MagazineItem -> item.magazine.title
            is LibraryItem.DVDItem -> item.dvd.title
            else -> ""
        }
    }
}
fun groupByAuthor(library: Library<LibraryItem>): Map<String, List<Book>>{
    return library.item
        .filter { it is LibraryItem.BookItem }
        .map { (it as LibraryItem.BookItem).book }
        .groupBy { it.author }
}
fun calculateTotalDuration(library: Library<LibraryItem>): Int{
    return library.item
        .filter { it is LibraryItem.DVDItem }
        .map { (it as LibraryItem.DVDItem).dvd.duration }
        .sum()
}
fun main(){
    val library=Library<LibraryItem>()
    library.item.add(LibraryItem.BookItem(Book("Мизери", "Кинг", 1987,"978-5-17-136550-9")))
    library.item.add(LibraryItem.MagazineItem(Magazine("Мурзилка", 17, "Декабрь")))
    library.item.add(LibraryItem.DVDItem(DVD("Один дома","Крис Коламбус",103)))
    library.item.add(LibraryItem.BookItem(Book("A Sinistra", "Пелевин", 2025,"978-5-04-225988-3")))
    library.item.add(LibraryItem.DVDItem(DVD("Назад в будущее","Роберт Земекис",116)))
    println("По названию ${sortByTitle(library)}")
    println("Книги 2025 года ${filterByYear(library,2025)}")
    println("По авторам ${groupByAuthor(library)}")
    println("Длительность всех фильмов ${calculateTotalDuration(library)}")
}


