package com.dod.whateat.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dod.whateat.data.DefaultData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource<T: Any>(private val item: DefaultData): PagingSource<Int, Any>() {

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
            val nextKey = item.nextPage
            val data = gson.fromJson<MutableList<T>>(item.data, object: TypeToken<MutableList<T>>(){}.type)

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}