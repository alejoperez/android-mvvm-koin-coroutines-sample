package com.mvvm.koin.coroutines.sample.photos.detail

import android.annotation.SuppressLint
import com.mvvm.koin.coroutines.sample.base.BaseDialogFragment
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.mvvm.koin.coroutines.sample.BR
import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.databinding.DialogPhotoDetailBinding
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.viewModel

class PhotoDetailDialogFragment : BaseDialogFragment<DialogPhotoDetailBinding>() {

    val viewModel by currentScope.viewModel<PhotoDetailViewModel>(this)

    override fun getLayoutId(): Int = R.layout.dialog_photo_detail

    override fun getVariablesToBind(): Map<Int, Any> = mapOf(
            BR.photo to arguments?.getParcelable(PHOTO) as Photo
    )

    companion object {
        const val TAG = "PhotoDetailDialogFragment"
        private const val PHOTO = "PhotoDetailDialogFragment.PHOTO"

        fun newInstance(photo: Photo) = PhotoDetailDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(PHOTO, photo)
            }
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return AlertDialog.Builder(activity).apply {
            setView(dataBinding.root).setPositiveButton(R.string.accept) { _, _ -> dialog?.cancel() }
        }.create()
    }

}