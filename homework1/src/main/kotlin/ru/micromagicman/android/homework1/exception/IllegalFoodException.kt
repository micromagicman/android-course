package ru.micromagicman.android.homework1.exception

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.domain.Food

/**
 * Исключение, выбрасываемое при несовпадении еды с рационом питания животного.
 */
class IllegalFoodException(animal: Animal, food: Food) : RuntimeException("$animal не употребляет в пищу $food")