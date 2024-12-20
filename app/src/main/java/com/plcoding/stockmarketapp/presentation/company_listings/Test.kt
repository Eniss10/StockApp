package com.plcoding.stockmarketapp.presentation.company_listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.stockmarketapp.domain.model.CompanyListing
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel


@Destination(start = true)
@Preview
@Composable
fun drawObject(
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Box(modifier = Modifier.fillMaxSize().background(
        Color.Blue
    )) {
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(state.companies.size) { index ->
                val company = state.companies[index]
             CompanyItem(
                 company = company,
                 modifier = Modifier.fillMaxWidth().padding(16.dp)
             )
            }
        }
    }
}