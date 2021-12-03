package utility;

public class ValidityFieldsChecker {
	
	private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NUMERIC_PATTERN = "[0-9]+";
    private static final int PHONE_NUMBER = 10;
    private static final int CAP = 5;
    private static final int CF = 16;
    
    public boolean checkEmail(final String email) {
        return email.matches(EMAIL_PATTERN);
    }

    public boolean checkPhoneNumber(final String phoneNumber) {
        return phoneNumber.length() == PHONE_NUMBER && phoneNumber.matches(NUMERIC_PATTERN);
    }
    
    public boolean checkNumber(final String number) {
        return number.matches(NUMERIC_PATTERN);
    }
    
    public boolean checkCF(final String cf) {
        return cf.length() == CF;
    }
    
    public boolean checkCap(final String cap) {
        return cap.length() == CAP && cap.matches(NUMERIC_PATTERN);
    }
    
    public boolean checkNotEmpty(final String field) {
        return field.length() != 0 && field != null;
    }
    
}
