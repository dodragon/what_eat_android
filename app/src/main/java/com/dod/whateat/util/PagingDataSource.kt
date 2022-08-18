package com.dod.whateat.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dod.whateat.data.DefaultData
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource(private val items: MutableList<DefaultData>): PagingSource<Int, DefaultData>() {

    private val pageSize = 20
    private val startPage = 1

    override fun getRefreshKey(state: PagingState<Int, DefaultData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    //차후 마지막 페이지 관련 조정 필요
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DefaultData> {
        val position = params.key ?: startPage
        return try{
            val nextKey = if(items.isEmpty() || params.loadSize < pageSize){
                null
            }else {
                position + 1
            }

            LoadResult.Page(
                data = items,
                prevKey = if(position == 1) null else position - 1,
                nextKey = nextKey
            )
        }catch (e: IOException){
            return LoadResult.Error(e)
        }catch (e: HttpException){
            return LoadResult.Error(e)
        }
    }
}