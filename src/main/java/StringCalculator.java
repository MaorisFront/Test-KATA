import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public  static int add(String text) {
        String[] tokens = getSplit(text);
        List<Integer> numbers = Lambda.convert(tokens, toInt());
        List<Integer> negatives = Lambda.filter(lessThanOrEqualTo(0), numbers);
        if(negatives.size() > 0) {
            throw new RuntimeException("negatives not allowed");
        }
        return Lambda.sum(numbers).intValue();
    }

    private static String[] getSplit(String text) {
        if(text.isEmpty()) {
            return new String[0];
        }
        if(isUsesCustomDelimiterSyntax(text)){
            return splitUsingCustomDelimiterSyntax(text);
        } else {
            return splitUsingDelimiterNewlinesAndComma(text);
        }
    }

    private static boolean isUsesCustomDelimiterSyntax(String text) {
        return text.startsWith("//");
    }

    private static String[] splitUsingDelimiterNewlinesAndComma(String text) {
        String[] tokens =  text.split(",|\n");
        return tokens;
    }

    private static String[] splitUsingCustomDelimiterSyntax(String text) {
        Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(text);
        matcher.matches();
        String customDelimiter = matcher.group(1);
        String numbers = matcher.group(2);
        return numbers.split(Pattern.quote(customDelimiter));
    }

    private static Converter<String, Integer> toInt() {
        return new Converter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return toInt(from);
            }
        };
    }
    private static int toInt(String text) {
        return Integer.parseInt(text);
    }
}
