package org.csystem.app.samples.lottaryapp

import kotlin.random.Random

fun getLotteryColumn() = generateSequence { Random.nextInt(1, 50) }.distinct().take(6).sorted().toList().toIntArray()

fun getLotteryColumns(n: Int) = (1..n).map { getLotteryColumn() }.toTypedArray()