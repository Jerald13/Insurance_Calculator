package com.example.a13imageresourcesandcompatibility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var input_age = ""
    var input_gender = ""
    var input_smoke = ""
    var total_payment = 0

    lateinit var spinner : Spinner
    lateinit var radio_group : RadioGroup
    lateinit var text_radio : TextView
    lateinit var button : Button
    lateinit var checkbox_smoke : CheckBox
    lateinit var text_check : TextView
    lateinit var text_output : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        spinner = findViewById(R.id.spinner_age)
        radio_group = findViewById(R.id.radio_group)
        text_radio = findViewById(R.id.text_radio)
        button = findViewById(R.id.button)
        checkbox_smoke = findViewById(R.id.checkbox_smoke)
        text_check=findViewById(R.id.text_check)
        text_output=findViewById(R.id.text_output)

        spinner.onItemSelectedListener = this
        //#2 Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            //Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        checkbox_smoke.setOnClickListener {

            if(checkbox_smoke.isChecked){
                input_smoke=checkbox_smoke.text.toString()
                text_check.text=input_smoke.toString()

            } else {
                input_smoke=""
                text_check.text=""
            }
        }


        button.setOnClickListener{
            // Get the checked radio button id from radio group
            var id: Int = radio_group.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                text_radio.text="Gender : "+radio.text
            }else{
                text_radio.text="Gender : Nothing selected"
            }
Toast.makeText(applicationContext,input_smoke.toString(),Toast.LENGTH_SHORT).show()
            when(input_age.toInt()){
                0->{
                    text_output.text="60"
                }
                1->{
                    total_payment+=70
                    if(input_gender.toString().equals("Male")){
                        total_payment+=50
                    }
                    if(input_smoke.toString().equals("Smoke")){
                        total_payment+=100
                    }
                }
                2->{
                    total_payment+=90
                    if(input_gender.toString().equals("Male")){
                        total_payment+=100
                    }
                    if(input_smoke.toString().equals("Smoke")){
                        total_payment+=150
                    }
                }
                3->{
                    total_payment+=120
                    if(input_gender.toString().equals("Male")){
                        total_payment+=150
                    }
                    if(input_smoke.toString().equals("Smoke")){
                        total_payment+=200
                    }
                }
                4->{
                    total_payment+=150
                    if(input_gender.toString().equals("Male"))
                    total_payment+=200
                    if(input_smoke.toString().equals("Smoke"))
                    total_payment+=250
                }
                5->{
                    total_payment+=150

                    if(input_gender.toString().equals("male")){
                        total_payment+=200
                    }
                    if(input_smoke.toString().equals("Smoke")){
                        total_payment+=300
                    }
                }

            }
            text_output.text=total_payment.toString()
            total_payment=0


        }


    }
    // Get the selected radio button text using radio button on click listener
    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        input_gender = radio.text.toString()
        text_radio.text=input_gender

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val selectionTv : TextView = findViewById(R.id.text_age)
        input_age = pos.toString()
        selectionTv.text = parent.getItemAtPosition(pos).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        val selectionTv : TextView = findViewById(R.id.text_age)
        selectionTv.text = "You have not make a age selection"
        input_age = "You have not make a age selection"
    }
}