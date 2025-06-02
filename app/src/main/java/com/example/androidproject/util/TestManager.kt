package com.example.androidproject.util

import com.example.androidproject.model.Question

object TestManager {
    var questions: List<Question> = listOf()
    private var currentQuestionIndex = 0
    private val scores = mutableMapOf<String, Int>()

    fun reset() {
        currentQuestionIndex = 0
        scores.clear()
    }

    fun getCurrentQuestion(): Question = questions[currentQuestionIndex]

    fun nextQuestion(scoreMap: Map<String, Int>): Boolean {
        for ((character, score) in scoreMap) {
            scores[character] = scores.getOrDefault(character, 0) + score
        }
        currentQuestionIndex++
        return currentQuestionIndex < questions.size
    }

    fun getTopCharacter(): String {
        return scores.maxByOrNull { it.value }?.key ?: "알 수 없음"
    }
}
