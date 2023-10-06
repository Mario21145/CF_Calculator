package com.example.calculator_cf.data

class Dataset() {

    val consonants = listOf(
        'B',
        'C',
        'D',
        'F',
        'G',
        'H',
        'J',
        'K',
        'L',
        'M',
        'N',
        'P',
        'Q',
        'R',
        'S',
        'T',
        'V',
        'W',
        'X',
        'Y',
        'Z'
    )

    val vocals = listOf('A', 'E', 'I', 'O', 'U')

    val months = listOf(
        "Gennaio", "Febbraio", "Marzo", "Aprile",
        "Maggio", "Giugno", "Luglio", "Agosto",
        "Settembre", "Ottobre", "Novembre", "Dicembre", "mese"
    )

    val cities = listOf(
        "cardito", "afragola", "frattamaggiore", "aversa", "napoli", "comune"
    )



    val LastLetterCalc = mapOf(
        '0' to 1,
        '1' to 0,
        '2' to 5,
        '3' to 7,
        '4' to 9,
        '5' to 13,
        '6' to 15,
        '7' to 17,
        '8' to 19,
        '9' to 21,
        'A' to 1,
        'B' to 0,
        'C' to 5,
        'D' to 7,
        'E' to 9,
        'F' to 13,
        'G' to 15,
        'H' to 17,
        'I' to 19,
        'J' to 21,
        'K' to 2,
        'L' to 4,
        'M' to 18,
        'N' to 20,
        'O' to 11,
        'P' to 3,
        'Q' to 6,
        'R' to 8,
        'S' to 12,
        'T' to 14,
        'U' to 16,
        'V' to 10,
        'W' to 22,
        'X' to 24,
        'Y' to 23,
        'Z' to 25
    )


}