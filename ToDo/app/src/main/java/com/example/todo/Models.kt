package com.example.todo

data class Todo(var task: String, var done: Boolean) {
    override fun toString(): String {
        return this.task
    }
}