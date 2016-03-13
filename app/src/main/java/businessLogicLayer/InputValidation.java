package businessLogicLayer;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Jose Matundan on 3/13/2016.
 */
public class InputValidation
{
    private static final String ID_REGEX = "^\\d+$";
    private static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";


    private static final String REQUIRED_MSG = "required";
    private static final String ID_MSG = "invalid ID";
    private static final String DATE_MSG = "dd-mm-yyyy";

    public static boolean isID(EditText editText, boolean required) {
        return isValid(editText, ID_REGEX, ID_MSG, required);
    }

    public static boolean isDate(EditText editText, boolean required) {
        return isValid(editText, DATE_REGEX, DATE_MSG, required);
    }

    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if ( required && !hasText(editText) ) return false;

        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };

        return true;
    }

    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}
