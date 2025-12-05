package id.ac.polbeng.supriyanto.githubprofile.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ac.polbeng.supriyanto.githubprofile.R
import id.ac.polbeng.supriyanto.githubprofile.databinding.ActivityMainBinding
import id.ac.polbeng.supriyanto.githubprofile.helpers.Config
import id.ac.polbeng.supriyanto.githubprofile.models.GithubUser
import id.ac.polbeng.supriyanto.githubprofile.viewmodels.MainViewModel   // perhatiin import ini

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // --- ambil ViewModel ---
        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        // observe data user
        mainViewModel.githubUser.observe(this) { user ->
            if (user != null) {
                setUserData(user)
            }
        }

        // observe loading state
        mainViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        // load user default pertama kali
        mainViewModel.searchUser(Config.DEFAULT_USER_LOGIN)

        // tombol search
        binding.btnSearchUserLogin.setOnClickListener {
            val userLogin = binding.etSearchUserLogin.text.toString().trim()
            if (userLogin.isNotEmpty()) {
                mainViewModel.searchUser(userLogin)
            }
        }
    }

    private fun setUserData(githubUser: GithubUser) {
        // sementara tampilkan toString dulu
        binding.tvUser.text = githubUser.toString()

        Glide.with(this@MainActivity)
            .load(githubUser.avatarUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.outline_broken_image_24)
            )
            .into(binding.imgUser)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }
}
