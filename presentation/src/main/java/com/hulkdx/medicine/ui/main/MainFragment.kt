package com.hulkdx.medicine.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hulkdx.medicine.R
import com.hulkdx.medicine.ui.base.BaseFragment
import com.hulkdx.medicine.utils.getViewModel
import hulkdx.com.domain.models.MedicineCollection
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import androidx.recyclerview.widget.DividerItemDecoration


/**
 * Created by Mohammad Jafarzadeh Rezvan on 10/11/2018.
 *
 */
class MainFragment : BaseFragment(), SearchView.OnQueryTextListener {

    private lateinit var mAdapter: MedicineAdapter
    private lateinit var mMainViewModel: MainViewModel

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
        adapterSetup()

        recyclerSetup()

        searchViewSetup()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Timber.i("onActivityCreated")
        super.onActivityCreated(savedInstanceState)

        mMainViewModel = getViewModel(mainActivity.mViewModelProviderFactory)
        mMainViewModel.getMedicineLiveData().observe(this, Observer {
            when (it) {
                is MainContract.Loading -> medicineLoading()
                is MainContract.LoadDataSuccess -> medicineLoaded(it.value)
                is MainContract.LoadDataError -> medicineLoadingError(it.value)
            }
        })
        mMainViewModel.loadMedicines()
    }

    //---------------------------------------------------------------
    // Setup Views:
    //---------------------------------------------------------------

    private fun adapterSetup() {
        mAdapter = MedicineAdapter {
            mainActivity.replaceFragment(R.id.fragmentContainer, DetailFragment.newInstance(it))
        }
    }

    private fun recyclerSetup() {
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(activity)

        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        mRecyclerView.addItemDecoration(itemDecor)
    }

    private fun searchViewSetup() {
        mSearchView.setOnQueryTextListener(this)
    }

    //---------------------------------------------------------------
    // SearchView
    //---------------------------------------------------------------

    override fun onQueryTextSubmit(query: String?): Boolean = false

    override fun onQueryTextChange(newText: String?): Boolean {
        mMainViewModel.loadMedicines(newText)
        return false
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
