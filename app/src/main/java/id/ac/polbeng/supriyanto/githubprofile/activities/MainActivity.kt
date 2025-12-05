package id.ac.polbeng.supriyanto.githubprofile.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ac.polbeng.supriyanto.githubprofile.R
import id.ac.polbeng.supriyanto.githubprofile.databinding.ActivityMainBinding
import id.ac.polbeng.supriyanto.githubprofile.models.GithubUser
import id.ac.polbeng.supriyanto.githubprofile.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // observe data user
        mainViewModel.githubUser.observe(this) { user ->
            if (user != null) setUserData(user)
        }

        // observe loading
        mainViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        // tombol search
        binding.btnSearchUserLogin.setOnClickListener {
            val username = binding.etSearchUserLogin.text.toString().trim()
            if (username.isNotEmpty()) {
                mainViewModel.searchUser(username)
            } else {
                binding.etSearchUserLogin.error = "Username tidak boleh kosong"
            }
        }
    }

    private fun setUserData(githubUser: GithubUser) {
        // Sementara tampilkan semua data jadi String
        binding.tvUser.text = githubUser.toString()

        Glide.with(this)
            .load(githubUser.avatarUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.baseline_image_24)
                    .error(R.drawable.outline_broken_image_24)
            )
            .into(binding.imgUser)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
