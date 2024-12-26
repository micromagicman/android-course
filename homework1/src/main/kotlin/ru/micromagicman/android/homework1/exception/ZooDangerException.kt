package ru.micromagicman.android.homework1.exception

/**
 * Исключение, выбрасываемое в случае наступления опасности в зоопарке.
 */
class ZooDangerException(message: String) : RuntimeException(message)