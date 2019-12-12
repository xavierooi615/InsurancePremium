package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private lateinit var dataModel: DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataModel = ViewModelProviders.of(this).get(DataModel::class.java)
        display()
        buttonCalculate.setOnClickListener(){
            dataModel.totalPremium=calc()
            display()
        }
        buttonReset.setOnClickListener(){
            reset()
        }
    }

    private fun display(){
        if(dataModel.totalPremium!=0.00)
            textViewPremium.text=dataModel.totalPremium.toString()
    }

    private fun calc():Double{
        /*val age = findViewById<Spinner>(R.id.spinnerAge).selectedItemPosition;
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupGender)

        val selectedId = radioGroup.getCheckedRadioButtonId()
        val radioButton = findViewById<RadioButton>(selectedId)
        textViewPremium.text="%d".format(radioButton)*/
        return when(spinnerAge.selectedItemPosition){
            0->60.00
            1->70.00+(if(radioButtonMale.isChecked)50.00 else 0.0)+
                    (if(checkBoxSmoker.isChecked)100.00 else 0.0)
            2->90.00+(if(radioButtonMale.isChecked)100.00 else 0.0)+
                    (if(checkBoxSmoker.isChecked)150.00 else 0.0)
            3->120.00+(if(radioButtonMale.isChecked)150.00 else 0.0)+
                    (if(checkBoxSmoker.isChecked)200.00 else 0.0)
            4->150.00+(if(radioButtonMale.isChecked)200.00 else 0.0)+
                    (if(checkBoxSmoker.isChecked)250.00 else 0.0)
            else->150.00+(if(radioButtonMale.isChecked)200.00 else 0.0)+
                    (if(checkBoxSmoker.isChecked)300.00 else 0.0)
        }
        display()
    }

    private fun reset(){
        spinnerAge.setSelection(0)
        radioGroupGender.clearCheck()
        textViewPremium.setText("Insurance Premium :")
        checkBoxSmoker.setChecked(false)
        dataModel.totalPremium=0.00
    }
}
