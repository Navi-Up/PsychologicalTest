package com.example.androidproject.ui.character

import android.content.Intent
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

        // Intent에서 직렬화된 점수 맵 받기
        val scores = intent.getSerializableExtra("scores") as? HashMap<String, Float> ?: hashMapOf()

        if (scores.isEmpty()) {
            binding.tvResultTitle.text = "결과 없음"
            binding.tvResult.text = "테스트 점수를 가져오지 못했습니다."
            binding.ivCharacter.setImageResource(R.drawable.pikachu)
            return
        }

        // 점수가 가장 높은 캐릭터 찾기
        val topCharacter = scores.maxByOrNull { it.value }?.key ?: "알 수 없음"

        val resultTitle = when (topCharacter) {
            "피카츄" -> "피카츄"
            "파이리" -> "파이리"
            "이상해씨" -> "이상해씨"
            "꼬부기" -> "꼬부기"
            "이브이" -> "이브이"
            "리자몽" -> "리자몽"
            "뮤츠" -> "뮤츠"
            "팬텀" -> "팬텀"
            "잠만보" -> "잠만보"
            "루기아" -> "루기아"
            else -> "알 수 없는 캐릭터"
        }

        val resultText = when (topCharacter) {
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

        val imageResId = when (topCharacter) {
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
            else -> R.drawable.pikachu
        }

        // 결과 보여주기
        binding.tvResultTitle.text = resultTitle
        binding.ivCharacter.setImageResource(imageResId)

        // 점수를 퍼센트(%)로 바꾸어 텍스트에 포함 (예: "당신은 활발하고 귀여운 피카츄! (80%)")
        val percentage = (scores[topCharacter] ?: 0f) * 100
        binding.tvResult.text = "$resultText\n\n점수: ${"%.1f".format(percentage)}%"

        // 다시 테스트하기 버튼 클릭 시
        binding.btnRestart.setOnClickListener {
            finish()
        }

        // 결과 공유하기 버튼 클릭 시
        binding.btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "내 결과는 $resultTitle!\n점수: ${"%.1f".format(percentage)}%\n당신도 테스트 해보세요!"
                )
            }
            startActivity(Intent.createChooser(shareIntent, "공유하기"))
        }
    }
}
