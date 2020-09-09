package com.jay.chatwithinternet.ui.chatscreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jay.chatwithinternet.data.SearchResponse
import com.jay.chatwithinternet.data.data
import com.jay.chatwithinternet.repository.TwitterSearchRepoImp
import com.jay.chatwithinternet.utils.BEARER_TOKEN_CREDENTIALS

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.Util
import javax.inject.Inject

class ChatViewModel @Inject constructor(val repoImp: TwitterSearchRepoImp) : ViewModel() {

    var _answerMetaData = MutableLiveData<MutableList<SearchResponse>>()

    private val _loadError = MutableLiveData<String>()
    val loadError: LiveData<String>
        get() = _loadError

    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    fun addQuestion(searchResponse: SearchResponse) {
        if (_answerMetaData.value == null) {
            _answerMetaData.value = ArrayList<SearchResponse>()
        }
        _answerMetaData.value?.add(searchResponse)
        _answerMetaData.postValue(_answerMetaData.value)
        findSearchResult(searchResponse.text)
        _query.value = ""
    }

    fun findSearchResult(query: String) {
        viewModelScope.launch(exceptionHandler) {
            val searchData = async {
                repoImp.getSearchData(query)
            }
            try {
                val data = searchData.await()
                _answerMetaData.value?.addAll(data.data)
                _answerMetaData.postValue(_answerMetaData.value)
            } catch (t: Throwable) {


                if (t.message.toString()
                        .contains("java.lang.Object[] java.util.Collection.toArray()")
                ) {
                    val searchResponse = SearchResponse("Sorry, No Answer found", false)
                    _answerMetaData.value?.add(searchResponse)
                    _answerMetaData.postValue(_answerMetaData.value)
                } else {
                    _loadError.postValue(t.message)
                }

            }
        }
    }
}