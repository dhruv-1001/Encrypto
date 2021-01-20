package com.example.encrypto

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var st: String? = null
    var password: String? = null
    var encrypt = ""
    var decrypt = ""
    var len: Int? = null
    var l: Int? = null
    var ilength: Int? = null
    var jlength: Int? = null
    var ascii: Int? = null
    var asc: Int? = null
    var sum = 0
    var diff = 0
    var ch: Char? = null
    var c: Char? = null
    var en: Char? = ' '
    var normET: EditText? = null
    var normPass: EditText? = null
    private var encText: TextView? = null
    var decrET: EditText? = null
    var decrPass: EditText? = null
    private var decrText: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        normET = findViewById(R.id.etEncryptText)
        normPass = findViewById(R.id.etEnPassword)
        encText = findViewById(R.id.tvDecText)

        decrET = findViewById(R.id.etDecryptText)
        decrPass = findViewById(R.id.etDePassword)
        decrText = findViewById(R.id.tvNorText)
    }

    fun Encrypt(view: View){

        st = normET?.text.toString()
        password = normPass?.text.toString()
        encText?.text = ""
        len = st!!.length
        l = password!!.length
        if (len == null || l == null) return
        if (len!! >= l!! && l!! >= 4){
            val d: Int = len!! / l!!
            val m: Int = len!! % l!!
            ilength = if (m == 0) d else d + 1
            for (i in 0 until ilength!!) {
                jlength = if (i == d) len!! - l!! * d else l
                for (j in 0 until jlength!!) {
                    val calc: Int = j + i * l!!
                    ch = st!![calc]
                    c = password!![j]
                    ascii = c!!.toInt()
                    asc = ch!!.toInt()
                    sum = ascii!! + asc!!
                    en = sum.toChar()
                    encrypt += en
                }
            }
            encText?.text = "Encrypted text : ${encrypt!!}"
        }
        else {
            encText?.text = "Try Longer Password"
        }
    }

    fun copyToClipboard(view: View){
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", encrypt)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(applicationContext, "Encrypted Text Copied", Toast.LENGTH_SHORT).show()
    }

    fun Decrypt(view: View){

        decrypt = ""
        decrText?.text = ""
        st = decrET?.text.toString()
        password = decrPass?.text.toString()
        len = st!!.length
        l = password!!.length

        if (len!! >= l!! && l!! >= 4){
            val d = len!! / l!!
            val m = len!! % l!!
            ilength = if (m == 0) d else d + 1
            for (i in 0 until ilength!!) {
                jlength = if (i == d) len!! - l!! * d else l
                for (j in 0 until jlength!!) {
                    val calc = j + i * l!!
                    ch = st!![calc]
                    c = password!![j]
                    ascii = ch!!.toInt()
                    asc = c!!.toInt()
                    diff = ascii!! - asc!!
                    val fin = diff.toChar()
                    decrypt += fin
                    diff = 0
                }
            }
            decrText?.text = "Decrypted text: ${decrypt}"
        }
        else {
            decrText?.text = "Invalid Password"
        }
    }

}