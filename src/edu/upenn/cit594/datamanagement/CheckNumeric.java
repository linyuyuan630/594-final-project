package edu.upenn.cit594.datamanagement;

public class CheckNumeric {
	public static boolean isNumeric(final String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.chars().allMatch(Character::isDigit);

    }

}
