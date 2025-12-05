package id.ac.polbeng.supriyanto.githubprofile.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.polbeng.supriyanto.githubprofile.helpers.Config
import id.ac.polbeng.supriyanto.githubprofile.models.GithubUser
import id.ac.polbeng.supriyanto.githubprofile.services.GithubUserService
import id.ac.polbeng.supriyanto.githubprofile.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    companion object {
        val TAG: String = MainViewModel::class.java.simpleName
    }

    private val _githubUser = MutableLiveData<GithubUser>()
    val githubUser: LiveData<GithubUser> = _githubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // pertama kali app dibuka, langsung cari user default
        searchUser(Config.DEFAULT_USER_LOGIN)
    }

    fun searchUser(query: String) {
        _isLoading.value = true
        Log.d(TAG, "getDataUserProfileFromAPI: start, query=$query")

        val githubUserService: GithubUserService =
            ServiceBuilder.buildService(GithubUserService::class.java)

        // PENTING: HANYA SATU baris ini, pakai token + username
        val requestCall: Call<GithubUser> =
            githubUserService.loginUser(Config.PERSONAL_ACCESS_TOKEN, query)

        Log.d(TAG, "request url = ${requestCall.request().url}")

        requestCall.enqueue(object : Callback<GithubUser> {
            override fun onResponse(
                call: Call<GithubUser>,
                response: Response<GithubUser>
            ) {
                _isLoading.value = false
                Log.d(TAG, "onResponse: code=${response.code()}")

                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    Log.d(TAG, "result = $result")
                    _githubUser.postValue(result)
                } else {
                    Log.e(
                        TAG,
                        "Response gagal, code=${response.code()}, message=${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}", t)
            }
        })
    }
}
