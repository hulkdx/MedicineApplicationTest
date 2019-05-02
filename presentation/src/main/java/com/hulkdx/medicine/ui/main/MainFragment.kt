package com.hulkdx.medicine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hulkdx.medicine.R
import com.hulkdx.medicine.ui.base.BaseFragment
import com.hulkdx.medicine.utils.getViewModel
import hulkdx.com.domain.models.MedicineCollection
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import androidx.recyclerview.widget.DividerItemDecoration
import hulkdx.com.domain.models.Medicine


/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 *
 */
class MainFragment : BaseFragment() {

    private lateinit var mAdapter: MedicineAdapter

    private val mainActivity: MainActivity
        get() = this.activity as MainActivity

    //---------------------------------------------------------------
    // Lifecycle
    //---------------------------------------------------------------

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = MedicineAdapter{
            Timber.i(it.toString())
        }

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecor)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mMainViewModel = getViewModel<MainViewModel>(mainActivity.mViewModelProviderFactory)
        mMainViewModel.getMedicineLiveData().observe(this, Observer {
            when (it) {
                is MainContract.Loading -> medicineLoading()
                is MainContract.LoadDataSuccess -> medicineLoaded(it.value)
                is MainContract.LoadDataError -> medicineLoadingError(it.value)
            }
        })
        mMainViewModel.loadData()
    }

    //---------------------------------------------------------------
    // Medicine Data
    //---------------------------------------------------------------

    private fun medicineLoading() {
        mProgressBar.visibility = View.VISIBLE
        mErrorTextView.visibility = View.GONE
    }

    private fun medicineLoaded(medicineCollection: MedicineCollection) {
        Timber.i("data loaded")
        mProgressBar.visibility = View.GONE
        mAdapter.setData(medicineCollection)
    }

    private fun medicineLoadingError(throwable: Throwable) {
        Timber.e(throwable, "data loaded error")
        mProgressBar.visibility = View.GONE
        mErrorTextView.visibility = View.VISIBLE
    }
}
