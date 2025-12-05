package id.ac.polbeng.supriyanto.githubprofile.helpers

// Versi simpel, TANPA BuildConfig dulu
object Config {
    // delay splash dalam milidetik
    const val SPLASH_SCREEN_DELAY: Long = 3000L

    // base URL API GitHub
    const val BASE_URL: String = "https://api.github.com/"

    // user default saat pertama app dibuka
    const val DEFAULT_USER_LOGIN: String = "supriyantopnb"

    // kalau mau dipakai nanti buat header Authorization:
    // ganti ghp_xxx dengan tokenmu
    const val PERSONAL_ACCESS_TOKEN: String =
        "token ghp_UDLi62PZ6tpkBTIHQnAtHRlBt7M4uN3nvPPT"
}
