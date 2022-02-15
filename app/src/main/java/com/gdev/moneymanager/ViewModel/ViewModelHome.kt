package com.gdev.moneymanager.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelHome: ViewModel() {

    var bal = MutableLiveData<String>("")
    var total_exp = MutableLiveData<String>("")
       fun set_bal(bal : String,total_exp : String){
           this.bal.value = bal
           this.total_exp.value = total_exp
       }

}