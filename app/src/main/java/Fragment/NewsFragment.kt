package Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.Data.GithubService
import com.example.covid_19.Data.apiRequest
import com.example.covid_19.Data.httpClient
import com.example.covid_19.GithubUserAdapter
import com.example.covid_19.GithubUserItem
import com.example.covid_19.R
import com.example.covid_19.Util.dismissLoading
import com.example.covid_19.Util.showLoading
import com.example.covid_19.Util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsFragment : Fragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
        }
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
            //Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_news, container, false)
        }
        override fun onViewCreated(view: View,
        @Nullable savedInstanceState: Bundle?) {

            super.onViewCreated(view,savedInstanceState)
            callApiGetGithubUser()}

        private fun callApiGetGithubUser() {
            showLoading(context!!,swipeRefreshLayout)

            val httpClient = httpClient()
            val apiRequest = apiRequest<GithubService>(httpClient)

            val call = apiRequest.getUsers()
            call.enqueue(object : Callback<List<GithubUserItem>> {

                override fun onFailure(call: Call<List<GithubUserItem>>,t:Throwable)
                {
                    dismissLoading (swipeRefreshLayout) }

                override fun onResponse(call:Call<List<GithubUserItem>>,response:Response<List<GithubUserItem>>) {
                dismissLoading(swipeRefreshLayout)
                    when {response.isSuccessful->

                    when {response.body()?.size !=0->

                        tampilGithubUser(response.body()!!)

                        else -> {
                            tampilToast(context!!,  "Berhasil")
                        }
                    }

                    else -> {
                        tampilToast(context!!, " Gagal")
                    }
                }

            }
            })
        }

        private fun tampilGithubUser(githubUsers:List<GithubUserItem>)
        {listGithubUser.layoutManager = LinearLayoutManager(context)
            listGithubUser.adapter = GithubUserAdapter(context!!,githubUsers) {

            val  githubUser = it
            tampilToast(context!!, githubUser.login)

        }
        }

        override fun onDestroy() {super.onDestroy()
            this.clearFindViewByIdCache()
        }
    }
