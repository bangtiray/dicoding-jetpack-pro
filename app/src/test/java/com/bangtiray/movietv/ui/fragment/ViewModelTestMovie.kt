package com.bangtiray.movietv.ui.fragment

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ViewModelTestMovie {
    private lateinit var viewModel: ViewModel

    @Before
    fun setUp(){
        viewModel= ViewModel()
    }
    @Test
    fun genListMovie() {
        val entities=viewModel.genListMovie()
        assertNotNull(entities)
        assertEquals(10, entities.size)
    }

    @Test
    fun genListMovieTV() {
        val entities=viewModel.genListMovieTV()
        assertNotNull(entities)
        assertEquals(10, entities.size)
    }
}