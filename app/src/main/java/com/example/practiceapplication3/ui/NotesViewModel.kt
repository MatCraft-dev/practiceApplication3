package com.example.practiceapplication3.ui

import androidx.lifecycle.*
import com.example.practiceapplication3.data.model.Note
import com.example.practiceapplication3.data.repository.DataRepository
import com.example.practiceapplication3.data.repository.DataRepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class NotesViewModel @Inject constructor(
    private val dataRepository: DataRepositorySource
) : ViewModel() {

    private var noteId: Int = 0

    private var _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private var _content =MutableLiveData<String>()
    val content: LiveData<String> get() = _content


    fun initData(id: Int) {
        noteId = id
        val note: Note = dataRepository.getNote(id)
        _title.value = note.title
        _content.value = note.content
    }


    fun updateNote(title: String, content:String, id:Int?){
        //viewModelScope.launch{
        //    //dataRepository.updateNote(title, content, id)
        //}
    }


    }

