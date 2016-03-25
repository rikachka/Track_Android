package com.rikachka.task1_1;

import android.app.Activity;

public class NumbersToTextClass {
    private static final int MILLION = 1000000;
    private Activity callingActivity;

    public NumbersToTextClass(Activity callingActivity) {
        this.callingActivity = callingActivity;
    }

    String toString(int reference) {
        return callingActivity.getResources().getString(reference);
    }

    private static final int[] hundreds = {
            R.string.empty,
            R.string.one_hundred,
            R.string.two_hundred,
            R.string.three_hundred,
            R.string.four_hundred,
            R.string.five_hundred,
            R.string.six_hundred,
            R.string.seven_hundred,
            R.string.eight_hundred,
            R.string.nine_hundred
    };

    private static final int[] tens = {
            R.string.empty,
            R.string.one_ten,
            R.string.two_ten,
            R.string.three_ten,
            R.string.four_ten,
            R.string.five_ten,
            R.string.six_ten,
            R.string.seven_ten,
            R.string.eight_ten,
            R.string.nine_ten
    };

    private static final int[] nums = {
            R.string.empty,
            R.string.one_num,
            R.string.two_num,
            R.string.three_num,
            R.string.four_num,
            R.string.five_num,
            R.string.six_num,
            R.string.seven_num,
            R.string.eight_num,
            R.string.nine_num,
            R.string.ten_num,
            R.string.eleven_num,
            R.string.twelve_num,
            R.string.thirteen_num,
            R.string.fourteen_num,
            R.string.fivteen_num,
            R.string.sixteen_num,
            R.string.seventeen_num,
            R.string.eightteen_num,
            R.string.nineteen_num,
    };

    private String convertLessThanOneThousand(int number) {
        String currentNumber;

        if (number % 100 < 20) {
            currentNumber = toString(nums[number % 100]);
            number /= 100;
        } else {
            currentNumber = toString(nums[number % 10]);
            number /= 10;

            currentNumber = toString(tens[number % 10]) + currentNumber;
            number /= 10;
        }
        return toString(hundreds[number]) + currentNumber;
    }

    public String toText(int number) {

        if (number >= MILLION) {
            return toString(R.string.million_special);
        }

        String currentNumber = "";
        int iteration = 0;
        do {
            int lastThreeDigits = number % 1000;
            if (lastThreeDigits != 0) {
                String lastThreeDigitsString = convertLessThanOneThousand(lastThreeDigits);
                String thousands;
                if (iteration == 1) {
                    if ((lastThreeDigits % 10) == 1) {
                        thousands = toString(R.string.one_hundred_special);
                        /* Replacing "один тысяча" with "одна тысяча" */
                        lastThreeDigitsString = lastThreeDigitsString.substring(0, lastThreeDigitsString.length() - toString(nums[1]).length());
                        lastThreeDigitsString += toString(R.string.one_special);
                    } else if ((lastThreeDigits % 10) == 2) {
                        thousands = toString(R.string.two_hundred_special);
                        /* Replacing "два тысяча" with "две тысячи" */
                        lastThreeDigitsString = lastThreeDigitsString.substring(0, lastThreeDigitsString.length() - toString(nums[2]).length());
                        lastThreeDigitsString += toString(R.string.two_special);
                    } else if ((lastThreeDigits % 10) < 5) {
                        thousands = toString(R.string.two_hundred_special);
                    } else {
                        thousands = toString(R.string.three_hundred_special);
                    }
                    lastThreeDigitsString += thousands;
                }
                currentNumber = lastThreeDigitsString + currentNumber;
            }
            number /= 1000;
            iteration++;
        } while (number > 0);

        return currentNumber.trim();
    }
}
