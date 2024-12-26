class MyException(message: String) : Exception(message)

fun greeting() {
    println("Добро пожаловать в каталог пользователей!")
}

fun createList(list: Array<String?>) {
    val maxUsers = 10
    var currentUsers = 0

    while (currentUsers < maxUsers) {
        println("Введите имя:")
        val firstName = readLine() ?: ""
        if (firstName.isEmpty()) {
            throw MyException("Имя не может быть пустым.")
        }

        println("Введите фамилию:")
        val lastName = readLine() ?: ""
        if (lastName.isEmpty()) {
            throw MyException("Фамилия не может быть пустой.")
        }

        println("Введите позицию для размещения (0-${maxUsers - 1}):")
        val positionInput = readLine() ?: ""
        val position = positionInput.toIntOrNull()

        if (position == null || position < 0 || position >= maxUsers) {
            throw MyException("Некорректная позиция. Пожалуйста, введите число от 0 до ${maxUsers - 1}.")
        }

        if (list[position] != null) {
            println("Эта позиция уже занята. Пожалуйста, выберите другую позицию.")
            continue
        }

        list[position] = "$firstName $lastName"
        currentUsers++
        println("Пользователь добавлен на позицию $position.")
    }
}

fun viewList(list: Array<String?>) {
    println("Список пользователей:")
    list.forEachIndexed { index, user ->
        println("Позиция $index: ${user ?: "Свободно"}")
    }
}

fun main() {
    val userList = arrayOfNulls<String>(10) // Создаем массив для пользователей
    greeting()

    while (true) {
        println("\nВыберите действие:")
        println("1. Добавить пользователя")
        println("2. Просмотреть список пользователей")
        println("3. Выход")

        when (readLine()) {
            "1" -> {
                try {
                    createList(userList)
                } catch (e: MyException) {
                    println("Ошибка: ${e.message}")
                }
            }

            "2" -> {
                viewList(userList)
            }

            "3" -> {
                println("Выход из программы.")
                return
            }

            else -> {
                println("Некорректный ввод. Пожалуйста, выберите 1, 2 или 3.")
            }
        }
    }
}