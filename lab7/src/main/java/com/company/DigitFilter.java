package com.company;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class DigitFilter extends DocumentFilter {
    private static final String DIGITS = "\\d+";

    @Override
    public void insertString(FilterBypass fb, int offset, String str, AttributeSet as) throws BadLocationException {
        if (str.matches(DIGITS)) {
            super.insertString(fb, offset, str, as);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int len, String str, AttributeSet as) throws BadLocationException {
        if (string.matches(DIGITS)) {
            super.replace(fb, offset, len, str, as);
        }
    }
}
