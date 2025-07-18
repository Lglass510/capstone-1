package com.pluralsight;
import java.util.Random;

public class QuoteGenerator {
    private final String[] funnyQuotes = {
            "Saving money is like going on a diet – painful but worth it!",
            "Budgeting: Because you can't have champagne taste on a ramen noodle wallet.",
            "FlexFund: Where cents turn into sense!",
            "Money talks... but all mine says is 'Goodbye!'",
    };

    private final String[] motivationalQuotes = {
            "Believe you can and you're halfway there.",
            "Don’t watch the clock; do what it does. Keep going.",
            "Work hard in silence, let success make the noise.",
            "It always seems impossible until it's done.",
            "Little by little, a little becomes a lot."
    };

    public String getRandomFunnyQuote() {
        Random random = new Random();
        int index = random.nextInt(funnyQuotes.length);
        return funnyQuotes[index];
    }

    public String getRandomMotivationalQuote() {
        Random random = new Random();
        int index = random.nextInt(motivationalQuotes.length);
        return motivationalQuotes[index];
    }
}
