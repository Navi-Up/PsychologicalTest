package com.example.androidproject.util

import com.example.androidproject.model.Question

object TestManager {
    var questions: List<Question> = emptyList()
    private var currentIndex = 0
    private val scores = mutableMapOf<String, Int>()

    fun reset() {
        currentIndex = 0
        scores.clear()
    }

    fun getCurrentQuestion(): Question = questions[currentIndex]

    // 점수 누적 후 다음 질문으로
    fun nextQuestion(newScores: Map<String, Int>): Boolean {
        // 점수 합산
        newScores.forEach { (character, score) ->
            scores[character] = (scores[character] ?: 0) + score
        }
        currentIndex++
        return currentIndex < questions.size
    }

    // 정규화된 점수 반환 (0~1)
    fun getNormalizedScores(): Map<String, Float> {
        if (scores.isEmpty()) return emptyMap()
        val maxScore = scores.values.maxOrNull()?.toFloat() ?: 1f
        return scores.mapValues { it.value.toFloat() / maxScore }
    }

    fun getTopCharacter(): String {
        return scores.maxByOrNull { it.value }?.key ?: "알 수 없음"
    }
}


