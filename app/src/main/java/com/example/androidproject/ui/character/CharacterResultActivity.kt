package com.example.androidproject.ui.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.databinding.ActivityCharacterResultBinding
import com.example.androidproject.R

class CharacterResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val character = intent.getStringExtra("character")

        val resultText = when (character) {
            "피카츄" -> "당신은 활발하고 귀여운 피카츄!"
            "파이리" -> "열정적이고 뜨거운 파이리!"
            "이상해씨" -> "따뜻하고 온화한 이상해씨!"
            "꼬부기" -> "장난기 많고 귀여운 꼬부기!"
            "이브이" -> "변화무쌍한 매력의 이브이!"
            "리자몽" -> "강하고 카리스마 있는 리자몽!"
            "뮤츠" -> "지적인 천재 뮤츠!"
            "팬텀" -> "미스터리한 분위기의 팬텀!"
            "잠만보" -> "느긋하고 평화로운 잠만보!"
            "루기아" -> "위엄과 평화를 상징하는 루기아!"
            else -> "알 수 없는 캐릭터입니다."
        }

        val imageResId = when (character) {
            "피카츄" -> R.drawable.pikachu
            "파이리" -> R.drawable.charmander
            "이상해씨" -> R.drawable.bulbasaur
            "꼬부기" -> R.drawable.squirtle
            "이브이" -> R.drawable.eevee
            "리자몽" -> R.drawable.charizard
            "뮤츠" -> R.drawable.mewtwo
            "팬텀" -> R.drawable.gengar
            "잠만보" -> R.drawable.snorlax
            "루기아" -> R.drawable.lugia
            else -> R.drawable.pikachu // 기본 이미지
        }

        binding.ivCharacter.setImageResource(imageResId)
        binding.tvResult.text = resultText

        // 다시 테스트하기 버튼 클릭 시
        binding.btnRestart.setOnClickListener {
            finish() // 현재 액티비티 닫고 이전 화면(테스트 화면)으로 돌아감
        }
    }
}
