/*
 * Fuusio API : A Kotlin library for Android frameworks, patterns, and utilities.
 * Copyright (C) 2020 - 2022 Marko Salmela
 *
 * http://fuusio.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package org.fuusio.api.util

class Stack<T>(val capacity: Int = Int.MAX_VALUE) {

    private val items: MutableList<T> = mutableListOf()

    fun isEmpty() = items.isEmpty()

    fun isNotEmpty() = items.isNotEmpty()

    fun count() = items.count()

    fun push(item: T) : Int {

        if (items.count() >= capacity) {
            items.removeAt(0)
        }
        items.add(items.count(), item)
        return items.count()
    }

    fun pop(): T? = if (items.isNotEmpty()) items.removeAt(items.count() - 1) else null

    fun peek(): T? = if (items.isNotEmpty()) items[items.count() - 1] else null

    fun clear() = items.clear()

    override fun toString() = "Stack with ${items.count()} items"
}
