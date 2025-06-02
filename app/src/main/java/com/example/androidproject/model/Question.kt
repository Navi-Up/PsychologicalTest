package com.example.androidproject.model

data class Question(
    val questionText: String,
    val options: List<Option>
)

data class Option(
    val text: String,
    val scores: Map<String, Int> // 각 캐릭터에게 점수 부여
)

