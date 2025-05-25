package com.example.changeoriginal.activite

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.changeoriginal.R

class HomeActivity : AppCompatActivity() {

    private lateinit var conversionList: MutableList<Conversion>
    private lateinit var adapter: converadpt
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val spinnerFrom: Spinner = findViewById(R.id.countrySpinner1)
        val spinnerTo: Spinner = findViewById(R.id.countrySpinner2)
        val amountEditText: EditText = findViewById(R.id.amountEditText)
        val exchangeRateEditText: EditText = findViewById(R.id.exchangeRateEditText)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val convertButton: Button = findViewById(R.id.convertButton)
        recyclerView = findViewById(R.id.recyclerView)

        val countries = getCountries()

        val countryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries.map { it.name })
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = countryAdapter
        spinnerTo.adapter = countryAdapter

        conversionList = mutableListOf()
        adapter = converadpt(conversionList,
            onDeleteClick = { position ->
                conversionList.removeAt(position)
                adapter.notifyItemRemoved(position)
            },
            onEditClick = { position ->
                val conversion = conversionList[position]
                amountEditText.setText(conversion.amount.toString())
                exchangeRateEditText.setText(conversion.rate.toString())
                spinnerFrom.setSelection(countries.indexOfFirst { it.name == conversion.fromCountry })
                spinnerTo.setSelection(countries.indexOfFirst { it.name == conversion.toCountry })

                // Supprimer l'ancien pour le remplacer
                conversionList.removeAt(position)
                adapter.notifyItemRemoved(position)
            })

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        convertButton.setOnClickListener {
            val fromCountry = countries[spinnerFrom.selectedItemPosition]
            val toCountry = countries[spinnerTo.selectedItemPosition]
            val amount = amountEditText.text.toString().toDoubleOrNull()
            val rate = exchangeRateEditText.text.toString().toDoubleOrNull()

            if (amount != null && rate != null) {
                val result = amount * rate
                resultTextView.text = "Résultat : %.2f %s".format(result, toCountry.currency)

                // Ajouter à la liste
                val newConversion = Conversion(fromCountry.name, toCountry.name, amount, rate, result)
                conversionList.add(newConversion)
                adapter.notifyItemInserted(conversionList.size - 1)
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs correctement", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCountries(): List<Country> = listOf(
        Country("Maroc", "MAD"),
        Country("France", "EUR"),
        Country("Espagne", "EUR"),
        Country("États-Unis", "USD"),
        Country("Canada", "CAD"),
        Country("Arabie Saoudite", "SAR"),
        Country("Émirats Arabes Unis", "AED"),
        Country("Royaume-Uni", "GBP"),
        Country("Suisse", "CHF")
    )
}

data class Country(val name: String, val currency: String)
