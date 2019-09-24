package org.openrndr.extra.gradientdescent

import org.openrndr.math.Vector2
import org.openrndr.math.Vector3
import org.openrndr.math.Vector4

/**
 * converts a model to an array of doubles
 */
fun <T : Any> modelToArray(model: T): DoubleArray {
    val doubles = mutableListOf<Double>()
    model::class.java.declaredFields.forEach {
        when {
            it.type == Double::class.java -> {
                it.trySetAccessible()
                doubles.add(it.getDouble(model))
            }
            it.type == Vector2::class.java -> {
                it.trySetAccessible()
                val v2 = it.get(model) as Vector2
                doubles.add(v2.x)
                doubles.add(v2.y)
            }
            it.type == Vector3::class.java -> {
                it.trySetAccessible()
                val v3 = it.get(model) as Vector3
                doubles.add(v3.x)
                doubles.add(v3.y)
                doubles.add(v3.z)
            }
            it.type == Vector4::class.java -> {
                it.trySetAccessible()
                val v4 = it.get(model) as Vector4
                doubles.add(v4.x)
                doubles.add(v4.y)
                doubles.add(v4.z)
                doubles.add(v4.w)
            }
        }
    }
    return doubles.toDoubleArray()
}

/**
 * converts array of doubles to model values
 */
fun <T : Any> arrayToModel(data: DoubleArray, model: T) {
    var index = 0
    model::class.java.declaredFields.forEach {
        when {
            it.type == Double::class.java -> {
                it.trySetAccessible()
                it.setDouble(model, data[index])
                index++
            }
            it.type == Vector2::class.java -> {
                it.trySetAccessible()
                it.set(model, Vector2(data[index], data[index+1]))
                index+=2
            }
            it.type == Vector3::class.java -> {
                it.trySetAccessible()
                it.set(model, Vector3(data[index], data[index+1],data[index+2]))
                index+=3
            }
            it.type == Vector4::class.java -> {
                it.trySetAccessible()
                it.set(model, Vector4(data[index], data[index+1],data[index+2],data[index+3]))
                index+=3
            }
        }
    }
}