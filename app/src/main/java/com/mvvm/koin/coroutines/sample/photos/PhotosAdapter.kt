package com.mvvm.koin.coroutines.sample.photos

import com.mvvm.koin.coroutines.sample.R
import com.mvvm.koin.coroutines.sample.base.BaseRecyclerViewAdapter
import com.mvvm.koin.coroutines.sample.data.room.Photo

class PhotosAdapter(photoList: List<Photo>?,listener: BaseRecyclerViewAdapter.OnItemClickListener) : BaseRecyclerViewAdapter(photoList,listener) {

    override fun getItemLayoutId(): Int = R.layout.item_photo

}