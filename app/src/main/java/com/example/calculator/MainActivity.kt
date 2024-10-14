package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        findViewById<Button>(R.id.btnSum).setOnClickListener(this)
        findViewById<Button>(R.id.btnMin).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)

        findViewById<Button>(R.id.btnEq).setOnClickListener(this)
    }

    // Hàm xử lý khi người dùng nhập số
    private fun addDigit(digit: Int) {
        if (state == 1) {
            op1 = op1 * 10 + digit
            textResult.text = op1.toString()
        } else if (state == 2) {
            op2 = op2 * 10 + digit
            textResult.text = op2.toString()
        }
    }

    // Hàm tính toán kết quả
    private fun calculateResult() {
        val result = when (op) {
            1 -> op1 + op2 // Cộng
            2 -> op1 - op2 // Trừ
            3 -> op1 * op2 // Nhân
            4 -> {
                if (op2 != 0) op1 / op2 else {
                    textResult.text = "Error"
                    return
                } // Chia, kiểm tra chia cho 0
            }
            else -> 0
        }
        textResult.text = result.toString()
        op1 = result // Lưu kết quả vào op1 cho các phép tính tiếp theo
        op2 = 0 // Đặt lại op2
        state = 1 // Trở về trạng thái nhập số thứ nhất
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)

            R.id.btnSum -> {
                op = 1
                state = 2
            }
            R.id.btnMin -> {
                op = 2
                state = 2
            }
            R.id.btnMul -> {
                op = 3
                state = 2
            }
            R.id.btnDiv -> {
                op = 4
                state = 2
            }
            R.id.btnEq -> calculateResult()
        }
    }
}
