package com.mvvm.koin.coroutines.sample.photos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.koin.coroutines.sample.BR
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseFragment
import com.mvvm.koin.coroutines.sample.base.BaseRecyclerViewAdapter
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.databinding.FragmentPhotosBinding
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.livedata.Status
import com.mvvm.koin.coroutines.sample.photos.detail.PhotoDetailDialogFragment
import com.mvvm.koin.coroutines.sample.view.SimpleDividerItemDecorator
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class PhotosFragment : BaseFragment<FragmentPhotosBinding>(), BaseRecyclerViewAdapter.OnItemClickListener {

    companion object {
        const val TAG = "PhotosFragment"
        fun newInstance() = PhotosFragment()
    }

    val viewModel by currentScope.viewModel<PhotosViewModel>(this)

    override fun getLayoutId(): Int = R.layout.fragment_photos
    override fun getVariablesToBind(): Map<Int, Any> = mapOf(
            BR.viewModel to viewModel
    )


    override fun initViewModel() {
        super.initViewModel()
        viewModel.photos.observe(this, onPhotosResponseObserver)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?) {
        super.initView(inflater, container)
        viewModel.getPhotos()
    }

    private val onPhotosResponseObserver = Observer<Event<List<Photo>>> { onPhotosResponse(it) }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPhotosResponse(response: Event<List<Photo>>) {
        when(response.status) {
            Status.SUCCESS -> onPhotosSuccess(response.peekData())
            Status.FAILURE -> onPhotosFailure()
            Status.NETWORK_ERROR -> onNetworkError()
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPhotosSuccess(photos: List<Photo>?) {
        dataBinding.rvPhotos.apply {
            layoutManager = LinearLayoutManager(getViewContext())
            setHasFixedSize(true)
            addItemDecoration(SimpleDividerItemDecorator(getViewContext()))
            adapter = PhotosAdapter(photos, this@PhotosFragment)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onPhotosFailure() {
        showAlert(R.string.error_loading_photos)
    }

    override fun onItemClicked(item: Any?) {
        fragmentManager?.let {
            PhotoDetailDialogFragment.newInstance(item as Photo).show(it, PhotoDetailDialogFragment.TAG)
        }
    }

}