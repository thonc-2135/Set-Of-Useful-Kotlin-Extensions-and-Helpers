package com.crazylegend.setofusefulkotlinextensions


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crazylegend.kotlinextensions.context.getCompatColor
import com.crazylegend.kotlinextensions.delegates.activityVM
import com.crazylegend.kotlinextensions.exhaustive
import com.crazylegend.kotlinextensions.log.debug
import com.crazylegend.kotlinextensions.recyclerview.clickListeners.forItemClickListenerDSL
import com.crazylegend.kotlinextensions.retrofit.RetrofitResult
import com.crazylegend.kotlinextensions.runDelayed
import com.crazylegend.kotlinextensions.views.AppRater
import com.crazylegend.kotlinextensions.views.readAttributes
import com.crazylegend.setofusefulkotlinextensions.adapter.TestAdapter
import com.crazylegend.setofusefulkotlinextensions.adapter.TestModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainAbstractActivity : AppCompatActivity(R.layout.activity_main) {

    private val testAVM by activityVM<TestAVM>()
    private val adapter by lazy {
        TestAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppRater.appLaunched(this, supportFragmentManager, 0, 0) {
            appTitle = getString(R.string.app_name)
            buttonsBGColor = getCompatColor(R.color.colorAccent)
        }


        recycler.readAttributes(null, IntArray(0)) {

        }
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        adapter.forItemClickListener = forItemClickListenerDSL { position, item, _ ->
            debug("CLICKED AT ${item.title} at position $position")
        }
        adapter.submitList(listOf(
                TestModel("1", 1, "1", 1),
                TestModel("1", 1, "1", 1),
                TestModel("1", 1, "1", 1),
                TestModel("1", 1, "1", 1),
                TestModel("1", 1, "1", 1)
        ))
        runDelayed(3, TimeUnit.SECONDS) {
            debug("DELAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAYED")
            testAVM.getposts()
        }
        testAVM.posts?.observe(this, Observer {
            it?.apply {
                when (it) {
                    is RetrofitResult.Success -> {
                        adapter.submitList(it.value)
                    }
                    RetrofitResult.Loading -> {
                        debug(it.toString())
                    }
                    RetrofitResult.EmptyData -> {
                        debug(it.toString())
                    }
                    is RetrofitResult.Error -> {
                        debug(it.toString())
                    }
                    is RetrofitResult.ApiError -> {
                        debug(it.toString())
                    }
                }.exhaustive
            }
        })
    }

}


