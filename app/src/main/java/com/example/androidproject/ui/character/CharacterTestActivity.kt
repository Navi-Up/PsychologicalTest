package com.example.androidproject.ui.character

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.databinding.ActivityCharacterTestBinding
import com.example.androidproject.model.Option
import com.example.androidproject.model.Question
import com.example.androidproject.util.TestManager

class CharacterTestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 질문 및 캐릭터 설정 (2지선다, 10문항, 10캐릭터)
        TestManager.questions = listOf(
            Question("친구와 놀 때 나는?", listOf(
                Option("리더 역할을 한다", mapOf("리자몽" to 3, "뮤츠" to 2)),
                Option("따라가는 편이다", mapOf("푸린" to 2, "야도란" to 2))
            )),
            Question("가장 좋아하는 날씨는?", listOf(
                Option("화창한 날", mapOf("피카츄" to 3, "이브이" to 1)),
                Option("비 오는 날", mapOf("야도란" to 3, "팬텀" to 1))
            )),
            Question("시험 전날 나는?", listOf(
                Option("계획적으로 공부한다", mapOf("뮤츠" to 3, "꼬부기" to 1)),
                Option("무계획으로 벼락치기", mapOf("파이리" to 2, "피카츄" to 1))
            )),
            Question("사람 많은 곳에서 나는?", listOf(
                Option("중심에서 논다", mapOf("리자몽" to 2, "피카츄" to 2)),
                Option("조용히 있는 편이다", mapOf("야도란" to 2, "이상해씨" to 1))
            )),
            Question("여행 스타일은?", listOf(
                Option("계획 짜서 움직임", mapOf("뮤츠" to 2, "꼬부기" to 2)),
                Option("즉흥적 모험", mapOf("파이리" to 3, "팬텀" to 1))
            )),
            Question("선호하는 음식은?", listOf(
                Option("달콤한 디저트", mapOf("푸린" to 3, "피카츄" to 1)),
                Option("매운 음식", mapOf("파이리" to 3, "리자몽" to 2))
            )),
            Question("혼자 있는 시간이 많을 때?", listOf(
                Option("오히려 편하다", mapOf("야도란" to 3, "뮤츠" to 2)),
                Option("심심하고 외롭다", mapOf("피카츄" to 2, "푸린" to 1))
            )),
            Question("갑자기 문제가 생기면?", listOf(
                Option("침착하게 해결", mapOf("꼬부기" to 3, "뮤츠" to 1)),
                Option("감정적으로 반응", mapOf("파이리" to 2, "팬텀" to 2))
            )),
            Question("내 방 스타일은?", listOf(
                Option("깔끔하고 정리정돈", mapOf("이상해씨" to 3, "뮤츠" to 1)),
                Option("자유로운 스타일", mapOf("이브이" to 2, "피카츄" to 1))
            )),
            Question("무서운 영화는?", listOf(
                Option("재밌어서 좋아함", mapOf("팬텀" to 3, "파이리" to 1)),
                Option("무서워서 안 봄", mapOf("푸린" to 2, "이상해씨" to 1))
            ))
        )

        TestManager.reset()
        showQuestion()
    }

    private fun showQuestion() {
        val question = TestManager.getCurrentQuestion()
        binding.tvQuestion.text = question.questionText

        val optionButtons = listOf(binding.btnOption1, binding.btnOption2, binding.btnOption3)
        optionButtons.forEach { it.visibility = View.GONE }

        question.options.forEachIndexed { index, option ->
            optionButtons[index].apply {
                visibility = View.VISIBLE
                text = option.text
                setOnClickListener {
                    goNext(option.scores)
                }
            }
        }
    }

    private fun goNext(scores: Map<String, Int>) {
        if (TestManager.nextQuestion(scores)) {
            showQuestion()
        } else {
            val topCharacter = TestManager.getTopCharacter()
            val intent = Intent(this, CharacterResultActivity::class.java)
            intent.putExtra("character", topCharacter)
            startActivity(intent)
            finish()
        }
    }
}
