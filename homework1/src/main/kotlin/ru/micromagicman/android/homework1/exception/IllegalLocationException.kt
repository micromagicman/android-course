package ru.micromagicman.android.homework1.exception

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.domain.Location

/**
 * Исключение, выбрасываемое при несовпадении локации с ареалом обитания животного.
 */
class IllegalLocationException(animal: Animal, location: Location) :
    RuntimeException("$animal не хочет гулять в локации $location")