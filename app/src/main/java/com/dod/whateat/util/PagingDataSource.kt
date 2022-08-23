package com.dod.whateat.util

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dod.whateat.data.DefaultData
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class PagingDataSource<T: Any>(private val item: DefaultData, private val classType: Class<T>): PagingSource<Int, Any>() {

    private val gson = GsonBuilder().create()

    override fun getRefreshKey(state: PagingState<Int, Any>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    //차후 마지막 페이지 관련 조정 필요
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Any> {
        return try{
            Log.e("item", item.toString())

            val nextKey = item.nextPage
            val data = gson.fromJson<MutableList<T>>(item.data, TypeToken.getParameterized(MutableList::class.java, classType).type)

            Log.e("NEXT PAGE", nextKey.toString())
            Log.e("data", data.toString())
            Log.e("type", data[0].javaClass.name)

            if(nextKey == params.key || nextKey == -1){
                return LoadResult.Error(IllegalAccessError())
            }else {
                LoadResult.Page(
                    data = data.orEmpty(),
                    prevKey = null,
                    nextKey = nextKey
                )
            }
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}