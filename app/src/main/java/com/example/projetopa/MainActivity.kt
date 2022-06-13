package com.example.projetopa

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.projetopa.databinding.ActivityMainBinding
import android.view.View;
import java.util.*


class MainActivity : AppCompatActivity() {

    private val sectors = arrayOf(
        "32 red", "15 black",
        "19 red", "4 black", "21 red", "2 black", "25 red", "17 black", "34 red",
        "6 black", "27 red", "13 black", "36 red", "11 black", "30 red", "8 black",
        "23 red", "10 black", "5 red", "24 black", "16 red", "33 black",
        "1 red", "20 black", "14 red", "31 black", "9 red", "22 black",
        "18 red", "29 black", "7 red", "28 black", "12 red", "35 black",
        "3 red", "26 black", "zero"
    )
    @BindView(R.id.spinBtn)
    var spinBtn: Button? = null
    @BindView(R.id.resultTv)
    var resultTv: TextView? = null
    @BindView(R.id.wheel)
    var wheel: ImageView? = null
    private val RANDOM: Random = Random()
    private var degree = 0
    private var degreeOld = 0
    private val HALF_SECTOR = 360f / 37f / 2f

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        setContentView(R.layout.jogo_roleta);
        ButterKnife.bind(this);

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    @OnClick(R.id.spinBtn)
    fun spin(v: View?) {
        degreeOld = degree % 360
        // calculamos o ângulo aleatório para rotação da nossa roda
        degree = RANDOM.nextInt(360) + 720
        // efeito de rotação no centro da roda
        val rotateAnim = RotateAnimation(
            degreeOld.toFloat(), degree.toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim.duration = 3600
        rotateAnim.fillAfter = true
        rotateAnim.interpolator = DecelerateInterpolator()
        rotateAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // esvaziamos a visualização de texto do resultado quando a animação começa
                resultTv!!.text = ""
            }

            override fun onAnimationEnd(animation: Animation) {
                // exibimos o setor correto apontado pelo triângulo no final da animação de rotação
                resultTv!!.text = getSector(360 - degree % 360)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        // A animação começa
        wheel!!.startAnimation(rotateAnim)
    }

    private fun getSector(degrees: Int): String? {
        var i = 0
        var text: String? = null
        do {
            // start and end of each sector on the wheel
            val start = HALF_SECTOR * (i * 2 + 1)
            val end = HALF_SECTOR * (i * 2 + 3)
            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors[i]
            }
            i++
        } while (text == null && i < sectors.size)
        return text
    }
}